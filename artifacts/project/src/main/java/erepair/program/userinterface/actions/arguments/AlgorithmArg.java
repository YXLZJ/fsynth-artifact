package erepair.program.userinterface.actions.arguments;

import erepair.program.repairer.Repairer;
import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliArgument;
import erepair.program.Algorithm;

import java.util.stream.Collectors;

/**
 **/
@CliArgument
public class AlgorithmArg extends Argument {
    public AlgorithmArg() {
        super('a', "algorithm", "Chooses an algorithm. Available:\n" + Repairer.getAllAvailableAlgorithms().map(Algorithm::toString).sorted().collect(Collectors.joining(", ")));
    }
}
