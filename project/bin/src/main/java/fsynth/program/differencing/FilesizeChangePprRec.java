package fsynth.program.differencing;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.file.Path;

public class FilesizeChangePprRec extends FilesizeChange {
    public FilesizeChangePprRec() {
        super(DifferencingAlgorithms.FILESIZE_CHANGE_PPR_REC.toString());
    }

    @Override
    public DifferencingAlgorithms getKind() {
        return DifferencingAlgorithms.FILESIZE_CHANGE_PPR_REC;
    }

    @Nullable
    @Override
    Long runAlgorithm(@Nonnull Path prettyPrintedFile, @Nonnull Path rectifiedFile) {
        return super.getFileSizeDifference(prettyPrintedFile, rectifiedFile);
    }
}
