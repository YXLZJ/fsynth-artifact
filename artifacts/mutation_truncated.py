#!/usr/bin/env python3
"""
mutate_and_store.py   –   tail-truncation variant (one success per file)
-------
CREATE TABLE IF NOT EXISTS mutations(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    file_path TEXT,
    mutation_pos INTEGER,   -- 截断偏移 cut_pos
    original_text TEXT,
    mutated_text TEXT
);
"""

import argparse, random, sqlite3, subprocess, sys
from pathlib import Path
from typing import Optional

# ─────────────────── validator ─────────────────── #
def run_validator(exe: str, path: Path) -> bool:
    """Return True if validator exit-code == 0."""
    try:
        res = subprocess.run([exe, str(path)],
                             stdout=subprocess.DEVNULL,
                             stderr=subprocess.DEVNULL,
                             timeout=10)
        return res.returncode == 0
    except Exception:
        return False

# ────────────────── mutation logic ─────────────── #
def truncate_tail(data: bytes) -> tuple[bytes, int]:
    """
    Choose a cut position in [len/2, len-1], biased toward the tail,
    and return (truncated_bytes, cut_pos).
    """
    if len(data) < 4:
        raise ValueError("file too small to truncate")
    lo = len(data) // 2
    hi = len(data) - 1
    # triangular distribution, mode at hi -> 更倾向靠后截断
    cut = int(random.triangular(lo, hi, hi))
    while data[cut] == "\"":
        cut = int(random.triangular(lo, hi, hi))
    return data[:cut], cut

# ───────────── SQLite helpers ───────────────────── #
def ensure_table(conn: sqlite3.Connection):
    conn.execute(
        """CREATE TABLE IF NOT EXISTS mutations(
               id INTEGER PRIMARY KEY AUTOINCREMENT,
               file_path TEXT,
               mutation_pos INTEGER,
               original_text TEXT,
               mutated_text TEXT
           );"""
    ); conn.commit()

def store_pair(conn: sqlite3.Connection, path: str,
               cut: int, orig: str, mut: str):
    conn.execute(
        "INSERT INTO mutations(file_path, mutation_pos, original_text, mutated_text) "
        "VALUES (?,?,?,?)", (path, cut, orig, mut))
    conn.commit()

# ───────────── per-file processing ─────────────── #
def process_file(path: Path, validator: str, conn: sqlite3.Connection,
                 max_attempts: int):
    data = path.read_bytes()
    if not data:
        return
    if not run_validator(validator, path):
        print(f"[skip] original invalid: {path}")
        return

    print(f"[info] processing {path}")
    for attempt in range(1, max_attempts + 1):
        try:
            mutated, cut_pos = truncate_tail(data)
        except ValueError:
            print(f"[warn] {path} too small")
            return

        tmp = path.with_suffix(".tmp_trunc")
        tmp.write_bytes(mutated)

        if not run_validator(validator, tmp):          # validator failed → success
            store_pair(conn, str(path), cut_pos,
                       data.decode(errors="ignore"),
                       mutated.decode(errors="ignore"))
            print(f"  [✓] success cut @ {cut_pos}  attempts={attempt}")
            tmp.unlink(missing_ok=True)
            return                                     # proceed to next file
        tmp.unlink(missing_ok=True)

    print(f"  [✗] no failing truncation within {max_attempts} attempts")

# ─────────── directory traversal ───────────────── #
def traverse(folder: Path, validator: str, db: Path,
             max_attempts: int, seed: Optional[int]):
    if seed is not None:
        random.seed(seed)
    with sqlite3.connect(db) as conn:
        ensure_table(conn)
        for p in folder.rglob("*"):
            if p.is_file():
                try:
                    process_file(p, validator, conn, max_attempts)
                except Exception as e:
                    print(f"[err] {p}: {e}", file=sys.stderr)

# ───────────────────── main ────────────────────── #
def main():
    ap = argparse.ArgumentParser("Tail-truncation mutator")
    ap.add_argument("--folder", required=True)
    ap.add_argument("--validator", required=True)
    ap.add_argument("--database", required=True)
    ap.add_argument("--max-attempts", type=int, default=100)
    ap.add_argument("--seed", type=int)
    args = ap.parse_args()

    traverse(Path(args.folder).resolve(),
             args.validator,
             Path(args.database).resolve(),
             args.max_attempts,
             args.seed)
    print("[done] all files processed")

if __name__ == "__main__":
    main()