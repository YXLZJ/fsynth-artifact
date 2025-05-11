package erepair.program.test.unittest;

import erepair.program.subject.Subjects;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

/**
 * System tests for the INI Oracles Return Status
 *
 */
public abstract class INIOracleTests extends OracleTestHelper {
    /**
     * Instantiate test class with the correct suffix
     */
    public INIOracleTests() {
        super("ini");
    }

    /**
     * Get the JUnit Parameters, i.e. the subjects to test
     *
     * @return all subjects that should be unit-tested
     */
    static Stream<Arguments> localParameters() {
        return Stream.of(
                Arguments.of(Subjects.INI)
        );
    }
}