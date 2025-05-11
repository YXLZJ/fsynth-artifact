package erepair.program.userinterface.actions.arguments;

import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliArgument;

/**
 **/
@CliArgument
public class InputFile extends Argument {
    public InputFile() {
        super('i', "input-file", "Specify the input file or directory.");
    }
}
