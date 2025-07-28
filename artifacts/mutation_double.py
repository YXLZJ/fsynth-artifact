#!/usr/bin/env python3
"""
mutate_and_store.py  (two-byte mutation: find p1 and p2 separately first)
---------------------------------------------------------------------------

For each file:
    • Find a byte position p1 whose **single-byte** mutation breaks validation.
    • Find a **different** byte position p2 whose single-byte mutation also breaks validation.
    • Apply both mutations together; if the double mutation also fails, record the pair.

Only one (p1,p2) pair is stored per original file.
"""

import argparse
import random
import sqlite3
import subprocess
import sys
from pathlib import Path
from typing import Tuple

PRINTABLE_ASCII = ["!", "^", "$", "%", "&"]

# ─────────────────────────── helpers ────────────────────────────────────────
def run_validator(exe: str, path: Path) -> bool:
    try:
        res = subprocess.run(
            [exe, str(path)],
            stdout=subprocess.DEVNULL,
            stderr=subprocess.DEVNULL,
            timeout=10,
        )
        return res.returncode == 0
    except Exception:
        return False

def mutate_at_positions(data: bytearray, *positions: int) -> bytearray:
    """
    For each pos in positions (from the original data):
      - randomly choose one of: replace, delete, or insert
      - apply it to the working buffer, adjusting an offset so that
        subsequent positions still refer to the original byte indices.
    """
    out = bytearray(data)
    offset = 0

    for pos in sorted(positions):
        idx = pos + offset
        op = random.choice(["replace", "delete", "insert"])

        if op == "replace" and idx < len(out):
            orig = out[idx]
            new_ch = random.choice(PRINTABLE_ASCII)
            while ord(new_ch) == orig:
                new_ch = random.choice(PRINTABLE_ASCII)
            out[idx] = ord(new_ch)

        elif op == "delete" and idx < len(out):
            del out[idx]
            offset -= 1

        else:
            # insert (or fallback if replace/delete invalid)
            new_ch = random.choice(PRINTABLE_ASCII)
            out.insert(idx, ord(new_ch))
            offset += 1

    return out

def ensure_table(conn: sqlite3.Connection):
    conn.execute(
        """CREATE TABLE IF NOT EXISTS mutations (
               id INTEGER PRIMARY KEY AUTOINCREMENT,
               file_path TEXT,
               mutation_pos TEXT,            -- "p1,p2"
               original_text TEXT,
               mutated_text TEXT
           )"""
    )
    conn.commit()

def store_pair(
    conn: sqlite3.Connection,
    fpath: str,
    pos_pair: Tuple[int, int],
    orig: str,
    mut: str
):
    conn.execute(
        "INSERT INTO mutations (file_path, mutation_pos, original_text, mutated_text) "
        "VALUES (?, ?, ?, ?)",
        (fpath, f"{pos_pair[0]},{pos_pair[1]}", orig, mut),
    )
    conn.commit()

def find_single_fault(
    data: bytes,
    validator: str,
    path: Path,
    exclude: set[int],
    max_attempts: int
) -> int | None:
    """Return a position whose single-byte mutation (replace/delete/insert) fails, else None."""
    tmp = path.with_suffix(".tmp_mut_single")
    tries = 0
    while tries < max_attempts:
        tries += 1
        pos = random.randrange(len(data))
        if pos in exclude or data[pos] == ord('"'):
            continue

        mutated = mutate_at_positions(bytearray(data), pos)
        tmp.write_bytes(mutated)
        if not run_validator(validator, tmp):
            tmp.unlink(missing_ok=True)
            return pos

    tmp.unlink(missing_ok=True)
    return None

# ────────────────────────── core logic ──────────────────────────────────────
def process_file(
    path: Path,
    validator: str,
    conn: sqlite3.Connection,
    max_attempts: int
):
    data = path.read_bytes()
    if not data:
        return

    # Only proceed if original is valid ASCII
    try:
        data.decode('ascii')
    except UnicodeDecodeError:
        print(f"[skip] non-ASCII file skipped: {path}")
        return
    if not run_validator(validator, path):
        print(f"[skip] original already invalid: {path}")
        return

    print(f"[info] processing {path}")

    # ── Stage 1: find p1 ─────────────────────────────────────────
    p1 = find_single_fault(data, validator, path, set(), max_attempts)
    if p1 is None:
        print("  [✗] no single-byte fault (p1) found")
        return

    # ── Stage 2: find p2 ─────────────────────────────────────────
    p2 = find_single_fault(data, validator, path, {p1}, max_attempts)
    if p2 is None:
        print("  [✗] no second single-byte fault (p2) found")
        return

    # ── Stage 3: confirm double mutation ────────────────────────
    mutated_double = mutate_at_positions(bytearray(data), p1, p2)
    tmp_double = path.with_suffix(".tmp_mut_double")
    tmp_double.write_bytes(mutated_double)
    double_fails = not run_validator(validator, tmp_double)
    tmp_double.unlink(missing_ok=True)

    if double_fails:
        store_pair(
            conn,
            str(path),
            (p1, p2),
            data.decode(errors="ignore"),
            mutated_double.decode(errors="ignore"),
        )
        print(f"  [✓] found pair ({p1},{p2}) – single faults & double fault")
    else:
        print("  [✗] p1 and p2 individually fault but not together – skipped")

def traverse(
    folder: Path,
    validator: str,
    db: Path,
    max_attempts: int,
    seed: int | None
):
    if seed is not None:
        random.seed(seed)

    with sqlite3.connect(db) as conn:
        ensure_table(conn)
        for file in folder.rglob("*"):
            if file.is_file():
                try:
                    process_file(file, validator, conn, max_attempts)
                except Exception as e:
                    print(f"[err] {file}: {e}", file=sys.stderr)

# ───────────────────────────────── main ─────────────────────────────────────
def main():
    ap = argparse.ArgumentParser(
        description="Two-byte mutate & store: find p1 then p2"
    )
    ap.add_argument("--folder", required=True)
    ap.add_argument("--validator", required=True)
    ap.add_argument("--database", required=True)
    ap.add_argument(
        "--max-attempts", type=int, default=100,
        help="max single-mutation attempts to find p1 or p2"
    )
    ap.add_argument("--seed", type=int)
    args = ap.parse_args()

    traverse(
        Path(args.folder).resolve(),
        args.validator,
        Path(args.database).resolve(),
        args.max_attempts,
        args.seed
    )
    print("[done] all files processed")

if __name__ == "__main__":
    main()