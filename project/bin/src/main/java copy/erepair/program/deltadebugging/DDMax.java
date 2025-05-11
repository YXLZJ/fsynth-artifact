package erepair.program.deltadebugging;

import erepair.program.subject.Subject;

import java.util.logging.Level;

/**
 **/
public abstract class DDMax<T> extends DD<T> {


    /**
     * Instantiates the Delta Debugging class
     *
     * @param prefix          Prefix that is appended to the log and identifies the class that used the logger.
     * @param input           Input to process
     * @param timeoutInMillis Timeout in milliseconds
     * @param fileSuffix      Suffix of the test files without dot, e.g. 'obj'
     * @param oracle          Oracle to test the files
     */
    public DDMax(String prefix, T input, long timeoutInMillis, String fileSuffix, Subject oracle) {
        super(prefix, input, timeoutInMillis, fileSuffix, oracle);
    }

    private void logTimeout() {
        //noinspection HardcodedFileSeparator
        log(Level.WARNING, "DDMax timed out (" + super.elapsed + "ms/" + super.timeout + "ms)");
    }

    /**
     * The recursive implementation of Delta Debugging for Maximizing Inputs
     *
     * @param deltaSet    Start DeltaSet which is used as ExclusionSet
     * @param granularity Granularity
     * @return resulting Exclusion Set
     */
    @SuppressWarnings("ObjectAllocationInLoop")
    @Override
    protected DeltaSet run_recursive(DeltaSet deltaSet, int granularity) {
        if (this.isTimedOut()) {
            this.logTimeout();
            return super.lastAcceptedDeltaSet;
        }
        //Check Delta Subsets
        final int inpLength = deltaSet.length();
        if (inpLength == 1) {//1-minimal
            log(Level.INFO, "DeltaSet is 1-minimal");
            return deltaSet; // At this point, this DeltaSet must have been already accepted, b/c the method is called recursively only if the DeltaSet was accepted. If not, the DeltaSet is not modified in the recorsive call and therefore cannot be non-accepted
        }
        if (inpLength == 0) {
            log(Level.WARNING, "Skipping DDMax because DeltaSet is empty");
            return lastAcceptedDeltaSet; // Handle cases where the interval is empty
        }
        for (int i = 0; i < granularity; i++) {//Check inclusion intervals
            if (this.isTimedOut()) {
                this.logTimeout();
                return super.lastAcceptedDeltaSet;
            }
            final DeltaInterval jointInterval = DD.getGranularityInterval(deltaSet, granularity, i);
            final DeltaSet newSet = new DeltaSet(jointInterval);
            if (super.runOracle(newSet)) {
                return run_recursive(newSet, 2);
            }
        }
        for (int i = 0; i < granularity; i++) {//Check exclusion Intervals
            if (this.isTimedOut()) {
                this.logTimeout();
                return super.lastAcceptedDeltaSet;
            }
            final DeltaInterval exclInterval = DD.getGranularityInterval(deltaSet, granularity, i);
            final DeltaSet newSet = new DeltaSet(deltaSet);
            newSet.excludeInterval(exclInterval);
            if (this.runOracle(newSet)) {
                return run_recursive(newSet, 2);
            }
        }
        if (granularity < inpLength) {
            log(Level.INFO, "Increased Granularity to " + Math.min(inpLength, 2 * granularity));
            return run_recursive(deltaSet, Math.min(inpLength, 2 * granularity));
        }
        log(Level.INFO, "DeltaSet is minimal.");
        return deltaSet;
    }
}
