package erepair.program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

/**
 **/
public class FileSize {
    private FileSize() {
    }

    /**
     * Evaluate the file size of the given file without whitespaces.
     *
     * @param file File under test
     * @return the number of characters in the file without whitespaces
     * @throws IOException if the file cannot be read
     */
    public static long withoutWhitespaces(Path file) throws IOException {
        try (FileReader fileReader = new FileReader(file.toFile())) {
            BufferedReader br = new BufferedReader(fileReader);
            long count = br.lines().flatMapToInt(String::codePoints).filter(e -> !Character.isWhitespace(e)).count();
            return count;
        }
    }
}
