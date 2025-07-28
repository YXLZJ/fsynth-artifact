package erepair.program.test.unittest;

import erepair.program.Parsing;
import erepair.program.subject.Subjects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 **/
@DisplayName("Unit Tests for the String Diff Utilities")
public class StringDiffTest {
    @Test
    public void getConsecutiveChangedLocationsTest1() {
        final String original = "ABCDEFGHIJKLMNOP";
        final String changeds = "ACDXXXEFGJKLMNP";
        final List<Integer> expected = List.of(1, 3, 2, 1);
        assertEquals(expected, Parsing.getConsecutiveChangedLocations(original, changeds));
    }
    @Test
    public void getConsecutiveChangedLocationsTest2() {
        final String original = "ABCDEFGHIJKLMNOP";
        final String changeds = "ABCDEfgHijklmnoP";
        final List<Integer> expected = List.of(2, 7);
        assertEquals(expected, Parsing.getConsecutiveChangedLocations(original, changeds));
    }
    @Test
    public void getConsecutiveChangedLocationsTest3() {
        final String original = "ABCDEFGHIJKLMNOP";
        final String changeds = "AhalloweltEFGHIJKLMNOPqrstuvwxyz";
        final List<Integer> expected = List.of(9, 10);
        assertEquals(expected, Parsing.getConsecutiveChangedLocations(original, changeds));
    }
}
