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
@SuppressWarnings({"JavaDoc", "HardcodedLineSeparator", "HardcodedFileSeparator"})
@DisplayName("JSON Oracle Tests - Valid")
public class JsonOracleTestsValid extends JsonOracleTests {

    @SuppressWarnings("unused")
    static Stream<Arguments> localParameters() {
        return JsonOracleTests.localParameters();
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Real-World File valid1")
    public void testValidJson1(Subjects subjects) {
        this.testSuccessful(
                super.copyResourceToTempDir("/testfiles/json-valid/valid1.json"), subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Real-World File valid2")
    public void testValidJson2(Subjects subjects) {
        this.testSuccessful(
                super.copyResourceToTempDir("/testfiles/json-valid/valid2.json"), subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Real-World File valid3")
    public void testValidJson3(Subjects subjects) {
        this.testSuccessful(
                super.copyResourceToTempDir("/testfiles/json-valid/valid3.json"), subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Large Real-World File valid4")
    public void testValidJson4(Subjects subjects) {
        this.testSuccessful(
                super.copyResourceToTempDir("/testfiles/json-valid/valid4.json"), subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Different Types in List")
    public void testValidJson5(Subjects subjects) {
        this.testSuccessful(
                "{\"foo\": \"lululu\", \"list\": [1,true,\"lol\",2,3,4,5,6,7,8,9,0]}", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Real-World Object Example")
    public void testValidJson6(Subjects subjects) {
        this.testSuccessful(
                "{ \"_phantomChildren\" : { } , \"_requested\" : { \"fetchSpec\" : \"0.0.1\" } , \"_requiredBy\" : [ \"/remark-parse\" ] }\n", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Multiple different Whitespaces")
    public void testValidJson7(Subjects subjects) {
        this.testSuccessful(
                "{\"foo\":\t[],      \"bar\"\n\t\n\t:\ttrue}\n \t\n\n\n\t\t\t \n", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Escaped Double Quotes in String")
    public void testValidJson8(Subjects subjects) {
        this.testSuccessful(
                "{ \"json\": \" { \\\"test\\\": 1234 \" }", subjects
        );
    }

    /**
     * According to ECMA-404, every JSON value can be placed at the root of a JSON document!
     * See https://www.ecma-international.org/wp-content/uploads/ECMA-404_2nd_edition_december_2017.pdf
     *
     * @param subjects
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("ECMA-404 - Only null")
    public void testECMA404_1(Subjects subjects) {
        this.testSuccessful(
                "null", subjects
        );
    }

    /**
     * According to ECMA-404, every JSON value can be placed at the root of a JSON document!
     * See https://www.ecma-international.org/wp-content/uploads/ECMA-404_2nd_edition_december_2017.pdf
     *
     * @param subjects
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("ECMA-404 - Only number")
    public void testECMA404_2(Subjects subjects) {
        this.testSuccessful(
                "1337", subjects
        );
    }

    /**
     * According to ECMA-404, every JSON value can be placed at the root of a JSON document!
     * See https://www.ecma-international.org/wp-content/uploads/ECMA-404_2nd_edition_december_2017.pdf
     *
     * @param subjects
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("ECMA-404 - Only string")
    public void testECMA404_3(Subjects subjects) {
        this.testSuccessful(
                "\"foo\"", subjects
        );
    }

    /**
     * According to ECMA-404, every JSON value can be placed at the root of a JSON document!
     * See https://www.ecma-international.org/wp-content/uploads/ECMA-404_2nd_edition_december_2017.pdf
     *
     * @param subjects
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("ECMA-404 - Only bool true")
    public void testECMA404_4(Subjects subjects) {
        this.testSuccessful(
                "true", subjects
        );
    }

    /**
     * According to ECMA-404, every JSON value can be placed at the root of a JSON document!
     * See https://www.ecma-international.org/wp-content/uploads/ECMA-404_2nd_edition_december_2017.pdf
     *
     * @param subjects
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("ECMA-404 - Only bool false")
    public void testECMA404_5(Subjects subjects) {
        this.testSuccessful(
                "false", subjects
        );
    }

    /**
     * According to ECMA-404, every JSON value can be placed at the root of a JSON document!
     * See https://www.ecma-international.org/wp-content/uploads/ECMA-404_2nd_edition_december_2017.pdf
     *
     * @param subjects
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("ECMA-404 - Only string 2")
    public void testECMA404_6(Subjects subjects) {
        this.testSuccessful(
                " \"a\"  ", subjects
        );
    }
        static Stream<Arguments> jsonTestFilesValid() {
        return instantiateValidFilesArgs(JsonOracleTests.localParameters(), "json-valid", ".json");
    }
}