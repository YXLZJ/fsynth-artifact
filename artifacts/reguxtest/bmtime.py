#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os
import random
import sqlite3
import subprocess

# --------------------------------------------------
# Global Variables and Configuration
# --------------------------------------------------

FORMATS = ["time"]  # Extend this list as needed, e.g. ["time", "date", "ipv4"]

dir_to_category = {
    "date": "Date",
    "ipv4": "IPv4",
    "ipv6": "IPv6",
    "isbn": "ISBN",
    "pathfile": "FilePath",
    "time": "Time",
    "url": "URL"
}

RESULT_DATABASE = "result8.db"
OUTPUT_TABLE = "results"

# A global set to track which corrupted strings have already been used
used_corrupted = set()

# --------------------------------------------------
# Helper Functions
# --------------------------------------------------

def choose_extension_for_format(fmt: str) -> str:
    """
    Returns a file extension or identifier for the given format.
    Adjust this to suit your real naming needs.
    """
    return fmt

def validate_with_matchpy(file_path: str, fmt: str) -> int:
    """
    Calls match.py to check if 'file_path' content matches 'fmt' (e.g. "time").
    Returns the exit code from match.py:
      0 = fully matched
     -1 = partially matched
      1 = not matched
    """
    try:
        category = dir_to_category[fmt]
    except KeyError:
        print(f"[ERROR] No category found for format '{fmt}'")
        return 1  # treat as not matched

    try:
        result = subprocess.run(
            ["python3", "match.py", category, file_path],
            capture_output=True,
            text=True,
            timeout=30
        )
        return result.returncode
    except subprocess.TimeoutExpired:
        print(f"[ERROR] Validation timeout for format '{fmt}'")
        return 1
    except Exception as e:
        print(f"[ERROR] Could not run match.py for '{fmt}': {e}")
        return 1

def repair_test_case(broken_text: str,
                     input_filepath: str,
                     output_filepath: str,
                     algorithm: str,
                     fmt: str) -> str:
    """
    Uses an external repair tool (e.g. `./erepair`) to attempt fixing the broken text.
    Writes `broken_text` into `input_filepath`, then calls the tool to produce
    `output_filepath`. Returns the repaired text, or an empty string on failure.

    这里根据 fmt 动态选择 parser_path，而不再固定 "Time"。
    """
    try:
        # 1. 将 broken_text 写入 input_filepath
        with open(input_filepath, "w") as f:
            f.write(broken_text)

        # 2. 动态选择正确的 parser_path
        #    如果 fmt="ipv4"，则 dir_to_category["ipv4"] -> "IPv4"
        #    拼接成 "python3 match.py IPv4"
        category = dir_to_category.get(fmt, "Time")  # 若找不到就默认用 "Time" 或其它逻辑
        parser_path = f"python3 match.py {category}"

        erepair_exe = "./erepair"

        # 3. 用 shell=True 执行命令，这里把 parser_path 当作一个整体字符串传给 C++。
        command_str = f'{erepair_exe} "{parser_path}" "{input_filepath}" "{output_filepath}"'
        print(f"Running repair command (shell=True): {command_str}")
        subprocess.run(command_str, shell=True, timeout=240, check=True)

        # 4. 如果修复工具成功生成 output_filepath，就读回来返回
        if os.path.exists(output_filepath):
            with open(output_filepath, "r") as f:
                return f.read()
        return ""

    except subprocess.TimeoutExpired:
        print(f"[ERROR] Repair timed out for algorithm '{algorithm}'")
        return ""
    except subprocess.CalledProcessError as e:
        print(f"[ERROR] Repair tool returned non-zero exit code for '{algorithm}': {e}")
        return ""
    except Exception as e:
        print(f"[ERROR] Repair failed for algorithm '{algorithm}': {e}")
        return ""

def levenshtein_distance(a: str, b: str) -> int:
    """
    Computes the Levenshtein distance between strings a and b.
    """
    if not a:
        return len(b)
    if not b:
        return len(a)

    dp = [[0] * (len(b) + 1) for _ in range(len(a) + 1)]
    for i in range(len(a) + 1):
        dp[i][0] = i
    for j in range(len(b) + 1):
        dp[0][j] = j

    for i in range(1, len(a) + 1):
        for j in range(1, len(b) + 1):
            cost = 0 if a[i - 1] == b[j - 1] else 1
            dp[i][j] = min(
                dp[i - 1][j] + 1,
                dp[i][j - 1] + 1,
                dp[i - 1][j - 1] + cost
            )
    return dp[-1][-1]

def get_unique_broken_test_case(original_text: str, fmt: str, max_attempts: int = 2000) -> str:
    """
    Randomly shuffle the character positions of 'original_text', then try
    replacing exactly one character at each position with one of
    ['*', ' ', '#']. As soon as we find a replacement that breaks match.py
    (return code != 0), we return it. Each returned corrupted text is
    recorded in 'used_corrupted' to avoid duplicates.

    If no single-character replacement can break parsing after 'max_attempts' tries,
    we raise a ValueError.
    """
    format_key = choose_extension_for_format(fmt)
    attempt_count = 0

    indices = list(range(len(original_text)))
    shuffled_indices = random.sample(indices, len(indices))

    for i in shuffled_indices:
        for replacement in ['*','#']:
            if original_text[i] == replacement:
                continue

            corrupted_text = original_text[:i] + replacement + original_text[i+1:]
            attempt_count += 1

            if attempt_count > max_attempts:
                break

            temp_file = f"temp_validation_{random.randint(1000, 9999)}.{format_key}"
            try:
                with open(temp_file, "w") as f:
                    f.write(corrupted_text)

                ret_code = validate_with_matchpy(temp_file, fmt)
                if ret_code != 0 and corrupted_text not in used_corrupted:
                    used_corrupted.add(corrupted_text)
                    return corrupted_text
            finally:
                if os.path.exists(temp_file):
                    os.remove(temp_file)

    raise ValueError("Could not generate unique corrupted text after trying all single-character modifications.")

# --------------------------------------------------
# Main Testing Logic
# --------------------------------------------------

def process_format(fmt: str):
    source_files_dir = f"data/{fmt}"
    if not os.path.isdir(source_files_dir):
        print(f"[ERROR] Source directory not found: {source_files_dir}")
        return

    conn = sqlite3.connect(RESULT_DATABASE)
    cursor = conn.cursor()

    cursor.execute(f"""
    CREATE TABLE IF NOT EXISTS {OUTPUT_TABLE} (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        format TEXT,
        algorithm TEXT,
        original_text TEXT,
        broken_text TEXT,
        repaired_text TEXT,
        distance_original_broken INTEGER,
        distance_original_repaired INTEGER,
        distance_broken_repaired INTEGER,
        fixed BOOLEAN
    )
    """)

    unmatched_or_partial = []

    file_list = os.listdir(source_files_dir)
    for idx, file_name in enumerate(file_list):
        file_path = os.path.join(source_files_dir, file_name)
        if not os.path.isfile(file_path):
            continue

        try:
            with open(file_path, "r") as fr:
                original_text = fr.read()
            print("filepath: ", file_path)
            print("original_text: ", original_text)

            # 1) Generate a broken text that fails match.py
            try:
                broken_text = get_unique_broken_test_case(original_text, fmt, max_attempts=1000)
            except ValueError as ve:
                print(f"[WARNING] Could not break file '{file_name}': {ve}")
                continue

            # 2) Write the broken text to a new temp file
            temp_broken_file = f"broken_{random.randint(1000, 9999)}.{fmt}"
            with open(temp_broken_file, "w") as fw:
                fw.write(broken_text)

            # 3) Validate the broken file
            broken_ret_code = validate_with_matchpy(temp_broken_file, fmt)

            # 4) Attempt to repair -> output goes to <baseName>.<fmt>
            base_name, _ = os.path.splitext(file_name)
            repaired_file_path = os.path.join('/tmp', f"{base_name}.{fmt}")

            # 注意：调用 repair_test_case 时，把 fmt 也传进去
            repaired_text = repair_test_case(
                broken_text,
                input_filepath=temp_broken_file,
                output_filepath=repaired_file_path,
                algorithm="erepair",
                fmt=fmt
            )

            # 5) Validate the repaired file
            repaired_ret_code = 1
            if repaired_text.strip():
                repaired_ret_code = validate_with_matchpy(repaired_file_path, fmt)

            # 6) Calculate Levenshtein distances
            dist_ob = levenshtein_distance(original_text, broken_text)
            dist_or = levenshtein_distance(original_text, repaired_text) if repaired_text else -1
            dist_br = levenshtein_distance(broken_text, repaired_text) if repaired_text else -1

            fixed = (repaired_ret_code == 0)

            # 7) Insert record into the database
            cursor.execute(f"""
                INSERT INTO {OUTPUT_TABLE} (
                    format,
                    algorithm,
                    original_text,
                    broken_text,
                    repaired_text,
                    distance_original_broken,
                    distance_original_repaired,
                    distance_broken_repaired,
                    fixed
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """, (
                fmt,
                "erepair",
                original_text,
                broken_text,
                repaired_text,
                dist_ob,
                dist_or,
                dist_br,
                fixed
            ))
            conn.commit()

            # 8) If the broken text is partially matched (-1) or unmatched (1), track it
            if broken_ret_code in (-1, 1):
                unmatched_or_partial.append((file_name, broken_ret_code))

        except Exception as e:
            print(f"[ERROR] Failed to process file '{file_name}': {e}")
        finally:
            if os.path.exists(temp_broken_file):
                os.remove(temp_broken_file)

    conn.close()

    print(f"\n[INFO] Finished processing format: {fmt}. Stored results in {RESULT_DATABASE}.")
    if unmatched_or_partial:
        print("[INFO] The following files had partially/unmatched broken text:")
        for fname, code in unmatched_or_partial:
            if code == -1:
                print(f"  - {fname} [PARTIALLY MATCHED] return code = -1")
            else:
                print(f"  - {fname} [NOT MATCHED] return code = {code}")
    else:
        print("[INFO] All broken texts returned code 0 (fully matched).")

def main():
    for fmt in FORMATS:
        process_format(fmt)

if __name__ == "__main__":
    main()