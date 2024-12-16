package fsynth.program.userinterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.nio.file.Path;

public interface Saveable {
    /**
     * Perform an autosave
     *
     * @throws FileNotFoundException            if the file does not exist
     * @throws SecurityException                if a SecurityManager prevents saving the file
     * @throws java.io.InvalidClassException    if a {@link java.io.Serializable} is to be saved and there is something wrong with the serialized class
     * @throws java.io.NotSerializableException if the class tries to serialize a non-serializable object
     * @throws IOException                      if there was an error saving the file
     */
    void autosave(Path path) throws IOException, InvalidClassException, NotSerializableException, SecurityException;

    /**
     * Save the saveable
     *
     * @throws FileNotFoundException            if the file does not exist
     * @throws SecurityException                if a SecurityManager prevents saving the file
     * @throws java.io.InvalidClassException    if a {@link java.io.Serializable} is to be saved and there is something wrong with the serialized class
     * @throws java.io.NotSerializableException if the class tries to serialize a non-serializable object
     * @throws IOException                      if there was an error saving the file
     */
    void save(Path path) throws IOException, InvalidClassException, NotSerializableException, SecurityException;

    /**
     * Load the saveable from the file that was saved in {@literal save()}
     *
     * @param path Path to load the object from
     * @throws IOException              if there was an error loading from the file
     * @throws InvalidClassException    if the loaded object has a wrong format or version
     * @throws NotSerializableException if a subclass or member of the loaded object is not serializable
     * @throws SecurityException        if the security manager prevents loading the file
     */
    void load(Path path) throws IOException, InvalidClassException, NotSerializableException, SecurityException;
}