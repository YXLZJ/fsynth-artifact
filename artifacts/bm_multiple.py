#!/usr/bin/env python3
import os
import sqlite3
import subprocess
import re
import time
import random
import concurrent.futures

# ------------------------------------------------------------------------------
# Configuration
# ------------------------------------------------------------------------------
DATABASE_PATH = "double.db"  # Name of the new database to create
REPAIR_OUTPUT_DIR = "repair_results"  # Directory where repair outputs are stored
os.makedirs(REPAIR_OUTPUT_DIR, exist_ok=True)

# Possible repair algorithms you want to test
REPAIR_ALGORITHMS = ["DDMax", "erepair", "DDMaxG", "Antlr"]

# Paths to the external format validators (adjust as needed)
PROJECT_PATHS = {
    "dot": "project/erepair-subjects/dot/build/dot_parser",
    "ini": "project/erepair-subjects/ini/ini",
    "json": "project/erepair-subjects/cjson/cjson",
    "lisp": "project/erepair-subjects/sexp-parser/sexp",
    "obj": "project/erepair-subjects/obj/build/obj_parser",
    "c": "project/erepair-subjects/tiny/tiny"
}

# Valid formats/folders to process
VALID_FORMATS = ["ini", "json", "lisp", "c"]

MUTATION_TYPES = ["double"]

# Parser timeout (in seconds)
VALIDATION_TIMEOUT = 30

# Repair timeout (in seconds)
REPAIR_TIMEOUT = 240

# ------------------------------------------------------------------------------
# Helper functions
# ------------------------------------------------------------------------------

def create_database(db_path: str):
    """
    Creates a new SQLite database (or overwrites if it already exists).
    This function will create a 'results' table with columns that store
    original/corrupted text, repaired text, and various repair metrics.
    """
    if os.path.exists(db_path):
        print(f"[WARNING] Database '{db_path}' already exists. It will be reused/overwritten.")
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()

    # Create the table if not exists
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS results (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            format TEXT,
            file_id INTEGER,
            corrupted_index INTEGER,
            algorithm TEXT,
            original_text TEXT,
            broken_text TEXT,
            repaired_text TEXT,
            fixed INTEGER,
            iterations INTEGER,
            repair_time REAL,
            correct_runs INTEGER,
            incorrect_runs INTEGER,
            incomplete_runs INTEGER,
            distance_original_broken INTEGER,
            distance_broken_repaired INTEGER,
            distance_original_repaired INTEGER
        )
    """)
    conn.commit()
    conn.close()
    print(f"[INFO] Created/checked table 'results' in database '{db_path}'.")


def load_test_samples_from_db(mutation_db_path: str):
    """
    Loads test samples from a mutation database.
    """
    if not os.path.exists(mutation_db_path):
        print(f"[ERROR] Mutation database not found: {mutation_db_path}")
        return []

    conn = sqlite3.connect(mutation_db_path)
    cursor = conn.cursor()
    cursor.execute("SELECT id, original_text, mutated_text FROM mutations")
    samples = cursor.fetchall()
    conn.close()

    # The format of test_samples should be (file_id, corrupted_index, original_text, corrupted_text)
    test_samples = [(row[0], 0, row[1], row[2]) for row in samples]
    return test_samples


def insert_test_samples_to_db(db_path: str, format_key: str, test_samples: list):
    """
    Insert the given list of (file_id, corrupted_index, original_text, corrupted_text)
    into the 'results' table in the database, for the specified format.
    """
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()

    # Insert each entry only if it doesn't already exist (resume capability)
    for (file_id, cindex, orig_text, broken_text) in test_samples:
        for alg in REPAIR_ALGORITHMS:
            # Skip if this combination already exists (enables resume)
            cursor.execute(
                "SELECT 1 FROM results WHERE format=? AND file_id=? AND corrupted_index=? AND algorithm=? LIMIT 1",
                (format_key, file_id, cindex, alg)
            )
            if cursor.fetchone():
                continue
            cursor.execute("""
                INSERT INTO results (format, file_id, corrupted_index, algorithm,
                                     original_text, broken_text,
                                     repaired_text, fixed, iterations, repair_time,
                                     correct_runs, incorrect_runs, incomplete_runs,
                                     distance_original_broken, distance_broken_repaired, distance_original_repaired)
                VALUES (?, ?, ?, ?, ?, ?, '', 0, 0, 0.0, 0, 0, 0, 0, 0, 0)
            """, (format_key, file_id, cindex, alg, orig_text, broken_text))
    conn.commit()
    conn.close()


def validate_with_external_tool(file_path: str, format_key: str) -> bool:
    """
    Validate a repaired file by running the corresponding python script.
    Return True if return code == 0, else False.
    """
    # The format key from the database is like "single_dot", we need to extract "dot"
    base_format = format_key.split('_')[-1]
    executable_path = PROJECT_PATHS.get(base_format)
    if not executable_path or not os.path.exists(executable_path):
        print(f"[WARNING] No validation executable found for format '{base_format}' (from key '{format_key}')")
        return False

    try:
        result = subprocess.run(
            [executable_path, file_path],
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True,
            timeout=VALIDATION_TIMEOUT
        )
        return (result.returncode == 0)
    except subprocess.TimeoutExpired:
        print(f"[ERROR] Validation timeout for '{file_path}', format '{format_key}'")
        return False
    except Exception as e:
        print(f"[ERROR] Could not run validation tool for format '{format_key}': {e}")
        return False


def levenshtein_distance(a: str, b: str) -> int:
    """Calculate the Levenshtein distance between two strings."""
    if not a: return len(b)
    if not b: return len(a)

    dp = [[0] * (len(b) + 1) for _ in range(len(a) + 1)]

    for i in range(len(a) + 1):
        dp[i][0] = i
    for j in range(len(b) + 1):
        dp[0][j] = j

    for i in range(1, len(a) + 1):
        for j in range(1, len(b) + 1):
            cost = 0 if a[i - 1] == b[j - 1] else 1
            dp[i][j] = min(
                dp[i - 1][j] + 1,       # Deletion
                dp[i][j - 1] + 1,       # Insertion
                dp[i - 1][j - 1] + cost # Substitution
            )
    return dp[-1][-1]


def extract_oracle_info(stdout: str):
    """
    Example parser for lines like:
        *** Number of required oracle runs: 10 correct: 5 incorrect: 3 incomplete: 2 ***
    Adjust if your actual output is different.
    """
    match = re.search(r"\*\*\* Number of required oracle runs: (\d+) correct: (\d+) incorrect: (\d+) incomplete: (\d+) \*\*\*", stdout)
    if match:
        return int(match.group(1)), int(match.group(2)), int(match.group(3)), int(match.group(4))
    return 0, 0, 0, 0


def repair_and_update_entry(cursor, conn, row):
    """
    Given a single row from the 'results' table, run the repair tool, measure results,
    and update the row in the database.
    """
    (id_, format_key, file_id, corrupted_index, algorithm,
     original_text, broken_text, _repaired, _fixed, _iter, _rtime,
     _correct, _incorrect, _incomplete, _distOB, _distBR, _distOR) = row

    print(f"[INFO] Repairing ID={id_}, format={format_key}, algorithm={algorithm}, file_id={file_id}, corrupted_index={corrupted_index}")

    # Prepare temporary input and output files
    base_format = format_key.split('_')[-1]
    ext = base_format
    if algorithm != "erepair":
        input_file = f"temp_{id_}_{random.randint(0, 9999)}_input.{ext}"
        output_file = os.path.join(REPAIR_OUTPUT_DIR, f"repair_{id_}_output.{ext}")
    else:
        input_file = f"temp_{id_}_{random.randint(0, 9999)}_input.{format_key}"
        output_file = os.path.join(REPAIR_OUTPUT_DIR, f"repair_{id_}_output.{format_key}")

    with open(input_file, "w", encoding="utf-8") as f:
        f.write(broken_text)

    distance_original_broken = levenshtein_distance(original_text, broken_text)
    distance_broken_repaired = -1
    distance_original_repaired = -1

    # By default, we mark as not fixed
    repaired_text = ""
    fixed = 0
    iterations, correct_runs, incorrect_runs, incomplete_runs = 0, 0, 0, 0
    repair_time = 0.0

    # Choose the repair command
    if algorithm == "erepair":
        base_format = format_key.split('_')[-1]
        oracle_executable = PROJECT_PATHS.get(base_format)
        if not oracle_executable:
            print(f"[ERROR] No oracle executable for format {base_format}")
            return
        cmd = ["./erepair", oracle_executable, input_file, output_file]
    else:
        # Example usage of your erepair.jar approach
        cmd = [
            "java", "-jar", "./project/bin/erepair.jar",
            "-r", "-a", algorithm,
            "-i", input_file,
            "-o", output_file
        ]

    try:
        start_time = time.time()
        proc = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
        stdout, stderr = proc.communicate(timeout=REPAIR_TIMEOUT)
        repair_time = time.time() - start_time

        # Extract oracle info (optional)
        iterations, correct_runs, incorrect_runs, incomplete_runs = extract_oracle_info(stdout)

        print(f"--- STDOUT (ID={id_}) ---\n{stdout}\n")
        print(f"--- STDERR (ID={id_}) ---\n{stderr}\n")

        if proc.returncode == 0 and os.path.exists(output_file):
            # Read the repaired output
            with open(output_file, "r", encoding="utf-8") as rf:
                repaired_text = rf.read()

            # Validate the repaired file
            if validate_with_external_tool(output_file, format_key):
                fixed = 1

            # Compute Levenshtein distances
            distance_broken_repaired = levenshtein_distance(broken_text, repaired_text)
            distance_original_repaired = levenshtein_distance(original_text, repaired_text)

    except subprocess.TimeoutExpired:
        print(f"[ERROR] Repair timed out for entry ID={id_}")
    except Exception as e:
        print(f"[ERROR] Repair failed for entry ID={id_}: {e}")
    finally:
        # Clean up temp files
        if os.path.exists(input_file):
            os.remove(input_file)
        if os.path.exists(output_file):
            os.remove(output_file)

    # Update the database record
    cursor.execute("""
        UPDATE results
        SET repaired_text = ?, fixed = ?, iterations = ?, repair_time = ?,
            correct_runs = ?, incorrect_runs = ?, incomplete_runs = ?,
            distance_original_broken = ?, distance_broken_repaired = ?, distance_original_repaired = ?
        WHERE id = ?
    """, (
        repaired_text, fixed, iterations, repair_time,
        correct_runs, incorrect_runs, incomplete_runs,
        distance_original_broken, distance_broken_repaired, distance_original_repaired,
        id_
    ))
    conn.commit()


def rerun_repairs_for_selected_formats(db_path: str, selected_formats=None):
    """
    Re-run (or run for the first time) repairs for the specified formats.
    If selected_formats is None, it will use all in VALID_FORMATS.
    """
    if not selected_formats:
        selected_formats = VALID_FORMATS

    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()

    # Fetch entries for the desired formats
    cursor.execute("""
        SELECT id, format, file_id, corrupted_index, algorithm,
               original_text, broken_text, repaired_text, fixed,
               iterations, repair_time, correct_runs, incorrect_runs,
               incomplete_runs, distance_original_broken, distance_broken_repaired,
               distance_original_repaired
        FROM results
        WHERE fixed = 0
    """)
    entries = cursor.fetchall()

    # Filter only those in the selected formats
    filtered_entries = [row for row in entries if row[1] in selected_formats]

    print(f"[INFO] Found {len(filtered_entries)} entries to (re)process.")

    def _worker(row):
        # Each thread gets its own connection to avoid SQLite locking
        thread_conn = sqlite3.connect(db_path, timeout=30)
        thread_cursor = thread_conn.cursor()
        try:
            repair_and_update_entry(thread_cursor, thread_conn, row)
        finally:
            thread_conn.close()

    with concurrent.futures.ThreadPoolExecutor(max_workers=os.cpu_count() or 4) as executor:
        executor.map(_worker, filtered_entries)

    conn.close()
    print("[INFO] Repair process completed!")


# ------------------------------------------------------------------------------
# Main script flow
# ------------------------------------------------------------------------------
def main():
    # 1) Create or reuse the database
    create_database(DATABASE_PATH)

    # 2) For each format and mutation type, load test samples from the corresponding DB
    for mutation_type in MUTATION_TYPES:
        for fmt in VALID_FORMATS:
            # Construct DB path, e.g., mutated_files/single_dot.db
            db_name = f"{mutation_type}_{fmt}.db"
            mutation_db_path = os.path.join("mutated_files", db_name)

            if not os.path.exists(mutation_db_path):
                print(f"[INFO] Skipping, not found: {mutation_db_path}")
                continue

            print(f"[INFO] Loading samples from {mutation_db_path}")
            samples = load_test_samples_from_db(mutation_db_path)
            
            if samples:
                # 3) Insert each sample into the 'results' table for *each* algorithm
                # We use a combined format key like "single_dot"
                format_key = f"{mutation_type}_{fmt}"
                insert_test_samples_to_db(DATABASE_PATH, format_key, samples)
            else:
                print(f"[INFO] No samples found in '{mutation_db_path}'")

    # 4) Now run or re-run the repairs for all newly inserted entries
    all_formats = [f"{mtype}_{fmt}" for mtype in MUTATION_TYPES for fmt in VALID_FORMATS]
    rerun_repairs_for_selected_formats(DATABASE_PATH, selected_formats=all_formats)


if __name__ == "__main__":
    main()
