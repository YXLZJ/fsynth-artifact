package erepair.program.subject;

import erepair.program.Loggable;
import erepair.program.Main;
import erepair.program.functional.ExecutionAction;
import erepair.program.visitor.GenericValidatorVisitor;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 * An abstract subject that can be run to test a test file using its {@link Subject#run(String, String, ExecutionAction, ExecutionAction)} method.
 *
 */
public abstract class Subject extends Loggable {
    static final Pattern LINEBREAK = Pattern.compile("\\n");
    private static final List<File> classpaths;

    static {
        classpaths = new ArrayList<>();
        try {
            final File jarpath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            classpaths.add(jarpath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        final Path libs = Main.BIN.resolve("lib");
        if (libs.toFile().isDirectory()) {
            try {
                Files.walk(libs).filter(path ->
                                !path.getFileName().toString().startsWith("antlr") &&
                                        !path.getFileName().toString().startsWith("commons") &&
                                        !(path.getFileName().toString().startsWith("lwjgl") && !path.getFileName().toString().startsWith("lwjgl-assimp"))
                        )
                        /*.filter(file -> file.getFileName().startsWith("gephi"))*/
                        .forEach(path -> {
                            if (path.getFileName().toString().endsWith(".jar")) {
                                classpaths.add(path.toFile());
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
//        classpaths.remove(0);//TODO JaCoCo ASM exception
        } else {
            System.err.println("Warning: Libs Folder did not exist at " + libs.normalize().toString());
        }
    }

    boolean successLinePresent = false;
    private Subjects kind;
    private boolean lastRunSucceeded = false;

    public Subject(Subjects kind) {
        super(kind.toString());
        this.kind = kind;
    }

    /**
     * Get the printable human-readable name of the oracle
     *
     * @return the name
     */
    public final String getName() {
        return kind.toString();
    }

    public final Subjects getKind() {
        return kind;
    }

    /**
     * Run the subject on the given file.
     *
     * @param file_to_open  File to run the subject with
     * @param file_to_save  File to save, or null
     * @param successAction Action to run in case of an success
     * @param failAction    Action to run in case of a failure
     * @return true, if subject succeeded, false otherwise.
     */
    public final SubjectStatus run(@Nonnull String file_to_open, @Nullable String file_to_save, @Nullable ExecutionAction successAction, @Nullable ExecutionAction failAction) {
        return run(file_to_open, file_to_save, successAction, failAction, false);
    }

    /**
     * Run the subject on the given file.
     *
     * @param file_to_open File to run the subject with
     * @param file_to_save File to save, or null
     * @return true, if subject succeeded, false otherwise.
     */
    public final SubjectStatus run(@Nonnull String file_to_open, @Nullable String file_to_save) {
        return run(file_to_open, file_to_save, null, null, false);
    }

    /**
     * Run the subject on the given file.
     *
     * @param file_to_open    File to run the subject with
     * @param file_to_save    File to save, or null
     * @param successAction   Action to run in case of an success
     * @param failAction      Action to run in case of a failure
     * @param collectCoverage If true, collect coverage during the run
     * @return true, if subject succeeded, false otherwise.
     */
    public final SubjectStatus run(@Nonnull String file_to_open, @Nullable String file_to_save, @Nullable ExecutionAction successAction, @Nullable ExecutionAction failAction, boolean collectCoverage) {
//        log(Level.FINER, "Running Test Oracle, file to test: " + file_to_open + (file_to_save != null ? ", file to save: " + file_to_save : ""));
        SubjectStatus ret = null;
        try {
            if (collectCoverage) {
                ret = this.runSubjectWithCoverage(file_to_open, file_to_save);
            } else {
                ret = this.runSubject(file_to_open, file_to_save);
            }// Subjects are NOT supposed to throw exceptions HERE, so we DO NOT ignore them!
        } /*catch (RuntimeException r) {
            log(Level.SEVERE, String.format("%s threw a runtime exception processing %s", this.getName(), file_to_open), r);
        } catch (Throwable e) {
            log(Level.SEVERE, String.format("%s threw an unhandled exception processing %s", this.getName(), file_to_open), e);
        }*/ finally {
            final boolean succ = ret != null && ret.wasSuccessful();
//            log(Level.FINER, "Oracle " + (succ ? "succeeded" : "failed (" + ret + ")") + ", testing " + file_to_open);
            if (succ) {
                if (successAction != null) {
                    successAction.run(new ExecutionInfo(ret, this.kind, this.getClass().getAnnotation(SubjectGroup.class).group()));
                }
            } else {
                if (failAction != null) {
                    failAction.run(new ExecutionInfo(ret, this.kind, this.getClass().getAnnotation(SubjectGroup.class).group()));
                }
            }
            this.lastRunSucceeded = succ;
        }
        assert ret != null; // If an exception was thrown, we should never reach this LOC
        return ret;
    }

    /**
     * Run the subject and return a reason for a fail, or null if the run succeeded.
     *
     * @param file_to_open File to open
     * @param file_to_save File to save
     * @return Reason for a fail, or null if it succeeded
     */
    @Nullable
    abstract SubjectStatus runSubject(String file_to_open, String file_to_save);

    /**
     * Run the subject and return a reason for a fail, or null if the run succeeded.
     * Collects Coverage and updates {@link Subject#getCoverageFile()}.
     *
     * @param file_to_open File to open
     * @param file_to_save File to save
     * @return Reason for a fail, or null if it succeeded
     */
    @Nullable
    abstract SubjectStatus runSubjectWithCoverage(String file_to_open, String file_to_save);

    /**
     * Get the exact path of the coverage file of the latest test run.
     *
     * @return the path of the latest coverage file.
     */
    abstract Path getCoverageFile();

    /**
     * Get a suitable validator visitor for the parse tree generated by parsing a file of the format of this subject
     * using ANTLR.
     * The visitor should return false if the file is invalid, true otherwise.
     *
     * @param skipErrorTokens If true, skip and ignore error tokens; if false, reject trees that contain error tokens
     * @return a suitable ParseTreeVisitor
     */
    @Nonnull
    public ParseTreeVisitor<Boolean> getValidator(boolean skipErrorTokens) {
        return new GenericValidatorVisitor(skipErrorTokens);
    }

    SubjectStatus reportProcessOutputError(IOException e) {
        final String msg = "Could not get output from process";
        log(Level.WARNING, msg + "!", e);
        return new SubjectStatus(msg + ": " + e.getMessage());
    }

    SubjectStatus reportNoStatusOutput() {
        final String failReason = "There was no status report from the oracle!";
        log(Level.WARNING, failReason);
        return new SubjectStatus(failReason);
    }

    SubjectStatus reportTimeout() {
        log(Level.WARNING, this.getName() + " timed out.");
        return SubjectStatus.TIMEOUT;
    }

    SubjectStatus reportInterrupt(InterruptedException e) {
        final String msg = "The process was interrupted";
        log(Level.WARNING, msg, e);
        return new SubjectStatus(msg + ": " + e.getMessage());
    }

    SubjectStatus reportProgramNotFound(IOException e) {
        final String msg = "Program not found: " + this.getName();
        log(Level.SEVERE, msg, e);
        return new SubjectStatus(msg + " " + e.getMessage());

    }

    final SubjectStatus reportException(Throwable e) {
        final String msg = "The Oracle threw an exception";
        log(Level.FINEST, msg, e);
        return new SubjectStatus(msg + " " + e.getMessage());
    }

    SubjectStatus reportExitCode(int exitValue) {
        final String msg = "The process terminated with exit code " + exitValue;
        log(Level.FINE, msg);
        return new SubjectStatus(msg);
    }

    /**
     * Report a severe IOException that should not happen
     *
     * @param e Exception
     */
    SubjectStatus reportIOException(IOException e) {
        final String msg = "There was an exception reading files in " + this.getName();
        log(Level.SEVERE, msg, e);
        return new SubjectStatus(msg + " " + e.getMessage());
    }

    /**
     * @return All accepted case-insensitive suffixes (file extensions) without dot (.)
     */
    String[] acceptedSuffixes() {
        if (!this.getClass().isAnnotationPresent(SubjectGroup.class)) {
            throw new RuntimeException("The subject " + this.getClass().getSimpleName() + " was not annotated with " + SubjectGroup.class.getSimpleName());
        }
        return new String[]{
                this.getClass().getAnnotation(SubjectGroup.class).group().getSuffix().substring(1)
        };
    }

    /**
     * Check if a given file has one of the accepted suffixes of the oracle
     *
     * @param file File to check
     * @return true, if the suffix matches
     */
    public final boolean suffixMatches(String file) {
        //TODO refactor to path system-wide?
        return Arrays.stream(acceptedSuffixes()).anyMatch(suffix -> file.toLowerCase().endsWith(suffix.toLowerCase()));
    }


}
