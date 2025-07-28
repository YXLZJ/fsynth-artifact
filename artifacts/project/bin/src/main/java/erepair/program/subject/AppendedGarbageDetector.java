package erepair.program.subject;

import erepair.program.Loggable;
import erepair.program.Parsing;
import erepair.program.synthesis.SynthesisTokenFactory;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.logging.Level;

/**
 **/
public class AppendedGarbageDetector extends Loggable {
    private final Class<? extends Lexer> lexerClass;
    private final Class<? extends Parser> parserClass;
    private final String startRuleName;

    /**
     * Instantiate an appended garbage detector.
     *
     * @param lexerClass    Class used for the lexer, providing a one-element constructor with a {@link CharStream} as argument type
     * @param parserClass   Class used for the parser, providing a one-element constructor with a {@link TokenStream} as argument type
     * @param startRuleName Name of the start rule
     */
    public AppendedGarbageDetector(Class<? extends Lexer> lexerClass, Class<? extends Parser> parserClass, String startRuleName) {
        super(AppendedGarbageDetector.class.getSimpleName());
        this.lexerClass = lexerClass;
        this.parserClass = parserClass;
        this.startRuleName = startRuleName;
    }

    /**
     * Check if a file has appended garbage at the end.
     *
     * @param file_to_open File under test
     * @return true, if the file has appended garbage, false otherwise
     * @throws NoSuchMethodException     if the provided lexer or parser class do not have the expected one-element constructor
     * @throws InvocationTargetException if the constructor of the lexer or parser could not be called
     * @throws InstantiationException    if the lexer or parser could not be instantiated
     * @throws IllegalAccessException    if there is a security policy that prevents lexer or parser from being instantiated
     * @throws IOException               if the input file could not be read
     */
    public boolean hasAppendedGarbage(String file_to_open) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        final String fileCont = Parsing.readStringFromFile(Paths.get(file_to_open));
        log(Level.FINEST, "File of length " + fileCont.length());
        final ByteArrayInputStream antlrFileStream = new ByteArrayInputStream(fileCont.getBytes(StandardCharsets.UTF_8));
        Lexer lexer = lexerClass.getConstructor(CharStream.class).newInstance(CharStreams.fromStream(antlrFileStream));
        lexer.removeErrorListeners();
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        Parser parser = parserClass.getConstructor(TokenStream.class).newInstance(commonTokenStream);
        parser.removeErrorListeners();
        StreamPositionListener streamPositionListener = new StreamPositionListener();
        parser.addParseListener(streamPositionListener);
        parser.setTokenFactory(new SynthesisTokenFactory(parser));

        final Object tree = parser.getClass().getMethod(startRuleName).invoke(parser);

        final int lastPosition = streamPositionListener.lastPosition + 1; // ANTLR indexes are inclusive
        if (lastPosition >= fileCont.length()) {
            return false; // There was no garbage since the whole file has been parsed into the parse tree apparently
        }
        final String remainingFileContent = fileCont.substring(lastPosition);

        if (remainingFileContent.isBlank()) {
            return false; // No appended garbage, only whitespaces
        }

        log(Level.FINEST, "There was appended garbage: " + remainingFileContent);
        return true;
    }
}

class StreamPositionListener implements ParseTreeListener {

    public int lastPosition = -1;

    @Override
    public void visitTerminal(TerminalNode node) {
//        System.out.println("Terminal line " + node.getSymbol().getLine() + " pos " + node.getSymbol().getCharPositionInLine() + " / from " + node.getSymbol().getStartIndex() + " to " + node.getSymbol().getStopIndex());
        if (lastPosition < node.getSymbol().getStopIndex()) { // Store the last position of the file that was parsed
            lastPosition = node.getSymbol().getStopIndex();
        }
    }

    @Override
    public void visitErrorNode(ErrorNode node) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {

    }
}