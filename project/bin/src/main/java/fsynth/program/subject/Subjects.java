package fsynth.program.subject;

public enum Subjects {
    INVALID(    "",             "<?>", 0),
    JQ(         "JQ",           "jjq", 1),
    GRAPHVIZ(   "Graphviz",     "grv", 2),
    CJSON(      "cJSON",        "cjs", 3),
    INI(        "INI",          "ini", 4),
    CSVPARSER(  "CSVParser",    "cvp", 5),
    TINYC(      "TinyC",        "tnc", 6),
    ORGJSON(    "OrgJson",      "ojs", 7),
    ANTLRJSON(  "ANTLRJson",    "ajs", 8),
    MINIMALJSON("MinimalJson",  "mjs", 9),
    KAITAIBMP(  "KaitaiBMP",    "kbm", 10),
    KAITAIJPEG( "KaitaiJPEG",   "kjp", 11),
    KAITAIPNG(  "KaitaiPNG",    "kpn", 12),
    KAITAIGIF(  "KaitaiGIF",    "kgf", 13),
    SEXP_PARSER("SExpParser",   "sxp", 14),
    ;

    public int arrayindex;
    private String displayName;
    private String jsonKey;

    Subjects(String displayName, String jsonKey, int arrayIndex) {
        this.displayName = displayName;
        this.jsonKey = jsonKey;
        this.arrayindex = arrayIndex;
    }

    public static Subjects fromJSONKey(String JSONKey) {
        for (Subjects v : Subjects.values()) {
            if (v.getJsonKey().equals(JSONKey)) {
                return v;
            }
        }
        return INVALID;
    }

    /**
     * Convert a String into a Subject. Use this instead of the {@link Subjects#valueOf(String)} method!
     * If this method is unable to find a Subject with the given casefold name, it falls back to valueOf
     *
     * @param subject String to convert
     * @return the subject
     */
    public static Subjects fromString(String subject) {
        for (Subjects v : Subjects.values()) {
            if (v.toString().equalsIgnoreCase(subject)) {
                return v;
            }
        }
        return Subjects.valueOf(subject.toUpperCase());
    }

    @Override
    public String toString() {
        return displayName;
    }

    /**
     * @return the unique JSON key of this subject
     */
    public String getJsonKey() {
        return jsonKey;
    }
}
