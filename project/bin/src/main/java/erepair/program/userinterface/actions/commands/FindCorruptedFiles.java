package erepair.program.userinterface.actions.commands;

import erepair.program.InputFormat;
import erepair.program.Logging;
import erepair.program.Main;
import erepair.program.Parsing;
import erepair.program.repairer.Repairer;
import erepair.program.subject.Oracle;
import erepair.program.subject.Subject;
import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliCommand;
import erepair.program.userinterface.actions.Command;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.apache.commons.io.FileUtils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 **/
@CliCommand
public class FindCorruptedFiles extends Command {

    public FindCorruptedFiles() {
        super('F', "find-corrupted-files",
                "Find all corrupt and working files that are rejected or accepted by all subject programs" +
                        " with the given format in the specified folder (-i) and copy them to a subdirectory of the " +
                        "output folder (-o). If no output folder is given, no files are copied, but the database " +
                        "is still being updated. The format may be one of the following formats:\n"
                        + Arrays.stream(InputFormat.values())
                        .filter(x -> x != InputFormat.INVALID)
                        .map(InputFormat::toString)
                        .collect(Collectors.joining(",\n")));
    }

    @Override
    public boolean hasArg() {
        return true;
    }

    @Override
    public char[] neededArguments() {
        return new char[]{'i'};
    }

    /**
     * Scan the input folder for corrupted files where all subjects fail.
     * Copy all corrupted files to the specified output folder.
     *
     * @return 0, if successful
     */
    @SuppressWarnings("ObjectAllocationInLoop")
    @Override
    public int run(@Nullable String argument, Map<Character, Argument> arguments) {
        assert argument != null;
        final InputFormat format = InputFormat.fromString(argument);
        final Path inputfolder = Paths.get(arguments.get('i').getArgumentValue());
        final String o = arguments.get('o').hasArg() ? arguments.get('o').getArgumentValue() : null;
        final Path outputfolder = o != null ? Paths.get(o) : null;
        final boolean moveFiles = outputfolder != null;
        final boolean oneMustFail = true;
        AtomicInteger savedex = new AtomicInteger();
        Logging.setAllLogLevels(Level.FINE);
        final List<Subject> oracles = Oracle.getSubjectsFor(format);
        try {
            walkOverFiles(inputfolder, true, null, file -> {
                try {
                    Repairer.setFileSizeIfMissing(file, Main.GLOBAL_DATABASE);
                    Boolean antlrPassed = null;
                    boolean allFailed = true;
                    boolean oneFailed = false;
                    boolean allPassed = true;
                    assert oracles.size() > 0;
                    for (Subject oracle : oracles) {
                        if (!format.isGrammarBased) { // Skip ANTLR if we encounter a non-grammar-based format
                            antlrPassed = true;
                        }
                        if (antlrPassed == null) {
                            final ParseTree tree = Parsing.parseAutodetect(file, false); //TODO: Refactor InputFormat to store ANTLR Metadata and change parseFormat(...) into parse(format,...)
                            assert tree != null;
                            final ParseTreeVisitor<Boolean> visitor = oracle.getValidator(true);
                            antlrPassed = tree.accept(visitor);
                            Logging.generalLogger.log(Level.FINE, "ANTLR " + (antlrPassed ? "passed" : "failed") + " for " + file.toString());
                            storeAntlrSuccess(file, antlrPassed, visitor, format);
                        }
                        final boolean oraclePassed = Oracle.runOracleIfSuffixMatches(oracle, file.normalize().toString(), null, Main.GLOBAL_DATABASE.successAction(file), Main.GLOBAL_DATABASE.failAction(file)).wasSuccessful();
                        allFailed = allFailed && !oraclePassed;
                        allPassed = allPassed && oraclePassed;
                        oneFailed = oneFailed || !oraclePassed;
                    }
                    if (antlrPassed && ((oneMustFail && oneFailed) || (!oneMustFail && allFailed))) {
                        if (oneMustFail) {
                            Logging.generalLogger.log(Level.INFO, "At least one oracle failed for " + file.toString());
                        } else {
                            Logging.generalLogger.info("All oracles (" + oracles.stream().map(Subject::getName).collect(Collectors.joining(", ")) + ") failed, but ANTLR passed for " + file.toString());
                        }
                        if (moveFiles) {
                            final Path dest = outputfolder.resolve(format.toString().toLowerCase() + Main.INVALIDFILESFOLDER_SUFFIX);
                            if (!Files.isDirectory(dest)) {
                                dest.toFile().mkdirs();
                            }
                            final Path newPath = dest.resolve(file.getFileName());
                            FileUtils.copyFile(file.toFile(), newPath.toFile());
                            Main.GLOBAL_DATABASE.changeFilePath(file, newPath);
                        }
                    }
                    if (antlrPassed && allPassed) {
                        Logging.generalLogger.info("All oracles (" + oracles.stream().map(Subject::getName).collect(Collectors.joining(", ")) + ") passed for " + file.toString());
                        if (moveFiles) {
                            final Path dest = outputfolder.resolve(format.toString().toLowerCase() + Main.VALIDFILESFOLDER_SUFFIX);
                            if (!Files.isDirectory(dest)) {
                                dest.toFile().mkdirs();
                            }
                            final Path newPath = dest.resolve(file.getFileName());
                            FileUtils.copyFile(file.toFile(), newPath.toFile());
                            Main.GLOBAL_DATABASE.changeFilePath(file, newPath);
                        }
                    }
                } catch (IOException e) {
                    Logging.generalLogger.log(Level.WARNING, "Could not test " + file.toString(), e);
                }
                if (savedex.getAndIncrement() % 20 == 0) { // Autosave with every n-th file
                    Main.uiController.doAutosave();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
        Main.saveStatistics();
        return 0;
    }
}
