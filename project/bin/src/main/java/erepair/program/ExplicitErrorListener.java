package erepair.program;


import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * This ANTLR Error Listener checks if an error occurred. It aims to report every ANTLR-reported error that can occur during parsing.
 *
 */
public class ExplicitErrorListener extends BaseErrorListener {
    private boolean errorOccurred = false;
    private List<Integer> errorLines = new ArrayList<>();
    private List<Integer> errorCharPositions = new ArrayList<>();
    private List<String> errorMessages = new ArrayList<>();
    private List<Token> errorOffendingTokens = new ArrayList<>();

    /**
     * Build a detailled log of all errors that ocurred in the ExplicitErrorListener that fits in one line
     *
     * @return a detailled list of errors
     */
    @SuppressWarnings("ObjectAllocationInLoop")
    public String makeDetailledErrorLog() {
        if (this.errorLines.size() < 1) {
            return "No errors detected";
        }
        List<Integer> nos = this.syntaxErrorLineNumbers();
        List<Integer> chs = this.syntaxErrorCharPositions();
        List<String> msgs = this.syntaxErrorMessages();
        StringBuilder detailedErrors = new StringBuilder();
        for (int i = 0; i < nos.size(); i++) {
            detailedErrors.append(nos.get(i) + ":" + chs.get(i) + "(" + msgs.get(i) + ")");
            if (this.errorOffendingTokens.get(i) != null) {
                detailedErrors.append("(")
                        .append(this.errorOffendingTokens.get(i).getText())
                        .append(")");
            }
            if (i != (nos.size() - 1)) {
                detailedErrors.append(", ");
            }
        }
        return detailedErrors.toString();
    }

    /**
     * Get all line numbers from all syntax errors, if there were any.
     * Return an empty list if there were none.
     *
     * @return a list of all erroneous line numbers
     */
    public List<Integer> syntaxErrorLineNumbers() {
        return this.errorLines;
    }

    /**
     * Get all char positions from all syntax errors, if there were any.
     * Return an empty list if there were none.
     *
     * @return a list of all erroneous char positions
     */
    public List<Integer> syntaxErrorCharPositions() {
        return this.errorCharPositions;
    }

    /**
     * Get all error messages from all syntax errors, if there were any.
     * Return an empty list if there were none.
     *
     * @return a list of all error messages
     */
    public List<String> syntaxErrorMessages() {
        return this.errorMessages;
    }

    /**
     * Get all offending tokens from all syntax errors, if there were any.
     * Might also contain null for error kinds where a token was missing.
     * Return an empty list if there were none.
     *
     * @return a list of all offending tokens
     */
    public List<Token> syntaxErrorOffendingTokens() {
        return this.errorOffendingTokens;
    }

    /**
     * Gets the error occurred state of the listener
     *
     * @return true, if an error has occurred
     */
    public boolean isErrorOccurred() {
        return errorOccurred;
    }

    /**
     * Resets the error occurred state of this listener
     */
    public void reset() {
        errorOccurred = false;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        errorOccurred = true;
        errorLines.add(line);
        errorCharPositions.add(charPositionInLine);
        errorMessages.add(msg);
        if (offendingSymbol != null && offendingSymbol instanceof Token) {
            this.errorOffendingTokens.add((Token) offendingSymbol);
        } else {
            this.errorOffendingTokens.add(null);
        }
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
    }

    @Override
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
        errorOccurred = true;
        super.reportAmbiguity(recognizer, dfa, startIndex, stopIndex, exact, ambigAlts, configs);
    }

    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
        errorOccurred = true;
        super.reportAttemptingFullContext(recognizer, dfa, startIndex, stopIndex, conflictingAlts, configs);
    }

    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
        errorOccurred = true;
        super.reportContextSensitivity(recognizer, dfa, startIndex, stopIndex, prediction, configs);
    }
}
