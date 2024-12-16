package fsynth.program.differencing;

import java.nio.file.Path;

public abstract class FilesizeChange extends DifferencingAlgorithm<Long> {
    public FilesizeChange(String loggingPrefix) {
        super(loggingPrefix);
    }

    Long getFileSizeDifference(Path file1, Path file2) {
        return file2.toFile().length() - file1.toFile().length();
    }
}
