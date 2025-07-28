package erepair.program.userinterface.actions.commands;

import erepair.program.Logging;
import erepair.program.subject.Oracle;
import erepair.program.subject.SubjectStatus;
import erepair.program.subject.Subjects;
import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliCommand;
import erepair.program.userinterface.actions.Command;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 **/
@CliCommand
public class OracleCommand extends Command {
    public OracleCommand() {
        super('O', "run-oracle", "Run an oracle on the specified input file.\nAvailable oracles:\n" +
                Oracle.getKinds().stream().map(kind -> kind + ": "
                        + Oracle.getSubjectsFor(kind).stream().map(subject -> subject.getName()).collect(Collectors.joining(", "))).collect(Collectors.joining(",\n")));
    }

    @Override
    public boolean hasArg() {
        return true;
    }

    @Override
    public char[] neededArguments() {
        return new char[]{'i'};
    }

    /**
     * Run an oracle and output a "failed" or "passed" status to the console.
     *
     * @return 0 if the oracle passed, 1 otherwise
     */
    @SuppressWarnings("deprecation")//Needed to run Assimp Legacy Code
    @Override
    public int run(@Nullable String argument, Map<Character, Argument> arguments) {
        assert (argument != null);
        String fileToOpen = arguments.get('i').getArgumentValue();
        String fileToSave = arguments.get('o').wasGiven() ? arguments.get('o').getArgumentValue() : null;
        final String oracleToRun = argument.toLowerCase();
        Logging.setAllLogLevels(Level.FINEST);
        boolean oraclePassed = false;
        final String oraclePrefix = "[Oracle] ";
        final Subjects oracle = Subjects.fromString(oracleToRun);
        final SubjectStatus ret = Oracle.runOracle(oracle, fileToOpen, fileToSave, null, null);
        System.out.println(ret.getStatusReport());
        oraclePassed = ret.wasSuccessful();
        if (oraclePassed) {
            System.out.println(oraclePrefix + "Passed");
            return 0;
        } else {
            System.out.println(oraclePrefix + "Failed gracefully");
            if (ret.wasIncomplete()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
