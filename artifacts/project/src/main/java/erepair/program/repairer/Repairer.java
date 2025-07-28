package erepair.program.repairer;

import erepair.program.*;
import erepair.program.db.FileDatabase;
import erepair.program.differencing.DifferencingAlgorithm;
import erepair.program.differencing.LevenshteinDistance;
import erepair.program.subject.Oracle;
import erepair.program.subject.Subject;
import erepair.program.subject.SubjectStatus;
import erepair.program.subject.Subjects;
import org.antlr.v4.runtime.tree.ParseTree;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.stream.Stream;

class GBTuple<T> {
    private T grammarbased = null;
    private T binary = null;

    public GBTuple<T> setGrammarBased(T t) {
        this.grammarbased = t;
        return this;
    }

    public Optional<T> getGrammarbased() {
        if (this.grammarbased == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.grammarbased);
        }
    }

    public Optional<T> getBinary() {
        if (this.binary == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.binary);
        }
    }

    public GBTuple<T> setBinary(T t) {
        this.binary = t;
        return this;
    }
}

/**
 * An abstract repairer that repairs an input file with a certain algorithm.
 * Must be subclassed with a concrete repairer that repairs using a certain algorithm.
 *
 **/
@SuppressWarnings("ClassNamePrefixedWithPackageName")
public abstract class Repairer extends DatabaseConnection {
    final static Map<Path, Path> prettyPrintCache = new HashMap<>();
    /**
     * All concrete repairer classes
     */
    private static Map<Algorithm, GBTuple<Class<? extends Repairer>>> CONCRETE_REPAIRERS;

    static {
        reload_algorithms(); // Load all algorithms
    }

    private final HashMap<Subjects, AtomicLong> subjectRunsCounter = new HashMap<>();
    /**
     * The file to be repaired by this Repairer
     */
    private final Path file;
    /**
     * The format of the file to be repaired
     */
    protected InputFormat format;
    Path resultPath;
    long timeouts = Main.timeoutPerFile;
    private long systemTimeOnStart = 0;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private OptionalLong systemTime = OptionalLong.empty();
    private boolean skipTestedSubjects = true;

    /**
     * Instantiate a repairer that repairs the given file
     *
     * @param file File to repair
     */
    public Repairer(Path file) {
        this.file = file;
        this.format = InputFormat.fromFileType(file);
    }

    public static final void reload_algorithms() {
        final Reflections reflections = new Reflections("erepair.program.repairer", new TypeAnnotationsScanner(), new SubTypesScanner());
        final Set<Class<?>> classes = reflections.getTypesAnnotatedWith(ConcreteRepairer.class, true);
        CONCRETE_REPAIRERS = new HashMap<>();
        classes.stream()
                .filter(test ->
                        Main.algorithm_only == null || test.getAnnotation(ConcreteRepairer.class).algorithm().equals(Main.algorithm_only))//Only return algorithms that are not filtered
                .forEach(test -> {
                    final Algorithm kind = test.getAnnotation(ConcreteRepairer.class).algorithm();
                    final IsBinaryEnum type = test.getAnnotation(ConcreteRepairer.class).type();
                    if (!CONCRETE_REPAIRERS.containsKey(kind)) {
                        CONCRETE_REPAIRERS.put(kind, new GBTuple<>());
                    }
                    if (type == IsBinaryEnum.BOTH || type == IsBinaryEnum.BINARY) {
                        if (CONCRETE_REPAIRERS.get(kind).getBinary().isPresent()) {
                            throw new RuntimeException(kind.toString() + " was instantiated multiple times for binary algorithms: " + test.getName() + " and " + CONCRETE_REPAIRERS.get(kind).getBinary().get().getName());
                        }
                        //noinspection unchecked
                        CONCRETE_REPAIRERS.get(kind).setBinary((Class<? extends Repairer>) test);
                    }
                    if (type == IsBinaryEnum.BOTH || type == IsBinaryEnum.GRAMMARBASED) {
                        if (CONCRETE_REPAIRERS.get(kind).getGrammarbased().isPresent()) {
                            throw new RuntimeException(kind.toString() + " was instantiated multiple times for grammar-based algorithms: " + test.getName() + " and " + CONCRETE_REPAIRERS.get(kind).getGrammarbased().get().getName());
                        }
                        //noinspection unchecked
                        CONCRETE_REPAIRERS.get(kind).setGrammarBased((Class<? extends Repairer>) test);
                    }

                });
    }

    /**
     * @return All available algorithms, distinct and not containing any garbage
     */
    public static final Stream<Algorithm> getAllAvailableAlgorithms() {
        return Stream.concat(getAllAvailableBinaryAlgorithms(), getAllAvailableGrammarBasedAlgorithms())
                .filter(a -> a != Algorithm.INVALID)
                .distinct();
    }

    /**
     * @return All available binary algorithms, distinct and not containing any garbage
     */
    public static final Stream<Algorithm> getAllAvailableBinaryAlgorithms() {
        return CONCRETE_REPAIRERS.values().stream()
                .map(GBTuple::getBinary)
                .filter(Optional::isPresent)
                .map(x -> x.get().getAnnotation(ConcreteRepairer.class).algorithm())
                .filter(a -> a != Algorithm.INVALID)
                .distinct();
    }

    /**
     * @return All available Grammar-Based algorithms, distinct and not containing any garbage
     */
    public static final Stream<Algorithm> getAllAvailableGrammarBasedAlgorithms() {
        return CONCRETE_REPAIRERS.values().stream()
                .map(GBTuple::getGrammarbased)
                .filter(Optional::isPresent)
                .map(x -> x.get().getAnnotation(ConcreteRepairer.class).algorithm())
                .filter(a -> a != Algorithm.INVALID)
                .distinct();
    }

    /**
     * Instantiate all annotated repairer subclasses
     *
     * @param fileToRepair File to repair by all instantiated Repairers
     * @param format       Format of the file to repair
     * @return all annotated repairers
     */
    @Nonnull
    public static Stream<Repairer> instantiateAll(Path fileToRepair, InputFormat format) {
        final boolean grammarbased = format.isGrammarBased;
        return CONCRETE_REPAIRERS.values().stream()
                .map(gbtuple -> {
                    if (grammarbased) {
                        return gbtuple.getGrammarbased();
                    } else {
                        return gbtuple.getBinary();
                    }
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(rep -> {
                    try {
                        return (Repairer) rep.getDeclaredConstructor(Path.class).newInstance(fileToRepair);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                        //noinspection ReturnOfNull
                        return null;
                    }
                }).filter(Objects::nonNull);
    }

    /**
     * Set the file size of the original file without whitespaces to the measured file size in bytes, if it is not already in the database
     *
     * @param file     File to measure
     * @param database Database to lookup
     */
    public static void setFileSizeIfMissing(Path file, FileDatabase database) {
        if (database.getFileRecord(file).getSize() == -1L) {
            final long size;
            try {
                size = FileSize.withoutWhitespaces(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Main.GLOBAL_DATABASE.getFileRecord(file).setSize(size);
        }
    }

    /**
     * Instantiate a single repairer
     *
     * @param fileToRepair File to repair
     * @param algorithm    Algorithm to repair with
     * @param format       Format of the file to repair
     * @return the repairer instance
     */
    public static Repairer instantiate(Path fileToRepair, Algorithm algorithm, InputFormat format) {
        final boolean grammarbased = format.isGrammarBased;
        try {
            final var tup = CONCRETE_REPAIRERS.get(algorithm);
            if ((grammarbased && !tup.getGrammarbased().isPresent()) ||
                    (!grammarbased && !tup.getBinary().isPresent())) {
                throw new RuntimeException(algorithm.toString() + " was requested, but not available for " + (grammarbased ? "grammar-based" : "binary") + " formats!");
            }
            Optional<Class<? extends Repairer>> ret;
            if (grammarbased) {
                ret = tup.getGrammarbased();
            } else {
                ret = tup.getBinary();
            }
            return ret.get().getDeclaredConstructor(Path.class).newInstance(fileToRepair);
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the number of subject runs that was required to run the repair
     *
     * @param subject Subject that was evaluated
     * @return the number of runs
     */
    public long getNumberOfSubjectRuns(Subjects subject) {
        return this.subjectRunsCounter.getOrDefault(subject, new AtomicLong(0)).get();
    }

    /**
     * Get the format of the file repaired by this repairer, as returned by {@link InputFormat#fromFileType(Path)}
     *
     * @return the format of the file
     */
    public InputFormat getFormat() {
        return this.format;
    }

    /**
     * Set the skipping of already-tested subjects
     *
     * @param skipTestedSubjects If true, skip tested subjects, if false retest everything. Default is TRUE
     */
    public void setSkipTestedSubjects(boolean skipTestedSubjects) {
        this.skipTestedSubjects = skipTestedSubjects;
    }

    /**
     * Checks if this test needs the presence of pretty-printed original files to work, e.g. for DDMin
     *
     * @return true, if pretty-printed files are needed
     */
    public boolean needsPrettyPrintedFiles() {
        return false;
    }

    /**
     * Parses a file
     *
     * @param file File to parse
     * @return the ANTLR parse tree of the file, containing error nodes if there was a parsing error
     * @throws IOException if the file could not be read
     */
    @Nonnull
    public ParseTree parse(@Nonnull Path file) throws IOException {
        return Objects.requireNonNull(Parsing.parseAutodetect(file, false), "Could not parse the file " + file.normalize().toString());
    }

    /**
     * Gets an array of all supported differencing algorithms.
     *
     * @return Array of Differencing Algorithms
     */
    public DifferencingAlgorithm<? extends Long>[] getDifferencingAlgorithms() {
        //noinspection unchecked
        return (DifferencingAlgorithm<? extends Long>[]) new DifferencingAlgorithm[]{
                new LevenshteinDistance(),
        };
    }

    /**
     * Returns the name of the test, e.g. 'Synthesis'
     *
     * @return name
     */
    public String NAME() {
        return this.getAlgorithmKind().toString();
    }

    /**
     * Sets the timeouts used during the tests.
     *
     * @param timeouts Timeouts in ms
     */
    public void setTimeouts(long timeouts) {
        this.timeouts = timeouts;
    }

    /**
     * Start the test timer. MUST be called as first action of every test to report valid times to the database.
     */
    final void startTestTimer() {
        systemTimeOnStart = System.currentTimeMillis();
    }

    /**
     * Stop the test timer and store the elapsed time in {@link Repairer#systemTime}
     */
    final void stopTestTimer() {
        systemTime = OptionalLong.of(System.currentTimeMillis() - systemTimeOnStart);
    }

    /**
     * Gets the result path
     *
     * @return Result path
     */
    public Path getResultPath() {
        return this.resultPath;
    }

    /**
     * Sets the result path to store the rectified files.
     * Automatically appends the appropriate folder to the path
     *
     * @param resultPath Path
     */
    public void setResultPath(Path resultPath) {
        this.resultPath = resultPath;
    }

    /**
     * Gets the algorithm kind of the test
     *
     * @return the corresponding enum value
     */
    public final Algorithm getAlgorithmKind() {
        return this.getClass().getAnnotation(ConcreteRepairer.class).algorithm();
    }

    /**
     * Repair a file and return the path of the repaired file.
     * If results SHOULD be saved into the database, use {@link Repairer#run(Subject)} instead
     *
     * @param file    File to repair
     * @param subject Subject to repair the file for
     * @return the path to the repaired file
     */
    protected abstract Path repair(Path file, Subject subject);

    /**
     * Run this repairer with the given subject and save all results to the database.
     * If results should NOT be saved into the database, use {@link Repairer#repair(Path, Subject)} instead
     *
     * @param subject Subject to evaluate
     * @return True, if the repair was successful
     */
    public final boolean run(Subject subject) {
        final Algorithm myAlgorithm = this.getClass().getAnnotation(ConcreteRepairer.class).algorithm();
        if (resultPath == null) {
            throw new IllegalStateException("The result path needs to be set beforehand");
        }
        setFileSizeIfMissing(file, getDatabase());
        boolean passed;
//        if (this.skipTestedSubjects && getDatabase().getFileRecord(this.file).wasTestedWith(subject.getKind(), myAlgorithm)) {
//            Logging.generalLogger.info("Skipping " + subject.getKind() + " because it was already tested with " + myAlgorithm + ".");
//            allPassed = allPassed && getDatabase().getFileRecord(this.file).getSuccess(subject.getKind(), myAlgorithm);
//            continue; // Skip already-tested subjects!
//        } // TODO Make absolutely sure skipping is handled inside Caller
        this.startTestTimer();
        final Path repairedFile = this.repair(this.file, subject);
        this.stopTestTimer();
        passed = this.runOracle(subject, repairedFile, this.file);
        if (!getDatabase().getFileRecord(this.file).wasTestedWith(subject.getKind(), myAlgorithm)) {
            throw new RuntimeException("Failed to put the test result of " +
                    this.getClass().getAnnotation(ConcreteRepairer.class).algorithm() +
                    " for " + subject.getKind() + " into the database");
        }
        return passed;
    }

    public int successful = 0;
    public int failed = 0;
    public int incomplete = 0;
    /**
     * Runs a single oracle and reports the result to the database.
     * If unmutated files are present, evaluate editing distances and store all collected data into the database.
     * The Test Timer must be stopped before calling this method!
     *
     * @param oracle              Oracle to run
     * @param rectifiedFile       Rectified file
     * @param mutatedOriginalFile Mutated file that was repaired
     * @return true, if Oracle succeeded
     * @throws NoSuchElementException if the test timer has not been stopped before this method is called
     */
    final boolean runOracle(Subject oracle, Path rectifiedFile, Path mutatedOriginalFile) throws NoSuchElementException {
        final SubjectStatus res = Oracle.runOracle(oracle, rectifiedFile.normalize().toString(), null, null, null);
        if(res.wasIncorrect())
        {
            failed++;
        } else if(res.wasSuccessful())
        {
            successful++;
        } else
        {
            incomplete++;   
        }
        final boolean ret = res.wasSuccessful();
        this.incrementSubjectRuns(oracle.getKind());
        final long sizeRectified;
        try {
            sizeRectified = FileSize.withoutWhitespaces(rectifiedFile);
        } catch (IOException e) {
            throw new RuntimeException("The generated file did not exist", e);
        }
        final long runtime = systemTime.orElseThrow();
        this.reportToDatabase(mutatedOriginalFile, this.getAlgorithmKind(), oracle.getKind(), runtime, ret, this.getSubjectRuns(oracle.getKind()), rectifiedFile, sizeRectified);

        if (Main.GLOBAL_DATABASE.getFileRecord(mutatedOriginalFile).hasUnmutatedFiles()) { // Differencing only makes sense if we have an unmutated file!
            final Path workingOriginalFile = Main.GLOBAL_DATABASE.getFileRecord(mutatedOriginalFile).getUnmutatedFile();
            assert workingOriginalFile != null;
            //Evaluate Distancing Algorithms
            for (DifferencingAlgorithm<? extends Long> algorithm : getDifferencingAlgorithms()) {
                //noinspection ObjectAllocationInLoop
                Logging.generalLogger.log(Level.INFO, "Evaluating " + algorithm.getKind().toString());
                final Long result = algorithm.run(workingOriginalFile, rectifiedFile);
                if (result != null) {
                    this.reportToDatabase(mutatedOriginalFile, this.getAlgorithmKind(), oracle.getKind(), algorithm.getKind(), result);
                }
            }
        }
        return ret;
    }

    /**
     * Get the number of subject runs for the given Subject.
     * If there has never been a number set, return 1 and store 1 into the subject
     *
     * @param subject Subject to query
     * @return Number of runs
     */
    private Long getSubjectRuns(Subjects subject) {
        subjectRunsCounter.putIfAbsent(subject, new AtomicLong(1));
        return subjectRunsCounter.get(subject).get();
    }

    /**
     * Increment the subject counter and return the new value
     *
     * @param subject Subject that was executed
     * @return the new number of runs
     */
    public Long incrementSubjectRuns(Subjects subject) {
        subjectRunsCounter.putIfAbsent(subject, new AtomicLong(0));
        return subjectRunsCounter.get(subject).incrementAndGet();
    }

    /**
     * Increment the subject counter by the given value and return the new value
     *
     * @param subject   Subject that was executed
     * @param increment Value to add to the counter
     * @return the new number of runs
     */
    public Long incrementSubjectRunsBy(Subjects subject, Long increment) {
        subjectRunsCounter.putIfAbsent(subject, new AtomicLong(0));
        return subjectRunsCounter.get(subject).addAndGet(increment);
    }

    /**
     * Pretty-Prints a File using ANTLR.
     * Caches the pretty-printed files and returns the cached file name, if this method is called twice.
     *
     * @param file File to print
     * @return the pretty-printed file
     * @throws IOException if there was an error saving the file
     */
    public final Path prettyPrintFile(Path file) throws IOException {
        if (prettyPrintCache.containsKey(file)) {
            return prettyPrintCache.get(file);
        }

        String antlrResult = Parsing.prettyPrint(this.parse(file));
        final Path result = getResultPathFor(null, file);
        Parsing.writeStringToFile(result, antlrResult);
        prettyPrintCache.put(file, result);
        return result;
    }

    /**
     * Get the full absolute path to the result file produced by this repairer and the given subject.
     *
     * @param subject      Subject that was evaluated. May be null if the algorithm is independent of the subject under test.
     * @param origFilePath The full path of the original file
     * @return the full absolute path to the result file
     */
    public Path getResultPathFor(@Nullable Subjects subject, @Nonnull Path origFilePath) {
        return this.resultPath;
    }

    /**
     * @return the test name
     */
    @Override
    public final String toString() {
        return this.NAME();
    }

    /**
     * @return true, if randomness is involved in the test run, e.g. for Random and Genetic algorithms
     */
    public boolean isRandomTest() {
        return false;
    }
}
