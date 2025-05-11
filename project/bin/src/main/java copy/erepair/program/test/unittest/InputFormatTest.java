package erepair.program.test.unittest;

import erepair.program.InputFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 **/
@SuppressWarnings("JavaDoc")
@DisplayName("InputFormat Unit Tests")
public class InputFormatTest extends TestHelper {
    public void testFormat(String fileContent, String fileSuffix, InputFormat inputFormat) {
        super.setUpTempFiles();
        try {
            final Path file = super.setUpTempFile(fileContent, fileSuffix);
            assertEquals(inputFormat, InputFormat.fromFileType(file), "A wrong input format was detected!");
        } finally {
            super.tearDownTempFiles();
        }
    }

    @Test
    @DisplayName("Simple JSON")
    public void testFromFile1() {
        testFormat("{}", ".json", InputFormat.JSON);
    }

    @Test
    @DisplayName("JSON Dict without Extension")
    public void testFromFile2() {
        testFormat("{}", "noextension", InputFormat.JSON);
    }

    @Test
    @DisplayName("JSON boolean without Extension")
    public void testFromFile3() {
        StringBuilder nonsenseFile = new StringBuilder(5010);
        IntStream.range(0, 5000).forEach(i -> nonsenseFile.append(" "));
        nonsenseFile.append("tRuE\n");
        testFormat(nonsenseFile.toString(), "", InputFormat.JSON);
    }

    @Test
    @DisplayName("No JSON 1")
    public void testFromFile4() {
        testFormat("truelululu", "", InputFormat.INVALID);
    }

    @Test
    @DisplayName("No JSON 2")
    public void testFromFile5() {
        testFormat("false5", "", InputFormat.INVALID);
    }

    @Test
    @DisplayName("JSON with garbage 1")
    public void testFromFile6() {
        testFormat("null{}[]", "", InputFormat.JSON);
    }

    @Test
    @DisplayName("JSON with garbage 2")
    public void testFromFile7() {
        testFormat("null.{}", "", InputFormat.JSON);
    }
}
