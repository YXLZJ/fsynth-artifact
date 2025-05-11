package erepair.program.userinterface.actions;

import erepair.program.InputFormat;
import erepair.program.Logging;
import erepair.program.Main;
import erepair.program.subject.Subject;
import erepair.program.subject.Subjects;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Consumer;

/**
 **/
public abstract class Command extends CliObject {

    /**
     * Instantiate this command
     *
     * @param shortArgName Short argument, e.g. 'h'
     * @param longArgName  Long argument name, e.g. 'help'
     * @param helpText     Help text, e.g. 'Prints the man page'
     */
    public Command(char shortArgName, String longArgName, String helpText) {
        super(shortArgName, longArgName, helpText);
    }

    /**
     * Gets whether this command needs an argument or not
     *
     * @return true, if this command needs an argument
     */
    @Override
    public abstract boolean hasArg();

    /**
     * Gets all needed arguments by this command
     *
     * @return
     */
    public abstract char[] neededArguments();

    /**
     * Run the command
     *
     * @param argument  If {@link Command#hasArg()} returns true, the given argument must be supplied. Else, the parameter must be null.
     * @param arguments All other given arguments, at least those requested in {@link Command#neededArguments()}
     * @return 0 if successful, non-zero return code if not
     */
    public abstract int run(@Nullable String argument, Map<Character, Argument> arguments);

    /**
     * The basic file walker, a helper method for every method that needs file walking over all files inside a directory.
     * Includes folder validation and file metadata validation.
     * Logs each file before executing the action using the {@link Logging#generalLogger}
     *
     * @param inputfiles             Folder that contains all input files
     * @param skipFilesAlreadyTested If true, skip files that have already been tested
     * @param suffixSubject          If given, filter files out that do not match the file format accepted by the given subject
     * @param action                 Action to execute with each file
     * @throws IOException if one of the given folders or files was not valid
     */
    protected void walkOverFiles(@Nonnull Path inputfiles, boolean skipFilesAlreadyTested, @Nullable Subject suffixSubject, @Nonnull Consumer<? super Path> action) throws IOException {
        if (!Files.isDirectory(inputfiles)) {
            throw new IOException("The given input path is not a valid directory!");
        }
        var files = Files.walk(inputfiles)
                .filter(file -> Files.isRegularFile(file));
        if (skipFilesAlreadyTested) {
            files = files.filter(file -> !Main.GLOBAL_DATABASE.containsKey(file));
        }
        if (suffixSubject != null) {
            files = files.filter(file -> suffixSubject.suffixMatches(file.getFileName().toString()));
        }
        files.forEach(t -> {
            Logging.generalLogger.info("Checking File " + t.toString());
            action.accept(t);
        });
    }

    /**
     * Store the success of ANTLR to the global database
     * @param file File that was examined
     * @param antlrPassed true if ANTLR passed for the given file
     * @param visitor The visitor that was used for validation
     * @param format The format of the input
     */
    protected void storeAntlrSuccess(Path file, boolean antlrPassed, ParseTreeVisitor<?> visitor, InputFormat format) {
        if (!antlrPassed) {
            Main.GLOBAL_DATABASE.getFileRecord(file).setFailReason(Subjects.INVALID, "ANTLR failed, reported by " + visitor.getClass().getSimpleName(), format);
        } else {
            Main.GLOBAL_DATABASE.getFileRecord(file).setFailReason(Subjects.INVALID, "", format);
        }
    }
}
