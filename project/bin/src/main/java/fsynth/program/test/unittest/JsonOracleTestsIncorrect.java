package fsynth.program.test.unittest;

import fsynth.program.subject.Subjects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * System tests for the JSON Oracles Return Status
 */
@SuppressWarnings({"JavaDoc", "HardcodedLineSeparator", "HardcodedFileSeparator", "RedundantSuppression"})
@DisplayName("JSON Oracle Tests - Incorrect")
public class JsonOracleTestsIncorrect extends JsonOracleTests {

    @SuppressWarnings("unused")
    static Stream<Arguments> localParameters() {
        return JsonOracleTests.localParameters();
    }

    /**
     * Test if the oracle accepts garbage after the JSON file
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Garbage appended to JSON Object")
    public void testIncorrectJson1_garbage1(Subjects subjects) {
        this.testIncorrect(
                "{\"foo\":1234}lululu\n\n", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Unfinished List in Finished Object")
    public void testIncorrectJson2(Subjects subjects) {
        this.testIncorrect(
                "{\"foo\": [1,true,\"lul\"}", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Object ended by Square Bracket")
    public void testIncorrectJson3(Subjects subjects) {
        this.testIncorrect(
                "{\"foo': 1 } \" ]", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("List ended by Curly Brace")
    public void testIncorrectJson4(Subjects subjects) {
        this.testIncorrect(
                "[}", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Missing Colon in Object")
    public void testIncorrectJson5(Subjects subjects) {
        this.testIncorrect(
                "{ \"foo\" true }", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Missing Comma in Object")
    public void testIncorrectJson6(Subjects subjects) {
        this.testIncorrect(
                "{ \"foo\": true \"bar\": false }", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Missing Comma in List")
    public void testIncorrectJson7(Subjects subjects) {
        this.testIncorrect(
                "[ \"foo\" \"bar\" ]", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Nested List with swapped closing parentheses")
    public void testIncorrectJson8(Subjects subjects) {
        this.testIncorrect(
                "{\"foo\": [1,true,\"lul\"}]", subjects
        );
    }

    /**
     * Test if the oracle accepts too many closing braces
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Too many closing braces")
    public void testIncorrectJson9_garbage2(Subjects subjects) {
        this.testIncorrect(
                "{\"foo\":1234}}}", subjects
        );
    }

    /**
     * Test if the oracle accepts a valid JSON object after an already finished JSON object
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Valid JSON appended to a finished object")
    public void testIncorrectJson10_garbage3(Subjects subjects) {
        this.testIncorrect(
                "{\"foo\":1234}{\"foo\":1234}", subjects
        );
    }

    /**
     * Test if the oracle accepts a complex JSON file with a single "f" appended as garbage
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Complex JSON file with appended garbage")
    public void testIncorrectJson11_garbage4(Subjects subjects) {
        this.testIncorrect(
                super.copyResourceToTempDir("/testfiles/json-invalid/invalid1.json"), subjects
        );
    }

    /**
     * Test if the oracle accepts 1116.json
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Mutated JSON File 1116 Single")
    public void testIncorrectJson12(Subjects subjects) {
        this.testIncorrect(
                "{ \"presets¼ : [ [ \"es2015\" ] ] }", subjects
        );
    }

    /**
     * Test if the oracle accepts JSON Objects without mapped values
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("JSON Object without Value")
    public void testIncorrectJson13(Subjects subjects) {
        this.testIncorrect(
                "{ \"presets\" }", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("JSON Object without Value 2")
    public void testIncorrectJson14(Subjects subjects) {
        this.testIncorrect(
                "{ 0 }", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("JSON Object with unfinished terminal")
    public void testIncorrectJson15(Subjects subjects) {
        this.testIncorrect(
                "{ 0: n }", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("JSON Object with unfinished terminal 2")
    public void testIncorrectJson16(Subjects subjects) {
        this.testIncorrect(
                "{ 0: nul }", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("JSON Object with whitespace after unfinished terminal")
    public void testIncorrectJson17(Subjects subjects) {
        this.testIncorrect(
                "{ 0: nu ", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("JSON Object with whitespace after unfinished terminal 2")
    public void testIncorrectJson18(Subjects subjects) {
        this.testIncorrect(
                "{ 0: n\n", subjects
        );
    }
}