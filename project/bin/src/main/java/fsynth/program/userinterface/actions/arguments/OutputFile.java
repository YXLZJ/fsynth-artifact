package fsynth.program.userinterface.actions.arguments;

import fsynth.program.userinterface.actions.Argument;
import fsynth.program.userinterface.actions.CliArgument;

/**
 * @author Lukas Kirschner
 * @since 2020-10-30
 **/
@CliArgument
public class OutputFile extends Argument {
    public OutputFile() {
        super('o', "output-file", "Specify the output file or directory.");
    }
}
