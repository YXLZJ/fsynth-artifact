import regex as re
import sys
import argparse

# Define regular expressions for the formats
patterns = {
    "Date": r"^\d{4}-\d{2}-\d{2}$",  # Format: YYYY-MM-DD
    "Time": r"^\d{2}:\d{2}:\d{2}$",  # Format: HH:MM:SS
    "URL":  r"https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)",
    "ISBN": r"^(?:\d[- ]?){9}[\dX]$",  # ISBN-10 
    "IPv4": r"^(\d{1,3}\.){3}\d{1,3}$",
    "IPv6": r"^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$",
    "FilePath": r"^[a-zA-Z]:\\(?:[^\\/:*?\"<>|\r\n]+\\)*[^\\/:*?\"<>|\r\n]*$"
}

def validate_data_with_partial(pattern, data):
    """Validate the data using partial matches."""
    match = re.match(pattern, data, partial=True)
    if match:
        if match.partial:
            return -1  # Partial match
        else:
            return 0  # Full match
    return 1  # Parsing error

def main(): 
    parser = argparse.ArgumentParser(description="Validate file content with partial match support.")
    parser.add_argument("category", choices=patterns.keys(), help="Category to validate against.")
    parser.add_argument("file_path", help="Path to the input file.")
    args = parser.parse_args()

    # Read the file
    try:
        with open(args.file_path, 'r') as file:
            data = file.read().strip()
    except FileNotFoundError:
        print(f"Error: File '{args.file_path}' not found.", file=sys.stderr)
        sys.exit(1)

    # Validate data
    pattern = patterns[args.category]
    result = validate_data_with_partial(pattern, data)
    sys.exit(result)

if __name__ == "__main__":
    main()