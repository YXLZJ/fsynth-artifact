import os
import re
import random
import sqlite3
import subprocess
import time

# -------------------------------------------------------------------
# 1. Configuration and "populate_database" section
# -------------------------------------------------------------------

DATABASE_PATH = "result1.db"  # Path to your SQLite database

# Paths to your executables/jar, etc. Adjust as needed.
PROJECT_PATHS = {
    "dot": "project/erepair-subjects/dot/build/dot_parser",
    "ini": "project/erepair-subjects/ini/ini",
    "json": "project/erepair-subjects/cjson/cjson",
    "lisp": "project/erepair-subjects/sexp-parser/sexp",
    "obj": "project/erepair-subjects/obj/build/obj_parser"
}

# Which formats to process
VALID_FORMATS = ["dot", "ini", "json", "lisp", "obj"]
MUTATION_TYPE = "double" # For this script, we are focusing on double mutations

# Which repair algorithms to insert into the DB.  
# If you only want to test one, you could just use ["erepair"] or similar.
REPAIR_ALGORITHMS = ["DDMax", "erepair", "DDMaxG", "Antlr"]


def populate_database():
    """
    Populate the database from the mutation databases.
    """
    conn = sqlite3.connect(DATABASE_PATH)
    cursor = conn.cursor()

    # Create table if it doesn't exist:
    cursor.execute("""CREATE TABLE IF NOT EXISTS results(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        format TEXT,
        algorithm TEXT,
        original_text TEXT,
        broken_text TEXT,
        repaired_text TEXT,
        fixed INTEGER DEFAULT 0,
        iterations INTEGER DEFAULT 0,
        repair_time REAL DEFAULT 0.0,
        correct_runs INTEGER DEFAULT 0,
        incorrect_runs INTEGER DEFAULT 0,
        incomplete_runs INTEGER DEFAULT 0,
        distance_original_broken INTEGER DEFAULT 0,
        distance_broken_repaired INTEGER DEFAULT 0,
        distance_original_repaired INTEGER DEFAULT 0
    );""")

    for fmt in VALID_FORMATS:
        mutation_db_path = os.path.join("mutated_files", f"{MUTATION_TYPE}_{fmt}.db")
        if not os.path.exists(mutation_db_path):
            print(f"[WARNING] Skipping, not found: {mutation_db_path}")
            continue

        print(f"[INFO] Loading from {mutation_db_path}")
        mut_conn = sqlite3.connect(mutation_db_path)
        mut_cursor = mut_conn.cursor()
        mut_cursor.execute("SELECT original_text, mutated_text FROM mutations")
        
        for original_text, broken_text in mut_cursor.fetchall():
            for algorithm in REPAIR_ALGORITHMS:
                cursor.execute("""
                    INSERT INTO results(format, algorithm, original_text, broken_text)
                    VALUES (?,?,?,?)
                """, (fmt, algorithm, original_text, broken_text))
        
        mut_conn.close()

    conn.commit()
    conn.close()
    print("[INFO] Inserted file-based test cases into the database.")


# -------------------------------------------------------------------
# 2. Repair logic (slightly adapted)
# -------------------------------------------------------------------

def validate_with_external_tool(file_path: str, format_key: str) -> bool:
    """Validate a file using the corresponding executable."""
    executable_path = PROJECT_PATHS.get(format_key)
    if not executable_path or not os.path.exists(executable_path):
        print(f"[WARNING] No validation executable found for format '{format_key}'")
        return False

    try:
        result = subprocess.run(
            [executable_path, file_path],
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True,
            timeout=30
        )
        return result.returncode == 0
    except subprocess.TimeoutExpired:
        print(f"[ERROR] Validation timeout for format '{format_key}'")
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
    Parses oracle info from stdout, e.g.:
    *** Number of required oracle runs: X correct: Y incorrect: Z incomplete: W ***
    """
    import re
    match = re.search(
        r"\*\*\* Number of required oracle runs: (\d+) correct: (\d+) incorrect: (\d+) incomplete: (\d+) \*\*\*",
        stdout
    )
    if match:
        return (
            int(match.group(1)),  # iterations
            int(match.group(2)),  # correct
            int(match.group(3)),  # incorrect
            int(match.group(4))   # incomplete
        )
    return 0, 0, 0, 0


def repair_and_update_entry(cursor, conn, entry):
    """
    Reruns the repair for a single database entry and updates the database.
    entry = (id, format, algorithm, original_text, broken_text)
    """
    id_, fmt, algorithm, original_text, broken_text = entry
    print(f"[INFO] Processing entry ID {id_} (Format: {fmt}, Algorithm: {algorithm})")

    ext = fmt
    input_file = f"temp_{id_}_{random.randint(0, 9999)}_input.{ext}"
    output_file = os.path.join("repair_results", f"repair_{id_}_output.{ext}")

    with open(input_file, "w", encoding="utf-8", errors="ignore") as f:
        f.write(broken_text)

    iterations = correct_runs = incorrect_runs = incomplete_runs = 0
    repaired_text = ""
    fixed = False
    repair_time = 0

    distance_original_broken = levenshtein_distance(original_text, broken_text)
    distance_broken_repaired = -1
    distance_original_repaired = -1

    try:
        # Choose command based on algorithm
        if algorithm == "erepair":
            oracle_script = PROJECT_PATHS.get(fmt)
            if not oracle_script:
                print(f"[ERROR] No oracle script for format {fmt}")
                return
            cmd = ["./erepair", oracle_script, input_file, output_file]
        else:
            # Using the erepair.jar approach
            cmd = [
                "java", "-jar", "./project/bin/erepair.jar",
                "-r", "-a", algorithm,
                "-i", input_file,
                "-o", output_file
            ]

        start_time = time.time()
        process = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
        stdout, stderr = process.communicate(timeout=240)
        repair_time = time.time() - start_time

        # Parse oracle info
        iterations, correct_runs, incorrect_runs, incomplete_runs = extract_oracle_info(stdout)

        print(f"[DEBUG] STDOUT (ID={id_}):\n{stdout}")
        print(f"[DEBUG] STDERR (ID={id_}):\n{stderr}")

        # If process succeeded and the output file exists
        if process.returncode == 0 and os.path.exists(output_file):
            with open(output_file, "r", encoding="utf-8", errors="ignore") as f:
                repaired_text = f.read()

            # Validate with external tool
            fixed = validate_with_external_tool(output_file, fmt)

            # Compute distances
            distance_broken_repaired = levenshtein_distance(broken_text, repaired_text)
            distance_original_repaired = levenshtein_distance(original_text, repaired_text)

    except subprocess.TimeoutExpired:
        print(f"[ERROR] Repair timeout for entry ID {id_}")
    except Exception as e:
        print(f"[ERROR] Repair failed for entry ID {id_}: {e}")
    finally:
        # Clean up temp files
        if os.path.exists(input_file):
            os.remove(input_file)
        if os.path.exists(output_file):
            os.remove(output_file)

    # Update the DB record
    cursor.execute("""
        UPDATE results
        SET repaired_text = ?,
            fixed = ?,
            iterations = ?,
            repair_time = ?,
            correct_runs = ?,
            incorrect_runs = ?,
            incomplete_runs = ?,
            distance_original_broken = ?,
            distance_broken_repaired = ?,
            distance_original_repaired = ?
        WHERE id = ?
    """, (
        repaired_text,
        1 if fixed else 0,
        iterations,
        repair_time,
        correct_runs,
        incorrect_runs,
        incomplete_runs,
        distance_original_broken,
        distance_broken_repaired,
        distance_original_repaired,
        id_
    ))
    conn.commit()


def rerun_repairs():
    """
    Reads records from the database, repairs them, and updates
    the results in the 'results' table.
    """
    conn = sqlite3.connect(DATABASE_PATH)
    cursor = conn.cursor()

    # Ensure required columns exist (skip if already present)
    cursor.execute("PRAGMA table_info(results)")
    existing_cols = {col[1] for col in cursor.fetchall()}
    for col in ["correct_runs", "incorrect_runs", "incomplete_runs"]:
        if col not in existing_cols:
            cursor.execute(f"ALTER TABLE results ADD COLUMN {col} INTEGER DEFAULT 0")

    # Fetch entries; optionally filter on format, etc.
    cursor.execute("SELECT id, format, algorithm, original_text, broken_text FROM results")
    entries = cursor.fetchall()

    # Another filter if needed (e.g., only certain formats)
    filtered_entries = [e for e in entries if e[1] in VALID_FORMATS]

    for entry in filtered_entries:
        repair_and_update_entry(cursor, conn, entry)

    conn.close()
    print("[INFO] All selected repairs have been completed!")


# -------------------------------------------------------------------
# Main
# -------------------------------------------------------------------
if __name__ == "__main__":
    # Step 1: Populate the DB from the mutation dbs
    populate_database()

    # Step 2: Perform repairs on those newly inserted records
    rerun_repairs()
