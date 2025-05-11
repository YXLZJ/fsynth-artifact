package erepair.program.userinterface.actions.arguments;

import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliArgument;

/**
 **/
@CliArgument
public class AlgorithmOnlyArg extends Argument {
    public AlgorithmOnlyArg() {
        super('V', "algorithm-only", "Only evaluate the given algorithm when the run command is given.");
    }
}
