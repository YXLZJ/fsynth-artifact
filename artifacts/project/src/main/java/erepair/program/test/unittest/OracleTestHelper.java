package erepair.program.test.unittest;

import erepair.program.Main;
import erepair.program.repairer.erepair.BinarySearch;
import erepair.program.repairer.erepair.BinarySearchable;
import erepair.program.repairer.erepair.StringBinarySearchWrapper;
import erepair.program.subject.Oracle;
import erepair.program.subject.SubjectStatus;
import erepair.program.subject.Subjects;
import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 **/
public abstract class OracleTestHelper extends TestHelper {
    private static HashMap<String, byte[]> testFileCache = new HashMap<>();
    protected final String suffix;

    public OracleTestHelper(String suffix) {
        this.suffix = suffix;
    }

    static byte[] getResource(String resourceLocation) {
        if (!testFileCache.containsKey(resourceLocation)) {
            try {
                testFileCache.put(resourceLocation,
                        Objects.requireNonNull(OracleTestHelper.class.getResourceAsStream(resourceLocation)).readAllBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return testFileCache.get(resourceLocation);
    }

    static Stream<Arguments> getAllFilesInDirectory(Path directory, String suffix) {
        try {
            return Files.walk(directory).filter(p -> p.getFileName().toString().endsWith(suffix)).map(Arguments::of);
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

    static Stream<Arguments> instantiateValidFilesArgs(Stream<Arguments> subjects, String testfileFolderName, String suffix) {
        return subjects
                .flatMap(subj -> OracleTestHelper.getAllFilesInDirectory(Main.BIN.resolve("testfiles").resolve(testfileFolderName), suffix)
                        .map(arg -> arg.get()[0])
                        .map(path -> Arguments.of(subj.get()[0], path))
                );
    }

    /**
     * @param resourceLocation The file that should be indexed
     * @param subjects         The subjects argument from the parameterized test class provider
     * @param startIndex       the start index, inclusive
     * @param endOffset        The offset how many bytes to omit at the end of a file
     * @return a stream of (Subjects, Integer), i.e. the subjects mapped to each index of a file
     */
    @SuppressWarnings("unused")
    static Stream<Arguments> indexesForResource(String resourceLocation, Supplier<Stream<Arguments>> subjects, int startIndex, int endOffset) {
        return subjects.get().flatMap(arg -> {
            final byte[] cont = getResource(resourceLocation);
            // The last index is omitted because we test for incorrectness. Removing or changing the last index might result in an incomplete file
            return IntStream.range(startIndex, cont.length - endOffset).mapToObj(i -> Arguments.of(arg.get()[0], i));
        });
    }

    /**
     * @param subjects   The subjects argument from the parameterized test class provider
     * @param resources  All resources that should be included in the arguments
     * @param startIndex the start index, inclusive
     * @param endOffset  The offset how many bytes to omit at the end of a file
     * @return a stream of (Resource, Subjects, Integer), i.e. the subjects mapped to each index of a file for each resource
     */
    @SuppressWarnings("unused")
    static Stream<Arguments> allInvalidFilesAndIndexes(int startIndex, int endOffset, Supplier<Stream<Arguments>> subjects, String... resources) {
        return Stream.of(
                resources
        ).flatMap(resourceLocation ->
                indexesForResource(resourceLocation, subjects, 0, 1)
                        .map(arr -> Arguments.of(resourceLocation, arr.get()[0], arr.get()[1])));
    }

    void testIncorrect(String fileContent, Subjects subjects) {
        this.testIncorrect(fileContent.getBytes(StandardCharsets.UTF_8), subjects);
    }

    void testIncorrect(byte[] fileContent, Subjects subjects) {
        super.setUpTempFiles();
        try {
            final Path file = super.setUpTempFile(
                    fileContent
                    , "." + this.suffix);
            this.testIncorrect(file, subjects);
        } finally {
            super.tearDownTempFiles();
        }
    }

    void testIncorrect(Path file, Subjects subjects) {
        final SubjectStatus subjectStatus = Oracle.runOracle(subjects, file.toString(), null, null, null);
        System.err.println(subjectStatus.getStatusReport());
        assertFalse(subjectStatus.wasSuccessful(), "File that was incorrect was reported as success!");
        assertFalse(subjectStatus.wasIncomplete(), "File that was incorrect was reported as incomplete!\n Status: " + subjectStatus.getStatusReport());
        assertTrue(subjectStatus.wasIncorrect(), "File that was incorrect was not reported as incorrect!\n Status: " + subjectStatus.getStatusReport());
    }

    void testIncomplete(String fileContent, Subjects subjects) {
        super.setUpTempFiles();
        try {
            final Path file = super.setUpTempFile(
                    fileContent
                    , "." + this.suffix);
            this.testIncomplete(file, subjects);
        } finally {
            super.tearDownTempFiles();
        }
    }

    void testIncomplete(byte[] fileContent, Subjects subjects) {
        super.setUpTempFiles();
        try {
            final Path file = super.setUpTempFile(
                    fileContent
                    , "." + this.suffix);
            this.testIncomplete(file, subjects);
        } finally {
            super.tearDownTempFiles();
        }
    }


    void testIncomplete(Path file, Subjects subjects) {
        final SubjectStatus subjectStatus = Oracle.runOracle(subjects, file.toString(), null, null, null);
        System.err.println(subjectStatus.getStatusReport());
        assertFalse(subjectStatus.wasSuccessful(), "File that was incomplete was reported as success!");
        assertFalse(subjectStatus.wasIncorrect(), "File that was incomplete was reported as incorrect!\n Status: " + subjectStatus.getStatusReport());
        assertTrue(subjectStatus.wasIncomplete(), "File that was incomplete was not reported as incomplete!\n Status: " + subjectStatus.getStatusReport());
    }

    void testSuccessful(String fileContent, Subjects subjects) {
        super.setUpTempFiles();
        try {
            final Path file = super.setUpTempFile(
                    fileContent
                    , "." + this.suffix);
            this.testSuccessful(file, subjects);
        } finally {
            super.tearDownTempFiles();
        }
    }

    void testSuccessful(Path file, Subjects subjects) {
        final SubjectStatus subjectStatus = Oracle.runOracle(subjects, file.toString(), null, null, null);
        System.err.println(subjectStatus.getStatusReport());
        assertFalse(subjectStatus.wasIncorrect(), "Correct file was reported as incorrect!\n Status: " + subjectStatus.getStatusReport());
        assertFalse(subjectStatus.wasIncomplete(), "Correct file was reported as incomplete!\n Status: " + subjectStatus.getStatusReport());
        assertTrue(subjectStatus.wasSuccessful(), "Correct file was not reported as success!\n Status: " + subjectStatus.getStatusReport());
    }

    void testBinarySearch(String fileContent, Subjects subjects, int referencePosition) {
        super.setUpTempFiles();
        Function<BinarySearchable<String>, SubjectStatus> tester = filecontent -> {
            final Path filename = super.setUpTempFile(filecontent.get(), "." + this.suffix);
            final var ret = Oracle.runOracle(subjects, filename.normalize().toString(), null, null, null);
            System.err.println("BINARY SEARCH PREFIX " + filecontent.get() + " RETURNED " + ret);
            return ret;
        };
        final int index = BinarySearch.binarySearchFaultLocation(new StringBinarySearchWrapper(fileContent), 0, fileContent.length(), tester);
        super.tearDownTempFiles();
        assertEquals(referencePosition, index, "The Binary Search returned a wrong position for the first fault location.");
        assertFalse(BinarySearch.latestBinarySearchStatus.wasIncorrect(), "The Binary Search returned an incorrect prefix!");
    }
}
