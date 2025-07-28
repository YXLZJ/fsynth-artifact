package erepair.program.userinterface.actions.arguments;

import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliArgument;

/**
 **/
@CliArgument
public class FilterOracleArg extends Argument {
    public FilterOracleArg() {
        super('j', "filter-for-oracle", "Filter files for a specific oracle. Takes the same oracles as -o");
    }
}
