package erepair.program.userinterface.actions.arguments;

import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliArgument;

/**
 **/
@CliArgument
public class OutputFile extends Argument {
    public OutputFile() {
        super('o', "output-file", "Specify the output file or directory.");
    }
}
