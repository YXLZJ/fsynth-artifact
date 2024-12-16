import difflib
import sys
import os
from pathlib import Path

assert len(sys.argv) == 6
_, mode,subject,mutated_filename, test_out_folder, fileformat = sys.argv
os.makedirs("_difffiles_ddmax/",exist_ok=True)
os.makedirs("_difffiles_brepair/",exist_ok=True)

def build_ddmax_diff_if_necessary(mutated_file, ddmax_output, diff_file):
    try:
        if os.path.isfile(diff_file):
            return diff_file
        if not os.path.isfile(mutated_file):
            print(f"The mutated file at {mutated_file} did not exist!",file=sys.stderr)
            return None
        if not os.path.isfile(ddmax_output):
            print(f"The algorithm output at {ddmax_output} did not exist!",file=sys.stderr)
            return None
        with open(mutated_file,"r",encoding="utf-8") as mut:
            c_mut = mut.read()
        with open(ddmax_output, "r", encoding="utf-8") as dd:
            c_ddm = dd.read()
        d_gen = difflib.ndiff(c_mut,c_ddm)
        ret = ""
        for character in d_gen:
            if character[0] == "-":
                ret += character[-1]
        with open(diff_file,"w",encoding="utf-8") as dif:
            dif.write(ret)
        return diff_file
    except UnicodeDecodeError as e:
        print(e, file=sys.stderr)
        return None

filename = os.path.basename(mutated_filename)
ddmax_output_file   = Path(test_out_folder, fileformat + "DDMax-"   + Path(mutated_filename).parent.name, filename + "-" + subject).as_posix()
brepair_output_file = Path(test_out_folder, fileformat + "bRepair-" + Path(mutated_filename).parent.name, filename + "-" + subject).as_posix()
ddmax_diff_file     = build_ddmax_diff_if_necessary(mutated_filename,ddmax_output_file,   "_difffiles_ddmax/"   + os.path.basename(ddmax_output_file))
brepair_diff_file   = build_ddmax_diff_if_necessary(mutated_filename,brepair_output_file, "_difffiles_brepair/" + os.path.basename(brepair_output_file))
ddmin_output_file   = Path(test_out_folder, fileformat + "DDMin-"   + Path(mutated_filename).parent.name, filename + "-" + subject).as_posix()


if mode == "1": # Size of the ddmin-created file
    print(os.path.getsize(ddmin_output_file))
    sys.exit(0)
elif mode == "2": # Size of the ddmax-diff
    print(os.path.getsize(ddmax_diff_file))
    sys.exit(0)
elif mode == "3": # Size of the ddmax output
    print(os.path.getsize(ddmax_output_file))
    sys.exit(0)
elif mode == "4": # How much percent of the DDMax Diff is contained in DDMin?
    path1 = ddmax_diff_file
    path2 = ddmin_output_file
elif mode == "5": # How much percent of DDMin is contained in the DDMax Output?
    path1 = ddmin_output_file
    path2 = ddmax_output_file
elif mode == "6": # Size of the brepair-diff
    print(os.path.getsize(brepair_diff_file))
    sys.exit(0)
elif mode == "7": # How much percent of the bRepair diff is contained in ddmin?
    path1 = brepair_diff_file
    path2 = ddmin_output_file
elif mode == "8": # How much percent of ddmin is contained in the bRepair output?
    path1 = ddmin_output_file
    path2 = brepair_output_file
elif mode == "9": # How much percent of the bRepair diff is contained in the ddmax diff?
    path1 = brepair_diff_file
    path2 = ddmax_diff_file
elif mode == "10": # How much percent of the ddmax diff is contained in the bRepair diff?
    path1 = ddmax_diff_file
    path2 = brepair_diff_file
elif mode == "11": # How much percent of the bRepair diff is contained in the ddmax output?
    path1 = brepair_diff_file
    path2 = ddmax_output_file
elif mode == "12": # How much percent of the ddmax diff is contained in the bRepair output?
    path1 = ddmax_diff_file
    path2 = brepair_output_file

if not os.path.isfile(path1):
    print("The diff at " + path1 + " did not exist!")
    sys.exit(1)
if not os.path.isfile(path2):
    print("The diff at " + path2 + " did not exist!")
    sys.exit(1)
with open(path1,"r",encoding="utf8") as file1:
    filecontent1 = file1.read()
with open(path2,"r",encoding="utf8") as file2:
    filecontent2 = file2.read()
print('{:.10f}'.format(difflib.SequenceMatcher(lambda x: False, filecontent1, filecontent2).ratio(),5))
