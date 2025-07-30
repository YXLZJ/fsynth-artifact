#!/usr/bin/env python3
"""
metrics_analysis_updated.py
----------------------------------
Compute summary tables (Tables 4–8) from the *results* table produced by
`update_repairs_method.py`.

The schema expected in each `results` table is:
    id INTEGER PRIMARY KEY,
    format TEXT,
    fid INTEGER,
    cidx INTEGER,
    algorithm TEXT,
    original_text TEXT,
    broken_text   TEXT,
    repaired_text TEXT,
    fixed INTEGER,                      -- 0 | 1
    iterations INTEGER,
    repair_time REAL,                   -- seconds
    correct_runs INTEGER,
    incorrect_runs INTEGER,
    incomplete_runs INTEGER,
    distance_original_broken   INTEGER,
    distance_broken_repaired   INTEGER,
    distance_original_repaired INTEGER

By default three DBs are analysed (single / double / truncated corruptions).
Modify `DATABASES` as needed.
"""

from __future__ import annotations
import sqlite3, math, sys
from pathlib import Path
from typing import Any, Dict, List, Tuple

# ───────────────────────────────── CONFIG ────────────────────────────────── #
DATABASES       = ["single.db", "double.db", "truncated.db"]
DEFAULT_TIMEOUT = 240.0  # seconds for NULL repair_time fallback
# ─────────────────────────────────────────────────────────────────────────── #

# Helpers ─────────────────────────────────────────────────────────────────── #

def _to_numeric_list(vals: List[Any]) -> List[float]:
    """Return a list containing *only* numeric values, safely converted to float."""
    res: List[float] = []
    for v in vals:
        if v is None:
            continue
        if isinstance(v, (int, float)):
            res.append(float(v))
            continue
        # Handle strings that might represent numbers
        if isinstance(v, str):
            try:
                res.append(float(v.strip()))
            except ValueError:
                continue
    return res

def _stats(vals: List[Any]) -> Tuple[float, float]:
    """Return (mean, stdev) computed on *numeric* subset of *vals*."""
    nums = _to_numeric_list(vals)
    n    = len(nums)
    if n == 0:
        return 0.0, 0.0
    mu   = sum(nums) / n
    if n == 1:
        return mu, 0.0
    return mu, math.sqrt(sum((x - mu) ** 2 for x in nums) / n)

# Levenshtein with op‑counts -------------------------------------------------- #

def edit_distance_with_ops(a: str, b: str) -> Tuple[int, int, int, int]:
    m, n = len(a), len(b)
    dp   = [[0] * (n + 1) for _ in range(m + 1)]
    op   = [["M"] * (n + 1) for _ in range(m + 1)]
    for i in range(1, m + 1):
        dp[i][0] = i; op[i][0] = "D"
    for j in range(1, n + 1):
        dp[0][j] = j; op[0][j] = "I"
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if a[i - 1] == b[j - 1]:
                dp[i][j] = dp[i - 1][j - 1]
            else:
                d, ins, r = dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1
                best      = min(d, ins, r)
                dp[i][j]  = best
                op[i][j]  = "DIR"[[d, ins, r].index(best)]
    i = m; j = n; dels = ins = reps = 0
    while i > 0 or j > 0:
        cur = op[i][j]
        if cur == "M":
            i -= 1; j -= 1
        elif cur == "D":
            dels += 1; i -= 1
        elif cur == "I":
            ins  += 1; j -= 1
        else:
            reps += 1; i -= 1; j -= 1
    return dp[m][n], dels, ins, reps

# DB query helper ------------------------------------------------------------- #

def _q(conn: sqlite3.Connection, sql: str, params: Tuple[Any, ...] = ()):  # noqa: D401
    cur = conn.cursor(); cur.execute(sql, params); return cur.fetchall()

# Table 4‑5 general ----------------------------------------------------------- #

def table_4_5_general():
    combined: Dict[Tuple[str, str], Dict[str, Any]] = {}
    for db in DATABASES:
        if not Path(db).is_file():
            print(f"[WARN] Skipping missing DB {db}", file=sys.stderr);
            continue
        conn = sqlite3.connect(db)
        rows = _q(conn, """
            SELECT format, algorithm, distance_broken_repaired, distance_original_repaired,
                   fixed, COALESCE(repair_time, ?) AS rt
            FROM results""", (DEFAULT_TIMEOUT,))
        conn.close()
        print(f"\nMetrics for {db}")
        _print_metrics(_aggregate(rows))
        # merge
        for fmt, alg, dbr, dor, fixed, rt in rows:
            key = (fmt, alg)
            bucket = combined.setdefault(key, {"dbr": [], "dor": [], "rt": [], "succ": 0, "tot": 0})
            bucket["tot"] += 1
            bucket["rt"].append(rt)
            if fixed:
                bucket["succ"] += 1
                bucket["dbr"].append(dbr)
                bucket["dor"].append(dor)
    print("\nCombined Metrics Across All Databases")
    _print_metrics(combined)


def _aggregate(rows):
    data: Dict[Tuple[str, str], Dict[str, Any]] = {}
    for fmt, alg, dbr, dor, fixed, rt in rows:
        key = (fmt, alg)
        bucket = data.setdefault(key, {"dbr": [], "dor": [], "rt": [], "succ": 0, "tot": 0})
        bucket["tot"] += 1
        bucket["rt"].append(rt)
        if fixed:
            bucket["succ"] += 1
            bucket["dbr"].append(dbr)
            bucket["dor"].append(dor)
    return data


def _print_metrics(data):
    hdr = f"{'Format':<8} {'Alg':<8} {'Avg BR':>8} {'σ BR':>8} {'Avg OR':>8} "\
          f"{'σ OR':>8} {'Avg t':>8} {'σ t':>8} {'Succ':>6} {'Tot':>6}"
    print("-" * len(hdr))
    print(hdr)
    print("-" * len(hdr))
    for (fmt, alg), b in sorted(data.items()):
        abr, sbr = _stats(b["dbr"])
        aor, sor = _stats(b["dor"])
        art, srt = _stats(b["rt"])
        print(f"{fmt:<8} {alg:<8} {abr:8.2f} {sbr:8.2f} {aor:8.2f} {sor:8.2f} "\
              f"{art:8.2f} {srt:8.2f} {b['succ']:6d} {b['tot']:6d}")

# 4‑5 distances --------------------------------------------------------------- #

def table_4_5_distances():
    data: Dict[str, Dict[str, List[Any]]] = {}
    for db in DATABASES:
        if not Path(db).is_file():
            continue
        conn = sqlite3.connect(db)
        rows = _q(conn, "SELECT algorithm, distance_broken_repaired, distance_original_repaired FROM results WHERE fixed=1")
        conn.close()
        for alg, dbr, dor in rows:
            bucket = data.setdefault(alg, {"dbr": [], "dor": []})
            bucket["dbr"].append(dbr); bucket["dor"].append(dor)
    print("\nOverall Distance Metrics Across All Databases")
    print(f"{'Alg':<8} {'Avg BR':>8} {'σ BR':>8} {'Avg OR':>8} {'σ OR':>8}")
    print("-" * 42)
    for alg, vals in data.items():
        abr, sbr = _stats(vals["dbr"]); aor, sor = _stats(vals["dor"])
        print(f"{alg:<8} {abr:8.2f} {sbr:8.2f} {aor:8.2f} {sor:8.2f}")

# 6: fixed counts ------------------------------------------------------------- #

def table_6_count_fixed():
    total: Dict[str, int] = {}
    for db in DATABASES:
        if not Path(db).is_file():
            continue
        conn = sqlite3.connect(db)
        rows = _q(conn, "SELECT algorithm, COUNT(*) FROM results WHERE fixed=1 GROUP BY algorithm")
        conn.close()
        print(f"\nFixed counts for {db}")
        for alg, cnt in rows:
            print(f"  {alg:<8} {cnt:6d}"); total[alg] = total.get(alg, 0) + cnt
    print("\nTotal fixed files across DBs")
    for alg, cnt in total.items():
        print(f"  {alg:<8} {cnt:6d}")

# 7: perfect repairs ---------------------------------------------------------- #

def table_7_perfect():
    for db in DATABASES:
        if not Path(db).is_file():
            continue
        conn = sqlite3.connect(db)
        rows = _q(conn, "SELECT format, algorithm, COUNT(*) FROM results WHERE distance_original_repaired=0 GROUP BY format, algorithm")
        conn.close()
        print(f"\nPerfect repairs in {db}")
        if rows:
            for fmt, alg, cnt in rows:
                print(f"  {fmt:<6} {alg:<8} {cnt:5d}")
        else:
            print("  None")

# 8: efficiency --------------------------------------------------------------- #

def table_8_efficiency():
    tot_t: Dict[str, float] = {}; tot_n: Dict[str, int] = {}; iter_tot: Dict[str, float] = {}
    for db in DATABASES:
        if not Path(db).is_file():
            continue
        conn = sqlite3.connect(db)
        rt_rows = _q(conn, "SELECT algorithm, AVG(repair_time), COUNT(*) FROM results GROUP BY algorithm")
        it_rows = _q(conn, "SELECT algorithm, AVG(iterations), COUNT(*) FROM results WHERE iterations>0 GROUP BY algorithm")
        conn.close()
        print(f"\nAverage runtime in {db}")
        for alg, avg_rt, cnt in rt_rows:
            print(f"  {alg:<8} t={avg_rt:6.2f}s (n={cnt})")
            tot_t[alg] = tot_t.get(alg, 0.0) + avg_rt * cnt; tot_n[alg] = tot_n.get(alg, 0) + cnt
        for alg, avg_it, cnt in it_rows:
            iter_tot[alg] = iter_tot.get(alg, 0.0) + avg_it * cnt
    print("\nOverall average runtime across DBs")
    for alg in tot_t:
        avg_rt = tot_t[alg] / tot_n[alg]; avg_it = (iter_tot.get(alg, 0) / tot_n[alg]) if tot_n[alg] else 0
        print(f"  {alg:<8} t={avg_rt:6.2f}s  iters={avg_it:8.2f} (n={tot_n[alg]})")

# Surviving‑data ratio -------------------------------------------------------- #

def table_surviving_ratio():
    for mode, src in (("OR", "original_text"), ("CR", "broken_text")):
        overall: Dict[str, List[float]] = {}
        for db in DATABASES:
            if not Path(db).is_file():
                continue
            conn = sqlite3.connect(db)
            rows = _q(conn, f"SELECT algorithm, {src}, repaired_text FROM results WHERE fixed=1 AND repaired_text IS NOT NULL")
            conn.close()
            print(f"\nSurviving‑data ratio ({mode}) in {db}")
            per_db: Dict[str, List[float]] = {}
            for alg, src_txt, rep_txt in rows:
                _, dels, _, _ = edit_distance_with_ops(src_txt, rep_txt)
                ratio = (len(src_txt) - dels) / len(src_txt) if src_txt else 0
                per_db.setdefault(alg, []).append(ratio)
                overall.setdefault(alg, []).append(ratio)
            _print_ratio(per_db)
        print(f"\nOverall surviving‑data ratio ({mode})")
        _print_ratio(overall)


def _print_ratio(buckets: Dict[str, List[float]]):
    if not buckets:
        print("  (no data)"); return
    print(f"{'Alg':<8} {'Avg':>10} {'σ':>10} {'n':>6}")
    for alg, vals in buckets.items():
        mu, sd = _stats(vals)
        print(f"{alg:<8} {mu:10.4f} {sd:10.4f} {len(vals):6d}")

# ─────────────────────────────────────────────────────────────────────────── #

if __name__ == "__main__":
    print("———— Table 4‑5 (general) ———————————————")
    table_4_5_general()
    print("———— Table 4‑5 (Levenshtein distances) ————")
    table_4_5_distances()
    # print("———— Table 4‑5 (data survive) ——————————")
    # table_surviving_ratio()
    print("———— Table 6 (count repaired) —————————")
    table_6_count_fixed()
    print("———— Table 7 (perfect repairs) —————————")
    table_7_perfect()
    print("———— Table 8 (efficiency) ———————————")
    table_8_efficiency()
