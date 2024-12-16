package fsynth.program.userinterface.actions.arguments;

import fsynth.program.repairer.Repairer;
import fsynth.program.userinterface.actions.Argument;
import fsynth.program.userinterface.actions.CliArgument;
import fsynth.program.Algorithm;

import java.util.stream.Collectors;

/**
 * @author Lukas Kirschner
 * @since 2020-10-30
 **/
@CliArgument
public class AlgorithmArg extends Argument {
    public AlgorithmArg() {
        super('a', "algorithm", "Chooses an algorithm. Available:\n" + Repairer.getAllAvailableAlgorithms().map(Algorithm::toString).sorted().collect(Collectors.joining(", ")));
    }
}
