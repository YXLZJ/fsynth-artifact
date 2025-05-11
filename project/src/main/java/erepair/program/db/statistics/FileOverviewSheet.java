package erepair.program.db.statistics;

import erepair.program.Main;
import erepair.program.Parsing;
import erepair.program.subject.Oracle;
import erepair.program.subject.Subject;
import erepair.program.InputFormat;
import erepair.program.db.FileDatabase;
import erepair.program.db.FileRecord;
import erepair.program.subject.Subjects;

import javax.annotation.Nonnull;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 **/
@SuppressWarnings({"HardcodedFileSeparator", "unused"})
@BuildStatistics
@ForEachFormatStatistics
public final class FileOverviewSheet extends GoogleSheet {
    @Nonnull
    private final InputFormat format;

    /**
     * Instantiate a new FileOverviewSheet
     *
     * @param format Format, case-insensitive. MUST be the same as annotated in the annotations of the subject
     */
    public FileOverviewSheet(InputFormat format) {
        super(format.toString().toLowerCase() + "_file_overview_sheet", "File Overview Sheet for " + format);
        this.format = format;
    }

    @Override
    final void buildTable() {
        final String sizeHeader = "Size";
        final String mutationsHeader = "Max length of consecutive mutations";
        List<Subject> subjects = Oracle.getSubjectsFor(this.format);
        this.addColumnHeader("File name");
        this.addColumnHeader(sizeHeader);
        this.addColumnHeader(Subjects.INVALID.toString(), "ANTLR");
        subjects.forEach(subject -> this.addColumnHeader(subject.getKind().toString()));
        this.addColumnHeader(mutationsHeader);
        this.addGeneralHeader("File names, FailReason for each subject and file sizes in Bytes for each file. Empty=Success");
        Stream<Map.Entry<Path, FileRecord>> files;
        final FileDatabase fileDatabase = super.getDatabase();
        if (fileDatabase == null) {
            throw new NullPointerException("This should not happen");
        }
        files = fileDatabase.getForFormat(this.format);
        files.sorted(Comparator.comparing(entry -> entry.getValue().toString())).forEach(file -> {
            Stream.concat(Stream.of(Subjects.INVALID), subjects.stream().map(Subject::getKind)).forEach(subject -> {
                this.set(file.getKey().toString(), subject.toString(), file.getValue().getFailReason(subject));
            });
            this.set(file.getKey().toString(), sizeHeader, Long.toString(file.getValue().getSize(), 10));
            if (file.getValue().hasUnmutatedFiles()) {
                final Path originalFile = Parsing.convert_docker_path(file.getValue().getUnmutatedFile());
                final Path mutatedFile = Parsing.convert_docker_path(file.getKey());
                if (originalFile != null && Files.isRegularFile(originalFile)) {
                    final String original = Parsing.readStringFromFile(originalFile);
                    final String mutated = Parsing.readStringFromFile(mutatedFile);
                    final List<Integer> lens = Parsing.getConsecutiveChangedLocations(original, mutated);
                    final int maxlen = lens.stream().mapToInt(i -> i).max().orElse(0);
                    this.set(file.getKey().toString(), mutationsHeader, Integer.toString(maxlen));
                } else {
                    this.set(file.getKey().toString(), mutationsHeader, "N/A (The original file did not exist at " + originalFile + ")");
                }
            } else {
                this.set(file.getKey().toString(), mutationsHeader, "N/A (No original files are present)");
            }
        });
    }
}
