package erepair.program;

import erepair.program.generated.*;
import erepair.program.synthesis.SynthesisErrorStrategy;
import erepair.program.synthesis.SynthesisTokenFactory;
import erepair.program.visitor.SimpleTreeFlattener;
import name.fraser.neil.plaintext.diff_match_patch;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class provides static methods used for parsing input files using ANTLR.
 * It aims to abstract the whole parsing process.
 *
 */
public final class Parsing {

    private static final ExplicitErrorListener myExplicitLexerErrorListener = new ExplicitErrorListener();

    /**
     * Parsing is static
     */
    private Parsing() {
    }

    /**
     * Parse and autodetect the suffix.
     * Throws a IllegalArgumentException if the suffix is invalid
     *
     * @param fileToParse        File to parse
     * @param cancelOnAntlrError Cancel on ANTLR error if true
     * @return the resulting parse tree or null, if an error occurred
     * @throws IOException              if there was an error reading the file
     * @throws IllegalArgumentException if the suffix was invalid
     */
    @Nullable
    public static ParseTree parseAutodetect(Path fileToParse, boolean cancelOnAntlrError) throws IOException, IllegalArgumentException {
        final InputFormat format = InputFormat.fromFileType(fileToParse);
        if (format == InputFormat.JSON || format == InputFormat.ECHART || format == InputFormat.GEOJSON || format == InputFormat.JSONLD) {
            return parseJSON(fileToParse, cancelOnAntlrError);
        }
        if (format == InputFormat.INI) {
            return parseINI(fileToParse, cancelOnAntlrError);
        }
        if (format == InputFormat.TINYC) {
            return parseTinyC(fileToParse, cancelOnAntlrError);
        }
        if (format == InputFormat.SEXP) {
            return parseSexpr(fileToParse, cancelOnAntlrError);
        }
        if (format == InputFormat.DOT) {
            return parseDOT(fileToParse, cancelOnAntlrError);
        }
        throw new IllegalArgumentException("The file " + fileToParse.normalize().toString() + " has an invalid format " + format.toString() + " that cannot be parsed with ANTLR!");
    }

    /**
     * Parse a DOT file using ANTLR.
     *
     * @param filePath           File path of the file to parse
     * @param cancelOnAntlrError if true, the parsing is cancelled if an error occurs
     * @return the AST
     * @throws IOException if the file could not be read
     */
    public static DOTParser.GraphContext parseDOT(Path filePath, boolean cancelOnAntlrError) throws IOException {
        ByteArrayInputStream fileStream = new ByteArrayInputStream(
                readStringFromFile(filePath).getBytes(StandardCharsets.UTF_8)
        );
        return parseDOT(fileStream, cancelOnAntlrError);
    }

    /**
     * Parse a DOT file using ANTLR.
     *
     * @param fileToParse File stream of the file to parse
     * @return the AST
     */
    public static DOTParser.GraphContext parseDOT(ByteArrayInputStream fileToParse) {
        return parseDOT(fileToParse, false);
    }

    /**
     * Parse a DOT file using ANTLR.
     *
     * @param fileToParse        File stream of the file to parse
     * @param cancelOnAntlrError if true, the parsing is cancelled if an error occurs
     * @return the AST
     */
    @Nullable
    public static DOTParser.GraphContext parseDOT(ByteArrayInputStream fileToParse, boolean cancelOnAntlrError) {
        DOTLexer lexer = null;
        try {
            lexer = new DOTLexer(CharStreams.fromStream(fileToParse));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        setupLexer(lexer);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        DOTParser parser = new DOTParser(commonTokenStream);
        setupParser(parser);

        DOTParser.GraphContext tree = parser.graph();
        if (cancelOnAntlrError && myExplicitLexerErrorListener.isErrorOccurred()) {
            return null;
        }

        return tree;
    }

    /**
     * Parse an INI file using ANTLR.
     *
     * @param fileToParse File path of the file to parse
     * @return the AST
     * @throws IOException if the file could not be read
     */
    public static INIParser.StartContext parseINI(Path fileToParse) throws IOException {
        ByteArrayInputStream fileStream = new ByteArrayInputStream(readStringFromFile(fileToParse).getBytes(StandardCharsets.UTF_8));
        return parseINI(fileStream);
    }

    /**
     * Parse an SExp file using ANTLR.
     *
     * @param fileToParse File path of the file to parse
     * @return the AST
     * @throws IOException if the file could not be read
     */
    public static sexpressionParser.SexprContext parseSexpr(Path fileToParse) throws IOException {
        ByteArrayInputStream fileStream = new ByteArrayInputStream(readStringFromFile(fileToParse).getBytes(StandardCharsets.UTF_8));
        return parseSexpr(fileStream);
    }

    /**
     * Parse a SExpr file using ANTLR.
     *
     * @param fileToParse        File path of the file to parse
     * @param cancelOnANTLRError if true, the parsing is cancelled if an error occurs
     * @return the AST
     * @throws IOException if the file could not be read
     */
    private static ParseTree parseSexpr(Path fileToParse, boolean cancelOnANTLRError) throws IOException {
        return parseSexpr(new ByteArrayInputStream(readStringFromFile(fileToParse).getBytes(StandardCharsets.UTF_8)), cancelOnANTLRError);
    }

    /**
     * Parse an INI file using ANTLR.
     *
     * @param fileToParse        File path of the file to parse
     * @param cancelOnANTLRError if true, the parsing is cancelled if an error occurs
     * @return the AST
     * @throws IOException if the file could not be read
     */
    private static ParseTree parseINI(Path fileToParse, boolean cancelOnANTLRError) throws IOException {
        return parseINI(new ByteArrayInputStream(readStringFromFile(fileToParse).getBytes(StandardCharsets.UTF_8)), cancelOnANTLRError);
    }


    /**
     * Parse a TinyC file using ANTLR.
     *
     * @param fileToParse        File path of the file to parse
     * @param cancelOnANTLRError if true, the parsing is cancelled if an error occurs
     * @return the AST
     * @throws IOException if the file could not be read
     */
    private static ParseTree parseTinyC(Path fileToParse, boolean cancelOnANTLRError) throws IOException {
        return parseTinyC(new ByteArrayInputStream(readStringFromFile(fileToParse).getBytes(StandardCharsets.UTF_8)), cancelOnANTLRError);
    }

    /**
     * Parse a Sexpr file using ANTLR.
     *
     * @param fileToParse File stream of the file to parse
     * @return the AST
     */
    public static sexpressionParser.SexprContext parseSexpr(ByteArrayInputStream fileToParse) {
        return parseSexpr(fileToParse, false);
    }

    /**
     * Parse an INI file using ANTLR.
     *
     * @param fileToParse File stream of the file to parse
     * @return the AST
     */
    public static INIParser.StartContext parseINI(ByteArrayInputStream fileToParse) {
        return parseINI(fileToParse, false);
    }


    /**
     * Parse a TinyC file using ANTLR.
     *
     * @param fileToParse File stream of the file to parse
     * @return the AST
     */
    public static tinycParser.ProgramContext parseTinyC(ByteArrayInputStream fileToParse) {
        return parseTinyC(fileToParse, false);
    }

    /**
     * Parse a Sexpr file using ANTLR.
     *
     * @param fileToParse        File stream of the file to parse
     * @param cancelOnANTLRError if true, the parsing is cancelled if an error occurs
     * @return the AST
     */
    @Nullable
    public static sexpressionParser.SexprContext parseSexpr(ByteArrayInputStream fileToParse, boolean cancelOnANTLRError) {
        sexpressionLexer lexer = null;
        try {
            lexer = new sexpressionLexer(CharStreams.fromStream(fileToParse));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        setupLexer(lexer);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        sexpressionParser parser = new sexpressionParser(commonTokenStream);
        setupParser(parser);
        sexpressionParser.SexprContext tree = parser.sexpr();
        if (cancelOnANTLRError && myExplicitLexerErrorListener.isErrorOccurred()) return null;
        return tree;
    }

    /**
     * Parse an INI file using ANTLR.
     *
     * @param fileToParse        File stream of the file to parse
     * @param cancelOnANTLRError if true, the parsing is cancelled if an error occurs
     * @return the AST
     */
    @Nullable
    public static INIParser.StartContext parseINI(ByteArrayInputStream fileToParse, boolean cancelOnANTLRError) {
        INILexer lexer = null;
        try {
            lexer = new INILexer(CharStreams.fromStream(fileToParse));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        setupLexer(lexer);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        INIParser parser = new INIParser(commonTokenStream);
        setupParser(parser);
        INIParser.StartContext tree = parser.start();
        if (cancelOnANTLRError && myExplicitLexerErrorListener.isErrorOccurred()) return null;
        return tree;
    }

    /**
     * Parse a TinyC file using ANTLR.
     *
     * @param fileToParse        File stream of the file to parse
     * @param cancelOnANTLRError if true, the parsing is cancelled if an error occurs
     * @return the AST
     */
    @Nullable
    public static tinycParser.ProgramContext parseTinyC(ByteArrayInputStream fileToParse, boolean cancelOnANTLRError) {
        //TODO refactor & rewrite code using Reflections, store class and rule names in enum!
        tinycLexer lexer = null;
        try {
            lexer = new tinycLexer(CharStreams.fromStream(fileToParse));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        setupLexer(lexer);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        tinycParser parser = new tinycParser(commonTokenStream);
        setupParser(parser);
        tinycParser.ProgramContext tree = parser.program();
        if (cancelOnANTLRError && myExplicitLexerErrorListener.isErrorOccurred()) return null;
        return tree;
    }


    /**
     * Parse a JSON file using ANTLR.
     *
     * @param filePath           File path of the file to parse
     * @param cancelOnANTLRError if true, the parsing is cancelled if an error occurs
     * @return the AST
     * @throws IOException if the file could not be read
     */
    public static JSONParser.JsonContext parseJSON(Path filePath, boolean cancelOnANTLRError) throws IOException {
        ByteArrayInputStream fileStream = new ByteArrayInputStream(readStringFromFile(filePath).getBytes(StandardCharsets.UTF_8));
        return parseJSON(fileStream, cancelOnANTLRError);
    }

    /**
     * Parse a JSON file using ANTLR.
     *
     * @param fileToParse File stream of the file to parse
     * @return the AST
     */
    public static JSONParser.JsonContext parseJSON(ByteArrayInputStream fileToParse) {
        return parseJSON(fileToParse, false);
    }

    /**
     * Parse a JSON file using ANTLR.
     *
     * @param fileToParse        File stream of the file to parse
     * @param cancelOnANTLRError if true, the parsing is cancelled if an error occurs
     * @return the AST
     */
    @Nullable
    public static JSONParser.JsonContext parseJSON(ByteArrayInputStream fileToParse, boolean cancelOnANTLRError) {
        JSONLexer lexer = null;
        try {
            lexer = new JSONLexer(CharStreams.fromStream(fileToParse));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        setupLexer(lexer);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        JSONParser parser = new JSONParser(commonTokenStream);
        setupParser(parser);
        JSONParser.JsonContext tree = parser.json();
        if (cancelOnANTLRError && myExplicitLexerErrorListener.isErrorOccurred()) return null;
        return tree;
    }

    /**
     * Set up the given parser with all required handlers and factories
     *
     * @param parser parser instance to set up
     */
    private static void setupParser(Parser parser) {
        parser.removeErrorListeners();
        parser.setErrorHandler(new SynthesisErrorStrategy());
        parser.setTokenFactory(new SynthesisTokenFactory(parser));
        parser.addErrorListener(myExplicitLexerErrorListener);
//        parser.addErrorListener(new LocationGetterErrorHandler());
    }

    /**
     * Set up the given lexer with all required handlers and factories
     *
     * @param lexer parser instance to set up
     */
    private static void setupLexer(Lexer lexer) {
        lexer.removeErrorListeners();
        myExplicitLexerErrorListener.reset();
        lexer.addErrorListener(myExplicitLexerErrorListener);
    }

    /**
     * Pretty-Prints a parse tree
     *
     * @param tree Parse Tree
     * @return pretty-printed String
     */
    @Nonnull
    public static String prettyPrint(@Nullable ParseTree tree) {
        if (tree == null) return "";
        final List<String> myFlattenedTree = tree.accept(new SimpleTreeFlattener());
        return String.join("", myFlattenedTree);
    }

    /**
     * Writes a byte array to a file, creating the parent directories if necessary
     *
     * @param file     File to write
     * @param contents Content of the file
     * @throws RuntimeException if file could not be written or directory could not be created
     */
    public static void writeBinaryFile(@Nonnull Path file, @Nonnull byte[] contents) throws RuntimeException {
        try {
            final Path parent = file.getParent();
            if (parent != null) {
                File testoutputdir = parent.toFile();
                if (!(testoutputdir.exists() || testoutputdir.mkdirs())) {
                    Logging.error("Could not create directory " + file.normalize().getParent().toString());
                    throw new RuntimeException("Could not create directory " + parent);
                }
            }
            Files.write(file, contents,
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }

    /**
     * Writes a string to a file, creating the parent directories if necessary.
     * Assumes a UTF-8 Charset.
     *
     * @param file     File to write
     * @param contents Content of the file
     * @throws RuntimeException if file could not be written or directory could not be created
     */
    public static void writeStringToFile(@Nonnull Path file, @Nonnull String contents) throws RuntimeException {
        Parsing.writeBinaryFile(file, contents.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Read a binary file into a byte array without encoding it to a string
     *
     * @param file File to read
     * @return the read byte array
     * @throws RuntimeException if there was an error reading the file
     */
    public static byte[] readBinaryFile(Path file) throws RuntimeException {
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Read a string from a file, trying charsets UTF-8, ISO-8859-1, ASCII and UTF16.
     *
     * @param file File to open
     * @return the string
     * @throws RuntimeException if none of the viable charsets resulted in a successful read or the file could not be read for another reason
     */
    public static String readStringFromFile(Path file) throws RuntimeException {
        if (!Files.isRegularFile(file)) {
            throw new RuntimeException(new NoSuchFileException("File does not exist: " + file));
        }
        try {
            //Check Byte-Order Mark
            BOMInputStream bomInputStream = new BOMInputStream(Files.newInputStream(file, StandardOpenOption.READ),
                    ByteOrderMark.UTF_8,
                    ByteOrderMark.UTF_16BE,
                    ByteOrderMark.UTF_16LE,
                    ByteOrderMark.UTF_32BE,
                    ByteOrderMark.UTF_32LE
            );
            ByteOrderMark bom = bomInputStream.getBOM();
            if (bom == null) {
                //File does not have a Byte-Order Mark, just try to read it using different charsets:
                return readStringFromFileNoBom(file);
            } else {
                if (bom.equals(ByteOrderMark.UTF_16LE)) {
                    return new BufferedReader(new InputStreamReader(bomInputStream, StandardCharsets.UTF_16LE)).lines().parallel().collect(Collectors.joining(System.getProperty("line.separator")));
                } else if (bom.equals(ByteOrderMark.UTF_16BE)) {
                    return new BufferedReader(new InputStreamReader(bomInputStream, StandardCharsets.UTF_16BE)).lines().parallel().collect(Collectors.joining(System.getProperty("line.separator")));
                } else if (bom.equals(ByteOrderMark.UTF_32LE)) {
                    return new BufferedReader(new InputStreamReader(bomInputStream, "UTF-32LE")).lines().parallel().collect(Collectors.joining(System.getProperty("line.separator")));
                } else if (bom.equals(ByteOrderMark.UTF_32BE)) {
                    return new BufferedReader(new InputStreamReader(bomInputStream, "UTF-32BE")).lines().parallel().collect(Collectors.joining(System.getProperty("line.separator")));
                } else if (bom.equals(ByteOrderMark.UTF_8)) {
                    //This should not happen because UTF-8 Files usually do not have BOMs, but we catch this case anyways:
                    return new BufferedReader(new InputStreamReader(bomInputStream, StandardCharsets.UTF_8)).lines().parallel().collect(Collectors.joining(System.getProperty("line.separator")));
                } else {
                    //Unknown BOM, try to read the file with any charset:
                    return readStringFromFileNoBom(file);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Read a String from a file without a Byte-Order Mark
     *
     * @param file File to read
     * @return String
     * @throws RuntimeException if no charset could support the file
     */
    static String readStringFromFileNoBom(Path file) throws RuntimeException {
        try {
            return Files.readString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            try {
                return Files.readString(file, StandardCharsets.ISO_8859_1);
            } catch (IOException ioException) {
                try {
                    return Files.readString(file, StandardCharsets.US_ASCII);
                } catch (IOException exception) {
                    try {
                        return Files.readString(file, StandardCharsets.UTF_16);
                    } catch (IOException ex) {
                        Logging.fatal("Could not read the file, trying charsets UTF8, ISO, ASCII and UTF16!");
                        Logging.fatal("UTF8:", e);
                        Logging.fatal("ISO-8859-1:", ioException);
                        Logging.fatal("ASCII:", exception);
                        Logging.fatal("UTF16:", ex);
                        throw new RuntimeException("Could not read file in any of the supported charsets!", ex);
                    }
                }
            }
        }
    }

    /**
     * Get the lengths of all consecutive change locations in {@code originalString} that were changed such that the result is {@code changedString}
     *
     * @param originalString Original String
     * @param changedString  Changed String
     * @return a set of lengths
     */
    public static List<Integer> getConsecutiveChangedLocations(String originalString, String changedString) {
        diff_match_patch dmp = new diff_match_patch();
        LinkedList<diff_match_patch.Diff> diff = dmp.diff_main(originalString, changedString);
        List<Integer> ret = new ArrayList<>();
        int prev_chg_len = 0;
        for (final diff_match_patch.Diff diffitem : diff) {
            final diff_match_patch.Operation op = diffitem.operation;
            final String text = diffitem.text;
            switch (op) {
                case DELETE:
                case INSERT:
                    if (prev_chg_len != 0) {
                        ret.add(Math.max(text.length(), prev_chg_len));
                        prev_chg_len = 0;
                    } else {
                        prev_chg_len = text.length();
                    }
                    break;
                case EQUAL:
                    if (prev_chg_len != 0) {
                        ret.add(prev_chg_len);
                        prev_chg_len = 0;
                    }
                    break;
            }
        }
        if (prev_chg_len != 0) {
            ret.add(prev_chg_len);
        }
        return ret;
    }

    /**
     * Convert a docker path to a local path if necessary
     *
     * @param orig Docker path
     * @return a local path
     */
    public static Path convert_docker_path(Path orig) {
        if (orig == null) {
            return orig;
        }
        if (!Files.isRegularFile(orig)) {
            // The path in the database is probably a path generated in the Dockerfile that needs to be converted to a path
            // relative to the program.
            return Paths.get(Main.BIN.toString() + orig.toString());
        }
        return orig;
    }
}