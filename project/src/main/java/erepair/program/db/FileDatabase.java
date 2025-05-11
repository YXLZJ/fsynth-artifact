package erepair.program.db;

import erepair.program.InputFormat;
import erepair.program.Parsing;
import erepair.program.functional.ExecutionAction;
import erepair.program.userinterface.Saveable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * The database class that stores all information about test runs, file attributes and other input file metadata.
 **/
public class FileDatabase extends HashMap<Path, FileRecord> implements Saveable {
    /**
     * Create a new empty file database
     */
    public FileDatabase() {
        super();
    }

    /**
     * Load the file database from an existing JSON Object
     *
     * @param jsonObject JSON Object to load
     * @throws JSONException          Thrown if the JSON file is invalid / does not contain valid database keys
     * @throws IllegalAccessException if the JSON object contained a malformed JSONArray
     */
    @SuppressWarnings("ObjectAllocationInLoop")
    public FileDatabase(JSONObject jsonObject) throws JSONException, IllegalAccessException {
        super();
        for (String jsonKey : jsonObject.keySet()) {
            final Path key = Paths.get(jsonKey);
            FileRecord value;
            if (jsonObject.isNull(jsonKey)) {
                value = new FileRecord();
            } else {
                value = new FileRecord(jsonObject.getJSONArray(jsonKey));
            }
            this.put(key, value);
        }
    }

    /**
     * Converts the database to a JSON Object
     *
     * @return the JSON Object
     * @throws IllegalAccessException if there was an internal error creating the JSON Object
     */
    JSONObject toJSONObject() throws IllegalAccessException {
        final JSONObject object = new JSONObject();
        for (Entry<Path, FileRecord> entry : this.entrySet()) {
            if (entry.getValue().isEmpty()) {
                object.put(entry.getKey().toString(), JSONObject.NULL);
            } else {
                final JSONArray arr = new JSONArray();
                entry.getValue().storeToJSON(arr);
                object.put(entry.getKey().toString(), arr);
            }
        }
        return object;
    }

    /**
     * Get a file record or create a new one if no one exists
     *
     * @param key File to query
     * @return the file record of the given file
     */
    @Nonnull
    public FileRecord getFileRecord(Path key) {
        if (!super.containsKey(key)) {
            super.put(key, new FileRecord());
        }
        return super.get(key);
    }

    @Override
    public void autosave(Path path) throws IOException, InvalidClassException, NotSerializableException, SecurityException {
        this.save(path);
    }

    @Override
    public void save(Path path) throws IOException, InvalidClassException, NotSerializableException, SecurityException {
        try {
            final JSONObject object = this.toJSONObject();
            final String cont = object.toString(1);
            Parsing.writeStringToFile(path,cont);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new InvalidClassException(e.toString());
        }
    }

    @Override
    public void load(Path path) throws IOException, InvalidClassException, NotSerializableException, SecurityException {
        throw new IllegalAccessError("This operation is not supported. A " + this.getClass().getCanonicalName() + " must be loaded from a file using the constructor instead.");
    }

    /**
     * Get the Execution Action that should be executed if an oracle succeeds. Resets the fail reason of the file
     *
     * @param file File under test
     * @return the action
     */
    @Nonnull
    public ExecutionAction successAction(@Nonnull Path file) {
        return executionInfo -> this.getFileRecord(file).setFailReason(executionInfo.getSubject(), executionInfo.getFailedReason(), executionInfo.getFormat());
    }

    /**
     * Get the Execution Action that should be executed if an oracle fails. Updates the fail reason of the file
     *
     * @param file File under test
     * @return the action
     */
    @Nonnull
    public ExecutionAction failAction(@Nonnull Path file) {
        return this.successAction(file);
    }

    /**
     * Change the file path of the given file to the given new path.
     * Creates an empty FileRecord for the old file to avoid false negatives during lookup of files that have already been tested.
     *
     * @param oldFile Old path
     * @param newFile New path
     * @return the new path if the action succeeded or null, if no such file was in the database
     */
    @Nullable
    public Path changeFilePath(@Nonnull Path oldFile, @Nonnull Path newFile) {
        if (!super.containsKey(oldFile)) {
            return null;
        }
        final var entry = super.get(oldFile);
        super.put(newFile, entry);
        super.put(oldFile, new FileRecord());
        return newFile;
    }

    /**
     * Get all files that have the required format
     *
     * @param format Format to get
     * @return all files of the required format
     */
    public Stream<Entry<Path, FileRecord>> getForFormat(@Nonnull InputFormat format) {
        final Stream<Entry<Path, FileRecord>> stream = this.entrySet().stream();
        return stream.filter(entry -> entry.getValue() != null && entry.getValue().isFormat(format));
    }
}