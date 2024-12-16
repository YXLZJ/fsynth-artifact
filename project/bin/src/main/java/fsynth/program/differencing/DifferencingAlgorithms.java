package fsynth.program.differencing;

import fsynth.program.Logging;

public enum DifferencingAlgorithms {
    INVALID("Invalid", "inv", 0),
    FILESIZE_CHANGE_PPR_REC("Size Orig->Rect", "sor", 1),
    FILESIZE_CHANGE_MUT_REC("Size Mut->Rect", "smr", 2),
    FILESIZE_CHANGE_PPR_MUT("Size Orig->Mut", "spm", 3),
    LEVENSHTEIN("Levenshtein", "lev", 4),
    PHASH_A("PHashA", "pha", 5),
    PHASH_B("PHashB", "phb", 6),
    ;

    public int arrayindex;
    private String displayName;
    private String jsonKey;

    DifferencingAlgorithms(String displayName, String jsonKey, int arrayIndex) {
        this.displayName = displayName;
        this.jsonKey = jsonKey;
        this.arrayindex = arrayIndex;
    }

    public static DifferencingAlgorithms fromJSONKey(String JSONKey) {
        for (DifferencingAlgorithms v : DifferencingAlgorithms.values()) {
            if (v.getJsonKey().equals(JSONKey)) {
                return v;
            }
        }
        return INVALID;
    }

    public static DifferencingAlgorithms fromString(String string) {
        for (DifferencingAlgorithms v : DifferencingAlgorithms.values()) {
            if (v.toString().equals(string)) {
                return v;
            }
        }
        Logging.error("Differencing Algorithm " + string + " was not found.");
        return INVALID;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public String getJsonKey() {
        return jsonKey;
    }
}
