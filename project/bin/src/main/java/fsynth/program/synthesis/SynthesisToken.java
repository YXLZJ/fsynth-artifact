package fsynth.program.synthesis;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.misc.IntervalSet;

import java.util.ArrayList;
import java.util.List;

public class SynthesisToken implements Token {
    private final Token wrappedToken;
    private final Vocabulary vocabulary;
    private final List<String> invocationStack;
    private IntervalSet expectedTokens;

    public SynthesisToken(Token wrappedNode, Vocabulary vocabulary, IntervalSet expectedTokens, List<String> invocationStack) {
        this.wrappedToken = wrappedNode;
        this.vocabulary = vocabulary;
        this.expectedTokens = expectedTokens;
        this.invocationStack = new ArrayList<>(invocationStack);
    }

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public IntervalSet getExpectedTokens() {
        return this.expectedTokens;
    }

    /**
     * Get the text of the token.
     */
    @Override
    public String getText() {
        return wrappedToken.getText();
    }

    /**
     * Get the token type of the token
     */
    @Override
    public int getType() {
        return wrappedToken.getType();
    }

    /**
     * The line number on which the 1st character of this token was matched,
     * line=1..n
     */
    @Override
    public int getLine() {
        return wrappedToken.getLine();
    }

    /**
     * The index of the first character of this token relative to the
     * beginning of the line at which it occurs, 0..n-1
     */
    @Override
    public int getCharPositionInLine() {
        return wrappedToken.getCharPositionInLine();
    }

    /**
     * Return the channel this token. Each token can arrive at the parser
     * on a different channel, but the parser only "tunes" to a single channel.
     * The parser ignores everything not on DEFAULT_CHANNEL.
     */
    @Override
    public int getChannel() {
        return wrappedToken.getChannel();
    }

    /**
     * An index from 0..n-1 of the token object in the input stream.
     * This must be valid in order to print token streams and
     * use TokenRewriteStream.
     * <p>
     * Return -1 to indicate that this token was conjured up since
     * it doesn't have a valid index.
     */
    @Override
    public int getTokenIndex() {
        return wrappedToken.getTokenIndex();
    }

    /**
     * The starting character index of the token
     * This method is optional; return -1 if not implemented.
     */
    @Override
    public int getStartIndex() {
        return wrappedToken.getStartIndex();
    }

    /**
     * The last character index of the token.
     * This method is optional; return -1 if not implemented.
     */
    @Override
    public int getStopIndex() {
        return wrappedToken.getStopIndex();
    }

    /**
     * Gets the {@link TokenSource} which created this token.
     */
    @Override
    public TokenSource getTokenSource() {
        return wrappedToken.getTokenSource();
    }

    /**
     * Gets the {@link CharStream} from which this token was derived.
     */
    @Override
    public CharStream getInputStream() {
        return wrappedToken.getInputStream();
    }

    public List<String> getInvocationStack() {
        return invocationStack;
    }
}
