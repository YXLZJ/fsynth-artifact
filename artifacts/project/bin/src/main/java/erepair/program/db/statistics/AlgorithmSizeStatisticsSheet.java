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
public final class AlgorithmSizeStatisticsSheet extends GoogleSheet {
    @Nonnull
    private final Algorithm algorithm;

    /**
     * Instantiate a new AlgorithmSizeStatisticsSheet
     *
     * @param algorithm Algorithm to evaluate
     */
    @SuppressWarnings("unused")
    public AlgorithmSizeStatisticsSheet(Algorithm algorithm) {
        super(algorithm.toString().toLowerCase() + "_size_statistics_sheet", "Size Statistics Sheet for " + algorithm + ".");
        this.algorithm = algorithm;
    }

    private String headerOrigSize() {
        final String successRectifiedSuffix = "Original Size";
        return successRectifiedSuffix;
    }

    private String headerRepairedSize(Subject subject) {
        final String sizeHeaderRectifiedSuffix = " Repaired Size";
        return subject.getKind().toString() + sizeHeaderRectifiedSuffix;
    }

    private String headerNumDeletions(Subject subject) {
        final String sizeHeaderRectifiedSuffix = " Deletions";
        return subject.getKind().toString() + sizeHeaderRectifiedSuffix;
    }

    private String headerNumInsertions(Subject subject) {
        final String sizeHeaderRectifiedSuffix = " Insertions";
        return subject.getKind().toString() + sizeHeaderRectifiedSuffix;
    }

    @Override
    final void buildTable() {
        final String formatHeader = "Format";
        List<Subject> subjects = Oracle.getSubjects();
        this.addColumnHeader("File Name");
        this.addColumnHeader(formatHeader);
        this.addColumnHeader(this.headerOrigSize());
        subjects.forEach(subject -> {
            this.addColumnHeader(this.headerRepairedSize(subject));
        });
        if (this.algorithm.isBFuzzerAlgorithm()) {
            subjects.forEach(subject -> {
                this.addColumnHeader(this.headerNumDeletions(subject));
                this.addColumnHeader(this.headerNumInsertions(subject));
            });
        }
        this.addGeneralHeader("Size Change Statistics for files repaired with " + algorithm + " for each subject. Empty value means untested.");
        Stream<Map.Entry<Path, FileRecord>> files;
        final FileDatabase fileDatabase = super.getDatabase();
        files = fileDatabase.entrySet().stream();
        files.sorted(Comparator.comparing(entry -> entry.getKey().toString())) // Sort by File Name
                .filter(file -> Objects.nonNull(file.getValue().getFormat())) // Filter out files that have not been processed at all, since those don't make any sense! These might be in the database if the program has been used to filter files without further processing those files
                .sorted(Comparator.comparing(entry -> entry.getValue().getFormat().toString())) // Sort by Format
                .forEach(file -> {
                    final String fileName = file.getKey().toString();
                    this.set(fileName, this.headerOrigSize(), Long.toString(file.getValue().getSize(), 10));
                    this.set(fileName, formatHeader, file.getValue().getFormat().toString());
                    if (file.getValue().wasTestedWith(this.algorithm)) {
                        subjects.forEach(subject -> {
                            if (file.getValue().wasTestedWith(subject.getKind(), this.algorithm)) {
                                final boolean success = file.getValue().getSuccess(subject.getKind(), algorithm);
                                String newsize;
                                if (success) {
                                    newsize = Long.toString(file.getValue().getSizeOfRectifiedFile(subject.getKind(), algorithm));
                                    this.set(fileName, this.headerRepairedSize(subject), newsize);
                                } else {
                                    this.set(fileName, this.headerRepairedSize(subject), "0"); // Treat non-repaired files as total data loss
                                }
                                if (this.algorithm.isBFuzzerAlgorithm()) {
                                    this.set(fileName, this.headerNumDeletions(subject), Long.toString(file.getValue().getNumberOfDeletions(subject.getKind(), this.algorithm).orElse(0L)));
                                    this.set(fileName, this.headerNumInsertions(subject), Long.toString(file.getValue().getNumberOfInsertions(subject.getKind(), this.algorithm).orElse(0L)));
                                }
                            }
                        });
                    }
                });
    }
}
