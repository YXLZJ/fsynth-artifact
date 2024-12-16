package fsynth.program.synthesis;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.misc.IntervalSet;

import java.util.List;

public class SynthesisErrorToken extends SynthesisToken {

    public SynthesisErrorToken(Token wrappedNode, Vocabulary vocabulary, IntervalSet expectedTokens, List<String> invocationStack) {
        super(wrappedNode, vocabulary, expectedTokens, invocationStack);
    }
}
