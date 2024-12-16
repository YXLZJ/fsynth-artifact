package fsynth.program.test.unittest;

import fsynth.program.subject.Subjects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * System tests for the Binary Search for the leftmost fault location
 */
@SuppressWarnings({"JavaDoc", "HardcodedLineSeparator", "MethodMayBeStatic"})
@DisplayName("JSON Oracle Tests - Binary Search")

public class JsonOracleTestsBinarySearch extends JsonOracleTests {

    @SuppressWarnings("unused")
    static Stream<Arguments> localParameters() {
        return JsonOracleTests.localParameters();
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Object with garbage")
    public void testBinarySearchJson1(Subjects subjects) {
        this.testBinarySearch("{ \"foo\": 4h }", subjects, 10
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Unfinished List")
    public void testBinarySearchJson2(Subjects subjects) {
        this.testBinarySearch("[1,2,3,4,5", subjects, 10
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Unfinished Object")
    public void testBinarySearchJson3(Subjects subjects) {
        this.testBinarySearch("{\"1\":2,\"2\":3,\"3\":4,\"4\":5,\"5\":6", subjects, 30
        );
    }

    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Object without opening brace")
    public void testBinarySearchJson4(Subjects subjects) {
        this.testBinarySearch("1:2,2:3,3:4,4:5,5:6}", subjects, 1
        );
    }
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Real-World Example")
    public void testBinarySearchJson5(Subjects subjects) {
        this.testBinarySearch("{\"_\":{}}\n", subjects, 9
        );
    }
}