package erepair.program.userinterface.actions.commands;

import erepair.program.InputFormat;
import erepair.program.Logging;
import erepair.program.Main;
import erepair.program.Parsing;
import erepair.program.repairer.Repairer;
import erepair.program.subject.Oracle;
import erepair.program.subject.Subject;
import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliCommand;
import erepair.program.userinterface.actions.Command;
import erepair.program.Algorithm;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Level;

import static java.lang.System.exit;

/**
 **/
@SuppressWarnings({"HardcodedLineSeparator", "ObjectAllocationInLoop"})
@CliCommand
public class RepairSingleFileCommand extends Command {

    public RepairSingleFileCommand() {
        super('r', "repair", "Repair an input file and save the repaired file to the given output folder. Auto-detect the file format.\nUsage: -r -a <algorithm> -i <inputfile> -o <outputfolder>");
    }

    @Override
    public boolean hasArg() {
        return false;
    }

    @Override
    public char[] neededArguments() {
        return new char[]{'i', 'a'};
    }

    /**
     * Run a repair on a given file.
     * Warning - This also modifies the database ({@link Main#GLOBAL_DATABASE}) if set!
     *
     * @param argument  If {@link Command#hasArg()} returns true, the given argument must be supplied. Else, the parameter must be null.
     * @param arguments All other given arguments, at least those requested in {@link Command#neededArguments()}
     * @return 0, if repair was successful, 1 otherwise.
     */
    @Override
    public int run(@Nullable String argument, Map<Character, Argument> arguments) {
        Logging.setAllLogLevels(Level.FINEST);
        final Path fileToRepair = Paths.get(arguments.get('i').getArgumentValue());
        final String algorithm = arguments.get('a').getArgumentValue();
        final String outputfolder = arguments.get('o').wasGiven() ? arguments.get('o').getArgumentValue() : null;
        InputFormat autodetected = InputFormat.fromFileType(fileToRepair);
        Repairer myRepairer = Repairer.instantiate(fileToRepair, Algorithm.fromString(algorithm), InputFormat.fromFileType(fileToRepair));
        if (outputfolder != null) {
            myRepairer.setResultPath(Paths.get(outputfolder));
        } else {
            try {
                myRepairer.setResultPath(Files.createTempDirectory("testresults"));
            } catch (IOException e) {
                e.printStackTrace();
                exit(1);
            }
        }
        myRepairer.setSkipTestedSubjects(false);
        myRepairer.setTimeouts(Main.timeoutPerFile);
        boolean allSuccessful = true;
        for (final Subject subject : Oracle.getSubjectsFor(myRepairer.getFormat())) {
            allSuccessful = myRepairer.run(subject) && allSuccessful;
            //TODO put this piece of code into the repairer class:
            final Path of = myRepairer.getResultPath();
            if (autodetected.isGrammarBased) {
                System.out.println("*** File before Repair: ***\n" + Parsing.readStringFromFile(fileToRepair));
                System.out.println("*** File after Repair: ***\n" + Parsing.readStringFromFile(of));
            } else {
                System.out.println("*** File path of repaired file: ***\n" + of.toAbsolutePath().toString());
            }
            System.out.println("*** Number of required oracle runs: " + myRepairer.getNumberOfSubjectRuns(subject.getKind()) +" correct: "+myRepairer.successful+ " incorrect: "+myRepairer.failed+ " incomplete: 0" + " ***");
        }
        if (allSuccessful) return 0;
        else return 1;
    }
}
