package erepair.program;

/**
 **/
public interface JsonSerializableEnum {
    /**
     * Gets a string that can be used as key in JSON dictionaries
     *
     * @return a JSON key
     */
    String getJsonKey();
}
