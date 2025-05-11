package erepair.program.userinterface.actions.commands;

import erepair.program.FileSize;
import erepair.program.InputFormat;
import erepair.program.Logging;
import erepair.program.Main;
import erepair.program.db.FileDatabase;
import erepair.program.repairer.Repairer;
import erepair.program.subject.Oracle;
import erepair.program.subject.Subject;
import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliCommand;
import erepair.program.userinterface.actions.Command;
import erepair.program.Algorithm;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;

/**
 **/
@CliCommand
public class RunCommand extends Command {

    public RunCommand() {
        super('R', "run", "Run the paper evaluation.\nUsage: -R <num> -i <workingdir>\nUses the file information from the database to determine test files (may be regenerated using -F and -f).");
    }

    /**
     * Check if the given file does not yet have an unmutated file and if one is present, set it in the database.
     * Unmutated files are expected to be in ../{prefix}-valid/{filename}, relative to the file
     *
     * @param invalidFile Invalid file
     */
    static void sanitizeValidFilesDatabase(Path invalidFile) {
        final FileDatabase database = Main.GLOBAL_DATABASE;
        if (database.getFileRecord(invalidFile).hasUnmutatedFiles()) {
            return; // Files are already added
        }
        String pathprefix = invalidFile.getParent().getFileName().toString().split("-")[0];
        Path validfile = invalidFile.getParent().getParent().resolve(pathprefix + "-valid").resolve(invalidFile.getFileName());
        if (Files.isRegularFile(validfile)) {
            long fsize = 0;
            try {
                fsize = FileSize.withoutWhitespaces(validfile);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            database.getFileRecord(invalidFile).setUnmutatedFile(validfile, fsize);
        }
        return;
    }

    /**
     * Check if the given file has already been tested with a subject program.
     * If not, test it and store the fail reason to the database.
     *
     * @param fileUnderTest File under test
     */
    static void sanitizeFailReasonsDatabase(Path fileUnderTest) {
        if (Main.GLOBAL_DATABASE.getFileRecord(fileUnderTest).getFormat() == null) {
            final InputFormat format = InputFormat.fromFileType(fileUnderTest);
            final List<Subject> oracles = Oracle.getSubjectsFor(format);
            //The successAction and failAction automatically store the values into the database:
            oracles.forEach(subject -> Oracle.runOracle(subject, fileUnderTest.normalize().toString(), null, Main.GLOBAL_DATABASE.successAction(fileUnderTest), Main.GLOBAL_DATABASE.failAction(fileUnderTest)));
        }
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
     * Batch-evaluate all files from the test files directory and store the results in the output folder.
     * <p>
     * Expects the following paths inside the given directory:
     * <ul>
     * <li>{format}-invalid-{single,mult,realworld}/... - Sets of mutated input test files</li>
     * <li>{format}-invalid/... - Sets of real-world invalid files</li>
     * </ul>
     *
     * @param argument Limit how many files are evaluated at once from each format.
     *                 The files are chosen non-deterministically.
     */
    @Override
    public int run(@Nullable String argument, Map<Character, Argument> arguments) {
        if (arguments.containsKey('V') && arguments.get('V').wasGiven()) {
            Main.algorithm_only = Algorithm.fromString(arguments.get('V').getArgumentValue()); // Limit us to the given Algorithm
            Logging.generalLogger.log(Level.INFO, "Limiting Execution to " + Algorithm.fromString(arguments.get('V').getArgumentValue()) + ". Reloading Algorithms...");
            Repairer.reload_algorithms();
        }
        final Path workingdir = Paths.get(arguments.get('i').getArgumentValue());
        final int fileLimit = Integer.parseInt(argument);
        Logging.generalLogger.log(Level.INFO, "Starting Test Run of all remaining files limited to " + fileLimit + " of each format.");
        InputFormat[] formats = InputFormat.values(); // Todo Allow for single format to be evaluated via cli args?
        Arrays.stream(formats)
                .filter(format -> format != InputFormat.INVALID)
                .forEach(format -> {
                    Logging.generalLogger.log(Level.INFO, "Starting Test Run for up to " + fileLimit + " " + format + " files.");
                    try {
                        Files.walk(workingdir).filter(p -> p.getFileName().toString().startsWith(format.toString().toLowerCase() + "-invalid"))
                                .flatMap(formatfolder -> {
                                    try {
                                        Logging.generalLogger.info("Searching folder " + formatfolder);
                                        return Files.walk(formatfolder).filter(pp -> pp.toFile().isFile());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        throw new RuntimeException(e);
                                    }
                                })
                                .filter(file -> !Main.GLOBAL_DATABASE
                                        .getFileRecord(file)
                                        .wasTestedWith(Objects.requireNonNullElse(Main.algorithm_only, Algorithm.erepair))) // TODO change to one runner per algorithm to skip already-tested algorithms
                                .limit(fileLimit)
                                .forEach(fileUnderTest -> {
                                    sanitizeValidFilesDatabase(fileUnderTest); // Check if there are prettyprinted files and add them to the database, if they are not already added
                                    sanitizeFailReasonsDatabase(fileUnderTest);
                                    Repairer.instantiateAll(fileUnderTest, format)
                                            .forEach(repairer -> {
                                                if (repairer.needsPrettyPrintedFiles() && !Main.GLOBAL_DATABASE.getFileRecord(fileUnderTest).hasUnmutatedFiles()) { //TODO Possibility to add prettyprinted files?
                                                    return;
                                                }
                                                Logging.generalLogger.log(Level.INFO, "Running " + repairer.NAME() + " for " + fileUnderTest.toString() + ".");
                                                repairer.setResultPath(Main.TESTOUTPUT_FOLDER);
                                                repairer.setTimeouts(Main.timeoutPerFile);
                                                //repairer.setSkipTestedSubjects(false);//Uncomment to avoid skipping already-tested subjects
                                                Oracle.getSubjectsFor(format).stream()
                                                        .forEach(subject -> {
                                                            if (Main.GLOBAL_DATABASE.getFileRecord(fileUnderTest).wasTestedWith(subject.getKind(), repairer.getAlgorithmKind())) {
                                                                Logging.generalLogger.log(Level.FINE, "Skipping " + fileUnderTest + " because it was already tested with " + repairer.getAlgorithmKind() + " / " + subject.getKind());
                                                            } else {
                                                                repairer.run(subject);
                                                            }
                                                        });

                                            });
                                    Main.uiController.doAutosave();
                                });
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
//                    Main.GLOBAL_DATABASE.getForFormat(format).filter(entry -> !entry.getValue().wasTestedWith(Algorithm.DDMAXG))
//                            .limit(fileLimit)
//                            .forEach(entry -> {
//                                final Path fileUnderTest = entry.getKey();
//                                Repairer.instantiateAll(fileUnderTest)
//                                        .forEach(repairer -> {
//                                            if (repairer.needsPrettyPrintedFiles() && !entry.getValue().hasUnmutatedFiles()) { //TODO Possibility to add prettyprinted files?
//                                                return;
//                                            }
//                                            Logging.generalLogger.log(Level.INFO, "Running " + repairer.NAME() + " for " + fileUnderTest.toString() + ".");
//                                            repairer.setResultPath(Main.TESTOUTPUT_FOLDER);
//                                            repairer.setTimeouts(Main.timeoutPerFile);
//                                            repairer.run();
//                                        });
//                                Main.uiController.doAutosave();
//                            });
                });
        return 0;
    }
}
