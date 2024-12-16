package fsynth.program.userinterface.actions.arguments;

import fsynth.program.userinterface.actions.Argument;
import fsynth.program.userinterface.actions.CliArgument;

/**
 * @author Lukas Kirschner
 * @since 2022-03-16
 **/
@SuppressWarnings({"unused", "JavaDoc"})
@CliArgument
public class MaxSimultaneousCorrectionsArg extends Argument {
    public MaxSimultaneousCorrectionsArg() {
        super('m', "max-simultaneous-corrections", "Specify the maximum number of simultaneous corrections for bRepair. Set to -1 if all corrections should be considered.");
    }
}
