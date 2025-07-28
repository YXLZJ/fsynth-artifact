package erepair.program.userinterface.actions.arguments;

import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliArgument;

/**
 **/
@CliArgument
public class StatisticsFile extends Argument {
    public StatisticsFile() {
        super('s', "statistics", "Specify the statistics file to load and store the test results. Statistics are automatically appended to this file. If the file does not exist, an empty statistics file is created immediately. If an empty string is given, do not save statistics.");
    }
}
