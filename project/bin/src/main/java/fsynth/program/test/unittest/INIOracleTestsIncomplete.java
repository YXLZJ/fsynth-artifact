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
@DisplayName("INI Oracle Tests - Incomplete")
public class INIOracleTestsIncomplete extends INIOracleTests {

    @SuppressWarnings("unused")
    static Stream<Arguments> localParameters() {
        return INIOracleTests.localParameters();
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete Header")
    public void testIncompleteINI1(Subjects subjects) {
        this.testIncomplete(
                "[incompleteHeader", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete Header with whitespaces")
    public void testIncompleteINI2(Subjects subjects) {
        this.testIncomplete(
                "\n\t\t\t\n\t   \t\n\t\n[incompleteHeader", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete String Key")
    @Disabled("INIH does not support quoted values in INI files")
    public void testIncompleteINI3(Subjects subjects) {
        this.testIncomplete(
                "[Header]\n\"key=value", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete String Value")
    @Disabled("INIH does not support quoted values in INI files")
    public void testIncompleteINI4(Subjects subjects) {
        this.testIncomplete(
                "[Header]\n\"key\"=\"value", subjects
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete String")
    @Disabled("INIH does not support quoted values in INI files")
    public void testIncompleteINI5(Subjects subjects) {
        this.testIncomplete(
                "[Header]\n\"key\"=\"value\"\nkey2=\"string\n\t\n  \t\n", subjects
        );
    }
}