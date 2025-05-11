package erepair.program.subprocess;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * This class represents a return value of a subprocess, which consists of the stdout, stderr and return value.
 *
 */
public class ProcessReturnValue implements Map.Entry<String, String> {
    public final String stdout;
    public final String stderr;
    public final int returnValue;

    /**
     * Initialize a new immutable ProcessReturnValue
     *
     * @param stdout       stdout string of the process
     * @param stderr       stderr of the process
     * @param return_value Return Value of the process
     */
    public ProcessReturnValue(@Nonnull String stdout, @Nonnull String stderr, int return_value) {
        this.stdout = stdout;
        this.stderr = stderr;
        this.returnValue = return_value;
    }

    /**
     * Returns the stdout of the process
     *
     * @return the stdout
     */
    @Override
    public String getKey() {
        return stdout;
    }

    /**
     * Returns the stderr of the process
     *
     * @return the stderr of the process
     */
    @Override
    public String getValue() {
        return stderr;
    }

    /**
     * Not supported
     *
     * @param s String
     * @return Throws an exception immediately
     */
    @Override
    public String setValue(String s) {
        throw new IllegalStateException("Operation not supported here");
    }

    /**
     * The toString() method is overwritten in order to return the stdout of the process.
     *
     * @return the stdout
     */
    @Override
    public String toString() {
        return this.stdout;
    }
}