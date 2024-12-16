package fsynth.program.userinterface.actions.arguments;

import fsynth.program.userinterface.actions.Argument;
import fsynth.program.userinterface.actions.CliArgument;

/**
 * @author Lukas Kirschner
 * @since 2020-10-30
 **/
@CliArgument
public class AlgorithmOnlyArg extends Argument {
    public AlgorithmOnlyArg() {
        super('V', "algorithm-only", "Only evaluate the given algorithm when the run command is given.");
    }
}
