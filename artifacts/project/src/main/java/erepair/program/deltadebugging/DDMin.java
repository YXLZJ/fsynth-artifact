package erepair.program.deltadebugging;

import erepair.program.Parsing;
import erepair.program.subject.Subject;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

/**
 **/
public class DDMin extends DD<String> {


    /**
     * Instantiates the Minimizing Delta Debugging class
     *
     * @param input           Input to process
     * @param timeoutInMillis Timeout in milliseconds
     * @param fileSuffix      Suffix of the test files without dot, e.g. 'obj'
     * @param oracle          Oracle to test the files
     */
    public DDMin(String input, long timeoutInMillis, String fileSuffix, Subject oracle) {
        super("DDMin", input, timeoutInMillis, fileSuffix, oracle);
    }

    @Override
    protected String exclude(DeltaSet deltaInterval, String input) {
        return intersect(input, deltaInterval);
    }

    @Override
    protected DeltaSet runAlgorithm() {
        log(Level.INFO, "Running DDMin...");
        return this.run_recursive(new DeltaSet(0, input.length()), 2);
    }

    @Override
    protected int getLength(String input) {
        return input.length();
    }

    @Override
    protected String toString(String input) {
        return input;
    }

    /**
     * The recursive implementation of DDmin.
     * Minimizes a failing input, while the minimized input still fails and is parsed by ANTLR nonetheless
     *
     * @param deltaSet    Start Set
     * @param granularity Start Granularity
     * @return the minimized DeltaSet
     */
    @SuppressWarnings("ObjectAllocationInLoop")
    @Override
    DeltaSet run_recursive(DeltaSet deltaSet, int granularity) {
        if (super.isTimedOut()) {
            log(Level.WARNING, "DDMin timed out.");
            return super.lastAcceptedDeltaSet;
        }

        final int inpLength = deltaSet.length();
        if (inpLength == 1) {//1-minimal
            log(Level.INFO, "DeltaSet is 1-minimal");
            return deltaSet;
        }
        if (inpLength == 0) {
            log(Level.WARNING, "Skipping DDMax because DeltaSet is empty");
            return deltaSet; // Handle cases where the interval is empty
        }

        for (int i = 0; i < granularity; i++) {//Check intervals
            if (super.isTimedOut()) {
                log(Level.WARNING, "DDMin timed out.");
                return super.lastAcceptedDeltaSet;
            }
            final DeltaInterval jointInterval = getGranularityInterval(deltaSet, granularity, i);
            final DeltaSet newSet = new DeltaSet(jointInterval);
            if (!this.runOracle(newSet) && this.antlrParses(newSet)) {
                super.lastAcceptedDeltaSet = newSet;
                log(Level.INFO, "ddmin accepted a jointDeltaSet. Continuing using " + newSet);
                return run_recursive(newSet, 2);
            }
        }
        for (int i = 0; i < granularity; i++) {//Check intervals
            if (super.isTimedOut()) {
                log(Level.WARNING, "DDMin timed out.");
                return super.lastAcceptedDeltaSet;
            }
            final DeltaInterval exclInterval = getGranularityInterval(deltaSet, granularity, i);
            final DeltaSet newSet = new DeltaSet(deltaSet);
            newSet.excludeInterval(exclInterval);
            if (!this.runOracle(newSet) && this.antlrParses(newSet)) {
                super.lastAcceptedDeltaSet = newSet;
                log(Level.INFO, "ddmin accepted an exclSet. Continuing using " + newSet);
                return run_recursive(newSet, 2);
            }
        }
        if (granularity < inpLength) {
            log(Level.INFO, "Increased Granularity to " + Math.min(inpLength, 2 * granularity));
            return run_recursive(deltaSet, Math.min(inpLength, 2 * granularity));
        }
        log(Level.INFO, "DeltaSet is minimal");
        return deltaSet;
    }

    /**
     * Checks if a string can be parsed by ANTLR.
     *
     * @param excludingSet Exclusion set to exclude from the string
     * @return true, if ANTLR parses the string
     */
    private boolean antlrParses(DeltaSet excludingSet) {
        String bytearray = this.writeString(excludingSet, input);
        boolean result = false;
        Path ddfile = Paths.get(super.tempfolder.normalize().toString(), "ddminAntlrRun_" + System.currentTimeMillis() + "." + super.fileSuffix);
        try {
            Parsing.writeStringToFile(ddfile, bytearray);
            log(Level.FINE, String.format("Oracle Run %04d, testing file %s",
                    getNumberOfOracleRuns(),
                    ddfile.toString()));
            return Parsing.parseAutodetect(ddfile, true) != null;
        } catch (IOException e) {
            log(Level.SEVERE, "Could not write temporary file", e);
        }
        try {
            Files.delete(ddfile);
        } catch (IOException e) {
            log(Level.SEVERE, "Could not delete temporary file", e);
        }

        return result;//Return true if oracle passes?
    }

    @Override
    boolean keepLastDeltaset(@Nonnull DeltaSet oldSet, @Nonnull DeltaSet newSet) {
        return oldSet.length() <= newSet.length(); // Keep the largest DeltaSet
    }
}
