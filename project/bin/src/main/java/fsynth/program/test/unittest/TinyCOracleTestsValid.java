package fsynth.program.test.unittest;

import fsynth.program.subject.Subjects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * System tests for the TinyC Oracles Return Status
 */
@SuppressWarnings({"JavaDoc", "HardcodedLineSeparator", "HardcodedFileSeparator"})
@DisplayName("TinyC Oracle Tests - Valid")
public class TinyCOracleTestsValid extends TinyCOracleTests {

    @SuppressWarnings("unused")
    static Stream<Arguments> localParameters() {
        return TinyCOracleTests.localParameters();
    }

    static Stream<Arguments> cTestFilesValid() {
        return instantiateValidFilesArgs(TinyCOracleTests.localParameters(), "tinyc-valid", ".c");
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Extremely simple file")
    public void testValidTinyC1(Subjects subjects) {
        this.testSuccessful(
                "1 + 1;",
                subjects
        );
    }

    @ParameterizedTest(name = "{index}: Valid file {1} for {0}")
    @MethodSource("cTestFilesValid")
    @DisplayName("Valid local test files must be valid")
    public void testValidTestfiles(Subjects subjects, Path fileUnderTest) {
        this.testSuccessful(fileUnderTest, subjects);
    }
}