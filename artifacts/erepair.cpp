#include <iostream>
#include <queue>
#include <string>
#include <vector>
#include <functional>
#include <fstream>
#include <cctype>
#include <set>
#include <cstdlib>
#include <random>
#include <stdexcept>
#include <math.h>   
#include <unistd.h>    // for close(), getpid()
#include <fcntl.h>     // for mkstemp
#include <stdio.h>     // for mkstemp
#include <sys/wait.h>  // for WIFEXITED, WEXITSTATUS

int interations = 0;
int success = 0;
int failure = 0;
int incomplete = 0;
//-------------------------------------
// 0. CharacterSet
//-------------------------------------
class CharacterSet {
private:
    std::set<char> valid_chars;

public:
    CharacterSet() {
        initializeDefault();
    }

    void initializeDefault() {
        valid_chars.clear();

        std::vector<char> chars = { ')', '}', ']' };

        for(auto c: chars){
            valid_chars.insert(c);
        }

        for (int i = 33; i <= 126; ++i) {
            char c = static_cast<char>(i);
            if (std::find(chars.begin(), chars.end(), c) 
                == chars.end()) 
            {
                valid_chars.insert(c);
            }
        }

        valid_chars.insert('\n');
        valid_chars.insert('\t');
    }

    std::set<char>::iterator begin() { return valid_chars.begin(); }
    std::set<char>::iterator end() { return valid_chars.end(); }
};

//-------------------------------------
// 1. ParseResult enum class
//-------------------------------------
enum class ParseResult { INCOMPLETE, CORRECT, INCORRECT };

//-------------------------------------
// Generate a unique temporary filename and create the file
// Using mkstemp() ensures there is no conflict with existing files
//-------------------------------------
std::string generateTempFile()
{
    // "XXXXXX" will be replaced by mkstemp() with a unique string
    char pattern[] = "/tmp/parser_inputXXXXXX";
    int fd = mkstemp(pattern);
    if (fd == -1) {
        throw std::runtime_error("Failed to create temp file");
    }
    // We only create the file here and get the filename, then close the fd
    close(fd);
    return std::string(pattern);
}

//-------------------------------------
// 2. External parser returning ParseResult
//    Uses a unique temporary file name to avoid concurrency conflicts
//-------------------------------------
std::function<ParseResult(const std::string&)> createParser(const std::string& parser_path) {
    return [parser_path](const std::string& input) -> ParseResult {
        // Generate a unique temporary file
        std::string temp_file;
        try {
            temp_file = generateTempFile();
        } catch (const std::exception& e) {
            std::cerr << "Error: " << e.what() << "\n";
            return ParseResult::INCORRECT;
        }

        // Write the input to the temporary file
        {
            std::ofstream temp_out(temp_file);
            if (!temp_out.is_open()) {
                std::cerr << "Error: Could not create temporary file." << std::endl;
                return ParseResult::INCORRECT;
            }
            temp_out << input;
        }
        interations++;
        // Call the external parser
        // parser_path + " " + temp_file + " > /dev/null 2>&1"
        std::string command = parser_path + " " + temp_file + " > /dev/null 2>&1";
        int status = system(command.c_str());

        ParseResult result = ParseResult::INCORRECT;
        if (WIFEXITED(status)) {
            int exit_code = WEXITSTATUS(status);
            if (exit_code == 0) {
                success++;
                result = ParseResult::CORRECT;
            } else if (exit_code == 1) {
                failure++;
                result = ParseResult::INCORRECT;
            } else if (exit_code == 255) {
                incomplete++;
                result = ParseResult::INCOMPLETE;
            } else {
                failure++;
                // Handle more exit codes if needed
                result = ParseResult::INCORRECT;
            }
        }

        // Remove the temporary file when done to avoid leftovers
        std::remove(temp_file.c_str());
        return result;
    };
}

//-------------------------------------
// 3. BSearch function
//-------------------------------------
int BSearch(const std::string& s,
            const std::function<ParseResult(const std::string&)>& parser,
            int left = 0) {
    int right = static_cast<int>(s.size());
    // If the entire string is not INCORRECT, return directly
    if (parser(s.substr(0, right)) != ParseResult::INCORRECT) return right;

    // Binary search for boundary
    while (left < right - 1) {
        int middle = (left + right) / 2;
        if (parser(s.substr(0, middle)) != ParseResult::INCORRECT) {
            left = middle;
        } else {
            right = middle;
        }
    }

    return left;
}

//-------------------------------------
// 4. DRepair function
//-------------------------------------
std::string DRepair(const std::string& input,
                    const std::function<ParseResult(const std::string&)>& parser) {
    struct State {
        std::string str;        // current string
        int boundary;           // current boundary
        int editingDistance;    // accumulated editing distance (lower is higher priority)

        bool operator>(const State& other) const {
            return editingDistance > other.editingDistance;
        }
    };

    // Min-heap, with smaller editingDistance having higher priority
    std::priority_queue<State, std::vector<State>, std::greater<State>> pq;

    // Initial boundary
    int boundary = BSearch(input, parser, 0);
    pq.push({input, boundary, 0});

    CharacterSet valid_chars;

    while (!pq.empty()) {
        State current = pq.top();
        pq.pop();
        std::cout << "Dealing with current string:\n" << current.str << "\n\n";        // If the entire string is CORRECT, return directly
        if (parser(current.str) == ParseResult::CORRECT) {
            return current.str;
        }

        // 1) Try deleting the character at the boundary
        if (current.boundary < static_cast<int>(current.str.size())) {
            std::string new_str = current.str;
            new_str.erase(current.boundary, 1);
            if (parser(new_str)== ParseResult::CORRECT){
                return new_str;
            }
            int new_boundary = BSearch(new_str, parser);

            if(new_boundary - current.boundary > 0){
                // Believe this corruption has been healed, handling next corruption
                std::priority_queue<State, std::vector<State>, std::greater<State>> empty;
                pq.swap(empty);
                pq.push({new_str, new_boundary, current.editingDistance + 1});
                continue;
            }
            pq.push({new_str, new_boundary, current.editingDistance + 1});
        }

        // 2) Try inserting various valid characters at the boundary
        bool flag = false;
        bool all_accepted = true;
        for (char c : valid_chars) {
            std::string new_str = current.str;
            new_str.insert(current.boundary, 1, c);

            if(parser(new_str) == ParseResult::CORRECT){
                return new_str;
            }
            int new_boundary = BSearch(new_str, parser);
            if (new_boundary - current.boundary > 1) {
                // Believe this corruption has been healed, handling next corruption 
                std::priority_queue<State, std::vector<State>, std::greater<State>> empty;
                pq.swap(empty);
                pq.push({new_str, new_boundary, current.editingDistance + 1});
                break;
            } else if (new_boundary - current.boundary == 1) {
                pq.push({new_str, new_boundary, current.editingDistance + 1});
                if(c!='\n' && c!='\t'){
                    flag = true;
                }
            } else {
                all_accepted = false;
                continue;
            }
        }
        if (!flag) {
            if(parser(current.str.substr(0, current.boundary)) == ParseResult::CORRECT){
                return current.str.substr(0, current.boundary);
            }
        }
        if (all_accepted && current.boundary == current.str.size()) {
            std::cout<<"All accepted"<<std::endl;
            std::string temp  = current.str;
            for(int i=33;i<=126;i++){
                char c = static_cast<char>(i);
                temp.push_back(c);
            }
            temp.push_back('a'); //watchman
            int temp_boundary = BSearch(temp, parser);
            if(temp_boundary!=temp.size()-1){

                char c = temp[temp_boundary-1];
                // std::cout<<"c: "<<c<<std::endl;
                // std::cout<<temp<<std::endl;
                current.str.push_back(c);
                int new_boundary = BSearch(current.str, parser);
                pq.push({current.str, new_boundary, current.editingDistance-1}); // priority is not increased
                // std::priority_queue<State, std::vector<State>, std::greater<State>> empty;
                // pq.swap(empty);
                // pq.push({current.str, new_boundary, current.editingDistance + 1});
            } 
        }
    }

    // No feasible solution found
    return "";
}

//-------------------------------------
// 5. Main function
//-------------------------------------
int main(int argc, char* argv[]) {
    if (argc < 4) {
        std::cerr << "Usage: " << argv[0] << " <parser_path> <input_file> <output_file>\n";
        return 1;
    }

    std::string parser_path    = argv[1];
    std::string input_filename = argv[2];
    std::string output_filename= argv[3];

    std::ifstream input_file(input_filename);
    if (!input_file.is_open()) {
        std::cerr << "Error: Could not open file " << input_filename << std::endl;
        return 1;
    }

    // Read the file contents into a string
    std::string input((std::istreambuf_iterator<char>(input_file)), std::istreambuf_iterator<char>());
    input_file.close();

    // Create the parser and run DRepair
    auto parser = createParser(parser_path);
    std::string result = DRepair(input, parser);

    if (!result.empty()) {
        std::ofstream out_file(output_filename);
        if (!out_file.is_open()) {
            std::cerr << "Error: Could not open output file " << output_filename << std::endl;
            return 1;
        }
        out_file << result;
        out_file.close();

        std::cout << "After repair:\n" << result << "\n";
        std::cout << "Repaired string saved to: " << output_filename << std::endl;
    } else {
        std::cout << "No valid repair found." << std::endl;
    }
    printf("*** Number of required oracle runs: %d correct: %d incorrect: %d incomplete: %d ***\n", interations, success, failure, incomplete);
    return 0;
}