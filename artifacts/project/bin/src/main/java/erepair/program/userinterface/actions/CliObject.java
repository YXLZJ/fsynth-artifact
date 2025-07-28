package erepair.program.userinterface.actions;

/**
 **/
public abstract class CliObject {
    /**
     * @return The short argument name
     */
    public final char shortArgName;
    /**
     * @return The long argument name
     */
    public final String longArgName;
    /**
     * @return the help text for command-line help
     */
    public final String helpText;

    /**
     * Instantiate this object
     *
     * @param shortArgName Short argument, e.g. 'h'
     * @param longArgName  Long argument name, e.g. 'help'
     * @param helpText     Help text, e.g. 'Prints the man page'
     */
    CliObject(char shortArgName, String longArgName, String helpText) {
        this.shortArgName = shortArgName;
        this.longArgName = longArgName;
        this.helpText = helpText;
    }

    /**
     * Gets whether this object has an argument or not (e.g. for boolean flags)
     *
     * @return
     */
    public abstract boolean hasArg();
}
