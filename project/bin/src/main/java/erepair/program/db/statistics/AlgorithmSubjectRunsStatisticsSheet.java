package erepair.program.db.statistics;

import erepair.program.db.FileDatabase;
import erepair.program.db.FileRecord;
import erepair.program.subject.Oracle;
import erepair.program.subject.Subject;
import erepair.program.Algorithm;

import javax.annotation.Nonnull;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 **/
@SuppressWarnings({"CodeBlock2Expr", "FinalMethodInFinalClass"})
@BuildStatistics
@ForEachAlgorithmStatistics
public final class AlgorithmSubjectRunsStatisticsSheet extends GoogleSheet {
    @Nonnull
    private final Algorithm algorithm;

    /**
     * Instantiate a new AlgorithmSubjectRunsStatisticsSheet
     *
     * @param algorithm Algorithm to evaluate
     */
    @SuppressWarnings("unused")
    public AlgorithmSubjectRunsStatisticsSheet(Algorithm algorithm) {
        super(algorithm.toString().toLowerCase() + "_subject_runs_statistics_sheet", "Subject Runs Statistics Sheet for " + algorithm + ".");
        this.algorithm = algorithm;
    }

    private String headerSubjectRuns(Subject subject) {
        final String subjRunsSuffix = " # Subject Runs";
        return subject.getKind().toString() + subjRunsSuffix;
    }

    private String headerSuccess(Subject subject) {
        final String successRectifiedSuffix = " Success";
        return subject.getKind().toString() + successRectifiedSuffix;
    }

    @Override
    final void buildTable() {
        final String formatHeader = "Format";
        List<Subject> subjects = Oracle.getSubjects();
        this.addColumnHeader("File Name");
        this.addColumnHeader(formatHeader);
        subjects.forEach(subject -> {
            this.addColumnHeader(this.headerSubjectRuns(subject));
            this.addColumnHeader(this.headerSuccess(subject));
        });
        this.addGeneralHeader("Subject Runs Statistics for files repaired with " + algorithm + " for each subject.");
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
                                final var subject_runs = file.getValue().getNumberOfOracleRuns(subject.getKind(), algorithm);
                                subject_runs.ifPresent(
                                        match -> this.set(fileName, this.headerSubjectRuns(subject), Long.toString(match))
                                );
                            }
                        });
                    }
                });
    }
}
