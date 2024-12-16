package fsynth.program.test.unittest;

import fsynth.program.subject.Subjects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * System tests for the TinyC Oracles Return Status
 */
@SuppressWarnings({"JavaDoc", "HardcodedLineSeparator", "MethodMayBeStatic"})
@DisplayName("TinyC Oracle Tests - Incomplete")

public class TinyCOracleTestsIncomplete extends TinyCOracleTests {

    @SuppressWarnings("unused")
    static Stream<Arguments> localParameters() {
        return TinyCOracleTests.localParameters();
    }

    /**
     * Missing Semicolon at the end of an expression
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Real-World Prefix 1")
    public void testIncompleteTinyC1(Subjects subjects) {
        this.testIncomplete(
                "6<666+(66666<666+(6666666+(66+(666+(66666<666+(6666666+(66+6)))))))", subjects
        );
    }

    /**
     * Missing letter in 'while' keyword
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete Keyword 2")
    public void testIncompleteTinyC3(Subjects subjects) {
        this.testIncomplete(
                "whil", subjects
        );
    }

    /**
     * 'while' keyword without statement or expression
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete while 1")
    public void testIncompleteTinyC4(Subjects subjects) {
        this.testIncomplete(
                "while", subjects
        );
    }

    /**
     * 'while' keyword with incomplete expression
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete while 2")
    public void testIncompleteTinyC5(Subjects subjects) {
        this.testIncomplete(
                "while (5 < 4", subjects
        );
    }

    /**
     * 'while' keyword with incomplete statement
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete while 3")
    public void testIncompleteTinyC6(Subjects subjects) {
        this.testIncomplete(
                "while (5 < 4) x + 1", subjects
        );
    }

    /**
     * 'while' keyword with missing statement
     */
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete while 4")
    public void testIncompleteTinyC7(Subjects subjects) {
        this.testIncomplete(
                "while (5 < 4)", subjects
        );
    }
}