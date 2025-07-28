package erepair.program;

import java.util.Arrays;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Logging class that provides a global logging system.
 *
 */
public class Logging {
    /**
     * The Oracle Logger that should be used by test oracles
     */
    public static Logger oracleLogger = Logger.getLogger("Oracle");
    /**
     * The general logger that should be used for system messages
     */
    public static Logger generalLogger = Logger.getLogger("System");
    /**
     * The ANTLR logger that should be used for parsing
     */
    public static Logger antlrLogger = Logger.getLogger("ANTLR");
    /**
     * The Result logger that should be used for normal user output
     */
    private static Logger resultLogger = Logger.getLogger("ANTLR");

    static {
        oracleLogger.setUseParentHandlers(false);
        oracleLogger.addHandler(new ConsoleHandler(Level.INFO));
        oracleLogger.setLevel(Level.FINEST);
        generalLogger.setUseParentHandlers(false);
        generalLogger.addHandler(new ConsoleHandler(Level.INFO));
        generalLogger.setLevel(Level.FINEST);
        antlrLogger.setUseParentHandlers(false);
        antlrLogger.addHandler(new ConsoleHandler(Level.INFO));
        antlrLogger.setLevel(Level.FINEST);
        resultLogger.setUseParentHandlers(false);
        resultLogger.addHandler(new ConsoleHandler(Level.FINEST));
        resultLogger.setLevel(Level.FINEST);
    }

    private Logging() {
    }

    /**
     * Set the log level of all loggers to the given value
     *
     * @param newLevel New LogLevel
     */
    public static void setAllLogLevels(Level newLevel) {
        Arrays.asList(oracleLogger, generalLogger, antlrLogger).forEach(x -> Arrays.stream(x.getHandlers()).forEach(y -> y.setLevel(newLevel)));
    }

    public static void result(String msg) {
        resultLogger.log(Level.INFO, msg);
    }

    public static void fatal(String msg) {
        generalLogger.log(MoreLogLevels.FATAL, msg);
    }

    public static void fatal(String msg, Throwable e) {
        generalLogger.log(MoreLogLevels.FATAL, msg, e);
    }

    public static void error(String msg) {
        generalLogger.log(MoreLogLevels.ERROR, msg);
    }

    public static void error(String msg, Throwable e) {
        generalLogger.log(MoreLogLevels.ERROR, msg, e);
    }
}

/**
 * This class provides two more log levels
 *
 */
class MoreLogLevels extends Level {
    /**
     * Log Level "Fatal" for errors that cause the program to crash
     */
    public static final Level FATAL = new MoreLogLevels("FATAL", Level.SEVERE.intValue() + 10);
    /**
     * Log Level "Error" for errors that are not severe
     */
    public static final Level ERROR = new MoreLogLevels("ERROR", Level.SEVERE.intValue() - 10);
    private static final long serialVersionUID = -8578348733356223107L;

    private MoreLogLevels(String name, int value) {
        super(name, value);
    }
}

class ConsoleHandler extends Handler {
    public static final String COL_RESET = "\033[0m";
    public static final String COL_RED = "\033[0;31m";
    public static final String COL_YELLOW = "\033[0;33m";
    public static final boolean printColors = true;//System.console() != null && System.getenv().get("TERM") != null;
    private Level logLevel;

    public ConsoleHandler(Level logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public Level getLevel() {
        return logLevel;
    }

    @Override
    public synchronized void setLevel(Level logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * Publish a {@code LogRecord}.
     * <p>
     * The logging request was made initially to a {@code Logger} object,
     * which initialized the {@code LogRecord} and forwarded it here.
     * <p>
     * The {@code Handler}  is responsible for formatting the message, when and
     * if necessary.  The formatting should include localization.
     *
     * @param record description of the log event. A null record is
     *               silently ignored and is not published
     */
    @Override
    public void publish(LogRecord record) {
        if (this.logLevel.intValue() <= record.getLevel().intValue()) {
            String logLevel = record.getLevel().toString();
            if (printColors) {
                final Level recordLevel = record.getLevel();
                if (recordLevel.intValue() == (Level.WARNING.intValue())) {
                    logLevel = String.format("%s%s%s", COL_YELLOW, logLevel, COL_RESET);
                } else if (recordLevel.intValue() >= (MoreLogLevels.ERROR.intValue())) {
                    logLevel = String.format("%s%s%s", COL_RED, logLevel, COL_RESET);
                }
            }
            final String prefix = String.format("%d [%s %s] ", record.getMillis(), record.getLoggerName(), logLevel);
            System.out.printf("%s%s%n", prefix, record.getMessage());
            final Throwable throwable = record.getThrown();
            if (throwable != null) {
                System.out.printf("%s%s%n", prefix, "The program threw an exception:");
                throwable.printStackTrace(System.out);
            }
        }
    }

    /**
     * Flush any buffered output.
     */
    @Override
    public void flush() {
        System.out.flush();
    }

    /**
     * Close the {@code Handler} and free all associated resources.
     * <p>
     * The close method will perform a {@code flush} and then close the
     * {@code Handler}.   After close has been called this {@code Handler}
     * should no longer be used.  Method calls may either be silently
     * ignored or may throw runtime exceptions.
     *
     * @throws SecurityException if a security manager exists and if
     *                           the caller does not have {@code LoggingPermission("control")}.
     */
    @Override
    public void close() throws SecurityException {

    }
}
