package erepair.program.test.unittest;

import erepair.program.Parsing;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Test Helper class with commonly used testing functions
 *
 **/
public abstract class TestHelper {
    private Path tempDir = null;
    private List<Path> tempFiles = new ArrayList<>();

    void setUpTempFiles() {
        if (this.tempDir == null) {
            try {
                this.tempDir = Files.createTempDirectory("unittest");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void tearDownTempFiles() {
        for (final Path file : this.tempFiles) {
            try {
                Files.deleteIfExists(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.walk(this.tempDir).filter(f -> !Files.isDirectory(f)).forEach(f -> {
                try {
                    System.err.println("File was not deleted properly: " + f);
                    Files.delete(f);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            Files.delete(this.tempDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.tempDir = null;
    }

    /**
     * Set up a temp file with the given content.
     *
     * @param fileContent Content of the temp file
     * @param suffix      Suffix of the file, e.g. ".json"
     * @return Path of the temp file
     * @throws RuntimeException if there is any exception
     */
    Path setUpTempFile(String fileContent, String suffix) {
        return setUpTempFile(fileContent.getBytes(StandardCharsets.UTF_8), suffix);
    }

    /**
     * Set up a temp file with the given content.
     *
     * @param fileContent Content of the temp file
     * @param suffix      Suffix of the file, e.g. ".json"
     * @return Path of the temp file
     * @throws RuntimeException if there is any exception
     */
    Path setUpTempFile(byte[] fileContent, String suffix) {
        if (this.tempDir == null) {
            this.setUpTempFiles();
        }
        final Path ret;
        try {
            ret = Files.createTempFile(this.tempDir, "unittest" + System.currentTimeMillis(), suffix);
            this.tempFiles.add(ret);
            Parsing.writeBinaryFile(ret, fileContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    /**
     * Copy a resource to a temporary directory
     *
     * @param resourceLocation Location of the resource
     * @return the full path of the new temp file
     */
    Path copyResourceToTempDir(String resourceLocation) {
        final Path ret = setUpTempFile("", resourceLocation.substring(resourceLocation.length() - 4));
        try (InputStream is = TestHelper.class.getResourceAsStream(resourceLocation)) {
            if (is == null) {
                throw new IOException("is " + resourceLocation + " was null!");
            }
            Files.copy(is, ret, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
