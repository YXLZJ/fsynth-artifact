package fsynth.program.userinterface.actions.arguments;

import fsynth.program.userinterface.actions.Argument;
import fsynth.program.userinterface.actions.CliArgument;

/**
 * @author Lukas Kirschner
 * @since 2020-10-30
 **/
@CliArgument
public class TimesArg extends Argument {
    public TimesArg() {
        super('t', "times", "Specify maximum number of times each file will be mutated.");
    }
}
