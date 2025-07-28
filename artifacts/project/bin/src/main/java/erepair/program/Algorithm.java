package erepair.program;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents an algorithm to repair a corrupted file.
 *
 */
public enum Algorithm {
    /**
     * The Baseline Algorithm
     */
    BASELINE("Baseline", "bas"),
    /**
     * The ANTLR algorithm
     */
    PRETTYPRINT("Antlr", "ant"),
    /**
     * The Lexical Delta-Debugging Algorithm
     */
    DDMAX("DDMax", "dmx"),
    /**
     * The Syntactical Delta-Debugging algorithm
     */
    DDMAXG("DDMaxG", "dmg"),
    /**
     * The Minimizing Delta-Debugging Algorithm
     */
    DDMIN("DDMin", "dmi"),
    /**
     * The erepair algorithm that deletes and inserts characters such that a minimal editing distance is kept
     */
    erepair("erepair", "bre"),
    /**
     * An invalid algorithm that is returned on error
     */
    INVALID("{{ERROR}}", "{{ERROR}}");

    private final String displayName;
    private final String jsonKey;

    /**
     * Enum Constructor
     *
     * @param displayName Display Name of the Algorithm
     * @param jsonKey     JSON Key of the algorithm
     */
    Algorithm(String displayName, String jsonKey) {
        this.displayName = displayName;
        this.jsonKey = jsonKey;
    }

    /**
     * Converts a given JSON key into an algorithm
     *
     * @param JSONKey The json key to lookup
     * @return the corresponding algorithm or {@link Algorithm#INVALID}, if the key was invalid
     */
    public static Algorithm fromJSONKey(String JSONKey) {
        for (Algorithm v : Algorithm.values()) {
            if (v.getJsonKey().equals(JSONKey)) {
                return v;
            }
        }
        Logging.error("Algorithm " + JSONKey + " was not found.");
        return INVALID;
    }

    /**
     * Gets an algorithm from a string, as taken from {@link Algorithm#toString()}
     *
     * @param algorithm String to convert
     * @return the algorithm represented by the string
     */
    @SuppressWarnings("unused")
    public static Algorithm fromString(String algorithm) {
        for (Algorithm v : Algorithm.values()) {
            if (v.toString().equals(algorithm)) {
                return v;
            }
        }
        Logging.error("Algorithm " + algorithm + " was not found. Available algorithms: " + Arrays.stream(Algorithm.values()).map(Algorithm::toString).collect(Collectors.joining(", ")));
        return INVALID;
    }

    /**
     * Convert the Algorithm into a readable string
     *
     * @return the String
     */
    @Override
    public String toString() {
        return displayName;
    }

    /**
     * Convert the algorithm into a JSON key
     *
     * @return the JSON key
     */
    public String getJsonKey() {
        return jsonKey;
    }

    /**
     * Checks if this algorithm is powered by bFuzzer
     *
     * @return true, if the algorithm is powered by bFuzzer
     */
    public boolean isBFuzzerAlgorithm() {
        return this == Algorithm.erepair; // TODO change to use local field instead? Does not really make sense if we only have two of this kind of algorithms
    }
}
