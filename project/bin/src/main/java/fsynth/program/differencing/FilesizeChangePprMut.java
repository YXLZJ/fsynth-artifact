package fsynth.program.differencing;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.file.Path;

public class FilesizeChangePprMut extends FilesizeChange {
    public FilesizeChangePprMut() {
        super(DifferencingAlgorithms.FILESIZE_CHANGE_PPR_MUT.toString());
    }

    @Override
    public DifferencingAlgorithms getKind() {
        return DifferencingAlgorithms.FILESIZE_CHANGE_PPR_MUT;
    }

    @Nullable
    @Override
    Long runAlgorithm(@Nonnull Path prettyPrintedFile, @Nonnull Path mutatedFile) {
        return super.getFileSizeDifference(prettyPrintedFile, mutatedFile);
    }
}
