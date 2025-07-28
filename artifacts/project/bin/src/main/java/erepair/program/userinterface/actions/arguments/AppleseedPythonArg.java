package erepair.program.userinterface.actions.arguments;

import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliArgument;

/**
 **/
@CliArgument
public class AppleseedPythonArg extends Argument {
    public AppleseedPythonArg() {
        super('p', "python", "Specify the Appleseed Python Interpreter.");
    }
}
