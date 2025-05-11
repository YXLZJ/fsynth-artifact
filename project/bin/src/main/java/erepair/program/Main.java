package erepair.program;

import erepair.program.db.FileDatabase;
import erepair.program.db.statistics.GoogleSheet;
import erepair.program.repairer.Repairer;
import erepair.program.subject.Oracle;
import erepair.program.subject.Subject;
import erepair.program.userinterface.UIController;
import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliArgument;
import erepair.program.userinterface.actions.CliCommand;
import erepair.program.userinterface.actions.Command;
import org.apache.commons.cli.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.System.exit;

/**
 * Main class
 *
 */
@SuppressWarnings("ObjectAllocationInLoop")
public class Main {
    public static final Path TESTOUTPUT_FOLDER = Paths.get("out");
    public static final Path COVERAGE_REFERENCE_PATH = Paths.get("coverage-data");
    public static final String _OBJ = "obj";
    public static final String OBJ_SUFFIX = "." + _OBJ;
    public static final String _JSON = "json";
    public static final String JSON_SUFFIX = "." + _JSON;
    public static final String _DOT = "dot";
    public static final String DOT_SUFFIX = "." + _DOT;
    public static final String PROPOSALFOLDER_ORIGINALS_FMT = "%s-valid";
    public static final String VALIDFILESFOLDER_SUFFIX = "-valid";
    public static final String INVALIDFILESFOLDER_SUFFIX = "-invalid";
    public static final String ALLINVALIDFILESFOLDER_SUFFIX = "-all-invalid";
    /**
     * Default file to store statistics to
     */
    public static final Path DEFAULT_STATISTICS_FILE = Paths.get("statistics.db");
    /**
     * Reports folder where CSV files and test reports will be stored to
     */
    public static final Path REPORTS_FOLDER = Paths.get("reports");
    public static final String APPLICATION_NAME = "Input Repair";
    public static final Path TOKENS_DIRECTORY_PATH = Paths.get("tokens"); //Token Path for the Google Sheets Tokens
    public static final Path GOOGLE_SHEETS_IDS_PATH = Paths.get("google-sheet-ids.json");

    /**
     * The Sampling Size for erepair
     */
    public static final int erepair_SAMPLING_SIZE = 1;
    /**
     * If true, return incomplete files as if they were repaired, if erepair cannot find a viable repair.
     * If false, throw an exception.
     */
    public static final boolean RETURN_INCOMPLETE_FILES = true;
    /**
     * The full path to the ddmindiff script
     */
    public static final Path ddmindiff_script;
    private static final Pattern R_WHITESPACE = Pattern.compile("\\s");
    private static final Map<Character, Argument> cmdlineargs;
    private static final Map<Character, Command> cmdlinecommands;
    /**
     * The max number of simultaneous corrections that should be considered in each step of erepair, or -1 if no limit should be applied
     */
    public static int MAX_SIMULTANEOUS_CORRECTIONS = 2;
    /**
     * If set, only evaluate the given algorithm instead of all algorithms
     */
    public static Algorithm algorithm_only = null;
    /**
     * The directory of this jar file
     */
    public static Path BIN;
    /**
     * Timeout per file and algorithm run
     */
    @SuppressWarnings("PointlessArithmeticExpression")
    public static long timeoutPerFile = 4 * 60 * 1000;//4 minute timeout
    public static Path TESTRESULTS_FOLDER = Paths.get("results");
    public static String PYTHON = "appleseed/bin/python";
    public static FileDatabase GLOBAL_DATABASE = null;
    public static UIController uiController = new UIController();
    private static HashSet<String> myKnownMD5Hashes = new HashSet<>();

    static {
        //Load jarfile path
        try {
            BIN = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).toPath().getParent();
            if (!BIN.resolve("lib").toFile().isDirectory()) {
                throw new Warning("There is no lib folder in the program directory! We assume this means that we either run from a classpath, or the program dir is corrupted! We take the cwd as program dir instead.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            BIN = Paths.get(".");
        }
        ddmindiff_script = BIN.resolve("ddmindiff.py");
        Set<Character> usedArgs = new HashSet<>();
        usedArgs.add('h');//Hard-coded help argument

        //Load available arguments
        Reflections reflections = new Reflections("erepair.program.userinterface.actions.arguments", new TypeAnnotationsScanner());
        final Set<Class<?>> classes = reflections.getTypesAnnotatedWith(CliArgument.class, true);
        cmdlineargs = new HashMap<>();
        classes.forEach(arg -> {
            try {
                final Argument instance = (Argument) arg.getDeclaredConstructor().newInstance();
                final Character c = instance.shortArgName;
                if (usedArgs.contains(c)) {
                    throw new IllegalArgumentException("Command line short arg used multiple times: " + c + " by argument " + instance.longArgName + " (" + instance.helpText + ")");
                }
                usedArgs.add(c);
                cmdlineargs.put(c, instance);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //Load available commands
        Reflections c_reflections = new Reflections("erepair.program.userinterface.actions.commands", new TypeAnnotationsScanner());
        final Set<Class<?>> c_classes = c_reflections.getTypesAnnotatedWith(CliCommand.class, true);
        cmdlinecommands = new HashMap<>();
        c_classes.forEach(cmd -> {
            try {
                final Command instance = (Command) cmd.getDeclaredConstructor().newInstance();
                final Character c = instance.shortArgName;
                if (usedArgs.contains(c)) {
                    throw new IllegalArgumentException("Command line short arg used multiple times: " + c + " by command " + instance.longArgName + " (" + instance.helpText + ")");
                }
                usedArgs.add(c);
                cmdlinecommands.put(c, instance);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static int maintest(String[] args) throws IOException {
        Main.GLOBAL_DATABASE.forEach((path, fileRecord) -> {
            final InputFormat inputFormat = fileRecord.getFormat();
            Oracle.getSubjectsFor(inputFormat).forEach(subject -> {
//                Arrays.stream(Algorithm.values()).forEach(algorithm -> {
                final Algorithm algorithm = Algorithm.erepair;
                if (fileRecord.getTime(subject.getKind(), algorithm).isPresent()) {
                    if (fileRecord.getTime(subject.getKind(), algorithm).get() >= Main.timeoutPerFile && !fileRecord.getSuccess(subject.getKind(), algorithm)) {
                        final Path source = Paths.get(path.toString().substring(1));
                        final Path destination = Paths.get("_temp_timedout_files").resolve(source.getParent().getFileName()).resolve(source.getFileName());
                        if (!Files.isDirectory(destination.getParent())) {
                            destination.getParent().toFile().mkdirs();
                        }
                        try {
                            Files.copy(source, destination);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
//                });
            });

        });
        return 0;
    }

    /**
     * Load a statistics database file
     *
     * @param file File to load
     */
    private static void loadStatistics(Path file) {
//        statisticsDatabase = new JSONDatabase();
        if (file.toFile().exists()) {
            final String jsonContents = Parsing.readStringFromFile(file);
            final JSONObject jsonObject = new JSONObject(jsonContents);
            try {
                GLOBAL_DATABASE = new FileDatabase(jsonObject);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.exit(1);
            }
//                statisticsDatabase.load(file);
        } else {
            GLOBAL_DATABASE = new FileDatabase();
        }
        uiController.addSaveable(GLOBAL_DATABASE, file);
    }

    /**
     * Save the statistics database file
     */
    public static void saveStatistics() {
        uiController.doAutosave();
    }

    /**
     * Main program method
     *
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        GoogleSheet.initialize();
        Options opts = buildOptions();
        CommandLine cli;
        CommandLineParser cliParser = new DefaultParser();
        try {
            cli = cliParser.parse(opts, args);
        } catch (ParseException e) {
            Logging.fatal("There was an error parsing the command line.", e);
            return;
        }

        //Print Help if no argument was given or --help is present
        if (args.length == 0 || cli.hasOption("h")) {
            help(opts);
            return;
        }

        //Load arguments
        for (final Argument argument : cmdlineargs.values()) {
            if (cli.hasOption(argument.shortArgName)) {
                argument.setArgumentValue(cli.getOptionValue(argument.shortArgName));
            }
        }

        //Load the specified statistics file, or the default one, if no one has been given
        if (cmdlineargs.get('s').wasGiven()) {
            final String file = cmdlineargs.get('s').getArgumentValue();
            if (!file.isEmpty()) {
                final Path statistics = Paths.get(file);
                loadStatistics(statistics);
            } else {
                GLOBAL_DATABASE = new FileDatabase();//Do not save statistics
            }
        } else {
            loadStatistics(DEFAULT_STATISTICS_FILE);
        }
        //Store the python path
        if (cmdlineargs.get('p').wasGiven()) {
            PYTHON = cmdlineargs.get('s').getArgumentValue();
        }
        //Store the timeouts
        if (cmdlineargs.get('T').wasGiven()) {
            timeoutPerFile = Long.parseLong(cmdlineargs.get('T').getArgumentValue());
        }
        //Store the erepair parameters
        if (cmdlineargs.get('m').wasGiven()) {
            MAX_SIMULTANEOUS_CORRECTIONS = Integer.parseInt(cmdlineargs.get('m').getArgumentValue());
        }

        Command commandToRun = null;
        for (final Command command : cmdlinecommands.values()) {
            if (cli.hasOption(command.shortArgName)) {
                if (commandToRun != null) {
                    System.err.println("More than one command argument was given on the command line! You can print a command-line help with --help");
                    exit(1);
                }
                commandToRun = command;
            }
        }
        //Debug Mode
        if (cli.hasOption("x")) {
            try {
                exit(maintest(args));
            } catch (Exception e) {
                e.printStackTrace();
                exit(1);
            }
        }
        if (commandToRun == null) {
            System.err.println("No command was given! Exiting");
            exit(1);
        }
        for (char c : commandToRun.neededArguments()) {
            final Argument argument = cmdlineargs.get(c);
            if (!argument.wasGiven()) {
                System.err.println("Missing argument: " + argument.longArgName);
                exit(1);
            }
        }
        if (commandToRun.hasArg()) {
            exit(commandToRun.run(cli.getOptionValue(commandToRun.shortArgName), cmdlineargs));
        } else {
            exit(commandToRun.run(null, cmdlineargs));
        }
    }

    /**
     * Checks if a JSON file should be ignored by the filter for corrupted files.
     * e.g. if the file only consists of a single string, if the file is empty or if the file is multiple JSON files concatenated
     *
     * @param file File under test
     * @return true, if file should be blacklisted
     */
    public static boolean isBlacklistedFile(Path file) {
        if (!file.getFileName().toString().endsWith(".json")) { // A file format other than JSON is considered blacklisted if the file size is too small
            return file.toFile().length() < 4;
        }
        int curlybrace_stack = 0;
        int sqbrace_stack = 0;
        boolean has_started = false;
        String contents;
        contents = Parsing.readStringFromFile(file);
        contents = R_WHITESPACE.matcher(contents).replaceAll("");
        if (contents.length() < 3 /*|| contents.equals("{}") || contents.equals("[]")*/) {
            System.out.println("empty");
            return true; //Empty JSON files should be ignored
        } else if (contents.startsWith("\"") || contents.startsWith("'") || Character.isLetterOrDigit(contents.charAt(0))) {
            System.out.println("SingleLiteral");
            return true; // Ignore single literals
        }
        for (char c : contents.toCharArray()) {
            if (c == '[') {
                if (has_started && sqbrace_stack == 0 && curlybrace_stack == 0) {
                    System.out.println("Multiple Json");
                    return true; //Ignore files that are just multiple arrays concatenated
                }
                has_started = true;
                sqbrace_stack++;
            } else if (c == ']') {
                sqbrace_stack--;
            } else if (c == '{') {
                if (has_started && curlybrace_stack == 0 && sqbrace_stack == 0) {
                    System.out.println("Multiple JSON Dicts");
                    return true; //Ignore files that contain multiple dicts concatenated
                }
                has_started = true;
                curlybrace_stack++;
            } else if (c == '}') {
                curlybrace_stack--;
            }
        }
        return false;
    }

    /**
     * Build the CLI options and their documentation.
     *
     * @return the CLI options for the main routine
     */
    @SuppressWarnings("HardcodedLineSeparator")
    private static Options buildOptions() {
        final Options opts = new Options();
        opts.addOption("h", "help", false, "Print this help");//Hard-coded help
        for (final Argument argument : cmdlineargs.values()) {
            opts.addOption(Character.toString(argument.shortArgName), argument.longArgName, argument.hasArg(), argument.helpText);
        }
        for (final Command command : cmdlinecommands.values()) {
            opts.addOption(Character.toString(command.shortArgName), command.longArgName, command.hasArg(), command.helpText);
        }
        //Modes:
        opts.addOption("x", "debugmode", false, "");
        return opts;
    }

    /**
     * Prints the CLI help to the stdout.
     *
     * @param o Options to print
     */
    private static void help(Options o) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(Main.class.getPackage().getImplementationTitle() + " " + Main.class.getPackage().getImplementationVersion(), o);
        System.out.println("\n=== Available Algorithms ===\n");
        System.out.println("Grammar-Based: " + Repairer.getAllAvailableGrammarBasedAlgorithms().map(Algorithm::toString).collect(Collectors.joining(", ")));
        System.out.println("Binary:        " + Repairer.getAllAvailableBinaryAlgorithms().map(Algorithm::toString).collect(Collectors.joining(", ")));
        System.out.println("\n=== Available Formats ===\n");
        System.out.println("Grammar-Based: " + Arrays.stream(InputFormat.values()).filter(f -> !f.equals(InputFormat.INVALID)).filter(f -> !Oracle.getSubjectsFor(f).isEmpty()).filter(f -> f.isGrammarBased).map(InputFormat::toString).collect(Collectors.joining(", ")));
        System.out.println("Binary:        " + Arrays.stream(InputFormat.values()).filter(f -> !f.equals(InputFormat.INVALID)).filter(f -> !Oracle.getSubjectsFor(f).isEmpty()).filter(f -> !f.isGrammarBased).map(InputFormat::toString).collect(Collectors.joining(", ")));
        System.out.println("\n=== Available Subject Programs ===\n");
        System.out.println("" + Oracle.getSubjects().stream().map(Subject::getName).collect(Collectors.joining(", ")));
    }

    /**
     * Check if the given file is a duplicate of another file, i.e. check if the file has the same MD5 hash as another file.
     *
     * @param file File to check.
     * @return true, if the file is a duplicate
     */
    private static boolean isDuplicate(Path file) {
        try (InputStream is = Files.newInputStream(file)) {
            String md5 = DigestUtils.md5Hex(is);
            return !myKnownMD5Hashes.add(md5);
        } catch (IOException e) {
            Logging.error("Could not create MD5 Hash of file " + file.normalize().toString(), e);
            return true;
        }
    }

    private static class Warning extends Exception {
        public Warning(String reason) {
            super(reason);
        }
    }
}