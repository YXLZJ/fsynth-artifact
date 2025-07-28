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
@BuildStatistics
@ForEachAlgorithmStatistics
public final class AlgorithmSuccessSheet extends GoogleSheet {
    @Nonnull
    private final Algorithm algorithm;

    /**
     * Instantiate a new FileOverviewSheet
     *
     * @param algorithm Algorithm to evaluate
     */
    @SuppressWarnings("unused")
    public AlgorithmSuccessSheet(Algorithm algorithm) {
        super(algorithm.toString().toLowerCase() + "_success_sheet", "Success Overview Sheet for " + algorithm + ".");
        this.algorithm = algorithm;
    }

    private String headerSuccess(Subject subject) {
        final String successRectifiedSuffix = " Success";
        return subject.getKind().toString() + successRectifiedSuffix;
    }

    private String headerSize(Subject subject) {
        final String sizeHeaderRectifiedSuffix = " Size";
        return subject.getKind().toString() + sizeHeaderRectifiedSuffix;
    }

    @Override
    final void buildTable() {
        final String sizeHeader = "Size Corrupted";
        final String formatHeader = "Format";
        List<Subject> subjects = Oracle.getSubjects();
        this.addColumnHeader("File Name");
        this.addColumnHeader(formatHeader);
        this.addColumnHeader(sizeHeader);
        subjects.forEach(subject -> {
            this.addColumnHeader(this.headerSuccess(subject));
            this.addColumnHeader(this.headerSize(subject));
        });
        this.addGeneralHeader("Success and Size Change Overview for " + algorithm + " for each subject. Empty values mean untested.");
        Stream<Map.Entry<Path, FileRecord>> files;
        final FileDatabase fileDatabase = super.getDatabase();
        files = fileDatabase.entrySet().stream();
        files.sorted(Comparator.comparing(entry -> entry.getKey().toString())) // Sort by File Name
                .filter(file -> Objects.nonNull(file.getValue().getFormat())) // Filter out files that have not been processed at all, since those don't make any sense! These might be in the database if the program has been used to filter files without further processing those files
                .sorted(Comparator.comparing(entry -> entry.getValue().getFormat().toString())) // Sort by Format
                .forEach(file -> {
                    final String fileName = file.getKey().toString();
                    this.set(fileName, sizeHeader, Long.toString(file.getValue().getSize(), 10));
                    this.set(fileName, formatHeader, file.getValue().getFormat().toString());
                    if (file.getValue().wasTestedWith(this.algorithm)) {
                        subjects.forEach(subject -> {
                            if (file.getValue().wasTestedWith(subject.getKind(), this.algorithm)) {
                                final boolean success = file.getValue().getSuccess(subject.getKind(), algorithm);
                                String newsize;
                                if (success) {
                                    newsize = Long.toString(file.getValue().getSizeOfRectifiedFile(subject.getKind(), algorithm));
                                } else {
                                    newsize = "";
                                }
                                this.set(fileName, this.headerSuccess(subject), Boolean.toString(success));
                                this.set(fileName, this.headerSize(subject), newsize);
                            }
                        });
                    }
                });
    }
}
