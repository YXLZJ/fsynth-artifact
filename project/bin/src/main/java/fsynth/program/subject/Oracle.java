package fsynth.program.subject;

import fsynth.program.InputFormat;
import fsynth.program.Logging;
import fsynth.program.Parsing;
import fsynth.program.functional.ExecutionAction;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This class provides all necessary static methods to run the test oracles for all different subjects.
 */
public final class Oracle extends Subject {
    public static Logger logger = Logging.oracleLogger;
    static Map<InputFormat, List<Subject>> SUBJECTS = null;
    private static Oracle instance = new Oracle();
    private static Path tempfolder;

    static {
        reloadSubjects();
    }

    static {
        try {
            tempfolder = Files.createTempDirectory("fsynth_testoracle");
            tempfolder.toFile().deleteOnExit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Oracle() {
        super(Subjects.INVALID);
    }

    private static void reloadSubjects() {
        final Reflections reflections = new Reflections("fsynth.program.subject", new TypeAnnotationsScanner());
        final Set<Class<?>> classes = reflections.getTypesAnnotatedWith(SubjectGroup.class, true);
        SUBJECTS = new HashMap<>();
        classes.forEach(subject -> {
            final InputFormat kind = subject.getAnnotation(SubjectGroup.class).group();
            if (!SUBJECTS.containsKey(kind)) {
                SUBJECTS.put(kind, new ArrayList<>());
            }
            try {
                SUBJECTS.get(kind).add((Subject) subject.getDeclaredConstructor().newInstance());
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
        SUBJECTS.values().forEach(list -> list.sort(Comparator.comparing(Subject::getName)));
    }

    /**
     * Get the subjects for a specific key in alphabetical order.
     *
     * @param key Key to query
     * @return a list of all subjects with the key
     * @throws IllegalArgumentException if no class with the annotated key exists in the subject package
     */
    public static List<Subject> getSubjectsFor(InputFormat key) throws IllegalArgumentException {
        assert key != InputFormat.INVALID;
        final List<Subject> ret = SUBJECTS.get(key);
        if (ret == null) {
//            throw new IllegalArgumentException("No such key: " + key.toString() + " (" + key.getJsonKey() + ")");
            return Collections.emptyList();
        }
        return ret;
    }

    /**
     * Get all subjects, sorted by input format / name
     *
     * @return all subjects
     */
    public static List<Subject> getSubjects() {
        return SUBJECTS.entrySet().stream()
                .sorted((e1, e2) -> e1.getKey().toString().compareTo(e2.getKey().toString()))
                .flatMap(t -> t.getValue().stream())
                .filter(s -> !Subjects.INVALID.equals(s.getKind()))
                .collect(Collectors.toList());
    }

    public static Set<InputFormat> getKinds() {
        if (SUBJECTS == null) {
            reloadSubjects();
        }
        return SUBJECTS.keySet();
    }

    public static Subject resolve(Subjects toResolve) throws IllegalArgumentException {
        for (List<Subject> subjectSet : SUBJECTS.values()) {
            for (Subject subject : subjectSet) {
                if (subject.getKind().equals(toResolve)) {
                    return subject;
                }
            }
        }
        throw new IllegalArgumentException("Could not find subject " + toResolve.toString());
    }

    /**
     * Run the given oracle on a temporary file.
     * The given string will be automatically written into the temporary file, which will be automatically deleted afterwards.
     *
     * @param fileContent Content of the file
     * @param oracle      Oracle to run
     * @param fileSuffix  Suffix of the file, e.g. "obj"
     * @return the status of the subject run
     */
    public static SubjectStatus runOracleWithTemporaryFile(String fileContent, Subject oracle, String fileSuffix) {
        return runOracleWithTemporaryFileMB(fileContent, oracle, fileSuffix, false);
    }

    /**
     * Run the given oracle on a temporary file.
     * The given byte array will be automatically written into the temporary file, which will be automatically deleted afterwards.
     *
     * @param fileContent Content of the file
     * @param oracle      Oracle to run
     * @param fileSuffix  Suffix of the file, e.g. "obj"
     * @return the status of the subject run
     */
    public static SubjectStatus runOracleWithTemporaryBinaryFile(byte[] fileContent, Subject oracle, String fileSuffix) {
        return runOracleWithTemporaryFileMB(fileContent, oracle, fileSuffix, true);
    }

    /**
     * Run the given oracle on a temporary file.
     * The given byte list will be automatically converted into an array, which might be time- and memory-intensive
     *
     * @param fileContent Content of the file
     * @param oracle      Oracle to run
     * @param fileSuffix  Suffix of the file, e.g. "obj"
     * @return the status of the subject run
     */
    public static SubjectStatus runOracleWithTemporaryBinaryFile(List<Byte> fileContent, Subject oracle, String fileSuffix) {
        byte[] t = new byte[fileContent.size()];
        int i = 0;
        for (byte b : fileContent) {
            t[i++] = b;
        }
        return runOracleWithTemporaryBinaryFile(t, oracle, fileSuffix);
    }

    /**
     * Internal Overload for writing binary and encoded files
     *
     * @param fileContent File content, String if we deal with a grammar-based format, else byte[]
     * @param oracle      Oracle to run
     * @param fileSuffix  File Suffix
     * @param binary      If true, the format is binary
     * @return the status of the execution
     */
    private static SubjectStatus runOracleWithTemporaryFileMB(Object fileContent, Subject oracle, String fileSuffix, boolean binary) {
        SubjectStatus result;
        if (fileSuffix.startsWith(".")) {
            fileSuffix = fileSuffix.substring(1);
        }
        Path ddfile = Paths.get(tempfolder.normalize().toString(), "oracle_" + System.currentTimeMillis() + "." + fileSuffix);
        if (binary) {
            Parsing.writeBinaryFile(ddfile, (byte[]) fileContent);
        } else {
            Parsing.writeStringToFile(ddfile, (String) fileContent);
        }
//            Logging.oracleLogger.log(Level.FINE, "Running test oracle with temporary file " + ddfile.toString());
        result = oracle.run(ddfile.normalize().toString(), null);

        try {
            Files.delete(ddfile);
        } catch (IOException e) {
            Logging.oracleLogger.log(Level.SEVERE, "Could not delete temporary file", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Run the given oracle on the given file.
     *
     * @param kind          Oracle to run
     * @param file_to_open  File to run
     * @param file_to_save  File to save, if the oracle supports this
     * @param successAction Action to run on success
     * @param failAction    Action to run on fail
     * @return true, if the oracle passed
     */
    public static SubjectStatus runOracle(Subjects kind,
                                          @Nonnull String file_to_open,
                                          @Nullable String file_to_save,
                                          @Nullable ExecutionAction successAction,
                                          @Nullable ExecutionAction failAction) {
        Subject subject;
        try {
            subject = resolve(kind);
        } catch (IllegalArgumentException e) {
            Logging.error("Non-Existent Subject: " + kind.toString(), e);
            throw new RuntimeException(e);
        }
        return runOracle(subject, file_to_open, file_to_save, successAction, failAction);
    }

    /**
     * Run the given oracle on the given file, failing if the file's suffix does not match with those that the oracle supports
     *
     * @param kind          Oracle to run
     * @param file_to_open  File to run
     * @param file_to_save  File to save, if the oracle supports this
     * @param successAction Action to run on success
     * @param failAction    Action to run on fail
     * @return true, if the oracle passed
     */
    public static SubjectStatus runOracleIfSuffixMatches(Subjects kind,
                                                         @Nonnull String file_to_open,
                                                         @Nullable String file_to_save,
                                                         @Nullable ExecutionAction successAction,
                                                         @Nullable ExecutionAction failAction) {
        Subject subject;
        try {
            subject = resolve(kind);
        } catch (IllegalArgumentException e) {
            Logging.error("Non-Existent Subject: " + kind.toString(), e);
            throw new RuntimeException(e);
        }
        return runOracleIfSuffixMatches(subject, file_to_open, file_to_save, successAction, failAction);
    }

    /**
     * Run the given oracle on the given file, failing if the file's suffix does not match with those that the oracle supports
     *
     * @param kind          Oracle to run
     * @param file_to_open  File to run
     * @param file_to_save  File to save, if the oracle supports this
     * @param successAction Action to run on success
     * @param failAction    Action to run on fail
     * @return true, if the oracle passed
     */
    public static SubjectStatus runOracleIfSuffixMatches(Subject kind,
                                                         @Nonnull String file_to_open,
                                                         @Nullable String file_to_save,
                                                         @Nullable ExecutionAction successAction,
                                                         @Nullable ExecutionAction failAction) {
        if (!kind.suffixMatches(file_to_open)) {
            Logging.generalLogger.info(file_to_open.toString() + " had a mismatching file suffix and will be skipped (failed oracle)");
            return new SubjectStatus("Skipped in runOracleIfSuffixMatches()");
        }
        return runOracle(kind, file_to_open, file_to_save, successAction, failAction);
    }

    /**
     * Run the given oracle on the given file.
     *
     * @param oracle        Oracle to run
     * @param file_to_open  File to run
     * @param file_to_save  File to save, if the oracle supports this
     * @param successAction Action to run on success
     * @param failAction    Action to run on fail
     * @return true, if the oracle passed
     */
    public static SubjectStatus runOracle(Subject oracle,
                                          @Nonnull String file_to_open,
                                          @Nullable String file_to_save,
                                          @Nullable ExecutionAction successAction,
                                          @Nullable ExecutionAction failAction) {
        return oracle.run(file_to_open, file_to_save, successAction, failAction);
    }

    private static void report(String s) {
//        System.out.println(String.format("[%s] %s", TESTHELPER_PREFIX, s));
        logger.info(s);
    }

    public static boolean createDirectoryIfNotExists(Path directory) {
        if (!directory.toFile().exists()) {
            return directory.toFile().mkdirs();
        }
        return true;
    }

    private static void report(Subjects subject, boolean succeeded) {
        logger.fine(subject.toString() + " " + (succeeded ? "succeeded." : "failed."));
    }

    @Nullable
    @Override
    SubjectStatus runSubject(String file_to_open, String file_to_save) {
        throw new IllegalStateException("Oracle cannot be run directly");
    }

    @Nullable
    @Override
    SubjectStatus runSubjectWithCoverage(String file_to_open, String file_to_save) {
        throw new IllegalStateException("Oracle cannot be run directly");
    }

    @Override
    Path getCoverageFile() {
        throw new IllegalStateException("Oracle cannot be run directly");
    }


    @Override
    String[] acceptedSuffixes() {
        throw new IllegalStateException("Oracle cannot be run directly");
    }
}
