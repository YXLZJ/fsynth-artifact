package erepair.program.deltadebugging;

import erepair.program.Loggable;
import erepair.program.subject.Oracle;
import erepair.program.subject.Subject;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * @param <T> Type of the elements that are minimized
 **/
public abstract class DD<T> extends Loggable {

    protected final T input;
    final String fileSuffix;
    final long timeout;
    final Subject oracle;
    Path tempfolder;
    long numberOfOracleRuns;
    DeltaSet lastAcceptedDeltaSet = new DeltaSet();
    private long time = 0;
    private DeltaSet exclusionSet = null;
    protected long elapsed = 0;

    /**
     * Instantiates the Delta Debugging class
     *
     * @param prefix          Prefix that is appended to the log and identifies the class that used the logger.
     * @param input           Input to minimize
     * @param timeoutInMillis Timeout
     * @param fileSuffix      Suffix without dot
     * @param oracle          Oracle that runs the files
     */
    public DD(String prefix, T input, long timeoutInMillis, String fileSuffix, Subject oracle) {
        super(prefix);
        this.timeout = timeoutInMillis;
        this.input = input;
        this.fileSuffix = fileSuffix;
        this.oracle = oracle;
        try {
            this.tempfolder = Files.createTempDirectory("deltadebugging");
        } catch (IOException e) {
            this.tempfolder = null;
            log(Level.SEVERE, "Could not create temporary directory!", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Excludes given DeltaSet from a list
     *
     * @param input    List to exclude from
     * @param deltaSet DeltaSet to exclude
     * @param <X>      Type of the list elements
     * @return List without the excluded parts
     */
    static <X> List<X> exclude(List<X> input, DeltaSet deltaSet) {
        ArrayList<X> ret = new ArrayList<>(input.size() - deltaSet.length());
        for (int i = 0; i < input.size(); i++) {
            if (!deltaSet.inside(i)) {
                ret.add(input.get(i));
            }
        }
        return ret;
    }

    /**
     * Intersects the given DeltaSet with a string.
     *
     * @param input    String to use for  intersection
     * @param deltaSet DeltaSet to intersect
     * @return Intersected part of the string
     */
    public static String intersect(String input, DeltaSet deltaSet) {
        StringBuilder ret = new StringBuilder(deltaSet.length());
        for (int i = 0; i < input.length(); i++) {
            if (deltaSet.inside(i)) {
                ret.append(input.charAt(i));
            }
        }
        return ret.toString();
    }

    /**
     * Intersects the given DeltaSet with a List of Strings.
     *
     * @param input    String to use for  intersection
     * @param deltaSet DeltaSet to intersect, must be a subset of {@code input}
     * @return Intersected part of the string
     */
    public static List<String> intersect(List<String> input, DeltaSet deltaSet) {
        String[] ret = new String[deltaSet.length()];
        int ind = 0;
        for (int i = 0; i < input.size(); i++) {
            if (deltaSet.inside(i)) {
                ret[ind++] = input.get(i);
            }
        }
        return Arrays.asList(ret);
    }

    /**
     * Generates a new DeltaInterval for the given granularity.
     *
     * @param deltaSet    DeltaSet, which is the environment the new DeltaInterval is based upon
     * @param granularity Granularity
     * @param i           Index of the Delta, between 0 and granularity-1
     * @return DeltaInterval
     */
    static DeltaInterval getGranularityInterval(DeltaSet deltaSet, int granularity, int i) {
        final int granuLength = deltaSet.length() / granularity;
        final int lb = deltaSet.getNthIndex(i * granuLength);
        final int ub;
        if (i != granularity - 1) {
            ub = deltaSet.getNthIndex((i + 1) * granuLength - 1) + 1;
        } else {
            ub = deltaSet.getNthIndex(deltaSet.length() - 1) + 1;
        }
        return new DeltaInterval(lb, ub);
    }

    /**
     * Tear down the DD class, which deletes the temp directories.
     * This method MUST be called after repairing a file, else there will be temp storage leaks
     */
    public void tearDown() {
        if (this.tempfolder.toFile().isDirectory()) {
            String[] tfiles = this.tempfolder.toFile().list();
            for (String s : tfiles) {
                File tfile = new File(this.tempfolder.toFile().getPath(), s);
                if (tfile.isFile()) {
                    tfile.delete();
                }
            }
            this.tempfolder.toFile().delete();
        }
    }

    /**
     * Checks if this instance is timed out
     *
     * @return true, if the instance has timed out
     */
    boolean isTimedOut() {
        this.elapsed = System.currentTimeMillis() - this.time;
        return this.elapsed >= timeout;
    }

    /**
     * Get the exclusion set that resulted in the previous algorithm run
     *
     * @return the exclusion set
     */
    public DeltaSet getExclusionSet() {
        return exclusionSet;
    }

    /**
     * Run the Delta Debugging algorithm on the given input
     *
     * @return the minimized input
     */
    public final T run() {
        this.time = System.currentTimeMillis();
        this.numberOfOracleRuns = 0;
        this.exclusionSet = runAlgorithm();
        return this.exclude(this.exclusionSet, this.input);
    }

    /**
     * Exclude the part given by the DeltaSet from the given input
     *
     * @param deltaInterval Part to exclude
     * @param input         Input to process
     * @return the input without the excluded part
     */
    protected abstract T exclude(DeltaSet deltaInterval, T input);

    /**
     * Run the algorithm and return the resulting DeltaInterval.
     *
     * @return the result
     */
    protected abstract DeltaSet runAlgorithm();

    /**
     * Get the length of the given input fragment
     *
     * @param input Input fragment
     * @return the length of the fragment
     */
    protected abstract int getLength(T input);

    /**
     * Write the part of the input that is not excluded by the exclusionSet into an output string to be written into a file
     *
     * @param exclusionSet Excluded part of the file
     * @param input        Input to write
     * @return the content of the output file
     */
    String writeString(DeltaSet exclusionSet, T input) {
        final T excluded = this.exclude(exclusionSet, input);
        return this.toString(excluded);
    }

    /**
     * Converts the given input to a string
     *
     * @param input Input to convert
     * @return the resulting String
     */
    protected abstract String toString(T input);

    /**
     * The recursive implementation of the Delta Debugging algorithm
     *
     * @param deltaSet    Delta Set
     * @param granularity Granularity
     * @return the new DeltaSet after one iteration
     */
    abstract DeltaSet run_recursive(DeltaSet deltaSet, int granularity);

    /**
     * Runs the oracle and logs if a DeltaSet has been accepted.
     *
     * @param excludingSet DeltaSet that is excluded from the test input
     * @return true, if test run succeeded
     */
    boolean runOracle(DeltaSet excludingSet) {
        final boolean ret = Oracle.runOracleWithTemporaryFile(this.writeString(excludingSet, input), this.oracle, this.fileSuffix).wasSuccessful();
        this.numberOfOracleRuns++;
        if (ret) {
            //Keep the smallest DeltaSet
            if (this.lastAcceptedDeltaSet == null || this.keepLastDeltaset(this.lastAcceptedDeltaSet, excludingSet)) {
                this.lastAcceptedDeltaSet = excludingSet;
            }
            log(Level.INFO, "ddmax accepted the exclusion set " + excludingSet);
        }

        return ret;
    }

    /**
     * Keep last successful DeltaSet?
     *
     * @param oldSet Old Set
     * @param newSet New Set
     * @return True, if we should keep the new set
     */
    boolean keepLastDeltaset(@Nonnull DeltaSet oldSet, @Nonnull DeltaSet newSet) {
        return oldSet.length() >= newSet.length(); // Keep the smallest DeltaSet
    }

    /**
     * Gets the number of oracle runs for a test run
     *
     * @return Number of Oracle Runs
     */
    public long getNumberOfOracleRuns() {
        return this.numberOfOracleRuns;
    }

}
