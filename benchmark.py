import sqlite3
import subprocess
import os
from langchain.llms import Ollama

# Configuration variables
OLLAMA_MODEL = "your-model-name"  # Replace with your Ollama model name
FORMATS = ["INI", "JSON", "s-expression(evry simple)", """TinyC(evry simple version of C coke ,like "a=b=c=2<3;", "{ i=1; while (i<100) i=i+i; }" etc. )"""]  # Test formats
NUM_TESTS_PER_FORMAT = 100  # Number of test cases per format
TOTAL_TESTS = NUM_TESTS_PER_FORMAT * len(FORMATS)

FSYNTH_JAR = "/Users/apple/daikon-5.8.20/examples/java-examples/fsynth-artifact/project/bin/fsynth.jar"  # Path to the fsynth.jar file
REPAIR_ALGORITHM = "bRepair"  # Repair algorithm to be used
REPAIR_OUTPUT_DIR = "./repaired_files"  # Directory to save repaired files

# Create the repair output directory if it does not exist
if not os.path.exists(REPAIR_OUTPUT_DIR):
    os.makedirs(REPAIR_OUTPUT_DIR)

# Create/connect to a SQLite database
conn = sqlite3.connect('results.db')
cursor = conn.cursor()
cursor.execute('''
CREATE TABLE IF NOT EXISTS results (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    format TEXT,
    original_text TEXT,
    broken_text TEXT,
    repaired_text TEXT,
    distance_original_broken INTEGER,
    distance_original_repaired INTEGER,
    distance_broken_repaired INTEGER
)
''')
conn.commit()

get_example_prompt = """You are a example generator. your job is to genetrator SHORT code or text based on the user's description. Please strictly follow the user’s requirements.
NO COMMENTS, NO ```, NO anything else, No explain. ONLY the code."""

get_corrupt_prompt = """You are a corruption generator. Your job is to change ONLY one character of the given user input so that makes it grammar error cannot be parsed. You will receive a code snippet and its format. Output ONLY the CORRUPTED CODE ,no comments, no backticks, no explanations.
For example, if the input is:
(+ 1 2) format: s-expression
a possible output is:
(+ 1 2"""

def get_responce(prompt: str, model: str, text: str):
    target_model = Ollama(model = model)
    message = [
        {
            "role": "system",
            "content": prompt
        },
        {
            "role": "user",
            "content": text
        }
    ]
    response = target_model.invoke(message)
    return response


def levenshtein_distance(s1, s2):
    """
    Calculate the Levenshtein distance (edit distance) between two strings.
    """
    dp = [[0]*(len(s2)+1) for _ in range(len(s1)+1)]
    for i in range(len(s1)+1):
        dp[i][0] = i
    for j in range(len(s2)+1):
        dp[0][j] = j
    for i in range(1, len(s1)+1):
        for j in range(1, len(s2)+1):
            cost = 0 if s1[i-1] == s2[j-1] else 1
            dp[i][j] = min(
                dp[i-1][j] + 1,
                dp[i][j-1] + 1,
                dp[i-1][j-1] + cost
            )
    return dp[-1][-1]

def generate_test_case(fmt):
    """
    Generate a test case using Ollama for the given format.
    """
    result = get_responce(get_example_prompt,"qwen2.5-coder:14b", fmt)
    lines = result.split("\n")
    if len(lines) > 1 and lines[0].startswith("```") and lines[-1].startswith("```"):
        result = "\n".join(lines[1:-1])
    return result

def break_test_case_with_ai(original_text, fmt):
    """
    Use AI to modify the test case and ensure it is invalid.
    """
    result = get_responce(get_corrupt_prompt,"phi3:14b", original_text + "\nformat:"+fmt)
    lines = result.split("\n")
    if len(lines) > 1 and lines[0].startswith("```") and lines[-1].startswith("```"):
        result = "\n".join(lines[1:-1])
    return result

def repair_test_case(broken_text, input_filename, output_filename):
    """
    Repair a broken test case using the fsynth.jar tool.
    """
    # Write the broken text to a temporary input file
    with open(input_filename, 'w') as f:
        f.write(broken_text)
    
    # Call the fsynth.jar repair tool
    subprocess.run(["java", "-jar", FSYNTH_JAR, 
                    "-r",
                    "-a", REPAIR_ALGORITHM,
                    "-i", input_filename,
                    "-o", REPAIR_OUTPUT_DIR],
                   stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
    
    # Load the repaired file from the output directory
    repaired_file_path = os.path.join(REPAIR_OUTPUT_DIR, output_filename)
    if os.path.exists(repaired_file_path):
        with open(repaired_file_path, 'r') as f:
            repaired_text = f.read()
        return repaired_text
    else:
        # If no repaired file is generated, return an empty string
        return ""

def main():
    """
    Main function to generate, break, repair, and analyze test cases.
    """
    test_count = 0
    for fmt in FORMATS:
        for _ in range(NUM_TESTS_PER_FORMAT):
            print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
            print(f"Generating test case {test_count}/{TOTAL_TESTS} for format {fmt}...")
            test_count += 1
            # 1. Generate the original correct test case
            original_text = generate_test_case(fmt)
            print("Original:\n", original_text)
            if not original_text:
                # Skip if generation fails
                continue
            
            # 2. Use AI to break the test case
            broken_text = break_test_case_with_ai(original_text, fmt)
            print("Broken\n", broken_text)
            # 3. Repair the test case
            input_filename = f"tmp_input_{test_count}.txt"
            output_filename = f"repaired_{test_count}.txt"  # Expected output file name
            repaired_text = repair_test_case(broken_text, input_filename, output_filename)
            print("Repaired:\n", repaired_text)
            # 4. Calculate Levenshtein distances
            distance_original_broken = levenshtein_distance(original_text, broken_text)
            distance_original_repaired = levenshtein_distance(original_text, repaired_text) if repaired_text else -1
            distance_broken_repaired = levenshtein_distance(broken_text, repaired_text) if repaired_text else -1
            print("Distances:", distance_original_broken, distance_original_repaired, distance_broken_repaired)
            # 5. Save results to the database
            cursor.execute('''
                INSERT INTO results (format, original_text, broken_text, repaired_text, 
                                     distance_original_broken, distance_original_repaired, distance_broken_repaired)
                VALUES (?, ?, ?, ?, ?, ?, ?)
            ''', (fmt, original_text, broken_text, repaired_text, 
                  distance_original_broken, distance_original_repaired, distance_broken_repaired))
            conn.commit()

            # Clean up temporary files
            if os.path.exists(input_filename):
                os.remove(input_filename)

    print("All test cases generated and processed.")

if __name__ == "__main__":
    main()
    conn.close()