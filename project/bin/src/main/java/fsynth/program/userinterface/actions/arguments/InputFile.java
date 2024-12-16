package fsynth.program.userinterface.actions.arguments;

import fsynth.program.userinterface.actions.Argument;
import fsynth.program.userinterface.actions.CliArgument;

/**
 * @author Lukas Kirschner
 * @since 2020-10-30
 **/
@CliArgument
public class InputFile extends Argument {
    public InputFile() {
        super('i', "input-file", "Specify the input file or directory.");
    }
}
