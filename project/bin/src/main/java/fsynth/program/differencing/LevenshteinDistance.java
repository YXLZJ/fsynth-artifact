package fsynth.program.differencing;

import fsynth.program.Parsing;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.file.Path;

public class LevenshteinDistance extends DifferencingAlgorithm<Long> {
    public LevenshteinDistance() {
        super("Levenshtein");
    }

    @Override
    public DifferencingAlgorithms getKind() {
        return DifferencingAlgorithms.LEVENSHTEIN;
    }

    @Nullable
    @Override
    Long runAlgorithm(@Nonnull Path file1, @Nonnull Path file2) {
        final int threshold = 750;//Magic Number / make as large as possible
        final String string1 = Parsing.readStringFromFile(file1);
        final String string2 = Parsing.readStringFromFile(file2);
        org.apache.commons.text.similarity.LevenshteinDistance levenshteinDistance =
                new org.apache.commons.text.similarity.LevenshteinDistance(threshold);
        return (long) levenshteinDistance.apply(string1, string2);
    }
}
