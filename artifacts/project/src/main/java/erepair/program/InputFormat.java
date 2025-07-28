package erepair.program;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 **/
public enum InputFormat implements JsonSerializableEnum {
    /**
     * An invalid subject, as returned by invalid requests
     */
    INVALID("", "<?>", "", 0, true),
    /**
     * Wavefront Object file that can store 3D models
     */
    WAVEFRONT("Wavefront", "obj", ".obj", 1, true),
    /**
     * Javascript Object Notation (JSON) file for general purpose
     */
    JSON("JSON", "jso", ".json", 2, true),
    /**
     * Graphviz DOT files that store graphs
     */
    DOT("DOT", "dot", ".dot", 3, true),
    /**
     * GeoJSON files that store geographical data, e.g. for Open Street Map
     */
    GEOJSON("GeoJSON", "gjs", ".geojson", 4, true),
    /**
     * Json-LD files that store relational data
     */
    JSONLD("JsonLD", "jld", ".jsonld", 5, true),
    /**
     * Apache Echart graphs
     */
    ECHART("EChart", "ech", ".echart", 6, true),
    /**
     * INI files
     */
    INI("INI", "ini", ".ini", 7, true),
    /**
     * CSV files
     */
    CSV("CSV", "csv", ".csv", 8, true),
    /**
     * TinyC files
     */
    TINYC("TinyC", "tnc", ".c", 9, true),
    /**
     * BMP files
     */
    BMP("BMP", "bmp", ".bmp", 10, false),
    /**
     * JPEG files
     */
    JPEG("JPEG", "jpg", ".jpg", 11, false),
    /**
     * PNG files
     */
    PNG("PNG", "png", ".png", 12, false),
    /**
     * GIF files
     */
    GIF("GIF", "gif", ".gif", 13, false),
    /**
     * Lisp S-Expression files
     */
    SEXP("SExp", "sex", ".lisp", 14, true),
    ;
    /**
     * The Array Index in a JSON file
     */
    public final int arrayindex;
    /**
     * If true, the format is grammar-based. If false, the format is binary.
     * We expect grammar-based formats to be parseable by ANTLR and binary formats to have a
     * corresponding Kaitai struct.
     */
    public final boolean isGrammarBased;
    private final String displayName;
    private final String jsonKey;
    private final String suffix;

    /**
     * Enum Constructor
     *
     * @param displayName    The Display Name in a human-readable format
     * @param jsonKey        The JSON Key to store this enum inside a JSON Dictionary
     * @param suffix         The suffix of a file of this format, including the dot
     * @param arrayIndex     The array index to use in JSON files
     * @param isGrammarBased If true, the format is grammar-based. If false, the format is binary.
     *                       We expect grammar-based formats to be parseable by ANTLR and binary formats to have a
     *                       corresponding Kaitai struct.
     */
    InputFormat(String displayName, String jsonKey, String suffix, int arrayIndex, boolean isGrammarBased) {
        this.displayName = displayName;
        this.jsonKey = jsonKey;
        this.arrayindex = arrayIndex;
        this.suffix = suffix;
        this.isGrammarBased = isGrammarBased;
    }

    /**
     * Get an input format for an existing JSON key
     *
     * @param JSONKey JSONKey to query
     * @return the input format described by the JSON key
     */
    public static InputFormat fromJSONKey(String JSONKey) {
        for (InputFormat v : InputFormat.values()) {
            if (v.getJsonKey().equals(JSONKey)) {
                return v;
            }
        }
        return INVALID;
    }

    /**
     * Parses the given string case-insensitively into an enum object
     *
     * @param format String to parse
     * @return the enum object
     */
    public static InputFormat fromString(String format) {
        assert format != null;
        String lower = format.toLowerCase();
        for (InputFormat v : InputFormat.values()) {
            if (v.toString().toLowerCase().equals(lower)) {
                return v;
            }
        }
        Logging.error("Input Format " + format + " was not found.");
        return INVALID;
    }

    /**
     * Get the input format of the given file or return {@link InputFormat#INVALID} if the file has an invalid format
     *
     * @param file File to examine
     * @return the format of the file
     */
    public static InputFormat fromFileType(Path file) {
        final String extension = file.normalize().toString().toLowerCase();
        for (final InputFormat v : InputFormat.values()) {
            if (v == INVALID) continue;
            if (extension.endsWith(v.suffix)) {
                return v;
            }
        }
        final InputFormat ret = fromFileContent(file);
        if (ret == null) {
            Logging.error("Invalid file type - No suitable format could be found for " + file.normalize().toString());
            return INVALID;
        } else {
            return ret;
        }
    }

    /**
     * Determine a file's type from its content
     *
     * @param file File to determine
     * @return the type or null, if the type could not be recognized
     */
    private static @Nullable
    InputFormat fromFileContent(Path file) {
        final int BUFLEN = 256;
        try {
            try (SeekableByteChannel channel = Files.newByteChannel(file, StandardOpenOption.READ)) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(BUFLEN);
                StringBuilder previousReads = new StringBuilder(BUFLEN);
                while (true) {
                    byteBuffer.rewind();
                    int len = channel.read(byteBuffer);
                    byteBuffer.rewind();
                    if (len > 0) {
                        previousReads.append(StandardCharsets.UTF_8.decode(byteBuffer).toString().stripLeading());
                    }
                    if (previousReads.length() > 0) {

                        //Is it JSON?
                        final String start = previousReads.toString();
                        if (start.startsWith("{") ||
                                start.startsWith("[") ||
                                checkKeyword("null", start.toLowerCase()) ||
                                checkKeyword("true", start.toLowerCase()) ||
                                checkKeyword("false", start.toLowerCase())
                        ) {//TODO what about eCharts, etc? I think we need a library to do this properly
                            return JSON;
                        }
                        if (len == BUFLEN && previousReads.length() < 8) {
                            continue; // There might be more information that can be read in order to determine the type properly, e.g. if "tru" has been read and an "e" follows
                        }
                    } else {
                        if (len < BUFLEN) {
                            return null; // The complete file is read and no type could be determined
                        }
                        continue; // We only read whitespace which we want to ignore!
                    }
                    return null;
                }
            }
//            return null;
        } catch (IOException | IllegalArgumentException | NonReadableChannelException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Check if the given String starts with the given keyword
     *
     * @param keyword         Keyword
     * @param referenceString Reference String, must be already lstripped()!
     * @return true, if it starts with the keyword
     */
    private static boolean checkKeyword(String keyword, String referenceString) {
        if (referenceString.length() < keyword.length()) {
            return false;
        }
        if (!referenceString.startsWith(keyword)) {
            return false;
        }
        if (referenceString.length() > keyword.length()) {
            final Character next = referenceString.charAt(keyword.length());
            if (Character.isAlphabetic(next) || Character.isDigit(next)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the suffix of the file with a leading dot, e.g. ".json"
     *
     * @return the file suffix
     */
    public String getSuffix() {
        return suffix;
    }

    @Override
    public String toString() {
        return displayName;
    }

    @Override
    public String getJsonKey() {
        return jsonKey;
    }
}
