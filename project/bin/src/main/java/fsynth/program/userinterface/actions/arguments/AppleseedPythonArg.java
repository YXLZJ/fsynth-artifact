package fsynth.program.userinterface.actions.arguments;

import fsynth.program.userinterface.actions.Argument;
import fsynth.program.userinterface.actions.CliArgument;

/**
 * @author Lukas Kirschner
 * @since 2020-10-30
 **/
@CliArgument
public class AppleseedPythonArg extends Argument {
    public AppleseedPythonArg() {
        super('p', "python", "Specify the Appleseed Python Interpreter.");
    }
}
