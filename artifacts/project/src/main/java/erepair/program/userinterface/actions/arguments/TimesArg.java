package erepair.program.userinterface.actions.arguments;

import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliArgument;

/**
 **/
@CliArgument
public class TimesArg extends Argument {
    public TimesArg() {
        super('t', "times", "Specify maximum number of times each file will be mutated.");
    }
}
