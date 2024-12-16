package fsynth.program.test.unittest;

import fsynth.program.subject.Subjects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * System tests for the INI Oracles Return Status
 */
@SuppressWarnings({"JavaDoc", "HardcodedLineSeparator", "HardcodedFileSeparator"})
@DisplayName("INI Oracle Tests - Valid")
public class INIOracleTestsValid extends INIOracleTests {

    @SuppressWarnings("unused")
    static Stream<Arguments> localParameters() {
        return INIOracleTests.localParameters();
    }

    static Stream<Arguments> iniTestFilesValid() {
        return instantiateValidFilesArgs(INIOracleTests.localParameters(), "ini-valid", ".ini");
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Real-World File valid1")
    public void testValidINI1(Subjects subjects) {
        this.testSuccessful(
                super.copyResourceToTempDir("/testfiles/ini-valid/valid1.ini"), subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Simple INI - Header and some Values")
    public void testValidINI2(Subjects subjects) {
        this.testSuccessful(
                "[foo]\nbar=1337\nabc=xyz\n\n\t", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Simple INI - Header only")
    public void testValidINI3(Subjects subjects) {
        this.testSuccessful(
                "[foo]", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Simple INI - Header only with whitespaces")
    public void testValidINI4(Subjects subjects) {
        this.testSuccessful(
                " \n \t\t\n[foo]\n\t\t\n\t \t\n", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Simple INI - Value only")
    public void testValidINI5(Subjects subjects) {
        this.testSuccessful(
                "foo=bar", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Simple INI - Quoted Value")
    public void testValidINI6(Subjects subjects) {
        this.testSuccessful(
                "foo=\"bar\"", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Simple INI - Quoted Key Value Pair")
    public void testValidINI7(Subjects subjects) {
        this.testSuccessful(
                "\"foo\"=\"bar\"", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Multiline Real-World Snippet with special Characters")
    public void testValidINI8(Subjects subjects) {
        this.testSuccessful(
                " [section1]\n" +
                        "dup1 = v¡al1\n" +
                        "dup2 = val2Ú\n" +
                        "nospace = val\n" +
                        "mÚultiline = with\n" +
                        " leading\n" +
                        " space\n", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Garbage inside section - Real-World Timeout Example with Unicode \\0 character")
    public void testValidINI9(Subjects subjects) {
        this.testSuccessful(
                " [section1]\n" +
                        "dup1 = v¡al1\n" +
                        "dup2 = val2Ú\n" +
                        "nospace = val\n" +
                        "mÚultiline = with\n" +
                        " leading\n" +
                        " space\n" +
                        "n*multiline M= not supported with\\\n" +
                        "comment_after1 = val\n" +
                        "[commìent_after2 = valnot a commenttcomment_after3 = val #not a commenttescaped_not_processed = tYest \\nescapeecIolon b= valldouble_quotes = \"not removed\"\"single_quotes = 'not removed''spaces_stripped = vallinternal_not_s\u0000tripped = v  allnotempty1 = comment=vallempty =  python_interpolate = %s(dup1)s/blahhinterpolate2 = ${d up1}/blahhCaps = not nsignificanttcombine = sectionss\"t]", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Simple INI file with Zero byte")
    public void testValidINI10(Subjects subjects) {
        this.testSuccessful(
                "[Sectio\0nName]\n" +
                        "key=value", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Indented INI file")
    public void testValidINI11(Subjects subjects) {
        this.testSuccessful(
                " [SectionName]\n" +
                        "key=value", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Garbage in Section 1")
    public void testValidINI12(Subjects subjects) {
        this.testSuccessful(
                "[Section = Name]", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Garbage in Section 2")
    public void testValidINI13(Subjects subjects) {
        this.testSuccessful(
                "[Section = Name # Not A Comment]", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Garbage in Section 3")
    public void testValidINI14(Subjects subjects) {
        this.testSuccessful(
                "[Section = Name # Not A Comment \u0000 \u00a0]", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Garbage in Section 4 - Real-World Failing Example")
    public void testValidINI15(Subjects subjects) {
        this.testSuccessful(
                "[3 = v #not a commenttescaped_not_processed = tYest \\nescapeecIolon b= valldouble_quotes = \"not removed\"\"single_quotes = 'not removed''spaces_stripped = vallinternal_not_s tr  = nsignific = sectionss\"t]", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Very long section")
    public void testValidINI16(Subjects subjects) {
        this.testSuccessful(
                "[xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx]", subjects
        );
    }

    @ParameterizedTest(name = "{index}: Valid file {1} for {0}")
    @MethodSource("iniTestFilesValid")
    @DisplayName("Valid local test files must be valid")
    public void testValidTestfiles(Subjects subjects, Path fileUnderTest) {
        this.testSuccessful(fileUnderTest, subjects);
    }
}