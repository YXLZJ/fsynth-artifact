package erepair.program.mutator;

import erepair.program.InputFormat;
import erepair.program.Loggable;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This is the base class for all MutationModes.
 * <p>
 * Each Mutation Mode is designed as singleton that must be re-usable between mutations,
 * i.e. this class should not store any state data
 *
 **/
public abstract class MutationMode extends Loggable {
    private final @Nonnull
    String name;

    public MutationMode(@Nonnull String name) {
        super("mutator:" + name);
        this.name = name;
    }

    /**
     * Get a description of this mutation mode
     *
     * @return the human-readable name of this mode
     */
    public final @Nonnull
    String name() {
        return this.name;
    }

    /**
     * Run this mutation mode.
     * This ONLY randomly mutates a files once,
     * it does not guarantee to return a file that is not accepted by an oracle anymore!
     * May mutate the given byte array in-place!
     *
     * @param input The content of the file to mutate
     * @param r     The random number generator
     * @return the mutated file
     */
    public abstract @Nonnull
    byte[] run(@Nonnull byte[] input, @Nonnull Random r);

    /**
     * Get a list of all suffixes this mutation mode is applicable to, each including a leading dot, e.g., ".json"
     *
     * @return a list of all suffixes this mutation mode is applicable to
     */
    public @Nonnull
    List<String> applicableSuffixes() {
        return Arrays.stream(InputFormat.values())
                .filter(f -> !f.equals(InputFormat.INVALID))
                .map(InputFormat::getSuffix)
                .collect(Collectors.toList());
    }

    /**
     * Checks if this mutation mode's run() method changes the given data array in-place.
     * May be overridden by child classes for better performance
     *
     * @return false, if the given data array is not changed during mutation.
     */
    public boolean changesArrayInplace() {
        return true;
    }
}
