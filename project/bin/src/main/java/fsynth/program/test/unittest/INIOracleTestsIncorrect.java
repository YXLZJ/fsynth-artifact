package fsynth.program.test.unittest;

import fsynth.program.subject.Subjects;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * System tests for the INI Oracles Return Status
 */
@SuppressWarnings({"JavaDoc", "HardcodedLineSeparator", "HardcodedFileSeparator"})
@DisplayName("INI Oracle Tests - Incorrect")
public class INIOracleTestsIncorrect extends INIOracleTests {

    @SuppressWarnings("unused")
    static Stream<Arguments> localParameters() {
        return INIOracleTests.localParameters();
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete Header with whitespaces appended")
    public void testIncorrectINI1(Subjects subjects) {
        this.testIncorrect(
                "[incompleteHeader\n \n\t", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Header with Line Break in name")
    public void testIncorrectINI2(Subjects subjects) {
        this.testIncorrect(
                "[incorrectHeader\n]", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Header with UTF-16 Byte Order Mark")
    public void testIncorrectINI3(Subjects subjects) {
        this.testIncomplete( // This is also in fact incomplete - might be ÿþ[author]=value
                "ÿþ[author]", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Missing Brackets around header")
    public void testIncorrectINI4(Subjects subjects) {
        this.testIncorrect(
                " [section_list]\n" +
                        "section0\n" +
                        "section1\n" +
                        "\n" +
                        "[section0]\n" +
                        "key0=val0\n" +
                        "\n" +
                        "[section1]\n" +
                        "A^r1=val1\n", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Missing Brackets around simple header")
    public void testIncorrectINI5(Subjects subjects) {
        this.testIncomplete( // This is actually incomplete!!! Might be section=value
                "section", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Bracket in Value")
    @Disabled("According to Wikipedia, this seems to be valid")
    public void testIncorrectINI6(Subjects subjects) {
        this.testIncorrect(
                "[section]\nfoo=b]ar", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Bracket in Value 2")
    @Disabled("According to Wikipedia, this seems to be valid")
    public void testIncorrectINI7(Subjects subjects) {
        this.testIncorrect(
                "[section]\nfoo=b[ar", subjects
        );
    }

    /**
     * This unit test is extremely important for INI files, since they cannot be repaired by bRepair if this test fails:
     *
     * @param subjects Subject
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Missing Value with Newline")
    public void testIncorrectINI8(Subjects subjects) {
        this.testIncorrect(
                "[section]\nfoo\n", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Real-World INI file 78834115 with missing Equals")
    public void testIncorrectINI9(Subjects subjects) {
        this.testIncorrect(
                "; Test config file for ini_example.c and INIReaderTest.cpp\n" +
                        "\n" +
                        "[protocol]             ; Protocol configuration\n" +
                        "version=6              ; IPv6\n" +
                        "\n" +
                        "[user]\n" +
                        "name Bob Smith       ; Spaces around '=' are stripped\n" +
                        "email = bob@smith.com  ; And comments (like this) ignored\n" +
                        "active = true          ; Test a boolean\n" +
                        "pi = 3.14159           ; Test a floating point number\n", subjects
        );
    }

    /**
     * Test that incomplete lines with an appended comment are actually classified as inCORRECT -
     * Very important for bRepair to work properly!
     *
     * @param subjects Subject
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Comment after missing Equals")
    public void testIncorrectINI10(Subjects subjects) {
        this.testIncorrect(
                "[protocol]\n" +
                        "name Bob Smith       ; Spaces around '=' are stripped\n", subjects
        );
    }

    /**
     * Test that incomplete lines with an appended comment are actually classified as inCORRECT -
     * Very important for bRepair to work properly!
     * This test is a real-world example where bRepair failed to repair because of misclassification
     *
     * @param subjects Subject
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Comment after missing Equals with plenty of garbage")
    public void testIncorrectINI11(Subjects subjects) {
        //The file should be successful up to this point:
        this.testSuccessful("; Test config file for ini_example.c and INIReaderTest.cpp\n" +
                "\n" +
                "[protocol]             ; Protocol configuration\n" +
                "version=6              ; IPv6\n" +
                "\n" +
                "[user]\n", subjects
        );
        //then, incomplete up to this point:
        this.testIncomplete("; Test config file for ini_example.c and INIReaderTest.cpp\n" +
                "\n" +
                "[protocol]             ; Protocol configuration\n" +
                "version=6              ; IPv6\n" +
                "\n" +
                "[user]\n" +
                "name Bob Smith", subjects
        );
        //and all subsequently appended comments should cause the file to become incorrect:
        this.testIncorrect("; Test config file for ini_example.c and INIReaderTest.cpp\n" +
                "\n" +
                "[protocol]             ; Protocol configuration\n" +
                "version=6              ; IPv6\n" +
                "\n" +
                "[user]\n" +
                "name Bob Smith       ;", subjects
        );
        this.testIncorrect("; Test config file for ini_example.c and INIReaderTest.cpp\n" +
                "\n" +
                "[protocol]             ; Protocol configuration\n" +
                "version=6              ; IPv6\n" +
                "\n" +
                "[user]\n" +
                "name Bob Smith       ; Spaces around '", subjects
        );
        this.testIncorrect("; Test config file for ini_example.c and INIReaderTest.cpp\n" +
                "\n" +
                "[protocol]             ; Protocol configuration\n" +
                "version=6              ; IPv6\n" +
                "\n" +
                "[user]\n" +
                "name Bob Smith       ; Spaces around '=' are strippedemail = bob@smith.com", subjects
        );
        //Real-World failure:
        this.testIncorrect("; Test config file for ini_example.c and INIReaderTest.cpp\n" +
                "\n" +
                "[protocol]             ; Protocol configuration\n" +
                "version=6              ; IPv6\n" +
                "\n" +
                "[user]\n" +
                "name Bob Smith       ; Spaces around '=' are strippedemail = bob@smith.com  ; And comments (like this) ignoreddactive = true          ; Test a booleannpi = 3.14159           ; Test a floating point numberr=/", subjects
        );
    }

    /**
     * Test that incomplete lines with an appended comment are actually classified as inCORRECT -
     * Very important for bRepair to work properly!
     *
     * @param subjects Subject
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Comment after missing closing bracket")
    public void testIncorrectINI12(Subjects subjects) {
        this.testIncorrect(
                "[protocol lululu ; inline comment", subjects
        );
    }
}