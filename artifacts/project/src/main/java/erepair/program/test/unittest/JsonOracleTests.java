package erepair.program.test.unittest;

import erepair.program.subject.Subjects;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

/**
 * System tests for the JSON Oracles Return Status
 *
 */
public abstract class JsonOracleTests extends OracleTestHelper {
    /**
     * Instantiate test class with the correct suffix
     */
    public JsonOracleTests() {
        super("json");
    }

    /**
     * Get the JUnit Parameters, i.e. the subjects to test
     *
     * @return all subjects that should be unit-tested
     */
    static Stream<Arguments> localParameters() {
        return Stream.of(
                Arguments.of(Subjects.CJSON)
//                Arguments.of(Subjects.ANTLRJSON),
//                Arguments.of(Subjects.MINIMALJSON)

        );
    }
}