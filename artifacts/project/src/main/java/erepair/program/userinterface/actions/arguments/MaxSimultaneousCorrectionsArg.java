package erepair.program.userinterface.actions.arguments;

import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliArgument;

/**
 **/
@SuppressWarnings({"unused", "JavaDoc"})
@CliArgument
public class MaxSimultaneousCorrectionsArg extends Argument {
    public MaxSimultaneousCorrectionsArg() {
        super('m', "max-simultaneous-corrections", "Specify the maximum number of simultaneous corrections for erepair. Set to -1 if all corrections should be considered.");
    }
}
