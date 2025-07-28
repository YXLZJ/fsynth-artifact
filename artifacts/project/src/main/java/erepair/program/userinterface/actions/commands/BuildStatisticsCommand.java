package erepair.program.userinterface.actions.commands;

import erepair.program.Logging;
import erepair.program.Main;
import erepair.program.db.statistics.BuildStatistics;
import erepair.program.db.statistics.Statistics;
import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliCommand;
import erepair.program.userinterface.actions.Command;

import javax.annotation.Nullable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 **/
@CliCommand
public class BuildStatisticsCommand extends Command {

    public BuildStatisticsCommand() {
        super('S', "output-summary", "Outputs a summary of the previous test runs. Usage: [-s <StatisticsDB>] [-o <outputfolder>] -S.\nAn Output Directory may be specified using -o. You may also only build one single statistics object specified with -a. Available objects: " + Statistics.STATISTICS.stream().map(statobj -> statobj.getKey()).sorted().collect(Collectors.joining(",\n")));
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
     * Print all statistic classes that are annotated with {@link BuildStatistics}
     *
     * @return 0, if print was successful
     */
    @Override
    public int run(@Nullable String argument, Map<Character, Argument> arguments) {
        if (arguments.get('o').wasGiven()) {
            Main.TESTRESULTS_FOLDER = Paths.get(arguments.get('o').getArgumentValue());
        }
        String only = null;
        if (arguments.get('a').wasGiven()) {
            only = arguments.get('a').getArgumentValue().strip();
        }
        List<Statistics> builtStatistics = new ArrayList<>();
        List<Statistics> failedStatistics = new ArrayList<>();
        String finalOnly = only;
        Statistics.STATISTICS.forEach(statistic -> {
            if (finalOnly == null || statistic.getKey().equals(finalOnly)) {
                Logging.generalLogger.log(Level.INFO, "Building " + statistic.getDisplayName());
                final boolean succ = statistic.build();
                if (succ) {
                    builtStatistics.add(statistic);
                } else {
                    failedStatistics.add(statistic);
                }
            }
        });
        if (!failedStatistics.isEmpty()) {
            Logging.generalLogger.info("Failed to build " + failedStatistics.size() + " statistics objects:");
            failedStatistics.stream()
                    .sorted(Comparator.comparing(Statistics::getDisplayName))
                    .forEach(statistic -> {
                        Logging.generalLogger.info(statistic.getDisplayName());
                    });
        }
        Logging.generalLogger.info("Built " + builtStatistics.size() + " statistics objects:");
        builtStatistics.stream()
                .sorted(Comparator.comparing(Statistics::getDisplayName))
                .forEach(statistic -> {
                    Logging.generalLogger.info(statistic.getDisplayName() + " at " + statistic.getLocation());
                });
        return 0;
    }
}

