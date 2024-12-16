package fsynth.program.synthesis;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Pair;

import javax.annotation.Nonnull;

public class SynthesisTokenFactory implements TokenFactory<SynthesisToken> {
    private final CommonTokenFactory myFactory = new CommonTokenFactory();
    private Parser myParser;

    /**
     * Instantiate a new SynthesisTokenFactory
     *
     * @param parser Parser
     */
    public SynthesisTokenFactory(@Nonnull Parser parser) {
        this.myParser = parser;
    }

    /**
     * This is the method used to create tokens in the lexer and in the
     * error handling strategy. If text!=null, than the start and stop positions
     * are wiped to -1 in the text override is set in the CommonToken.
     *
     * @param source
     * @param type
     * @param text
     * @param channel
     * @param start
     * @param stop
     * @param line
     * @param charPositionInLine
     */
    @Override
    public SynthesisToken create(Pair<TokenSource, CharStream> source, int type, String text, int channel, int start, int stop, int line, int charPositionInLine) {
        Token t = myFactory.create(source, type, text, channel, start, stop, line, charPositionInLine);
        return new SynthesisToken(t, myParser.getVocabulary(), myParser.getExpectedTokens(), myParser.getRuleInvocationStack());
    }

    /**
     * Generically useful
     *
     * @param type
     * @param text
     */
    @Override
    public SynthesisToken create(int type, String text) {
        Token t = myFactory.create(type, text);
        return new SynthesisToken(t, myParser.getVocabulary(), myParser.getExpectedTokens(), myParser.getRuleInvocationStack());
    }

    public Parser getParser() {
        return myParser;
    }

    public void setParser(@Nonnull Parser myParser) {
        this.myParser = myParser;
    }
}
