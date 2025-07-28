package erepair.program.userinterface.actions.commands;

import erepair.program.FileSize;
import erepair.program.Logging;
import erepair.program.Main;
import erepair.program.db.statistics.BuildStatistics;
import erepair.program.userinterface.actions.Argument;
import erepair.program.userinterface.actions.CliCommand;
import erepair.program.userinterface.actions.Command;
import erepair.program.Algorithm;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

/**
 **/
@CliCommand
public class RebuildFileSizesCommand extends Command {

    public RebuildFileSizesCommand() {
        super('Y', "rebuild-file-sizes", "Rebuild all file sizes in the database. Be sure to backup the database before running this command since this may corrupt the database if there are missing files!");
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
        Main.GLOBAL_DATABASE.forEach((path, fileRecord) -> {
            fileRecord.forEachSubject((algorithm, subject) -> {
                Path outFile = Main.TESTOUTPUT_FOLDER.resolve(fileRecord.getFormat().toString() + algorithm.toString() + "-" + path.getParent().getFileName())
                        .resolve(path.getFileName());
                if (algorithm != Algorithm.PRETTYPRINT) {
                    outFile = outFile.getParent().resolve(outFile.getFileName() + "-" + subject.toString());
                }
                if (!outFile.toFile().isFile()) {
                    Logging.generalLogger.warning("File did not exist: " + outFile);
                    return;
                }
                try {
                    final long repairedFileSize = FileSize.withoutWhitespaces(outFile);
                    fileRecord.setTestResult(
                            subject,
                            algorithm,
                            fileRecord.getTime(subject, algorithm).orElseThrow(),
                            fileRecord.getSuccess(subject, algorithm),
                            fileRecord.getNumberOfOracleRuns(subject, algorithm).orElse(1L),
                            outFile,
                            repairedFileSize);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("File " + outFile + " did not exist");
                }

            });
            try {
                final long originalFileSize = FileSize.withoutWhitespaces(path);
                fileRecord.setSize(originalFileSize);
                final Path unmutatedFile = fileRecord.getUnmutatedFile();
                if (unmutatedFile != null) {
                    final long unmutatedSize = FileSize.withoutWhitespaces(unmutatedFile);
                    fileRecord.setUnmutatedFile(unmutatedFile, unmutatedSize);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
        Main.saveStatistics();
        return 0;
    }
}

