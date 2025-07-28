#!/usr/bin/env python3
"""
github_bulk_fetcher.py  •  v2.1
--------------------------------
Generic, resumable GitHub Code Search downloader.

Key features
------------
• --ext / --query / --size / --total        … standard search controls
• --split                                   … split on blank lines (\n\n)
• --sub-min / --sub-max                     … per-chunk byte-size filter
• --validator <exe>                         … keep only if exit-code == 0
• duplicate-name skipping + resume support  … never overwrite or re-grab
"""

import argparse
import os
import re
import subprocess
import sys
import textwrap
import time
from math import ceil
from pathlib import Path
from typing import Optional, Sequence

import requests


# ─────────────────────────────────────────────────────────────────────────────
def build_headers(token: str | None) -> dict:
    hdr = {
        "Accept": "application/vnd.github+json",
        "User-Agent": "BulkFetcher/2.1",
    }
    if token:
        hdr["Authorization"] = f"Bearer {token}"
    return hdr


def get_json(url: str, headers: dict, timeout: int) -> Optional[dict]:
    try:
        r = requests.get(url, headers=headers, timeout=timeout)
        if r.status_code == 200:
            return r.json()
        print(f"[{r.status_code}] {url}", file=sys.stderr)
    except Exception as exc:
        print(f"[Error] {url} – {exc}", file=sys.stderr)
    return None


def download_raw(url: str, headers: dict, timeout: int) -> Optional[bytes]:
    try:
        r = requests.get(url, headers=headers, timeout=timeout)
        if r.status_code == 200:
            return r.content
        print(f"[{r.status_code}] {url}", file=sys.stderr)
    except Exception as exc:
        print(f"[Error] {url} – {exc}", file=sys.stderr)
    return None


def validate_file(exe: str, file_path: Path, timeout: int = 10) -> bool:
    """Return True only if <exe> <file> exits 0."""
    try:
        completed = subprocess.run(
            [exe, str(file_path)],
            timeout=timeout,
            stdout=subprocess.DEVNULL,
            stderr=subprocess.DEVNULL,
        )
        return completed.returncode == 0
    except Exception as exc:
        print(f"[ValidatorError] {file_path.name} – {exc}", file=sys.stderr)
        return False


# ─────────────────────────────────────────────────────────────────────────────
def main(argv: Sequence[str] | None = None):
    ap = argparse.ArgumentParser(
        formatter_class=argparse.RawDescriptionHelpFormatter,
        description=textwrap.dedent(__doc__),
    )
    ap.add_argument("-e", "--ext", help="file extension filter (e.g. ini)")
    ap.add_argument("-q", "--query", default="", help="additional search keywords")
    ap.add_argument(
        "-n",
        "--total",
        type=int,
        required=True,
        help="number of NEW (sub-)files to download this run",
    )
    ap.add_argument("--size", default="<10000", help='GitHub size filter, e.g. "<5000"')
    ap.add_argument("--out", default="data", help="output directory")
    ap.add_argument("--pause", type=float, default=0.7, help="pause between requests (s)")
    ap.add_argument("--timeout", type=int, default=10, help="HTTP timeout (s)")
    ap.add_argument("--token", help="GitHub PAT (else $GITHUB_TOKEN)")
    #
    ap.add_argument("--split", action="store_true", help="split on blank lines")
    ap.add_argument("--sub-min", type=int, default=0, help="min bytes per chunk")
    ap.add_argument(
        "--sub-max", type=int, default=10**9, help="max bytes per chunk (default huge)"
    )
    ap.add_argument(
        "--validator",
        help="external program to validate each (sub-)file; keep only if exit 0",
    )
    args = ap.parse_args(argv)

    # ------------------------------------------------------------------ setup
    token = args.token or os.getenv("GITHUB_TOKEN")
    if not token:
        ap.error("GitHub token missing (set $GITHUB_TOKEN or use --token)")

    headers = build_headers(token)
    out_dir = Path(args.out).resolve()
    out_dir.mkdir(parents=True, exist_ok=True)

    # ---------------------------- resume logic: populate counters & seen names
    counter_pat = re.compile(r"^(\d{4})_")
    saved_index = 0                   # highest number already present
    seen_names: set[str] = set()      # final filenames already in folder

    for f in out_dir.iterdir():
        if not f.is_file():
            continue
        m = counter_pat.match(f.name)
        if m:
            saved_index = max(saved_index, int(m.group(1)))
            seen_names.add(f.name)

    print(f"[*] Resume: found {len(seen_names)} existing file(s) in {out_dir}")

    # ---------------------------------------------------------- search query
    q_parts = []
    if args.ext:
        q_parts.append(f"extension:{args.ext}")
    if args.query:
        q_parts.append(args.query)
    if args.size:
        q_parts.append(f"size:{args.size}")
    query = "+".join(q_parts)
    print(f"[*] GitHub search query: {query}")

    per_page = 100
    pages_to_visit = ceil((args.total) / per_page)
    new_saved = 0  # number of files/chunks saved in THIS run

    # Helper -----------------------------------------------------------------
    def try_save(content: bytes, base_name: str, part_idx: int):
        nonlocal saved_index, new_saved

        # size filter on chunk
        if not (args.sub_min <= len(content) <= args.sub_max):
            return

        # assemble name while keeping extension last
        stem, ext = os.path.splitext(base_name)
        if args.split:
            stem = f"{stem}_part{part_idx}"
        final_name = f"{saved_index + 1:04d}_{stem}{ext}"

        # duplicate filename check
        if final_name in seen_names:
            return

        tmp_path = out_dir / (final_name + ".tmp")
        tmp_path.write_bytes(content)

        if args.validator and not validate_file(args.validator, tmp_path):
            tmp_path.unlink(missing_ok=True)
            return

        tmp_path.rename(out_dir / final_name)
        print(f"[✓] {final_name}  ({len(content)} bytes)")
        seen_names.add(final_name)
        saved_index += 1
        new_saved += 1

    # ----------------------------------------------------------------- crawl
    for page in range(1, pages_to_visit + 1):
        if new_saved >= args.total:
            break

        api_url = (
            "https://api.github.com/search/code"
            f"?q={query}&per_page={per_page}&page={page}"
        )
        data = get_json(api_url, headers, args.timeout)
        if not data or "items" not in data:
            break

        for item in data["items"]:
            if new_saved >= args.total:
                break

            meta = get_json(item["url"], headers, args.timeout)
            if not meta or "download_url" not in meta:
                continue

            raw_bytes = download_raw(meta["download_url"], headers, args.timeout)
            if raw_bytes is None:
                continue

            if not args.split:
                try_save(raw_bytes, item["name"], 0)
            else:
                text = raw_bytes.decode(errors="ignore")
                chunks = [p.encode() for p in text.split("\n\n") if p.strip()]
                for idx, chunk in enumerate(chunks, 1):
                    if new_saved >= args.total:
                        break
                    try_save(chunk, item["name"], idx)

            time.sleep(args.pause)

        time.sleep(args.pause)

    print(f"[✓] Completed: {new_saved} new file(s) saved → {out_dir}")


if __name__ == "__main__":
    main()