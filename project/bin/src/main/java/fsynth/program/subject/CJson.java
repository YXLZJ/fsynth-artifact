package fsynth.program.subject;

import fsynth.program.InputFormat;
import fsynth.program.Main;
import fsynth.program.subprocess.ProcessReturnValue;

import java.nio.file.Path;
import java.util.List;

@SubjectGroup(group = InputFormat.JSON)
public class CJson extends CSubject {

    public CJson() {
        super(Subjects.CJSON);
    }


    @Override
    Path sourceRootPath() {
        return Main.BIN.resolve("subjects").resolve("cjson");
    }

    @Override
    Path executablePath() {
        return sourceRootPath().resolve("cjson.cov");
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
    List<List<String>> getCompileCommandLine() {
        return List.of(
//                List.of("make", "clean"),
//                List.of("./configure", "CFLAGS=-fprofile-arcs -ftest-coverage", "--enable-gcov", "--disable-docs"),
                List.of("make")
        );
    }


    @Override
    String[] acceptedSuffixes() {
        return new String[]{
                "json",
                "geojson",
                "echart",
                "jsonld"
        };
    }
}
