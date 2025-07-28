package erepair.program.userinterface.actions.commands;

import erepair.program.Logging;
import erepair.program.mutator.MutationDriver;
import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliCommand;
import erepair.program.userinterface.actions.Command;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.exit;

/**
 **/
@CliCommand
public class MutateCommand extends Command {

    public MutateCommand() {
        super('M', "mutate", "Mutate all files in directory.\n" +
                "Usage: -M -i <input> -o <output> [-a <algorithm>] [-t <times>]\n" +
                "\tThe input file format is detected automatically by parsing the file suffixes.\n" +
                "\tIf no algorithm is specified, a random algorithm is chosen for each file.\n" +
                "\tAvailable Algorithms:" +
                "\t" +
                Arrays.stream(MutationDriver.class.getDeclaredMethods())
                        .filter(x -> x.getName().startsWith("random_"))
                        .map(x -> x.getName().substring(7))
                        .collect(Collectors.joining(", ")) +
                "\tIf no -t number is given, mutate every file with one mutation.");
    }

    @Override
    public boolean hasArg() {
        return false;
    }

    @Override
    public char[] neededArguments() {
        return new char[]{'i', 'o'};
    }

    @Override
    public int run(@Nullable String argument, Map<Character, Argument> arguments) {
        final String inputdirectory = arguments.get('i').getArgumentValue();
        final String outputdirectory = arguments.get('o').getArgumentValue();
        String algorithm = arguments.get('a').wasGiven() ? arguments.get('a').getArgumentValue() : null;
        int times = 1;
        if (arguments.get('t').wasGiven()) {
            try {
                times = Integer.parseInt(arguments.get('t').getArgumentValue());
            } catch (NumberFormatException e) {
                Logging.fatal("Invalid Command Line Arguments: Could not parse integer", e);
                exit(1);
            }
        }
        MutationDriver.performMutations(inputdirectory, outputdirectory, algorithm, times);
        return 0;
    }
}
