package fsynth.program.test.unittest;

import fsynth.program.subject.Subjects;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

/**
 * System tests for the INI Oracles Return Status
 *
 * @author Lukas Kirschner
 * @since 2021-09-14
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