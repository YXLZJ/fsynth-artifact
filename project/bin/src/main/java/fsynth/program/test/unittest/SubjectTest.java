package fsynth.program.test.unittest;

import fsynth.program.subject.Subjects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Unit Tests for the Subjects Enum")
public class SubjectTest {

    @ParameterizedTest
    @CsvSource({
            "cjs, CJSON",
            "jjq, JQ",
            "grv, GRAPHVIZ",
            "ini, INI",
            "cvp, CSVPARSER",
            "tnc, TINYC",
            "xxx, INVALID",
    })
    public void fromJSONKey(String jsonKey, Subjects subjects) {
        final String test = jsonKey;
        final Subjects s = Subjects.fromJSONKey(test);
        assertEquals(s, subjects);
    }

    @Test
    public void toStringTest() {
        assertEquals("cJSON", Subjects.CJSON.toString());
    }

    @Test
    public void getJsonKey() {
        assertEquals("cjs", Subjects.CJSON.getJsonKey());
    }
}