package fsynth.program;

/**
 * @author Lukas Kirschner
 * @since 2020-05-17
 **/
public interface JsonSerializableEnum {
    /**
     * Gets a string that can be used as key in JSON dictionaries
     *
     * @return a JSON key
     */
    String getJsonKey();
}
