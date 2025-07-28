#include <iostream>
#include <string>
#include <vector>
#include <set>
#include <functional>
#include <fstream>
#include <cstdlib>
#include <cstdio>      // for remove(), tmpnam()
#include <sys/wait.h>  // for WIFEXITED, WEXITSTATUS
#include <unistd.h>    // for close()

//-------------------------------------
// Enum for parse results
//-------------------------------------
enum class ParseResult {
    INCOMPLETE,  // Parsing incomplete or ambiguous
    CORRECT,     // Parsing successful
    INCORRECT    // Parsing failed
};

//-------------------------------------
// Generates a unique temporary filename
//-------------------------------------
std::string generateTempFile() {
    char pattern[] = "/tmp/ddmax_inputXXXXXX";
    int fd = mkstemp(pattern);
    if (fd == -1) {
        throw std::runtime_error("Failed to create temp file");
    }
    close(fd);
    return std::string(pattern);
}

//-------------------------------------
// Executes an external parser on input
//-------------------------------------
ParseResult runExternalParser(const std::string& parser_path,
                              const std::string& input_content)
{
    std::string temp_file = generateTempFile();

    {
        std::ofstream ofs(temp_file);
        if (!ofs.is_open()) {
            std::cerr << "Error: Failed to open temp file\n";
            return ParseResult::INCORRECT;
        }
        ofs << input_content;
    }

    std::string cmd = parser_path + " " + temp_file + " > /dev/null 2>&1";
    int status = std::system(cmd.c_str());

    ParseResult result = ParseResult::INCORRECT; 
    if (WIFEXITED(status)) {
        int exit_code = WEXITSTATUS(status);
        if (exit_code == 0)       result = ParseResult::CORRECT;
        else if (exit_code == 1)  result = ParseResult::INCORRECT;
        else if (exit_code == 255)result = ParseResult::INCOMPLETE;
    }
    std::remove(temp_file.c_str());
    return result;
}

//-------------------------------------
// Represents a discrete interval [start, end)
//-------------------------------------
struct DeltaInterval {
    int inclusiveLower;
    int exclusiveUpper;

    DeltaInterval(int lb, int ub)
        : inclusiveLower(lb), exclusiveUpper(ub) {}

    int length() const { return exclusiveUpper - inclusiveLower; }

    bool inside(int x) const { return x >= inclusiveLower && x < exclusiveUpper; }
};

//-------------------------------------
// Represents a set of intervals
//-------------------------------------
class DeltaSet {
public:
    std::vector<DeltaInterval> intervals;

    DeltaSet() = default;

    DeltaSet(int lb, int ub) {
        intervals.push_back(DeltaInterval(lb, ub));
    }

    int totalLength() const {
        int sum = 0;
        for (auto& iv : intervals) {
            sum += iv.length();
        }
        return sum;
    }

    bool inside(int i) const {
        for (auto& iv : intervals) {
            if (iv.inside(i)) return true;
            if (i < iv.inclusiveLower) break;
        }
        return false;
    }

    int getNthIndex(int n) const {
        int count = 0;
        for (auto& iv : intervals) {
            int len = iv.length();
            if (n < len) return iv.inclusiveLower + n;
            n -= len;
        }
        throw std::out_of_range("Index out of range");
    }
};

//-------------------------------------
// Applies the DeltaSet to the input
//-------------------------------------
std::string applyDeltaSet(const std::string& input, const DeltaSet& ds) {
    std::string result;
    for (int i = 0; i < (int)input.size(); i++) {
        if (ds.inside(i)) result += input[i];
    }
    return result;
}

//-------------------------------------
// Creates a granularity interval
//-------------------------------------
DeltaInterval getGranularityInterval(const DeltaSet& ds, int granularity, int i) {
    int chunk_size = ds.totalLength() / granularity;
    int start_idx = i * chunk_size;
    int end_idx = (i == granularity - 1) ? ds.totalLength() : start_idx + chunk_size;

    int lb = ds.getNthIndex(start_idx);
    int ub = ds.getNthIndex(end_idx - 1) + 1;
    return DeltaInterval(lb, ub);
}

//-------------------------------------
// Recursive DDMax implementation
//-------------------------------------
DeltaSet ddmax_recursive(const DeltaSet& deltaSet,
                         int granularity,
                         const std::string& input,
                         const std::function<ParseResult(const std::string&)>& parser,
                         DeltaSet& lastAccepted)
{
    if (deltaSet.totalLength() <= 1) return deltaSet;

    for (int i = 0; i < granularity; ++i) {
        DeltaInterval interval = getGranularityInterval(deltaSet, granularity, i);
        DeltaSet newSet;
        newSet.intervals.push_back(interval);

        if (parser(applyDeltaSet(input, newSet)) == ParseResult::CORRECT) {
            lastAccepted = newSet;
            return ddmax_recursive(newSet, 2, input, parser, lastAccepted);
        }
    }

    if (granularity < deltaSet.totalLength()) {
        return ddmax_recursive(deltaSet, granularity * 2, input, parser, lastAccepted);
    }

    return deltaSet;
}

//-------------------------------------
// Main DDMax function
//-------------------------------------
std::string DDMax(const std::string& input,
                  const std::function<ParseResult(const std::string&)>& parser)
{
    DeltaSet fullSet(0, input.size());
    DeltaSet lastAccepted;

    if (parser(input) == ParseResult::CORRECT) return input;

    DeltaSet result = ddmax_recursive(fullSet, 2, input, parser, lastAccepted);
    return applyDeltaSet(input, result);
}

//-------------------------------------
// Main function for running DDMax
//-------------------------------------
int main(int argc, char* argv[]) {
    if (argc < 4) {
        std::cerr << "Usage: " << argv[0] << " <parser_path> <input_file> <output_file>\n";
        return 1;
    }

    std::string parser_path = argv[1];
    std::string input_file = argv[2];
    std::string output_file = argv[3];

    std::ifstream ifs(input_file, std::ios::binary);
    if (!ifs.is_open()) {
        std::cerr << "Error: Unable to open input file\n";
        return 1;
    }

    std::string input((std::istreambuf_iterator<char>(ifs)), std::istreambuf_iterator<char>());
    ifs.close();

    auto parser = [parser_path](const std::string& content) {
        return runExternalParser(parser_path, content);
    };

    std::string result = DDMax(input, parser);

    std::ofstream ofs(output_file, std::ios::binary);
    if (!ofs.is_open()) {
        std::cerr << "Error: Unable to open output file\n";
        return 1;
    }

    ofs << result;
    ofs.close();

    std::cout << "DDMax completed. Result saved to: " << output_file << "\n";
    return 0;
}