package erepair.program.subject;

import erepair.program.Main;

import java.nio.file.Path;

/**
 * This class is designed to run a subject by running this jar file itself.
 * This method must be used in order to avoid JNI crashes causing the whole program to crash.
 * <p>
 * Also implements collecting coverage via JaCoCo - This class should be the only one that uses JaCoCo for coverage,
 * since all subjects that are run via Java are BootstrapSubjects.
 *
 */
public abstract class BootstrapSubject extends ShellSubject {

    private final String oracleToRun;

    public BootstrapSubject(Subjects kind, String oracleToRun) {
        super(kind);
        this.oracleToRun = oracleToRun;
    }

    @Override
    final Path getCoverageFile() {
        return null;
    }

    @Override
    final ProcessBuilder getCommandLine(String file_to_open, String file_to_save, boolean coverage) {
        ProcessBuilder process = super.buildJavaProcess(
                coverage,
                jarfilepath.getPath(),
                "-i",
                file_to_open,
                "-o",
                file_to_save,
                "-T", Long.toString(Main.timeoutPerFile),
                "-O", oracleToRun,
                "-s", ""
        );
        process.redirectErrorStream(true);
        return process;
    }

    @Override
    boolean acceptExitCode(int exitCode) {
        return true;
    }

    @Override
    boolean line_is_failure(String line) {
        return line.contains("[Oracle] Failed gracefully");
    }

    @Override
    boolean line_is_success(String line) {
        return line.contains("[Oracle] Passed");
    }
}
