package erepair.program.repairer.erepair;

import erepair.program.Loggable;
import erepair.program.Logging;
import erepair.program.Main;
import erepair.program.repairer.Repairer;
import erepair.program.subject.Subject;
import erepair.program.subject.SubjectStatus;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.stream.Collectors;

class FoundCorrectInput extends Exception {
    public final Object input;

    public FoundCorrectInput(Object input) {
        this.input = input;
    }
}

/**
 **/
public abstract class erepairRepairer<FileContent> extends Repairer {
    private static final Random random = new Random(0); // Seed the random generator
    static int nextChangeID = 0;
    private final Map<FileContent, SubjectStatus> testCache = new HashMap();
    Subject subject;

    @SuppressWarnings("unused")
    public erepairRepairer(Path file) {
        super(file);
    }

    abstract FileContent readFile(Path file);

    abstract int length(FileContent fileContent);

    @SuppressWarnings("ObjectAllocationInLoop")
    private void addAllToQueue(Map<Integer, List<Change>> queue, Collection<Change> toAdd) {
        for (final Change c : toAdd) {
            final Integer edist = c.editingDistance;
            if (!queue.containsKey(edist)) {
                queue.put(edist, new ArrayList<>());
            }
            queue.get(edist).add(c);
        }
    }

    /**
     * Sort by boundary in descending order and limit number of distinct boundaries to {@link Main#MAX_SIMULTANEOUS_CORRECTIONS}
     *
     * @param changes Iterator of changes
     * @param max_num Number of items to limit the result to. If -1 is given, do not limit the number
     * @return
     */
    @SuppressWarnings("SameParameterValue")
    private Iterator<Change> filterBest(Iterator<Change> changes, int max_num) {
        if (max_num < 0) {
            return changes;
        }
        // This is rather inefficient, but impossible to do otherwise with an iterator
        final List<Change> tempList = new ArrayList<>(Math.min(8192, max_num * 128));
        while (changes.hasNext()) {
            tempList.add(changes.next());
        }

        Set<Integer> boundariesEncountered = new HashSet<>();

        // Sort by boundary in descending order and limit number of elements to {@link Main#MAX_SIMULTANEOUS_CORRECTIONS}
        return tempList.stream()
                .sorted(Comparator.comparingInt((Change c) -> c.current_position).reversed())
                .filter(change -> {
                    boundariesEncountered.add(change.current_position);
                    return boundariesEncountered.size() <= max_num; // Only pass through change if max_num has not been reached
                })
                .iterator();
    }

    /**
     * Sample the elements with the lowest priority and return {@link Main#erepair_SAMPLING_SIZE} of each sample category.
     * Remove the elements from the queue.
     * <p>
     * The categories are defined in {@link Change#maskHashCode()}
     *
     * @param PQ The queue
     * @return all sampled elements
     * @throws NoSuchElementException if there is no value for the lowest key of the queue (empty queue?)
     */
    @SuppressWarnings({"ObjectAllocationInLoop", "ConstantConditions", "ConstantAssertCondition", "Convert2Diamond"})
    private Iterator<Change> sampleLowestPriority(SortedMap<Integer, List<Change>> PQ) throws NoSuchElementException {
        assert Main.erepair_SAMPLING_SIZE > 0 : "Invalid Sampling Size: " + Main.erepair_SAMPLING_SIZE;
        if (PQ.isEmpty()) {
            throw new NoSuchElementException("Tried to sample, but the Queue was empty");
        }
        final List<Change> population = PQ.remove(PQ.firstKey()); // Pop first elements
        final Map<Integer, List<Change>> samplingMap = new LinkedHashMap<>(); // Preserve the order to get determinism
        for (final Change c : population) {
            final Integer key = c.maskHashCode();
            if (!samplingMap.containsKey(key)) {
                samplingMap.put(key, new ArrayList<>());
            }
            samplingMap.get(key).add(c);
        }
        Logging.generalLogger.finest("Sampled populations: {" + samplingMap.values().stream().map(l -> Integer.toString(l.size())).collect(Collectors.joining(",")) + "}");

        return new Iterator<Change>() {
            final Iterator<List<Change>> samplinglists = samplingMap.values().iterator();
            List<Change> current = null;
            int current_idx = 0;

            @Override
            public boolean hasNext() {
                return samplinglists.hasNext() || current != null;
            }

            @Override
            public Change next() {
                if (current == null) {
                    final var newList = samplinglists.next();
                    if (newList.size() <= Main.erepair_SAMPLING_SIZE) {
                        assert newList.size() > 0;
                        current = newList;
                    } else {
                        // Sample N Changes from the list
                        current = new ArrayList<>(Main.erepair_SAMPLING_SIZE);
                        random.ints(0, newList.size()).distinct().limit(Main.erepair_SAMPLING_SIZE)
                                .forEach(i -> current.add(newList.get(i)));
                        assert current.size() == Main.erepair_SAMPLING_SIZE;
                    }
                    current_idx = 0;
                }
                final Change ret = current.get(current_idx);
                current_idx++;
                if (current_idx >= current.size()) {
                    current = null;
                }
                return ret;
            }
        };
    }

    /**
     * The erepair algorithm that deletes and inserts characters into a file.
     * It works like follows:
     * <ol>
     *     <li>TODO</li>
     * </ol>
     *
     * @param file    File to repair
     * @param subject Subject to repair the file for
     * @return the repaired file
     */
    @SuppressWarnings({"ObjectAllocationInLoop", "HardcodedLineSeparator"})
    @Override
    protected Path repair(Path file, Subject subject) {
        OutOfMemoryError outOfMemoryError = null;
        long timeStarted = System.currentTimeMillis();
        this.subject = subject;
        FileContent fileContent = this.readFile(file); // Read the whole file
        Path outputFile = getResultPathFor(subject.getKind(), file);

        // As priority queue, we take a sorted Map of lists for each editing distance.
        // This allows us for quick and deterministic access to all elements of a certain edist.
        SortedMap<Integer, List<Change>> PQ = new TreeMap<>();

        // Iterator for the samples
        Iterator<Change> samples = Collections.emptyIterator();

        // Initial Binary Search to get a starting prefix.
        // This improves performance
        final SubjectStatus status = this.test(fileContent);
        final int initialIndex = BinarySearch.binarySearchFaultLocation(new BinarySearchableImpl(fileContent), 0, this.length(fileContent), this::testWrapper);
        Change currentChange = new Change(
                0,
                initialIndex,
                this.substring(fileContent, 0, initialIndex),
                fileContent,
                BinarySearch.latestBinarySearchStatus,
                erepairRepairer.nextChangeID++,
                "Initial Queue Element with fault location at pos " + initialIndex,
                0,
                0,
                "" // The initial mask is empty!
        );
        addAllToQueue(PQ, List.of(currentChange.tryExtend(this::test)));

        try {
            // Main loop - We iterate until we have a successful input that completely consumed the original file.
            while (!currentChange.currentStatus.wasSuccessful() || currentChange.current_position < this.length(currentChange.originalFileContent)) {
                boolean breakall = false;
                // Add all possible changes to the queue. Cancel this immediately, if a successful change has been found
                while (samples.hasNext() && !currentChange.isFinished()) {
                    currentChange = samples.next();
                    if (currentChange.currentStatus.wasSuccessful()) {
                        Logging.generalLogger.log(Level.FINEST, "Encountered successful prefix `" + currentChange.current_prefix + "` with position " + currentChange.current_position + "/" + this.length(currentChange.originalFileContent) + " in `" + currentChange.originalFileContent + "`, remaining prefix: `" + ((currentChange.current_position < length(currentChange.originalFileContent)) ? this.substring(currentChange.originalFileContent, currentChange.current_position, length(currentChange.originalFileContent)) : "") + "`");
                        if (currentChange.isFinished()) {
                            throw new FoundCorrectInput(currentChange);
                        }
                    }
                    Logging.generalLogger.log(Level.FINEST, "Attempting " + currentChange.currentStatus.enumtext() + " change " + currentChange.id + " (" + currentChange.description + ") [" + currentChange.editingDistance + "] {" + currentChange.getMask() + "}, Queue Size " + PQ.size() + " editing distances" + " with prefix: " + this.DebugPrintPrefix(currentChange.current_prefix) /*+ ", intermediate file " + intermediateFile.toString()*/);
                    addAllToQueue(PQ, currentChange.getAllPossibleChanges(this::test));

                    if ((System.currentTimeMillis() - timeStarted) >= Main.timeoutPerFile) {
                        Logging.generalLogger.log(Level.WARNING, "erepair timed out!");
                        breakall = true;
                        break;
                    }
                }
                if (breakall) break;
                try {
                    assert !samples.hasNext() || (currentChange.currentStatus.wasSuccessful() && currentChange.current_position >= this.length(currentChange.originalFileContent)) : "Forgot to set breakall?";
                    samples = filterBest(sampleLowestPriority(PQ), Main.MAX_SIMULTANEOUS_CORRECTIONS); // Sample all Changes with the lowest priority and remove from Queue
                } catch (NoSuchElementException e) {
                    Logging.generalLogger.log(Level.SEVERE, "Queue was empty testing file " + file +
                            ",\n\tcurrent prefix: " + this.DebugPrintPrefix(currentChange.current_prefix) +
                            "\n\tcurrent EDist: " + currentChange.editingDistance, e);
                    Logging.generalLogger.log(Level.WARNING, "The file could not be repaired because all viable alternatives resulted in a failing oracle.");
                    if (Main.RETURN_INCOMPLETE_FILES) {
                        break;
                    } else {
                        throw e;
                    }
                }
            }
        } catch (OutOfMemoryError e) {
            Logging.generalLogger.log(Level.SEVERE, "erepair ran out of memory with a queue size of " + PQ.size() + " editing distances (" + PQ.values().stream().mapToInt(List::size).sum() + " changes)", e);
            System.gc();
            outOfMemoryError = e;
        } catch (FoundCorrectInput foundCorrectInput) {
            //noinspection unchecked
            currentChange = (Change) foundCorrectInput.input;
        }

        this.writeFile(outputFile, currentChange.current_prefix);
        // To get the actual number of deletions, consider the non-appended parts of the original file content!
        super.reportToDatabase(file, this.getAlgorithmKind(), this.subject.getKind(), currentChange.numInsertions, currentChange.numDeletions + (this.length(currentChange.originalFileContent) - currentChange.current_position));
        if (outOfMemoryError != null) {
//            super.reportToDatabase(file, this.getAlgorithmKind(), this.subject.getKind(), "OutOfMemoryError", outOfMemoryError.getMessage());
        } else {
            //Remove database key here?
        }
        return outputFile;
    }

    abstract String DebugPrintPrefix(FileContent prefix);

    abstract void writeFile(Path file, FileContent content);

    abstract SubjectStatus runTestOracle(FileContent fileContent);

    abstract FileContent substring(FileContent content, int a, int b);

    private SubjectStatus test(FileContent fileContent) {
        if (!testCache.containsKey(fileContent)) {
            final SubjectStatus stat = runTestOracle(fileContent);
            super.incrementSubjectRuns(this.subject.getKind());
            testCache.put(fileContent, stat);
        }
        return testCache.get(fileContent);
    }

    private SubjectStatus testWrapper(BinarySearchable<FileContent> wrapper) {
        return this.test(wrapper.get());
    }

    abstract FileContent append(FileContent a, FileContent b);

    abstract FileContent charAt(FileContent content, int index);

    abstract Iterable<Tuple<FileContent, Optional<CharacterClass>>> replacementsIteratorFactory();

    abstract boolean replacementSkip(Tuple<FileContent, Optional<CharacterClass>> replacement, FileContent currentPrefix);

    /**
     * Debug-Print a human-readable version of the first char of the file content.
     * The File Content is always exactly 1 characters long
     *
     * @param c File content to print, exactly 1 character long
     * @return a human-readable version of the content
     */
    abstract String debugPrintChar(FileContent c);


    private class BinarySearchableImpl implements BinarySearchable<FileContent> {
        final FileContent content;

        private BinarySearchableImpl(FileContent content) {
            this.content = content;
        }

        @Override
        public int length() {
            return erepairRepairer.this.length(this.content);
        }

        @Override
        public BinarySearchable substring(int a, int b) {
            return new BinarySearchableImpl(erepairRepairer.this.substring(this.content, a, b));
        }

        @Override
        public FileContent get() {
            return this.content;
        }

        @Override
        public String toString() {
            return content.toString();
        }
    }

    public class Tuple<T, U> {
        private final T t;
        private final U u;

        public Tuple(T t, U u) {
            this.t = t;
            this.u = u;
        }

        public T getA() {
            return t;
        }

        public U getB() {
            return u;
        }
    }

    /**
     * A change object that represents a possible change of a subject file.
     * The toString() method is overridden to give the generated file's content, to be tested with a subject program.
     *
     */
    public class Change extends Loggable implements Comparable<Change>, Serializable {
        public final FileContent current_prefix;
        public final int editingDistance;
        public final FileContent originalFileContent;
        public final int current_position;
        public final SubjectStatus currentStatus;
        /**
         * The ID of this change for debugging purposes
         */
        public final int id;
        /**
         * The description of this change, for debugging purposes
         */
        public final String description;
        public final long numInsertions;
        public final long numDeletions;
        private final String mask;

        /**
         * Instantiate a new Change object.
         *
         * @param editingDistance     Editing distance of the change object
         * @param current_position    Current position in the original file content, i.e. the index of the next character to be inserted.
         *                            If current_position >= originalFileContent.length, the whole original input has been processed.
         * @param current_prefix      The current prefix of the repaired input file
         * @param originalFileContent The full immutable content of the original (corrupted) file
         * @param currentStatus       The subject status of the latest run
         * @throws NullPointerException if one of the arguments is NULL
         */
        public Change(
                int editingDistance,
                int current_position,
                @Nonnull FileContent current_prefix,
                @Nonnull FileContent originalFileContent,
                @Nullable SubjectStatus currentStatus,
                int id,
                String changeDescription,
                long numInsertions,
                long numDeletions,
                String mask) {
            super("erepair");
            this.numInsertions = numInsertions;
            this.numDeletions = numDeletions;
            this.mask = mask;
            Objects.requireNonNull(current_prefix);
            Objects.requireNonNull(originalFileContent);
            this.currentStatus = Objects.requireNonNullElse(currentStatus, new SubjectStatus("Subject did not run yet"));
            this.editingDistance = editingDistance;
            this.current_position = current_position;
            this.current_prefix = current_prefix;
            this.originalFileContent = originalFileContent;
            this.id = id;
            this.description = changeDescription;
        }

        /**
         * there are many more invalid inserts than valid inserts. So searching the whole file again is not useful.
         * Implemented analog to Rahul's algorithm.
         * <p>
         * This replaces binary search after an insertion to avoid having to run the subject oracle too often unnecessarily.
         *
         * @return the resulting change, which might be the same change as the current one.
         */
        public Change lsearchExtendItem(Function<FileContent, SubjectStatus> testSubject) {
            int NXT = 1; // Advancement to start with
            SubjectStatus previousOutcome = null;

            while (true) {
                // Check if we are already at the end. If we are, nothing needs to be tested since the current position cannot be further advanced.
                if (this.current_position + NXT > length(this.originalFileContent)) {
                    if (previousOutcome == null) {
                        return this; // We did not change anything
                    }
                    return new Change(
                            this.editingDistance,//The editing distance stays the same
                            current_position + NXT,//Advance orig by NXT
                            append(current_prefix, substring(originalFileContent, this.current_position, length(originalFileContent))),//Previous prefix with all remaining characters appended
                            originalFileContent,
                            previousOutcome, // The previous outcome is the one belonging to this prefix!
                            erepairRepairer.nextChangeID++,
                            "Appended remaining with NXT=" + NXT + " to " + this.description,
                            numInsertions,
                            numDeletions,
                            this.mask
                    );
                }

                // Check if appending NXT characters to the prefix changes outcome
                final FileContent probe_prefix = append(current_prefix, substring(originalFileContent, this.current_position, this.current_position + NXT));
                final SubjectStatus outcome = testSubject.apply(probe_prefix);
                if (outcome.wasIncomplete()) {
                    NXT++; // Still incomplete, so advance NXT and continue
                    previousOutcome = outcome;
                    continue;
                }
                if (outcome.wasIncorrect()) {
                    // Advancing the NXT caused the input to become invalid.
                    NXT--;
                    if (NXT == 0) {
                        return this; // Could not change anything
                    }
                    // We already tested this, so we can simply return it
                    return new Change(
                            this.editingDistance,//The editing distance stays the same
                            current_position + NXT,//Advance orig by NXT
                            append(current_prefix, substring(originalFileContent, this.current_position, this.current_position + NXT)),//Previous incomplete prefix
                            originalFileContent,
                            previousOutcome, // The previous outcome is the one belonging to this prefix!
                            erepairRepairer.nextChangeID++,
                            "Appended NXT=" + NXT + " characters from original input to " + this.description,
                            numInsertions,
                            numDeletions,
                            this.mask
                    );
                }
                if (outcome.wasSuccessful()) {
                    // Fall through and continue, the rest of the loop will take care of returning the appropriate Change
                    NXT++;
                    previousOutcome = outcome;
                    continue;
                }
                assert false : "Unreachable";
                NXT++;
                previousOutcome = outcome;
            }
        }

        /**
         * Try to extend this Change by increasing the boundary size, if possible
         *
         * @return the resulting Change
         */
        public Change tryExtend(Function<FileContent, SubjectStatus> testSubject) {
            if (this.current_position < length(this.originalFileContent)) {
                //Try to insert one character from the original file and see if it is accepted.
                //If accepted, do a binary search for the next fault location.
                final FileContent insertion = append(this.current_prefix, charAt(this.originalFileContent, this.current_position));
                final SubjectStatus ss = testSubject.apply(insertion);
                if (ss.wasIncomplete() || ss.wasSuccessful()) {

                    // Do a binary search to determine the new current_position
                    // and insert an appropriate Change into the set.
                    final FileContent fullPrefix = append(this.current_prefix, substring(this.originalFileContent, this.current_position, length(this.originalFileContent)));
                    final int newFaultLocation = BinarySearch.binarySearchFaultLocation(
                            new BinarySearchableImpl(fullPrefix), 0, length(fullPrefix), s -> testSubject.apply(s.get())
                    );
                    // Calculate the new fault position in the original input.
                    // Since we address the index of the next faulty character in the original input that has not yet been appended to the prefix,
                    // we need to calculate this index here.
                    final int newOrigFaultLocation = this.current_position + (newFaultLocation - length(current_prefix)); // The new position of the faulty location in the input

                    if (newOrigFaultLocation < this.current_position) {
//                        throw new AssertionError("The new fault location was " + newFaultLocation + " but the old location was " + this.current_position);
                    } else if (newOrigFaultLocation > this.current_position) {
                        final Change chg = new Change(
                                this.editingDistance,                       // The editing distance does not change, since we only append characters from the old file
                                newOrigFaultLocation,                           // The fault location found by Binary Search
                                substring(fullPrefix, 0, newFaultLocation),  // Everything up to the fault location that is still recognized as incomplete is appended to the current prefix
                                this.originalFileContent,
                                BinarySearch.latestBinarySearchStatus,
                                erepairRepairer.nextChangeID++,
                                "First Change of new Fault Location found at pos " + newOrigFaultLocation + " after " + this.description,
                                this.numInsertions,
                                this.numDeletions,
                                this.mask // Just the boundary changed, so the mask stays the same
                        );
                        log(Level.FINE, "Found a new fault location at " + newOrigFaultLocation + ".");
                        return chg;
                    }
                }
            }
            return this;
        }

        /**
         * Get all possible changes, all guaranteed to have a correct (incomplete) prefix
         * and an according editing distance.
         *
         * @param testSubject Subject test function that returns the SubjectStatus.
         *                    The oracle MUST reliably return "Incomplete" if the file under test was incomplete
         *                    and "incorrect" if it was incorrect!
         * @return all possible changes
         */
        @SuppressWarnings("ObjectAllocationInLoop")
        public Collection<Change> getAllPossibleChanges(Function<FileContent, SubjectStatus> testSubject) {
            List<Change> ret = new ArrayList<>(128);
            if (this.current_position < length(this.originalFileContent)) {

                // Introduce a change where we delete one character from the original content
                // (which adds 1 to the editing distance).
                // This proposed change does not need any testing since it will be tested in the binary search.
//                log(Level.FINEST, "Added Deletion of one character to the queue" + " [" + (this.editingDistance + 1) + "]");
                ret.add(new Change(this.editingDistance + 1,
                        this.current_position + 1,
                        this.current_prefix,
                        this.originalFileContent,
                        this.currentStatus,
                        erepairRepairer.nextChangeID++,
                        "Deletion of one Character",
                        this.numInsertions,
                        this.numDeletions + 1,
                        this.mask + "_D" + Integer.toString(length(this.current_prefix)) // Insert a deletion at the current position to the mask
                ).tryExtend(testSubject)); // Binary search after Deletion
            }

            //Try all possible insertions
            for (Tuple<FileContent, Optional<CharacterClass>> x : replacementsIteratorFactory()) {
                if (replacementSkip(x, this.current_prefix)) {
                    continue;
                }
                FileContent toreplace = x.getA();
                Optional<CharacterClass> characterClass = x.getB();
                // Append the character to the prefix and run a test
                final FileContent sample_string = append(this.current_prefix, toreplace);
                final SubjectStatus status = testSubject.apply(sample_string);
                if (status.wasIncomplete() || status.wasSuccessful()) {
                    boolean canReplaceWithClass = false;
                    //log(Level.FINEST, "Added Character \"" + debugPrintChar(toreplace) + "\" to queue" + " [" + (this.editingDistance + 1) + "]");
                    ret.add(new Change(
                            editingDistance + 1,
                            current_position,
                            append(current_prefix, toreplace),
                            originalFileContent,
                            status,
                            erepairRepairer.nextChangeID++,
                            "Insertion of one character (" + debugPrintChar(toreplace) + ")",
                            this.numInsertions + 1,
                            this.numDeletions,
                            this.mask + "_I" + Integer.toString(length(this.current_prefix))
                    ).lsearchExtendItem(testSubject)); // Special lsearch after insertion to save some subject program runs
                }
            }

            return ret;
        }


        @Override
        public int compareTo(Change o) {
            int ret = Integer.compare(this.editingDistance, o.editingDistance);
            if (ret == 0) {
                // If both have the same editing distance, compare the number of characters that are already consumed
                // from the original input to prioritize inputs that have already moved further.
                // This should cause the algorithm to terminate faster.
                // Notice the argument order is different from the call above:
                ret = Integer.compare(o.current_position, this.current_position);
            }
            return ret;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            @SuppressWarnings("unchecked") Change change = (Change) o; // As long as we only make use of the Change class inside the erepair class, this should not be a problem
            return editingDistance == change.editingDistance &&
                    current_position == change.current_position &&
                    originalFileContent.equals(change.originalFileContent) &&
                    current_prefix.equals(change.current_prefix);
        }

        /**
         * Get an unique hash code of this change.
         * Includes all information that is relevant to the outcome of the change, but NO debugging information!
         * i.e., two changes with a different ID may have the same HashCode
         *
         * @return the HashCode
         */
        @SuppressWarnings("ObjectInstantiationInEqualsHashCode")
        @Override
        public int hashCode() {
            return Objects.hash(editingDistance, originalFileContent, current_position, current_prefix);
        }

        /**
         * Get a hash code of this change's mask, for sampling similar changes in {@link erepairRepairer#sampleLowestPriority(SortedMap)}.
         * Includes all information that is relevant to the mask.
         * Two different changes might have the same Mask HashCode!
         *
         * @return the HashCode for this mask
         */
        public int maskHashCode() {
            if (length(current_prefix) > 1) {
                return Objects.hash(mask, length(current_prefix), charAt(current_prefix, length(current_prefix) - 1));
            } else {
                return Objects.hash(mask, length(current_prefix));
            }
        }

        /**
         * Get the editing distance from the current prefix to the original file's content.
         * Note that this does return the editing distance that we would have if we accepted this whole input as repair instead of just the prefix editing distance!
         *
         * @return the editing distance
         */
        public int getEditingDistance() {
            return this.editingDistance + (length(this.originalFileContent) - this.current_position);
        }

        /**
         * Get the mask, e.g. "_I3_D5"
         *
         * @return the mask
         */
        public String getMask() {
            return mask;
        }

        /**
         * Check if this Change is finished.
         * A change is finished if its status is Complete and the input has been completely consumed.
         *
         * @return true if this Change is finished
         */
        public boolean isFinished() {
            return (this.currentStatus.wasSuccessful() && this.current_position >= length(this.originalFileContent));
        }
    }
}


