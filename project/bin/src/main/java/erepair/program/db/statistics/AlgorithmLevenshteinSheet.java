package erepair.program.db.statistics;

import erepair.program.db.FileDatabase;
import erepair.program.db.FileRecord;
import erepair.program.differencing.DifferencingAlgorithms;
import erepair.program.subject.Oracle;
import erepair.program.subject.Subject;
import erepair.program.Algorithm;

import javax.annotation.Nonnull;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

/**
 **/
@SuppressWarnings({"CodeBlock2Expr", "FinalMethodInFinalClass"})
@BuildStatistics
@ForEachAlgorithmStatistics
public final class AlgorithmLevenshteinSheet extends GoogleSheet {
    @Nonnull
    private final Algorithm algorithm;

    /**
     * Instantiate a new AlgorithmLevenshteinSheet
     *
     * @param algorithm Algorithm to evaluate
     */
    @SuppressWarnings("unused")
    public AlgorithmLevenshteinSheet(Algorithm algorithm) {
        super(algorithm.toString().toLowerCase() + "_levenshtein_sheet", "Levenshtein Distancing Sheet for " + algorithm + ".");
        this.algorithm = algorithm;
    }

    private String headerSuccess(Subject subject) {
        final String successRectifiedSuffix = " Success";
        return subject.getKind().toString() + successRectifiedSuffix;
    }

    private String headerLevenshteinDistance(Subject subject) {
        final String levenshteinHeaderRectifiedSuffix = " Levenshtein Distance";
        return subject.getKind().toString() + levenshteinHeaderRectifiedSuffix;
    }

    @Override
    final void buildTable() {
        final String formatHeader = "Format";
        List<Subject> subjects = Oracle.getSubjects();
        this.addColumnHeader("File Name");
        this.addColumnHeader(formatHeader);
        subjects.forEach(subject -> {
            this.addColumnHeader(this.headerLevenshteinDistance(subject));
            this.addColumnHeader(this.headerSuccess(subject));
        });
        this.addGeneralHeader("Levenshtein Distance for " + algorithm + " for each file and subject.");
        Stream<Map.Entry<Path, FileRecord>> files;
        final FileDatabase fileDatabase = super.getDatabase();
        files = fileDatabase.entrySet().stream();
        files.sorted(Comparator.comparing(entry -> entry.getKey().toString())) // Sort by File Name
                .filter(file -> Objects.nonNull(file.getValue().getFormat())) // Filter out files that have not been processed at all, since those don't make any sense! These might be in the database if the program has been used to filter files without further processing those files
                .sorted(Comparator.comparing(entry -> entry.getValue().getFormat().toString())) // Sort by Format
                .forEach(file -> {
                    final String fileName = file.getKey().toString();
                    this.set(fileName, formatHeader, file.getValue().getFormat().toString());
                    if (file.getValue().wasTestedWith(this.algorithm)) {
                        subjects.forEach(subject -> {
                            if (file.getValue().wasTestedWith(subject.getKind(), this.algorithm)) {
                                final boolean success = file.getValue().getSuccess(subject.getKind(), algorithm);
                                this.set(fileName, this.headerSuccess(subject), Boolean.toString(success));
                                final Optional<Long> levenshteinDistance = file.getValue().getDifferencingResult(DifferencingAlgorithms.LEVENSHTEIN, subject.getKind(), algorithm);
                                levenshteinDistance.ifPresent(aLong -> this.set(fileName, this.headerLevenshteinDistance(subject), Long.toString(aLong)));
                            }
                        });
                    }
                });
    }
}
