package erepair.program.db;

import erepair.program.InputFormat;
import erepair.program.db.record.*;
import erepair.program.differencing.DifferencingAlgorithms;
import erepair.program.subject.Oracle;
import erepair.program.subject.Subjects;
import erepair.program.Algorithm;
import org.json.JSONArray;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * A file record represents a record for a file under test.
 * <p>
 * Unsolved TODOs:
 * <ul>
 * <li> Create a per-file hash (multi threaded?) to make sure that each file is treated uniquely</li>
 * </ul>
 *
 */
public class FileRecord extends JSONRecord {
    /**
     * All fields that are included in the JSON file,
     * MUST be subtypes of PropertyRecord
     */
    private static final String sizeOfOriginal = "size";
    private static final String sizeOfUnmutated = "unmutated_size";
    private static final String fileFormat = "format";
    private static final String unmutatedFile = "unmutated_path";


    /**
     * General Statistics for the file, e.g. File Size, etc.
     */
    @JSONField(index = 0)
    final StringLongPropertyRecord generalStats = new StringLongPropertyRecord(0);
    /**
     * The algorithm success value for each run. True means success
     */
    @JSONField(index = 1)
    final TwoLevelBooleanPropertyRecord algorithmSuccessRecord = new TwoLevelBooleanPropertyRecord(false);
    /**
     * The number of tests for each algorithm
     */
    @JSONField(index = 2)
    final TwoLevelLongPropertyRecord algorithmTestCountRecord = new TwoLevelLongPropertyRecord(0);
    /**
     * The number of oracle runs for each run
     */
    @JSONField(index = 3)
    final TwoLevelLongPropertyRecord oracleRuns = new TwoLevelLongPropertyRecord(0);
    /**
     * Time needed to rectify the file with the given algorithm
     */
    @JSONField(index = 4)
    final TwoLevelLongPropertyRecord algorithmTimeRecord = new TwoLevelLongPropertyRecord(0);
    /**
     * The P-Hash A value for each run
     */
    @JSONField(index = 5)
    final TwoLevelLongPropertyRecord phashARecord = new TwoLevelLongPropertyRecord(0);
    /**
     * The P-Hash B value for each run
     */
    @JSONField(index = 6)
    final TwoLevelLongPropertyRecord phashBRecord = new TwoLevelLongPropertyRecord(0);
    /**
     * The Levenshtein Distance between the invalid and the rectified file for each algorithm
     */
    @JSONField(index = 7)
    final TwoLevelLongPropertyRecord levenshteinRecord = new TwoLevelLongPropertyRecord(0);
    /**
     * The path of related files, e.g. the valid non-mutated file.
     * Also contains the format of the file
     */
    @JSONField(index = 8)
    final StringPropertyRecord relatedFiles = new StringPropertyRecord("");
    /**
     * The path of the Rectified files for each run
     */
    @JSONField(index = 9)
    final TwoLevelStringPropertyRecord rectifiedFiles = new TwoLevelStringPropertyRecord("");
    /**
     * The reason why a subject failed on a certain file. If empty, the subject succeeded.
     */
    @JSONField(index = 10)
    final SubjectStringPropertyRecord failReasons = new SubjectStringPropertyRecord("");
    /**
     * The rectified file sizes
     */
    @JSONField(index = 11)
    final TwoLevelLongPropertyRecord rectifiedFileSize = new TwoLevelLongPropertyRecord(0L);
    /**
     * The information if coverage has already been measured to avoid measuring the same file twice
     */
    @JSONField(index = 12)
    final SubjectBooleanPropertyRecord coverageDetermined = new SubjectBooleanPropertyRecord(false);
    /**
     * The number of Insertions for each algorithm
     */
    @JSONField(index = 13)
    final TwoLevelLongPropertyRecord algorithmbFuzzerInsertionsRecord = new TwoLevelLongPropertyRecord(0);
    /**
     * The number of Deletions for each algorithm
     */
    @JSONField(index = 14)
    final TwoLevelLongPropertyRecord algorithmbFuzzerDeletionsRecord = new TwoLevelLongPropertyRecord(0);


    /**
     * Instantiates a new empty FileRecord
     */
    public FileRecord() {
    }

    /**
     * Load a file record from an existing JSON Array
     *
     * @param jsonArray JSON Array that represents the File Record
     * @throws IllegalAccessException if the JSON Array is malformed
     */
    public FileRecord(JSONArray jsonArray) throws IllegalAccessException {
        super.loadFromJSON(jsonArray);
    }

    /**
     * Set the fail reason for a subject. Set to "" if the subject succeeded
     *
     * @param subject    Subject
     * @param failReason Fail reason or "" if succeeded
     * @param format     Format of the file
     */
    public void setFailReason(@Nonnull Subjects subject, @Nonnull String failReason, @Nonnull InputFormat format) {
        this.failReasons.put(subject, failReason);
        this.setFormat(format);
    }

    /**
     * Get the fail reason for the given subject
     *
     * @param subject Subject to query
     * @return the fail reason or "" if the subject succeeded
     */
    @Nonnull
    public String getFailReason(Subjects subject) {
        return this.failReasons.get(subject);
    }

    /**
     * @return true, iff all JSON-storable fields of this class are empty
     */
    public boolean isEmpty() {
        return jsonFields.stream().allMatch(field -> {
            try {
                return ((HashMap<?, ?>) field.get(this)).isEmpty();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    /**
     * Get the format of the file
     *
     * @return the format as string
     */
    @Nullable
    public InputFormat getFormat() {
        if (this.relatedFiles.containsKey(fileFormat)) {
            return InputFormat.fromString(this.relatedFiles.get(fileFormat));
        } else {
            return null;
        }
    }

    /**
     * Set the format of the file to the specified value
     *
     * @param format Format of the file
     */
    public void setFormat(InputFormat format) {
        this.relatedFiles.put(fileFormat, format.toString().toLowerCase());
    }

    /**
     * Checks if the file has the given format
     *
     * @param format Format to check
     * @return true, if the file has the given format
     */
    public boolean isFormat(@Nonnull InputFormat format) {
        return this.relatedFiles.containsKey(fileFormat) && format.equals(InputFormat.fromString(this.relatedFiles.get(fileFormat)));
    }

    /**
     * Get the size of the file on disk in bytes
     *
     * @return the size in bytes or -1 if the size has never been set
     */
    public long getSize() {
        return this.generalStats.getOrDefault(sizeOfOriginal, -1L);
    }

    /**
     * Set the size of the file on the disk in bytes
     *
     * @param size The new size in bytes
     */
    public void setSize(long size) {
        this.generalStats.put(sizeOfOriginal, size);
    }

    /**
     * Report a test run result
     *
     * @param algorithm          Algorithm that was used for rectification
     * @param subject            Subject that tested the file
     * @param time               Run time that the algorithm took to repair the file, without the validating subject runs (except for algorithms that include subject runs, e.g. DDMax) in ms
     * @param success            True, if the test was a success, false otherwise
     * @param numberOfOracleRuns The number of oracle runs that the test itself took to rectify the file
     * @param rectifiedFile      The rectified file path. Ignored if the test failed. Must not be null if the test succeeded.
     * @param rectifiedFileSize  The size of the rectified file in bytes
     * @throws IllegalArgumentException if the test succeeded, but the rectified file was null
     */
    public void setTestResult(Subjects subject, Algorithm algorithm, long time, boolean success, long numberOfOracleRuns, @Nullable Path rectifiedFile, long rectifiedFileSize) throws IllegalArgumentException {
        if (success && rectifiedFile == null) {
            throw new IllegalArgumentException("A path for the rectified file must be specified if the oracle succeeded");
        }
        this.algorithmSuccessRecord.getAndPut(subject).put(algorithm, success);
        this.algorithmTimeRecord.getAndPut(subject).put(algorithm, time);
        this.oracleRuns.getAndPut(subject).put(algorithm, numberOfOracleRuns);
        if (success) {
            this.rectifiedFiles.getAndPut(subject).put(algorithm, rectifiedFile.toString());
            this.rectifiedFileSize.getAndPut(subject).put(algorithm, rectifiedFileSize);
        }
    }

    /**
     * Set the file before it was mutated, e.g. for artificially mutated files
     *
     * @param path_prettyprinted     The path of the unmutated file
     * @param filesize_prettyprinted The size in bytes
     */
    public void setUnmutatedFile(Path path_prettyprinted, long filesize_prettyprinted) {
        this.relatedFiles.put(unmutatedFile, path_prettyprinted.toString());
        this.generalStats.put(sizeOfUnmutated, filesize_prettyprinted);
    }

    public @Nullable
    Path getUnmutatedFile() {
        if (this.relatedFiles.containsKey(unmutatedFile)) {
            return Paths.get(this.relatedFiles.get(unmutatedFile));
        }
        return null;
    }

    /**
     * Set the result of the distancing algorithm
     *
     * @param subject               Subject that was tested
     * @param algorithm             Algorithm that was used for rectification
     * @param differencingAlgorithm Algorithm that was used for distancing
     * @param value                 Value that was the result of the distancing algorithm
     * @throws IllegalArgumentException if an invalid distancing algorithm was supplied. Supported are {@link DifferencingAlgorithms#LEVENSHTEIN}, {@link DifferencingAlgorithms#PHASH_A} and {@link DifferencingAlgorithms#PHASH_B}
     */
    public void setDistancingResult(Subjects subject, Algorithm algorithm, DifferencingAlgorithms differencingAlgorithm, long value) throws IllegalArgumentException {
        switch (differencingAlgorithm) {
            case LEVENSHTEIN:
                this.levenshteinRecord.getAndPut(subject).put(algorithm, value);
                break;
            case PHASH_A:
                this.phashARecord.getAndPut(subject).put(algorithm, value);
                break;
            case PHASH_B:
                this.phashBRecord.getAndPut(subject).put(algorithm, value);
                break;
            default:
                throw new IllegalArgumentException("This differencing algorithm cannot be stored in the database: " + differencingAlgorithm.toString());
        }
    }

    /**
     * Checks if the given algorithm has been tested for all viable subjects
     *
     * @param algorithm
     * @return
     */
    public boolean wasTestedWith(Algorithm algorithm) {
        if (this.getFormat() == null) {
            return false;
        }
        return Oracle.getSubjectsFor(this.getFormat()).stream().allMatch(subj -> {
//            if (!this.wasTestedWith(subj.getKind(), algorithm)) {
//                System.err.println("A file was not tested with " + subj.getKind() + " " + algorithm + "! Available " + this.algorithmSuccessRecord.keySet().stream().map(Subjects::toString).collect(Collectors.joining(",")));
//            }
            return this.wasTestedWith(subj.getKind(), algorithm);
        });
    }

    /**
     * Checks if the given algorithm has been tested for the given subject
     *
     * @param subject   Subject
     * @param algorithm Algorithm
     * @return true, if tested
     */
    public boolean wasTestedWith(Subjects subject, Algorithm algorithm) {
//        if (!this.algorithmSuccessRecord.get(subject).containsKey(algorithm)) {
//            System.err.println("A file was not tested with " + subject + " " + algorithm + "! Available " + String.join(",", this.algorithmSuccessRecord.get(subject).keySet().stream().map(s -> s.toString()).collect(Collectors.toList())));
//        }
        return this.algorithmSuccessRecord.get(subject).containsKey(algorithm);
    }

    /**
     * Checks if this file has unmutated files
     *
     * @return true, if it has unmutated files
     */
    public boolean hasUnmutatedFiles() {
        return this.relatedFiles.containsKey(unmutatedFile);
    }

    public boolean getSuccess(Subjects subject, Algorithm algorithm) {
        return this.algorithmSuccessRecord.get(subject).get(algorithm);
    }

    /**
     * Get the size of the rectified file for the given properties
     *
     * @param subject   Subject
     * @param algorithm Algorithm
     * @return the size
     */
    public long getSizeOfRectifiedFile(Subjects subject, Algorithm algorithm) {
        return this.rectifiedFileSize.get(subject).get(algorithm);
    }

    /**
     * Marks the given subject as coverage determined.
     *
     * @param subject Subject to mark
     */
    public void setCoverageDeterminedFor(Subjects subject) {
        coverageDetermined.put(subject, true);
    }

    public boolean isCoverageDetermined(Subjects subject) {
        return coverageDetermined.getOrDefault(subject, false);
    }

    /**
     * Set the stats of bFuzzer Repair algorithms for the given file
     *
     * @param subject       Subject that was evaluated
     * @param algorithm     Algorithm that was performed
     * @param numInsertions Number of insertions
     * @param numDeletions  Number of deletions
     * @throws RuntimeException if the given algorithm is not powered by bFuzzer
     */
    public void setbFuzzerRepairStats(Subjects subject, Algorithm algorithm, long numInsertions, long numDeletions) {
        if (!algorithm.isBFuzzerAlgorithm()) {
            throw new RuntimeException("Can only report Insertions/Deletions for erepair-Powered Algorithm, not for " + algorithm);
        }
        this.algorithmbFuzzerInsertionsRecord.getAndPut(subject).put(algorithm, numInsertions);
        this.algorithmbFuzzerDeletionsRecord.getAndPut(subject).put(algorithm, numDeletions);
    }

    /**
     * Get the number of insertions done by the repair algorithm
     *
     * @param subject   Subject that was tested
     * @param algorithm Algorithm that was executed
     * @return the number of insertions or {@link Optional#empty()} if not tested or not applicable for the given algorithm
     */
    public Optional<Long> getNumberOfInsertions(Subjects subject, Algorithm algorithm) {
        return this.getIfApplicableForBFuzzerAlgorithm(this.algorithmbFuzzerInsertionsRecord, subject, algorithm);
    }

    /**
     * Get the number of deletions done by the repair algorithm
     *
     * @param subject   Subject that was tested
     * @param algorithm Algorithm that was executed
     * @return the number of deletions or {@link Optional#empty()} if not tested or not applicable for the given algorithm
     */
    public Optional<Long> getNumberOfDeletions(Subjects subject, Algorithm algorithm) {
        return this.getIfApplicableForBFuzzerAlgorithm(this.algorithmbFuzzerDeletionsRecord, subject, algorithm);
    }

    public Optional<Long> getTime(Subjects subject, Algorithm algorithm) {
        if (!this.algorithmTimeRecord.containsKey(subject)) {
            return Optional.empty();
        }
        if (!this.algorithmTimeRecord.get(subject).containsKey(algorithm)) {
            return Optional.empty();
        }
        return Optional.of(this.algorithmTimeRecord.get(subject).get(algorithm));
    }

    public Optional<Long> getNumberOfOracleRuns(Subjects subject, Algorithm algorithm) {
        if (!this.oracleRuns.containsKey(subject)) {
            return Optional.empty();
        }
        if (!this.oracleRuns.get(subject).containsKey(algorithm)) {
            return Optional.empty();
        }
        return Optional.of(this.oracleRuns.get(subject).get(algorithm));
    }

    /**
     * Get an optional from a container if the given algorithm is a bFuzzer-powered algorithm
     *
     * @param container Container to query
     * @param subject   Subject that was evaluated
     * @param algorithm Algorithm that was executed
     * @param <T>       Type of the subject
     * @param <U>       Type of the algorithm
     * @param <V>       Return Type
     * @return the queried number or {@link Optional#empty()} if not tested or not applicable for the given algorithm
     */
    private <T extends Serializable, U extends Algorithm, V extends Serializable> Optional<V> getIfApplicableForBFuzzerAlgorithm(TwoLevelPropertyRecord<T, U, V> container, T subject, U algorithm) {
        if (!algorithm.isBFuzzerAlgorithm()) {
            return Optional.empty();
        }
        if (!container.containsKey(subject) || !container.get(subject).containsKey(algorithm)) {
            return Optional.empty();
        }
        return Optional.of(container.get(subject).get(algorithm));
    }

    /**
     * Get the result of a given differencing algorithm for the given algorithm and subject.
     *
     * @param diffalgorithm Differencing Algorithm
     * @param subject       Subject
     * @param algorithm     Algorithm
     * @return {@link Optional#empty()} if the given algorithm has not been evaluated for the given algorithm and subject, the value otherwise.
     */
    public Optional<Long> getDifferencingResult(DifferencingAlgorithms diffalgorithm, Subjects subject, Algorithm algorithm) {
        TwoLevelLongPropertyRecord source;
        switch (diffalgorithm) {
            case LEVENSHTEIN:
                source = this.levenshteinRecord;
                break;
            case PHASH_A:
                source = this.phashARecord;
                break;
            case PHASH_B:
                source = this.phashBRecord;
                break;
            default:
                return Optional.empty();
        }
        if (!source.containsKey(subject)) {
            return Optional.empty();
        }
        if (!source.get(subject).containsKey(algorithm)) {
            return Optional.empty();
        }
        return Optional.of(source.get(subject).get(algorithm));
    }

    public void forEachSubject(BiConsumer<Algorithm, Subjects> fn) {
        this.algorithmSuccessRecord.forEach((subj, propertyRecord) -> propertyRecord.forEach((algo, bool) -> fn.accept(algo, subj)));
    }

}
