package fsynth.program.db.statistics;

import fsynth.program.Algorithm;
import fsynth.program.Logging;
import fsynth.program.Main;
import fsynth.program.db.FileDatabase;
import fsynth.program.db.FileRecord;
import fsynth.program.subject.Oracle;
import fsynth.program.subject.Subject;
import fsynth.program.subject.Subjects;
import fsynth.program.subprocess.ProcessReturnValue;
import fsynth.program.subprocess.SimpleSubprocess;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.stream.Stream;

/**
 * @author Lukas Kirschner
 * @since 2022-03-03
 **/
@SuppressWarnings("ObjectAllocationInLoop")
@BuildStatistics
public final class DiagnosisSheet extends GoogleSheet {
    private final String header_ddminsize = "DDMin Diagnosis Size"; // mode 1
    private final String header_ddmaxdiffsize = "DDMax Diff Size"; // mode 2
    private final String header_ddmaxoutputsize = "DDMax Output Size"; // mode 3
    private final String header_perc_ddmax_in_ddmin = "Percentage of DDMax Diff contained in DDMin Diagnosis"; // mode 4
    private final String header_perc_ddmin_in_ddmax = "Percentage of DDMin Diagnosis contained in DDMax Output"; // mode 5
    private final String header_brepairdiffsize = "BRepair Diff Size"; // mode 6
    private final String header_perc_brepair_in_ddmin = "Percentage of bRepair Diff contained in DDMin Diagnosis"; // mode 7
    private final String header_perc_ddmin_in_brepair = "Percentage of DDMin Diagnosis contained in bRepair Output"; // mode 8
    private final String header_perc_brepair_diff_in_ddmax_diff = "Percentage of BRepair Diagnosis contained in DDMax Diagnosis"; // mode 9
    private final String header_perc_ddmax_diff_in_brepair_diff = "Percentage of DDMax Diagnosis contained in bRepair Diagnosis"; // mode 10
    private final String header_perc_brepair_diff_in_ddmax_output = "Percentage of bRepair Diagnosis contained in DDMax Output"; // mode 8
    private final String header_perc_ddmax_diff_in_brepair_output = "Percentage of DDMax Diagnosis contained in bRepair Output"; // mode 8
    private final String formatHeader = "Format";
    private final String successHeader = "Success";

    /**
     * Instantiate a new DiagnosisSheet
     */
    @SuppressWarnings("unused")
    public DiagnosisSheet() {
        super("diagnosis_sheet", "Diagnosis Sheet.");
    }

    private String headerFor(String baseHeader, Subjects subject) {
        return subject.toString() + " " + baseHeader;
    }

    @Override
    final void buildTable() {
        List<Subject> subjects = Oracle.getSubjects();
        this.addColumnHeader("File Name");
        this.addColumnHeader(formatHeader);
        subjects.forEach(subject -> {
            for (String header : Arrays.asList(
                    header_ddminsize,
                    header_ddmaxdiffsize,
                    header_brepairdiffsize,
                    header_ddmaxoutputsize,
                    header_perc_ddmax_in_ddmin,
                    header_perc_ddmin_in_ddmax,
                    header_perc_brepair_in_ddmin,
                    header_perc_ddmin_in_brepair,
                    header_perc_brepair_diff_in_ddmax_diff,
                    header_perc_ddmax_diff_in_brepair_diff,
                    header_perc_brepair_diff_in_ddmax_output,
                    header_perc_ddmax_diff_in_brepair_output
            )) {
                this.addColumnHeader(this.headerFor(header, subject.getKind()));
            }
        });
        this.addGeneralHeader("Diagnosis Results.");
        Stream<Map.Entry<Path, FileRecord>> files;
        final FileDatabase fileDatabase = super.getDatabase();
        files = fileDatabase.entrySet().stream();
        files.sorted(Comparator.comparing(entry -> entry.getKey().toString())) // Sort by File Name
                .filter(file -> Objects.nonNull(file.getValue().getFormat())) // Filter out files that have not been processed at all, since those don't make any sense! These might be in the database if the program has been used to filter files without further processing those files
                .sorted(Comparator.comparing(entry -> entry.getValue().getFormat().toString())) // Sort by Format
                .forEach(file -> {
                    Path mutated_filename = file.getKey();
                    final String fileName = mutated_filename.toString(); // Key for the Google Sheet should be same as in the other google sheets
                    if (!Files.exists(mutated_filename)) {
                        final String pathname = mutated_filename.toString();
                        //noinspection HardcodedFileSeparator
                        if (pathname.startsWith("/")) {
                            mutated_filename = Paths.get(pathname.substring(1)); // If the files were evaluated in Docker, the path is /testfiles/ instead of testfiles/
                        }
                    }
                    this.set(fileName, formatHeader, file.getValue().getFormat().toString());
                    Path finalMutated_filename = mutated_filename;
                    subjects.forEach(subject -> {
                        if (
                                file.getValue().wasTestedWith(subject.getKind(), Algorithm.DDMAX) &&
                                        file.getValue().wasTestedWith(subject.getKind(), Algorithm.DDMAXG) &&
                                        file.getValue().wasTestedWith(subject.getKind(), Algorithm.BREPAIR)
                        ) {

                            //===
                            // = TODO - Refactor this completely to pre-compute and cache results to speed up processing!
                            // = Preferrably directly after the evaluation
                            // = This operation takes extremely long:
                            //===

                            // Run the python script to evaluate the values to fill this table
                            for (Map.Entry<String, Integer> mode : Arrays.asList(
                                    new AbstractMap.SimpleEntry<String, Integer>(header_ddminsize, 1),
                                    new AbstractMap.SimpleEntry<String, Integer>(header_ddmaxdiffsize, 2),
                                    new AbstractMap.SimpleEntry<String, Integer>(header_ddmaxoutputsize, 3),
                                    new AbstractMap.SimpleEntry<String, Integer>(header_perc_ddmax_in_ddmin, 4),
                                    new AbstractMap.SimpleEntry<String, Integer>(header_perc_ddmin_in_ddmax, 5),
                                    new AbstractMap.SimpleEntry<String, Integer>(header_brepairdiffsize, 6),
                                    new AbstractMap.SimpleEntry<String, Integer>(header_perc_brepair_in_ddmin, 7),
                                    new AbstractMap.SimpleEntry<String, Integer>(header_perc_ddmin_in_brepair, 8),
                                    new AbstractMap.SimpleEntry<String, Integer>(header_perc_brepair_diff_in_ddmax_diff, 9),
                                    new AbstractMap.SimpleEntry<String, Integer>(header_perc_ddmax_diff_in_brepair_diff, 10),
                                    new AbstractMap.SimpleEntry<String, Integer>(header_perc_brepair_diff_in_ddmax_output, 11),
                                    new AbstractMap.SimpleEntry<String, Integer>(header_perc_ddmax_diff_in_brepair_output, 12)
                            )) {
                                ProcessBuilder pb = new ProcessBuilder("python3", Main.ddmindiff_script.toString(), Integer.toString(mode.getValue()), subject.getKind().toString(), finalMutated_filename.toString(), Main.TESTOUTPUT_FOLDER.toString(), file.getValue().getFormat().toString());
                                try {
                                    Logging.generalLogger.log(Level.FINE, "Running " + String.join(" ", pb.command()));
                                    Process result = pb.start();
                                    ProcessReturnValue output;
                                    try {
                                        //noinspection PointlessArithmeticExpression
                                        output = SimpleSubprocess.runProcess(OptionalLong.of(1000L), result); // 1 second
                                    } catch (TimeoutException e) {
                                        Logging.generalLogger.log(Level.WARNING, "There was a timeout running " + String.join(" ", pb.command()), e);
                                        continue;
                                    }
                                    final String ret = output.stdout.strip();
                                    final int returncode = output.returnValue;
                                    if (returncode != 0) {
                                        Logging.generalLogger.log(Level.FINE, "The returncode was " + returncode + " with output " + ret + "\n" + output.stderr);
                                    } else {
                                        try {
                                            this.set(fileName, headerFor(mode.getKey(), subject.getKind()), Float.toString(Float.parseFloat(ret)));
                                        } catch (NumberFormatException e) {
                                            throw new RuntimeException("Not a float: " + ret);
                                        }
                                    }
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    });

                });
    }
}
