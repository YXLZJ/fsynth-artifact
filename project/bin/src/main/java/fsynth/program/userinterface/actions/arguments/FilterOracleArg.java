package fsynth.program.userinterface.actions.arguments;

import fsynth.program.userinterface.actions.Argument;
import fsynth.program.userinterface.actions.CliArgument;

/**
 * @author Lukas Kirschner
 * @since 2020-10-30
 **/
@CliArgument
public class FilterOracleArg extends Argument {
    public FilterOracleArg() {
        super('j', "filter-for-oracle", "Filter files for a specific oracle. Takes the same oracles as -o");
    }
}
