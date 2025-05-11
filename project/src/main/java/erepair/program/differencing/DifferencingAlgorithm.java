package erepair.program.differencing;

import erepair.program.Loggable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.logging.Level;

/**
 * The base class for a Differencing Algorithm.
 *
 */
public abstract class DifferencingAlgorithm<T extends Number> extends Loggable {
    public DifferencingAlgorithm(String loggingPrefix) {
        super("Diff " + loggingPrefix);
    }

    public abstract DifferencingAlgorithms getKind();

    /**
     * Runs the differencing algorithm on the given files.
     * Keep track of the correct order of the files!
     * e.g. file1=pprinted, file2=rectified
     *
     * @param file1 Original file
     * @param file2 File to compare
     * @return a value representing the distance, or null, if a RuntimeException was thrown.
     */
    @Nullable
    public final T run(@Nonnull Path file1, @Nonnull Path file2) {
        try {
            return this.runAlgorithm(file1, file2);
        } catch (RuntimeException e) {
            log(Level.SEVERE, "A RuntimeException was thrown", e);
            return null;
        }
    }

    @Nullable
    abstract T runAlgorithm(@Nonnull Path file1, @Nonnull Path file2);

}
