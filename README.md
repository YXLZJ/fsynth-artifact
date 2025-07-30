# ϵREPAIR

## Artifact repository

This repository is the artifact repository of ϵREPAIR.

***ϵREPAIR is an algorithm we proposed to help users repair corrupted text files, such as JSON, INI, TinyC, etc., even when the exact format is unknown. It ensures high efficiency while minimizing information loss as much as possible.***

## Prerequisites
During the experiments, we aimed to minimize dependencies on third-party packages or libraries in the artifacts. For most machines with Java, C++, and Python development toolchains installed, the artifacts should run out of the box. If you have security or other concerns, we also provide a Docker image.
You can build the Docker image with the following command:
> `docker build -t erepair .`
> `docker run -it --rm erepair`

This will start a container with the name `erepair`, all relevant software built and installed, and the working directory set to `/home/repairer`. 

## Usage
If you just want to use ϵREPAIR, you can run the `erepair` command directly in the container. The command is as follows:
> `./erepair <interpreter> <inputfile> <outputfile>`

Where `<interpreter>` is the path to the validation tool, `<inputfile>` is the file you want to repair, and `<outputfile>` is the file where you want to save the repaired content.

### Evaluation

Once you have been installed the docker image and started the container, you can run the evaluation script to reproduce the results in the paper without any additional setup. Please using following commands:

#### Evaluation quick start with the provided dataset

###### Single mutation

> `python3 bm_multiple.py`

###### double mutation

> `python3 bm_double.py`

###### Truncation mutation

> `python3 bm_truncation.py`

#### Evaluation with your own dataset
Custom datasets can also be used to evaluate the performance of ϵREPAIR. These datasets may follow any format, such as `JSON`, `INI`, or `TinyC`. The only requirement is the availability of a corresponding validation tool for the selected format; see the section `Build your own ‘interpreter’ as oracle validation tool` for details.

The `data_fetch.py` script enables automatic downloading of files in specific formats from GitHub, storing them in the `data` directory.

Then you can mutate them with the following command:
> `python3 bm_single.py -i data/ -o single_fmt.db`
> `python3 bm_double.py -i data/ -o double_fmt.db`
> `python3 bm_truncation.py -i data/ -o truncation_fmt.db`

Then you can run the evaluation script as the instruction above!

##### Build your own "interpreter" as oracle validation tool

All validation tools in this experimentare compiled automatically once you build the docker image. However if you want make ϵREPAIR works in more formats, you have to build oracle validation tools by you own. Don't be panic, here is how you should do:

You can repair files whose structure follows regular expression-defined grammar. Using the regex library and the template provided below, you can create a custom validation tool. This python script reads the content of a file and checks it against a given regular expression pattern. It returns 0 if the content fully matches the pattern, -1 for partial matches, and 1 if the content does not match at all.

```python 
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
```

Save it as match.py, then, you can use the full command to run this file as the interpreter path for erepair.

> `./erepair 'python3 match.py <category>' <inputfile> <outputfile>`

If the grammar is defined by Antlr, you can use the Antlr4 tool to generate the parser and lexer, then compile them into a validation tool. here is an [example](artifacts/project/erepair-subjects/obj), more grammars can be found in this [Github Repo](https://github.com/antlr/grammars-v4).

#### Obtain statistical report

You can obtain all tables in the paper with one command:

> `python3 report.py`

Example:

```
erepair-artifact % python '/Users/jack/erepair-artifact/report.py'
----------------Table 4-5(General)------------------------------------

Processing database: multiple.db

Metrics for result1_multiple.db
----------------------------------------------------------------------------------------------------------------------------------
Format     Algorithm  Avg BR     Stdev BR   Avg OR     Stdev OR   Avg Time     Stdev Time   Successes  Total     
----------------------------------------------------------------------------------------------------------------------------------
ini        DDMax      2.99       3.36       4.84       3.04       0.49         0.19         100        100       
ini        erepair    1.52       0.85       3.52       0.85       0.70         0.90         100        100       
ini        DDMaxG     26.59      6.82       28.22      6.75       0.36         0.01         100        100       
ini        Antlr      24.84      5.73       26.64      5.74       0.31         0.01         91         100       
json       DDMax      42.95      52.10      43.68      51.62      10.85        13.23        99         100       
json       erepair    7.04       24.29      7.82       24.22      4.81         10.78        98         100       
json       DDMaxG     50.14      32.59      50.66      32.39      0.92         1.54         98         100       
json       Antlr      39.97      27.01      40.62      26.97      0.31         0.01         68         100       
lisp       DDMax      10.89      16.72      12.48      16.34      1.58         2.43         100        100       
lisp       erepair    11.97      19.49      13.29      19.28      1.91         4.75         94         100       
lisp       DDMaxG     39.49      27.34      39.32      27.42      5.29         5.55         100        100       
lisp       Antlr      0.00       0.00       0.00       0.00       0.32         0.01         0          100       
c          DDMax      27.56      15.39      27.56      15.39      3.23         2.91         98         100       
c          erepair    6.64       5.15       6.52       5.20       9.14         8.04         100        100       
c          DDMaxG     27.33      12.53      27.06      12.52      2.59         3.95         98         100       
c          Antlr      20.71      10.83      20.36      10.66      0.32         0.01         28         100       

Processing database: result1_prefix.db

Metrics for result1_prefix.db
----------------------------------------------------------------------------------------------------------------------------------
Format     Algorithm  Avg BR     Stdev BR   Avg OR     Stdev OR   Avg Time     Stdev Time   Successes  Total     
----------------------------------------------------------------------------------------------------------------------------------
ini        DDMax      2.07       1.92       30.59      16.17      0.41         0.06         100        100       
ini        erepair    1.00       0.00       28.14      15.89      0.41         0.17         100        100       
ini        DDMaxG     18.60      5.04       45.45      15.64      0.35         0.02         100        100       
ini        Antlr      17.80      4.62       44.65      15.03      0.30         0.02         100        100       
json       DDMax      74.32      29.59      111.93     35.34      12.34        9.93         90         100       
json       erepair    3.26       1.47       35.12      23.09      29.29        27.17        82         100       
json       DDMaxG     83.18      34.64      121.76     41.62      5.44         3.20         100        100       
json       Antlr      0.00       0.00       0.00       0.00       0.32         0.02         0          100       
lisp       DDMax      22.24      18.66      40.13      23.95      2.54         2.64         100        100       
lisp       erepair    1.77       0.42       15.67      11.38      8.86         14.19        39         100       
lisp       DDMaxG     35.16      22.08      51.12      27.13      3.13         3.50         100        100       
lisp       Antlr      0.00       0.00       0.00       0.00       0.29         0.02         0          100       
c          DDMax      22.34      8.98       36.60      13.41      1.56         1.53         77         100       
c          erepair    1.88       0.77       7.77       7.59       20.66        43.04        82         100       
c          DDMaxG     28.01      9.70       39.08      11.71      3.60         3.08         77         100       
c          Antlr      0.00       0.00       0.00       0.00       0.28         0.01         0          100       

Processing database: result1.db

Metrics for result1.db
----------------------------------------------------------------------------------------------------------------------------------
Format     Algorithm  Avg BR     Stdev BR   Avg OR     Stdev OR   Avg Time     Stdev Time   Successes  Total     
----------------------------------------------------------------------------------------------------------------------------------
c          DDMax      9.17       13.51      9.17       13.51      1.35         1.88         984        1000      
c          erepair    4.16       6.51       4.10       6.54       4.39         5.05         1000       1000      
c          DDMaxG     25.14      11.71      24.99      11.75      1.49         3.22         984        1000      
c          Antlr      21.77      10.42      21.44      10.40      0.32         0.01         481        1000      
json       DDMax      25.96      43.71      26.15      43.63      6.87         10.47        971        1000      
json       erepair    5.15       18.95      5.27       19.01      5.00         13.87        999        1000      
json       DDMaxG     48.51      31.45      48.45      31.49      0.95         1.67         982        1000      
json       Antlr      40.47      24.30      40.37      24.34      0.31         0.01         703        1000      
ini        DDMax      2.46       3.04       3.34       2.73       0.43         0.12         1000       1000      
ini        erepair    1.42       0.78       2.42       0.78       0.62         0.88         1000       1000      
ini        DDMaxG     26.62      6.41       27.33      6.36       0.36         0.03         1000       1000      
ini        Antlr      24.92      5.57       25.76      5.59       0.31         0.03         884        1000      
lisp       DDMax      7.63       14.69      8.55       14.54      1.07         1.96         1000       1000      
lisp       erepair    10.31      19.08      11.02      19.22      0.97         1.52         966        1000      
lisp       DDMaxG     36.87      27.23      37.35      26.97      4.85         5.04         1000       1000      
lisp       Antlr      0.00       0.00       0.00       0.00       0.31         0.01         0          1000      

Combined Metrics Across All Databases
----------------------------------------------------------------------------------------------------------------------------------
Format     Algorithm  Avg BR     Stdev BR   Avg OR     Stdev OR   Avg Time     Stdev Time   Successes  Total     
----------------------------------------------------------------------------------------------------------------------------------
ini        DDMax      2.47       3.00       5.73       9.23       0.43         0.13         1200       1200      
ini        erepair    1.39       0.77       4.65       8.48       0.61         0.85         1200       1200      
ini        DDMaxG     25.95      6.72       28.91      9.10       0.36         0.03         1200       1200      
ini        Antlr      24.25      5.88       27.59      8.91       0.31         0.03         1075       1200      
json       DDMax      31.16      45.59      34.30      49.48      7.66         10.84        1160       1200      
json       erepair    5.18       18.81      7.56       21.20      7.01         16.63        1179       1200      
json       DDMaxG     51.59      33.25      54.84      38.39      1.32         2.22         1180       1200      
json       Antlr      40.43      24.55      40.39      24.58      0.31         0.02         771        1200      
lisp       DDMax      9.12       15.76      11.51      17.94      1.24         2.11         1200       1200      
lisp       erepair    10.14      18.85      11.38      19.03      1.71         5.03         1099       1200      
lisp       DDMaxG     36.94      26.86      38.66      27.28      4.74         5.00         1200       1200      
lisp       Antlr      0.00       0.00       0.00       0.00       0.31         0.01         0          1200      
c          DDMax      11.60      14.65      12.55      15.94      1.53         2.03         1159       1200      
c          erepair    4.21       6.25       4.56       6.61       6.14         14.21        1182       1200      
c          DDMaxG     25.52      11.70      26.10      12.33      1.76         3.34         1159       1200      
c          Antlr      21.71      10.45      21.38      10.42      0.32         0.02         509        1200      
----------------Table 4-5(Data Survive)-------------------------------

 Surviving Data Ratio (OR)

Results for result1_multiple.db:
----------------------------------------------------------------------
Algorithm       Avg Surviving Ratio  Stdev           Count     
----------------------------------------------------------------------
DDMax           0.7272               0.2999          397       
erepair         0.9136               0.1880          392       
DDMaxG          0.7886               0.2198          396       
Antlr           0.9000               0.1192          187       

Results for result1_prefix.db:
----------------------------------------------------------------------
Algorithm       Avg Surviving Ratio  Stdev           Count     
----------------------------------------------------------------------
DDMax           0.4283               0.2334          367       
erepair         0.7752               0.1419          303       
DDMaxG          0.4452               0.2469          377       
Antlr           0.7408               0.1323          100       

Results for result1.db:
----------------------------------------------------------------------
Algorithm       Avg Surviving Ratio  Stdev           Count     
----------------------------------------------------------------------
DDMax           0.8634               0.2279          3955      
erepair         0.9370               0.1645          3965      
DDMaxG          0.8201               0.2098          3966      
Antlr           0.9117               0.1284          2068      

Overall Results Across All Databases (OR):
----------------------------------------------------------------------
Algorithm       Avg Surviving Ratio  Stdev           Count     
----------------------------------------------------------------------
DDMax           0.8181               0.2638          4719      
erepair         0.9245               0.1700          4660      
DDMaxG          0.7876               0.2365          4739      
Antlr           0.9035               0.1324          2355      

 Surviving Data Ratio (CR)

Results for result1_multiple.db:
----------------------------------------------------------------------
Algorithm       Avg Surviving Ratio  Stdev           Count     
----------------------------------------------------------------------
DDMax           0.7272               0.2999          397       
erepair         0.9136               0.1880          392       
DDMaxG          0.7887               0.2200          396       
Antlr           0.9001               0.1192          187       

Results for result1_prefix.db:
----------------------------------------------------------------------
Algorithm       Avg Surviving Ratio  Stdev           Count     
----------------------------------------------------------------------
DDMax           0.5825               0.2841          367       
erepair         1.0000               0.0000          303       
DDMaxG          0.5985               0.2933          377       
Antlr           0.9941               0.0092          100       

Results for result1.db:
----------------------------------------------------------------------
Algorithm       Avg Surviving Ratio  Stdev           Count     
----------------------------------------------------------------------
DDMax           0.8634               0.2279          3955      
erepair         0.9370               0.1645          3965      
DDMaxG          0.8200               0.2099          3966      
Antlr           0.9118               0.1284          2068      

Overall Results Across All Databases (CR):
----------------------------------------------------------------------
Algorithm       Avg Surviving Ratio  Stdev           Count     
----------------------------------------------------------------------
DDMax           0.8301               0.2529          4719      
erepair         0.9391               0.1622          4660      
DDMaxG          0.7998               0.2266          4739      
Antlr           0.9143               0.1261          2355      
----------------Table 4-5(levenshtein distances)----------------------

Overall Distance Metrics Across All Databases
--------------------------------------------------------------------------------
Algorithm       Avg BR     Stdev BR   Avg OR     Stdev OR  
--------------------------------------------------------------------------------
DDMax           13.46      27.25      15.90      29.75     
erepair         5.13       13.90      6.95       15.37     
DDMaxG          35.01      24.80      37.15      27.19     
Antlr           29.00      17.36      30.44      17.65     
----------------Table 6(Count repaired)-------------------------------

Results for result1_multiple.db:
----------------------------------------
Algorithm       Fixed Count    
----------------------------------------
Antlr           187            
DDMax           397            
DDMaxG          396            
erepair         392            

Results for result1_prefix.db:
----------------------------------------
Algorithm       Fixed Count    
----------------------------------------
Antlr           100            
DDMax           367            
DDMaxG          377            
erepair         303            

Results for result1.db:
----------------------------------------
Algorithm       Fixed Count    
----------------------------------------
Antlr           2068           
DDMax           3955           
DDMaxG          3966           
erepair         3965           

Total Fixed Files Across All Databases:
----------------------------------------
Algorithm       Fixed Count    
----------------------------------------
Antlr           2355           
DDMax           4719           
DDMaxG          4739           
erepair         4660           
----------------Table 7(Perfectly repaire)----------------------------
Perfect Repairs by Algorithm in result1_multiple.db:
  No perfect repairs found.
Perfect Repairs by Algorithm in result1_prefix.db:
  Format: c, Algorithm: erepair, Perfect Repairs: 12
  Format: json, Algorithm: erepair, Perfect Repairs: 2
  Format: lisp, Algorithm: erepair, Perfect Repairs: 7
Perfect Repairs by Algorithm in result1.db:
  Format: c, Algorithm: erepair, Perfect Repairs: 51
  Format: json, Algorithm: erepair, Perfect Repairs: 23
----------------Table 8(Efficiency)-----------------------------------

Average Runtime for result1_multiple.db (Successful Repairs Only):
--------------------------------------------------
Algorithm       Avg Time (s)    Count     
--------------------------------------------------
Antlr           0.32            400       
DDMax           4.04            400       
DDMaxG          2.29            400       
erepair         4.14            400       

Average Runtime for result1_prefix.db (Successful Repairs Only):
--------------------------------------------------
Algorithm       Avg Time (s)    Count     
--------------------------------------------------
Antlr           0.30            400       
DDMax           4.21            400       
DDMaxG          3.13            400       
erepair         14.81           400       

Average Runtime for result1.db (Successful Repairs Only):
--------------------------------------------------
Algorithm       Avg Time (s)    Count     
--------------------------------------------------
Antlr           0.31            4000      
DDMax           2.43            4000      
DDMaxG          1.91            4000      
erepair         2.75            4000      

Overall Average Runtime Across All Databases:
--------------------------------------------------
Algorithm       Avg Time (s)    Count     
--------------------------------------------------
Antlr           0.31            4800      
DDMax           2.71            4800      
DDMaxG          2.05            4800      
erepair         3.87            4800      
Algorithm      Total Count    Average Iterations  
--------------------------------------------------
Antlr          4800           1.00                
DDMax          4800           786.73              
DDMaxG         4800           569.04              
erepair        4660           897.31              
```
