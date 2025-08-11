1) TARGET BADGE
Target badges: Available, Reviewed, Reproducible
- Available: This artifact includes all code, data samples, and scripts needed to run and inspect the system.
- Reviewed: The structure, usage, and design are documented and organized for evaluation.
- Reproducible: A Docker image and scripts are provided to reproduce the paper’s main results and tables.

2) INFO
- Paper title: Automatic Data Repair without Format Specifications
- Submission number: 26
- Contacts:
  - Zijian Luo, University of Sydney, Australia, lzij5923@uni.sydney.edu.au
  - Lukas Kirschner, Saarland University, Germany, kirschlu@gmail.com
  - Ezekiel Soremekun, Singapore University of Technology and Design, Singapore, ezekiel soremekun@sutd.edu.sg
  - Rahul Gopinath, University of Sydney, Australia, rahul.gopinath@sydney.edu.au

3) EXPECTED BEHAVIOUR
- Purpose: εREPAIR (erepair) repairs corrupted text files (e.g., JSON, INI, TinyC-like C, Lisp, and other regex/grammar-defined formats) with high efficiency while minimizing information loss.
- Scope: Works with formats for which a validation oracle (interpreter) is available. Oracles can be simple regex-based validators, or parsers generated from a formal grammar (e.g., ANTLR).
- Inputs:
  - interpreter: a command that returns exit status based on validity of the given file
  - inputfile: corrupted file to repair
  - outputfile: path to write the repaired result
- Output:
  - A repaired file saved to outputfile
  - When running evaluation scripts, additional metrics: BR/OR distance metrics, success counts, runtime statistics, and summary tables as in the paper.
- Command (inside the Docker container or a properly configured host):
  - erepair <interpreter> <inputfile> <outputfile>

4) ARTIFACT DESCRIPTION
Repository layout (key items):
- Dockerfile
  - Defines a Debian-based environment with Java 17, Python 3, C++ toolchain, CMake, and ANTLR4 C++ runtime; builds and installs εREPAIR.
- README.md
  - Additional human-readable overview of the artifact (duplicate of key points presented here in .txt for evaluation).
- Appendix.md
  - Supplementary documentation.
- artifacts/ (primary working directory inside the container)
  - bm_single.py: evaluation driver for single-mutation scenario
  - bm_multiple.py: evaluation driver for multiple/double-mutation scenario
  - bm_truncation.py: evaluation driver for truncation scenario
  - report.py: produces the tables/metrics reported in the paper
  - data_fetch.py: optional script to fetch additional datasets from GitHub by format
  - match.py: example/template regex-based interpreter (see README.md and section 6 below)
  - build_all.sh: builds native interpreter subjects used in evaluation
  - data/: contains example datasets (e.g., date, ipv4, etc.) and sample outputs
  - mutated_files/: example pre-built mutation databases (.db) for experiments
  - original_files/: original example corpora by format
  - project/: Gradle project that builds the erepair Java program (erepair.jar)
- Binaries and entrypoints:
  - /usr/bin/erepair is created by the Docker build. It ultimately points to a native C++ build (erepair2.cpp) for performance.

Notes:
- The working directory inside the container defaults to /home/repairer/artifacts.
- Gradle wrapper and dependencies are bundled to avoid external downloads at evaluation time; ANTLR runtime is built from source in the image.

5) ENVIRONMENT SETUP
Option A (Recommended): Docker
- Requirements: Docker 20.10+ (or compatible), 4 CPU cores and 8 GB RAM recommended for full experiments (2 cores / 4 GB for quick smoke tests).
- Build image:
  - docker build -t erepair .
- Run container:
  - docker run -it --rm -v "$(pwd)/outputs:/home/repairer/outputs" erepair
  - This launches a shell in /home/repairer/artifacts. The optional -v bind mounts a local outputs directory to persist results.
- Inside the container:
  - erepair is available on PATH
  - All interpreters and the jar are prebuilt during the image build process
  - Python 3 and required toolchains are installed

Option B: Native host setup (Linux recommended)
- Minimum: Linux x86_64, 4 CPU cores, 8 GB RAM recommended; Java 17, Python 3.8+, g++ (C++17), cmake, make
- ANTLR4 C++ runtime installed system-wide (as the Dockerfile does); or use the provided Docker to avoid host setup complexity
- Gradle 7.3.3 is included under artifacts/libs and used by the project build; ensure JAVA_HOME is set correctly

6) GETTING STARTED (≤ 30 minutes)
Quick verification via Docker (preferred):
1. Build and run the image:
   - docker build -t erepair .
   - docker run -it --rm erepair
   - You will drop into /home/repairer/artifacts
2. Basic functionality smoke test (using the regex-based interpreter template match.py):
   - Create a small test file:
     - echo "2025-08-11" > sample.txt
   - Repair with a simple date validator (full command is passed as a single argument to erepair):
     - erepair "python3 match.py Date" sample.txt repaired.txt
   - Check the output:
     - cat repaired.txt
   - Expected: If the input is already valid per the selected validator, the output will match or improve the input; for malformed inputs, a repaired variant is produced when possible.
3. Run a small evaluation example (bundled):
   - python3 bm_single.py
   - python3 bm_multiple.py
   - python3 bm_truncation.py
   Output artifacts and logs will be placed in the current directory (e.g., result databases and per-run outputs). These runs on the small bundled datasets complete quickly on a modern laptop.
4. Generate a quick report:
   - python3 report.py
   - This prints summary tables similar to those presented in the paper (BR/OR distances, success counts, runtimes).

Using your own small data (regex example):
- Edit match.py patterns or add a category (see README.md code snippet)
- Run:
  - erepair "python3 match.py <CategoryName>" input.txt output.txt

7) REPRODUCIBILITY (Paper claims and results)
This section details how to reproduce the tables and claims presented in the paper.
A. End-to-end reproduction (Docker)
1. Build and run the Docker image:
   - docker build -t erepair .
   - docker run -it --rm -v "$(pwd)/outputs:/home/repairer/outputs" erepair
   Inside the container, the working dir is /home/repairer/artifacts.
2. Build interpreter subjects (performed automatically in the Docker build). If re-running manually:
   - chmod +x artifacts/build_all.sh
   - ./artifacts/build_all.sh
3. Re-create or use provided mutation databases:
   - Provided quick-start:
     - python3 bm_single.py
     - python3 bm_multiple.py
     - python3 bm_truncation.py
   - Optional: generate your own datasets from GitHub by format:
     - python3 data_fetch.py
     - Then mutate:
       - python3 bm_single.py -i data/ -o single_fmt.db
       - python3 bm_multiple.py -i data/ -o multiple_fmt.db
       - python3 bm_truncation.py -i data/ -o truncation_fmt.db
     (Flags may vary; see the script -h if available.)
4. Produce all paper tables:
   - python3 report.py
   This prints:
   - General metrics tables (BR/OR distance metrics, averages, standard deviations, success counts)
   - Data survival analyses (OR/CR ratios)
   - Perfect repair counts
   - Efficiency (average runtime per algorithm)
   The output will resemble the example included in README.md. Differences due to environment or randomness should be minor and within expected variance.
5. Validating key claims:
   - εREPAIR achieves lower BR/OR distances than baselines on the evaluated corpora
   - εREPAIR shows competitive success counts and a high surviving data ratio
   - εREPAIR demonstrates practical performance; average runtime is reported per scenario
   Run report.py and compare aggregates to the shown example outputs printed by the script (included in README.md).

B. Direct usage claim reproduction
- Given an interpreter (regex- or grammar-based), εREPAIR repairs corrupted inputs:
  - erepair "<interpreter command>" <inputfile> <outputfile>
- Regex interpreter (example):
  - python3 match.py Date input.txt; use exit codes (0 full match, -1 partial, 1 no match)
  - erepair "python3 match.py Date" corrupted.txt repaired.txt
- Grammar interpreter (ANTLR example):
  - See artifacts/project/erepair-subjects/obj for an example of grammar-based validation
  - Additional grammars can be adapted from https://github.com/antlr/grammars-v4

C. Notes on runtime and resources
- Full-scale experiments (as in the paper) may take longer depending on CPU core count; the provided quick-start runs and bundled datasets complete faster
- For strict reproduction, we recommend dedicating at least 4 cores and 8 GB RAM; more cores further improve throughput

Appendix: Operational details
- erepair tool resolution in Docker:
  - Initially installed as a Java wrapper, then re-deployed and replaced with a native C++ build for performance:
    - /usr/bin/erepair invokes the native binary compiled from artifacts/erepair2.cpp
- Default container workspace:
  - /home/repairer/artifacts
- Persistence:
  - Bind-mount a host directory if you want results outside the container:
    - docker run -it --rm -v "$(pwd)/outputs:/home/repairer/outputs" erepair
- Troubleshooting:
  - Verify java --version and erepair --help inside the container
  - If building natively, ensure ANTLR4 C++ runtime is installed and visible to the compiler and linker
