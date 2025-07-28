package erepair.program.subject;

import erepair.program.subprocess.ProcessReturnValue;
import erepair.program.subprocess.SimpleSubprocess;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.OptionalLong;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

/**
 **/
public abstract class CSubject extends ShellSubject {

    /**
     * Instantiate a C or C++ subject that uses ./configure and make
     *
     * @param kind Subject kind
     */
    public CSubject(Subjects kind) {
        super(kind);
    }

    /**
     * Get the source root of the subject that contains the configure script and the makefile
     *
     * @return the source root
     */
    abstract Path sourceRootPath();

    /**
     * Get the executable path which must be a child of the {@link CSubject#sourceRootPath()}, e.g. ".../bin/jq"
     *
     * @return the executable path
     */
    abstract Path executablePath();

    @Override
    final ProcessBuilder getCommandLine(String file_to_open, String file_to_save, boolean coverage) {
        return null; // C Subjects cannot be generalized to superclass. Since we overwrite runSubject. we do not need this
    }

    @Override
    boolean acceptExitCode(int exitCode) {
        return exitCode == 0;
    }

    @Override
    final Path getCoverageFile() {
        return executablePath();//No coverage files are generated
    }


    @Override
    SubjectStatus runSubject(String file_to_open, String file_to_save) {
        tryCompile();
        return this.runSubject(file_to_open, file_to_save, false);
    }


    @Override
    SubjectStatus runSubjectWithCoverage(String file_to_open, String file_to_save) {
        tryCompile();
        return this.runSubject(file_to_open, file_to_save, false);
    }

    /**
     * Run the subject. Coverage has already been collected in the superclass
     *
     * @param file_to_open File to open
     * @param file_to_save File to save
     * @param coverage     True, if coverage is enabled
     * @return a reason for the fail
     */
    abstract SubjectStatus runSubject(String file_to_open, String file_to_save, boolean coverage);

    /**
     * Get all compile command lines needed to compile the program.
     *
     * @return all commands as list of a command with args
     */
    abstract List<List<String>> getCompileCommandLine();

    /**
     * Configure and compile the subject with gcov coverage enabled
     */
    @SuppressWarnings({"ObjectAllocationInLoop", "HardcodedFileSeparator"})
    final void compile() {
        final List<List<String>> cmdlines = getCompileCommandLine();
        for (final List<String> cmdline : cmdlines) {
            try {
                log(Level.INFO, "Running command " + String.join(" ", cmdline));
                final ProcessBuilder processBuilder = new ProcessBuilder(cmdline);
                processBuilder.directory(sourceRootPath().toFile()); // TODO Does not work for subjects that cd into a dir!!!!!!!!!
                final Process process = processBuilder.start();
                final ProcessReturnValue processReturnValue = SimpleSubprocess.runProcess(OptionalLong.of(600 * 1000), process);//10min timeout for compiling
                if (processReturnValue.returnValue != 0) {
                    throw new IOException("The return code was not zero! (was " + processReturnValue.returnValue + ") with output:\n" + processReturnValue.stdout + "\n\n" + processReturnValue.stderr);
                }
            } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
                log(Level.SEVERE, "Could not compile! Failed command: " + String.join(" ", cmdline) + "", e);
                throw new RuntimeException(e);
            }
        }
    }

    private void tryCompile() {
        if (!Files.isRegularFile(executablePath())) {
            log(Level.INFO, "The executable does not seem to exist at " + executablePath() + ". Attempting to compile it...");
            compile();
            if (!Files.isRegularFile(executablePath())) {
                log(Level.SEVERE, "Compilation failed, the executable does not exist at " + executablePath());
                throw new RuntimeException("Binary does not exist");
            }
        }
    }

    @Override
    boolean line_is_failure(String line) {
        return super.line_is_failure(line) || line.startsWith("parse error");
    }
}
