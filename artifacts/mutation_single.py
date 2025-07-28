#!/usr/bin/env python3
"""
mutate_and_store.py
-------------------
Traverse a folder, try up to `--max-attempts` single-byte mutations per file,
and store up to 10 (original, mutated) pairs that turn a previously-valid file
into an invalid one (validator exit-code ≠ 0).

Mutation strategy
-----------------
Randomly *replace*, *delete*, or *insert* a **single ASCII byte** chosen from
PRINTABLE_ASCII.

Guarantees
----------
* We never mutate a byte ≥ 0x80 (no UTF-8 continuation bytes, no multibyte
  Unicode code points).
* If the chosen position is exactly `len(data)` (i.e. after the last byte), the
  only legal operation is **insert**.
"""

from __future__ import annotations

import argparse
import os
import random
import sqlite3
import subprocess
import sys
from pathlib import Path
from typing import Set

# Only these five ASCII characters are used for insertion / replacement
PRINTABLE_ASCII = ["!", "^", "$", "%", "&"]


# ───────────────────────── helpers ────────────────────────────────────────
def run_validator(exe: str, path: Path) -> bool:
    """Return True iff the validator exits with status 0."""
    try:
        res = subprocess.run([exe, str(path)],
                             stdout=subprocess.DEVNULL,
                             stderr=subprocess.DEVNULL,
                             timeout=10)
        return res.returncode == 0
    except Exception:
        return False


def mutate_data(data: bytearray, pos: int) -> bytearray:
    """
    Apply one random mutation at *pos*:

    * replace the byte at pos with a different PRINTABLE_ASCII character
    * delete the byte at pos
    * insert a random PRINTABLE_ASCII character at pos

    When *pos == len(data)* (i.e. insertion after the last byte), only the
    **insert** operation is allowed.
    """
    if pos >= len(data):                # virtual position “after EOF”
        op = "insert"
    else:
        op = random.choice(["replace", "delete", "insert"])

    buf = bytearray(data)               # mutable copy

    if op == "replace":                 # pos < len(buf) guaranteed
        orig = buf[pos]
        new_ch = random.choice(PRINTABLE_ASCII)
        while ord(new_ch) == orig:      # ensure *change*
            new_ch = random.choice(PRINTABLE_ASCII)
        buf[pos] = ord(new_ch)

    elif op == "delete":                # pos < len(buf) guaranteed
        del buf[pos]

    else:                               # insert
        new_ch = random.choice(PRINTABLE_ASCII)
        buf.insert(pos, ord(new_ch))

    return buf


# ────────────────────── SQLite persistence ───────────────────────────────
def ensure_table(conn: sqlite3.Connection) -> None:
    conn.execute(
        """CREATE TABLE IF NOT EXISTS mutations (
               id            INTEGER PRIMARY KEY AUTOINCREMENT,
               file_path     TEXT,
               mutation_pos  INTEGER,
               original_text TEXT,
               mutated_text  TEXT
           );"""
    )
    conn.commit()


def store_pair(conn: sqlite3.Connection,
               file_path: str,
               pos: int,
               original_text: str,
               mutated_text: str) -> None:
    conn.execute(
        "INSERT INTO mutations (file_path, mutation_pos, original_text, mutated_text)"
        "VALUES (?, ?, ?, ?)",
        (file_path, pos, original_text, mutated_text),
    )
    conn.commit()


# ───────────────────────── core logic ─────────────────────────────────────
def process_file(file_path: Path,
                 validator: str,
                 conn: sqlite3.Connection,
                 max_attempts: int) -> None:
    raw = file_path.read_bytes()

    # Skip non-ASCII originals (keep the validator call cheap)
    try:
        raw.decode("ascii")
    except UnicodeDecodeError:
        print(f"[skip] non-ASCII: {file_path}")
        return

    # Skip files that are already invalid
    if not run_validator(validator, file_path):
        print(f"[skip] already invalid: {file_path}")
        return

    print(f"[info] processing {file_path}")
    found: Set[int] = set()
    attempts = 0

    while len(found) < 10 and attempts < max_attempts:
        attempts += 1
        pos = random.randrange(len(raw) + 1)   # allow “insert at end”

        # already tried this position in this file?
        if pos in found:
            continue

        # If pos is inside the buffer, ensure it’s a plain ASCII byte
        if pos < len(raw) and raw[pos] >= 0x80:
            continue

        # optional: avoid double-quotations (often trivial parser killers)
        if pos < len(raw) and raw[pos] == ord('"'):
            continue

        mutated = mutate_data(bytearray(raw), pos)

        # write to a temp file beside the original
        tmp = file_path.with_suffix(".tmp_mutate")
        tmp.write_bytes(mutated)

        try:
            if not run_validator(validator, tmp):
                store_pair(conn,
                           str(file_path),
                           pos,
                           raw.decode(errors="ignore"),
                           mutated.decode(errors="ignore"))
                found.add(pos)
                print(f"  [✓] mutation #{len(found)} @ byte {pos}")
        finally:
            tmp.unlink(missing_ok=True)

    if len(found) < 10:
        print(f"  [✗] only {len(found)} of 10 found")


def traverse_and_mutate(folder: Path,
                        validator: str,
                        db_path: Path,
                        max_attempts: int,
                        seed: int | None = None) -> None:
    if seed is not None:
        random.seed(seed)

    with sqlite3.connect(db_path) as conn:
        ensure_table(conn)
        for path in folder.rglob("*"):
            if path.is_file():
                try:
                    process_file(path, validator, conn, max_attempts)
                except Exception as exc:
                    print(f"[err] {path}: {exc}", file=sys.stderr)


# ──────────────────────────── CLI ─────────────────────────────────────────
def main() -> None:
    parser = argparse.ArgumentParser(description="Mutate files and store pairs")
    parser.add_argument("--folder", required=True, help="root folder containing input files")
    parser.add_argument("--validator", required=True, help="validator executable")
    parser.add_argument("--database", required=True, help="SQLite output file")
    parser.add_argument("--max-attempts", type=int, default=10_00,
                        help="max mutation attempts per file (default: 1000)")
    parser.add_argument("--seed", type=int, help="optional RNG seed for reproducibility")
    args = parser.parse_args()

    traverse_and_mutate(Path(args.folder).resolve(),
                        args.validator,
                        Path(args.database).resolve(),
                        args.max_attempts,
                        args.seed)
    print("[done] all files processed")


if __name__ == "__main__":
    main()