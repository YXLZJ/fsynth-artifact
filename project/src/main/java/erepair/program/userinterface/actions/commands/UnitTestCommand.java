package erepair.program.userinterface.actions.commands;

import erepair.program.test.TestExecutor;
import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliCommand;
import erepair.program.userinterface.actions.Command;

import javax.annotation.Nullable;
import java.util.Map;

/**
 **/
@CliCommand
public class UnitTestCommand extends Command {

    public UnitTestCommand() {
        super('X', "unit-tests", "Run all unit tests and try to create a XML test report inside the reports folder in the current working directory.");
    }

    @Override
    public boolean hasArg() {
        return false;
    }

    @Override
    public char[] neededArguments() {
        return new char[]{};
    }

    /**
     * Run all Unit Tests
     *
     * @param argument  null
     * @param arguments All given arguments
     * @return 0, if successful, 1 if unsuccessful
     */
    @Override
    public int run(@Nullable String argument, Map<Character, Argument> arguments) {
        TestExecutor.runTests();
        return 0;
    }
}
