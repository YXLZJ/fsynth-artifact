package fsynth.program.userinterface.actions.arguments;

import fsynth.program.userinterface.actions.Argument;
import fsynth.program.userinterface.actions.CliArgument;

/**
 * @author Lukas Kirschner
 * @since 2020-10-30
 **/
@CliArgument
public class StatisticsFile extends Argument {
    public StatisticsFile() {
        super('s', "statistics", "Specify the statistics file to load and store the test results. Statistics are automatically appended to this file. If the file does not exist, an empty statistics file is created immediately. If an empty string is given, do not save statistics.");
    }
}
