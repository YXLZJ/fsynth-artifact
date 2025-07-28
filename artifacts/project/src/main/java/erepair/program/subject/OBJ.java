package erepair.program.subject;

import erepair.program.InputFormat;
import erepair.program.Main;
import erepair.program.subprocess.ProcessReturnValue;

import java.nio.file.Path;
import java.util.List;

@SubjectGroup(group = InputFormat.WAVEFRONT)
public class OBJ extends CSubject {

    public OBJ() {
        super(Subjects.WAVWFRONTOBJ);
    }



    @Override
    Path sourceRootPath() {
        return Main.BIN.resolve("subjects").resolve("obj");
    }

    @Override
    Path executablePath() {
        return sourceRootPath().resolve("build").resolve("obj_parser");
    }

    SubjectStatus runSubject(String file_to_open, String file_to_save, boolean coverage) {
        ProcessBuilder process;
        //, file_to_save//We save the process output instead
        process = new ProcessBuilder(
                executablePath().toString(), file_to_open
        );
        process.redirectErrorStream(true);
        ProcessReturnValue output;
        return super.runShellSubject(process);
    }

    /**
     * We override this method to make sure to detect incomplete inputs!
     *
     * @param exitValue Exit value. -1 (i.e. 255) is expected to be incomplete, 1 to be incorrect
     * @return the subject status
     */
    @Override
    SubjectStatus reportExitCode(int exitValue) {
        if (exitValue == 255) {
            return SubjectStatus.INCOMPLETE;
        } else if (exitValue == 1) {
            return new SubjectStatus("Reported an incorrect input with exit code " + exitValue);
        } else {
            return super.reportExitCode(exitValue);
        }
    }

    @Override
    String validateResult(String processOutput) {
        return null; // Discard everything the program outputs, except for the exit code
    }

    @Override
    boolean acceptSuccessOnly() {
        return false; // Only consider the exit code as status report
    }
    @Override
    boolean acceptEmptyOutput() {
        return true; // dot_parser uses exit code only, accept empty stdout
    }

    @Override
    List<List<String>> getCompileCommandLine() {
        return List.of(
//               
             List.of("cmake", "-B", "build", "-S","."),
             //cmake --build build -j
            List.of("cmake", "--build", "build", "-j")

        );
    }


    @Override
    String[] acceptedSuffixes() {
        return new String[]{
                "obj",
        };
    }
}
