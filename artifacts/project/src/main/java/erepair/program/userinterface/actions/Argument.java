package erepair.program.userinterface.actions;

import javax.annotation.Nonnull;

/**
 **/
public abstract class Argument extends CliObject {
    String value = null;

    /**
     * Instantiate this argument
     *
     * @param shortArgName Short argument, e.g. 'h'
     * @param longArgName  Long argument name, e.g. 'help'
     * @param helpText     Help text, e.g. 'Prints the man page'
     */
    public Argument(char shortArgName, String longArgName, String helpText) {
        super(shortArgName, longArgName, helpText);
    }

    @Override
    public boolean hasArg() {
        return true;
    }

    /**
     * Get the argument value or throw an exception, if not given
     *
     * @return the argument value
     */
    @Nonnull
    public String getArgumentValue() {
        if (this.value == null) {
            throw new IllegalStateException("The requested argument " + super.longArgName + " was queried, but not given! Check before querying");
        }
        return this.value;
    }

    /**
     * Set the given value of this argument, as taken from the command-line
     *
     * @param value Given value or an empty string if this argument takes no parameter (e.g. for boolean flags)
     */
    public void setArgumentValue(String value) {
        this.value = value;
    }

    /**
     * Chacks if this argument was given on the command line
     *
     * @return true, if this argument was given
     */
    public boolean wasGiven() {
        return value != null;
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof Argument)) {
            return false;
        }
        final Argument other = (Argument) obj;
        return super.shortArgName == other.shortArgName;
    }

    @Override
    public final int hashCode() {
        return Character.hashCode(super.shortArgName);
    }
}
