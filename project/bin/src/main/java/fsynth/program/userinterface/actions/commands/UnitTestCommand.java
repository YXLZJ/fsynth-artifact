package fsynth.program.userinterface.actions.commands;

import fsynth.program.test.TestExecutor;
import fsynth.program.userinterface.actions.Argument;
import fsynth.program.userinterface.actions.CliCommand;
import fsynth.program.userinterface.actions.Command;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author Lukas Kirschner
 * @since 2020-10-30
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
