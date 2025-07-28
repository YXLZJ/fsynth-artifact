package erepair.program.test.unittest;

import erepair.program.subject.Subjects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * System tests for the JSON Oracles Return Status
 */
@SuppressWarnings({"JavaDoc", "HardcodedLineSeparator", "MethodMayBeStatic"})
@DisplayName("JSON Oracle Tests - Incomplete")

public class JsonOracleTestsIncomplete extends JsonOracleTests {

    @SuppressWarnings("unused")
    static Stream<Arguments> localParameters() {
        return JsonOracleTests.localParameters();
    }

    /**
     * Test mentioned in Issue 1, see https://projects.cispa.saarland/lukas.kirschner/bfuzzerrepairer/-/issues/1
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Real-World File incomplete1")
    public void testIncompleteJson1(Subjects subjects) {
        this.testIncomplete(
                super.copyResourceToTempDir("/testfiles/json-incomplete/incomplete1.json"), subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Missing Closing Curly Brace")
    public void testIncompleteJson2(Subjects subjects) {
        this.testIncomplete(
                "{ \"true\": true", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Missing Closing Square Bracket")
    public void testIncompleteJson3(Subjects subjects) {
        this.testIncomplete(
                "[true,false,true", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Unfinished List in Object")
    public void testIncompleteJson4(Subjects subjects) {
        this.testIncomplete(
                "{\"foo\": [1,true,\"lul\"", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Single Opening Bracket")
    public void testIncompleteJson5(Subjects subjects) {
        this.testIncomplete(
                "[", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Unfinished List")
    public void testIncompleteJson6(Subjects subjects) {
        this.testIncomplete(
                "[ \"lululu", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete Escape Character")
    public void testIncompleteJson7(Subjects subjects) {
        //noinspection HardcodedFileSeparator
        this.testIncomplete(
                "[ \"lululu\\", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Unfinished List 2")
    public void testIncompleteJson8(Subjects subjects) {
        this.testIncomplete(
                "[ \"lululu\"", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Unfinished List 3")
    public void testIncompleteJson9(Subjects subjects) {
        this.testIncomplete(
                "[ \"lululu\",", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Cut away End of 1116 Single")
    public void testIncompleteJson10(Subjects subjects) {
        this.testIncomplete(
                "{ \"presets", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("JSON unterminated object with garbage from mult 3790.json")
    public void testIncompleteJson11(Subjects subjects) {
        this.testIncomplete(
                "{\n" +
                        "  \"tros\"\n" +
                        "  :n", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("JSON unterminated object with garbage 2 from mult 8240.json")
    public void testIncompleteJson12(Subjects subjects) {
        this.testIncomplete(
                "{ \"depèndenc\u0000es\" :9\n" +
                        " ,\"foo\" : fal\n" +
                        "        \n" +
                        "         \n" +
                        " \n" +
                        "                        \n" +
                        "\n" +
                        "\n", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("JSON unterminated object with garbage 3 from Realworld 255.json")
    public void testIncompleteJson13(Subjects subjects) {
        this.testIncomplete(
                "{\n" +
                        "  \"// This config contains proposed rules that we'd like to have enabled but haven't\n" +
                        "         \n" +
                        "\n" +
                        "     ", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("JSON successively add null value from mult 3790.json")
    public void testIncompleteJson14_null1(Subjects subjects) {
        this.testIncomplete(
                "{\n" +
                        "  \"tros\"\n" +
                        "  :nu", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("JSON successively add null value from mult 3790.json")
    public void testIncompleteJson14_null2(Subjects subjects) {
        this.testIncomplete(
                "{\n" +
                        "  \"tros\"\n" +
                        "  :nul", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("JSON successively add null value from mult 3790.json")
    public void testIncompleteJson14_null3(Subjects subjects) {
        this.testIncomplete(
                "{\n" +
                        "  \"tros\"\n" +
                        "  :null \n\t", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("JSON successively add null value from mult 3790.json")
    public void testIncompleteJson14_null4(Subjects subjects) {
        this.testSuccessful(
                "{\n" +
                        "  \"tros\"\n" +
                        "  :null}", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Empty File")
    public void testIncompleteJson15(Subjects subjects) {
        this.testIncomplete(
                "", subjects
        );
    }
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Quote only")
    public void testIncompleteJson16(Subjects subjects) {
        this.testIncomplete(
                "\"", subjects
        );
    }
}