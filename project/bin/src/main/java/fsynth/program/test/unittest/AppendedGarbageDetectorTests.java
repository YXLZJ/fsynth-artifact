package fsynth.program.test.unittest;

import fsynth.program.Logging;
import fsynth.program.generated.JSONLexer;
import fsynth.program.generated.JSONParser;
import fsynth.program.subject.AppendedGarbageDetector;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * System tests for the Appended Garbage Detector
 */
@SuppressWarnings({"JavaDoc", "HardcodedLineSeparator", "MethodMayBeStatic"})
@DisplayName("Appended Garbage Detector Tests")
public class AppendedGarbageDetectorTests extends TestHelper {
    private void testIt(String fileContent, Class<? extends Lexer> lexerClass, Class<? extends Parser> parserClass, String startRuleName, boolean expectGarbage) {
        super.setUpTempFiles();
        Logging.setAllLogLevels(Level.FINEST);
        try {
            final Path file = super.setUpTempFile(
                    fileContent
                    , "");
            AppendedGarbageDetector detector = new AppendedGarbageDetector(lexerClass, parserClass, startRuleName);
            if (expectGarbage) {
                assertTrue(detector.hasAppendedGarbage(file.normalize().toString()), "The " + detector.getClass().getSimpleName() + " did not report appended garbage!");
            } else {
                assertFalse(detector.hasAppendedGarbage(file.normalize().toString()), "The " + detector.getClass().getSimpleName() + " reported appended garbage for a file without appended garbage!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            super.tearDownTempFiles();
            Logging.setAllLogLevels(Level.INFO);
        }
    }

    private void testAppendedGarbage(String fileContent, Class<? extends Lexer> lexerClass, Class<? extends Parser> parserClass, String startRuleName) {
        testIt(fileContent, lexerClass, parserClass, startRuleName, true);
    }

    private void testNoAppendedGarbage(String fileContent, Class<? extends Lexer> lexerClass, Class<? extends Parser> parserClass, String startRuleName) {
        testIt(fileContent, lexerClass, parserClass, startRuleName, false);
    }

    @Test
    @DisplayName("Simple JSON File 1")
    void testJsonSimple1() {
        testNoAppendedGarbage(
                "{ \"foo\": 1 }",
                JSONLexer.class,
                JSONParser.class,
                "json"
        );
    }

    @Test
    @DisplayName("Simple JSON File 2")
    void testJsonSimple2() {
        testNoAppendedGarbage(
                "[ 1, 2, 3, 4 ]",
                JSONLexer.class,
                JSONParser.class,
                "json"
        );
    }

    @Test
    @DisplayName("JSON File with Whitespaces 1")
    void testJsonWhitespace1() {
        testNoAppendedGarbage(
                "     [  1,\t\t  2, 3 ,   4 \t\n\t ]\n\t\t\t\n\t\n \t",
                JSONLexer.class,
                JSONParser.class,
                "json"
        );
    }

    @Test
    @DisplayName("JSON File with Whitespaces 2")
    void testJsonWhitespace2() {
        testNoAppendedGarbage(
                "     true\n\n\n\t\n\t\n\t\n\t\n\t\r\n\t  \t\t\t",
                JSONLexer.class,
                JSONParser.class,
                "json"
        );
    }

    @Test
    @DisplayName("Simple JSON File with garbage 1")
    void testJsonGarbage1() {
        testAppendedGarbage(
                "{ \"foo\": 1 }lululu",
                JSONLexer.class,
                JSONParser.class,
                "json"
        );
    }

    @Test
    @DisplayName("Simple JSON File with garbage 2")
    void testJsonGarbage2() {
        testAppendedGarbage(
                "{}0",
                JSONLexer.class,
                JSONParser.class,
                "json"
        );
    }

    @Test
    @DisplayName("Simple JSON File with garbage 3")
    void testJsonGarbage3() {
        testAppendedGarbage(
                "[1,2,3,4]{}",
                JSONLexer.class,
                JSONParser.class,
                "json"
        );
    }

    @Test
    @DisplayName("Simple JSON File with garbage 4")
    void testJsonGarbage4() {
        testAppendedGarbage(
                "[]null",
                JSONLexer.class,
                JSONParser.class,
                "json"
        );
    }

    @Test
    @DisplayName("Complex JSON File with garbage 1")
    void testJsonGarbage5() {
        //noinspection HardcodedFileSeparator
        testAppendedGarbage(
                new Scanner(
                        Objects.requireNonNull(
                                AppendedGarbageDetectorTests.class.getResourceAsStream("/testfiles/json-invalid/invalid1.json")
                        ), StandardCharsets.UTF_8
                ).useDelimiter("\\A").next(),
                JSONLexer.class,
                JSONParser.class,
                "json"
        );
    }

    @Test
    @DisplayName("ECMA JSON with boolean as root and appended garbage")
    void testJsonGarbage6() {
        testAppendedGarbage(
                "true that",
                JSONLexer.class,
                JSONParser.class,
                "json"
        );
    }

    @Test
    @DisplayName("ECMA JSON with null as root and appended garbage")
    void testJsonGarbage7() {
        testAppendedGarbage(
                "null value",
                JSONLexer.class,
                JSONParser.class,
                "json"
        );
    }
}