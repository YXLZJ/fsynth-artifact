package erepair.program.subject;

import erepair.program.Main;
import erepair.program.subprocess.ProcessReturnValue;
import erepair.program.subprocess.SimpleSubprocess;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalLong;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

/**
 **/
public abstract class ShellSubject extends Subject {
    /**
     * The path of the jar file of this program itself
     */
    protected final File jarfilepath;

    /**
     * Instantiate a ShellSubject and all related paths
     *
     * @param kind Subject kind
     */
    public ShellSubject(Subjects kind) {
        super(kind);
        try {
            jarfilepath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
//            JACOCOPATH = jarfilepath.toPath().getParent().resolve("lib").resolve("org.jacoco.agent-0.8.5.jar");
//            JACOCOFILE = jarfilepath.toPath().getParent().resolve("tmp.exec");//UODO Create tmpfile instead?
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Run the oracle
     *
     * @param file_to_open File to open
     * @param file_to_save File to save
     * @return true, if oracle succeeds (if it loads a non-empty model)
     */
    @Override
    SubjectStatus runSubject(String file_to_open, String file_to_save) {
        return runShellSubject(getCommandLine(file_to_open, file_to_save, false));
    }

    @Override
    SubjectStatus runSubjectWithCoverage(String file_to_open, String file_to_save) {
        return runShellSubject(getCommandLine(file_to_open, file_to_save, true));
    }

    /**
     * Get the command line to be run. Example implementation:
     * <pre>{@code
     * ProcessBuilder process;
     * if (file_to_save == null) {
     *     process = new ProcessBuilder(
     *             blendercommand, "--python", "blender-convert-nooutputfile.py", "--background", "--", file_to_open//,tosave
     *     );
     * } else {
     *     process = new ProcessBuilder(
     *             blendercommand, "--python", "blender-convert-nooutputfile.py", "--background", "--", file_to_open, file_to_save
     *     );
     * }
     * process.redirectErrorStream(true);
     * return process;
     * }</pre>
     *
     * @param file_to_open File to open
     * @param file_to_save File to save
     * @param coverage     If true, collect coverage during the test run
     * @return
     */
    abstract ProcessBuilder getCommandLine(String file_to_open, String file_to_save, boolean coverage);

    /**
     * Checks if the given line is a success, i.e. if the whole run should be accepted
     *
     * @param line Line to check
     * @return true, if the run was a success
     */
    boolean line_is_success(String line) {
        return line.startsWith("[") && line.contains("Passed");
    }

    /**
     * Decide whether a given line is a success or a failure.
     * The default implementation checks if the line begins with '[' and contains the word 'Failed'.
     *
     * @param line Line to check
     * @return true, if the line turns the complete oracle run into a failure.
     */
    boolean line_is_failure(String line) {
        if (line.contains("has been compiled by a more recent version of the Java Runtime")) {
            throw new UnsupportedClassVersionError("Please fix your System Java Environment!\n" + line);
        }
        return line.startsWith("[") && line.contains("Failed");
    }

    /**
     * Decide whether a given exit code should be accepted by the oracle
     *
     * @param exitCode Exit code
     * @return true, if accepted, false if not
     */
    abstract boolean acceptExitCode(int exitCode);

    String validateResult(String processOutput) {
        String ret = null;
        successLinePresent = false;
        for (String line : LINEBREAK.split(processOutput)) {
            log(Level.FINEST, line);
            if (!successLinePresent) {
                if (line_is_failure(line)) {
                    ret = "Reported Fail";
                    break;
                } else if (line_is_success(line)) {
                    successLinePresent = true;
                    ret = null;
                }
            }
        }
        return ret;
    }

    /**
     * Run a subject that is started in a system shell.
     * Handles all possible exceptions and returns null, if the oracle succeeded.
     * The oracle's output MUST contain a line that starts with an opening bracket ('[')
     * and contains the string "Passed" or "Failed"
     * <p>
     * If fail and success functions are supplied, those criteria are overridden and the method searches for the first matching
     * criteria.
     * The failure condition is evaluated first.
     * <p>
     * The oracle is also considered a fail if the exit code is not 0.
     *
     * @param commandLine Command line to run
     * @return null if the oracle succeeded, a reason if the oracle failed.
     */
    SubjectStatus runShellSubject(ProcessBuilder commandLine) {
        boolean hasReport = false;
        String ret = null;
        try {
            Process result = commandLine.start();
            ProcessReturnValue output = SimpleSubprocess.runProcess(OptionalLong.empty(), result);
            ret = validateResult(output.stdout);
            String stderr = output.stderr.strip();
            hasReport = !output.stdout.isBlank();
            final int returncode = output.returnValue;
            if (!acceptExitCode(returncode)) {
                if (stderr.isEmpty()) {
                    return reportExitCode(returncode);
                } else {
                    return reportExitCode(returncode).append(stderr);
                }
            }
        } catch (IOException e) {
            return reportProcessOutputError(e);
        } catch (InterruptedException e) {
            return reportInterrupt(e);
        } catch (ExecutionException e) {
            log(Level.WARNING, "The STDOUT could not be read!", e);
            return reportNoStatusOutput();
        } catch (TimeoutException e) {
            return reportTimeout();
        }
        if (!acceptEmptyOutput() && !hasReport) {
            return reportNoStatusOutput();
        }
        if (ret == null && acceptSuccessOnly() && !successLinePresent) {
            return reportNoStatusOutput();
        }
        return SubjectStatus.SUCCESS;
    }

    /**
     * Returns whether this oracle should accept an empty program output
     *
     * @return whether this oracle should accept an empty program output
     */
    boolean acceptEmptyOutput() {
        return false;
    }

    /**
     * Returns whether this oracle should only succeed if a line was reported as success.
     *
     * @return whether this oracle should only succeed if a line was reported as success.
     */
    boolean acceptSuccessOnly() {
        return true;
    }

    /**
     * Build a Java process with the given arguments - of the form
     * <p>
     * {@code
     * "java [agent] -jar [jarfile] -[?] [inputfile] -[?] [outputfile] [...]"
     * }
     * </p>
     *
     * @param coverage          If true, inject the jacoco coverage collector argument in the JVM
     * @param jarfile           Jar file to run
     * @param file_to_open_flag File to open flag, e.g. "-i"
     * @param file_to_open      File to open. If null, no file to open flag is appended
     * @param file_to_save_flag File to save flag, e.g. "-o"
     * @param file_to_save      File to save. If null, no file to save flag is appended
     * @param additionalArgs    Additional args that are appended to the command line
     * @return a ProcessBuilder that executes the given java executable
     */
    protected ProcessBuilder buildJavaProcess(
            boolean coverage,
            @Nonnull String jarfile,
            @Nullable String file_to_open_flag,
            @Nullable String file_to_open,
            @Nullable String file_to_save_flag,
            @Nullable String file_to_save,
            @Nonnull String... additionalArgs
    ) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("java");
        arrayList.add("-jar");
        arrayList.add(jarfile);
        if (file_to_open != null) {
            arrayList.add(file_to_open_flag);
            arrayList.add(file_to_open);
        }
        if (file_to_save != null) {
            arrayList.add(file_to_save_flag);
            arrayList.add(file_to_save);
        }
        arrayList.addAll(Arrays.asList(additionalArgs));
        return new ProcessBuilder(arrayList);
    }
}
