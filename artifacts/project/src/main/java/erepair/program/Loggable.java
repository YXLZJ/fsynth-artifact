package erepair.program;

import java.io.Serializable;
import java.util.logging.Level;

/**
 * This class represents a base class for all classes that need to log something to a log.
 *
 */
public abstract class Loggable implements Serializable {
    private static final long serialVersionUID = 7864971914003266409L;
    protected String prefix;

    /**
     * Instantiates a new Loggable
     *
     * @param prefix Prefix that is appended to the log and identifies the class that used the logger.
     */
    public Loggable(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Log a string to the log.
     *
     * @param level   LogLevel to use
     * @param message Message to log
     */
    protected void log(Level level, String message) {
        Logging.generalLogger.log(level, String.format("[%s] %s", this.prefix, message));
    }

    /**
     * Logs a Throwable to the log
     *
     * @param level   LogLevel
     * @param message Message to append
     * @param e       Throwable to log
     */
    protected void log(Level level, String message, Throwable e) {
        Logging.generalLogger.log(level, String.format("[%s] %s", this.prefix, message), e);
    }
}
