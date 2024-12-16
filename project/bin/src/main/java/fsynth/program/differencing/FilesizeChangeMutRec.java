package fsynth.program.differencing;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.file.Path;

public class FilesizeChangeMutRec extends FilesizeChange {
    public FilesizeChangeMutRec() {
        super(DifferencingAlgorithms.FILESIZE_CHANGE_MUT_REC.toString());
    }

    @Override
    public DifferencingAlgorithms getKind() {
        return DifferencingAlgorithms.FILESIZE_CHANGE_MUT_REC;
    }

    @Nullable
    @Override
    Long runAlgorithm(@Nonnull Path mutatedFile, @Nonnull Path rectifiedFile) {
        return super.getFileSizeDifference(mutatedFile, rectifiedFile);
    }
}
