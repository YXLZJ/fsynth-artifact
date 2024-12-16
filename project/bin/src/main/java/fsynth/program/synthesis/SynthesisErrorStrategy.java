package fsynth.program.synthesis;

import org.antlr.v4.runtime.*;

public class SynthesisErrorStrategy extends DefaultErrorStrategy {
    private boolean isErrorOcurred = false;

    public boolean isErrorOcurred() {
        return isErrorOcurred;
    }

    /**
     * Reset the error handler state for the specified {@code recognizer}.
     *
     * @param recognizer the parser instance
     */
    @Override
    public void reset(Parser recognizer) {
        super.reset(recognizer);
    }

    /**
     * This method is called when an unexpected symbol is encountered during an
     * inline match operation, such as {@link Parser#match}. If the error
     * strategy successfully recovers from the match failure, this method
     * returns the {@link Token} instance which should be treated as the
     * successful result of the match.
     *
     * <p>This method handles the consumption of any tokens - the caller should
     * <b>not</b> call {@link Parser#consume} after a successful recovery.</p>
     *
     * <p>Note that the calling code will not report an error if this method
     * returns successfully. The error strategy implementation is responsible
     * for calling {@link Parser#notifyErrorListeners} as appropriate.</p>
     *
     * @param recognizer the parser instance
     * @throws RecognitionException if the error strategy was not able to
     *                              recover from the unexpected input symbol
     */
    @Override
    public Token recoverInline(Parser recognizer) throws RecognitionException {
//        System.out.println("recover in " + recognizer.getRuleInvocationStack() +
//                " index=" + recognizer.getInputStream().index() +
//                ", lastErrorIndex=" +
//                lastErrorIndex +
//                ", states=" + lastErrorStates);
        Token t = super.recoverInline(recognizer);
        if (t != null) {
            isErrorOcurred = true;
            SynthesisErrorToken tt = new SynthesisErrorToken(t, recognizer.getVocabulary(), recognizer.getExpectedTokens(), recognizer.getRuleInvocationStack());
            return tt;
        } else {
            return null;
        }
    }

    /**
     * This method is called to recover from exception {@code e}. This method is
     * called after {@link #reportError} by the default exception handler
     * generated for a rule method.
     *
     * @param recognizer the parser instance
     * @param e          the recognition exception to recover from
     * @throws RecognitionException if the error strategy could not recover from
     *                              the recognition exception
     * @see #reportError
     */
    @Override
    public void recover(Parser recognizer, RecognitionException e) throws RecognitionException {
        super.recover(recognizer, e);
    }

    /**
     * This method provides the error handler with an opportunity to handle
     * syntactic or semantic errors in the input stream before they result in a
     * {@link RecognitionException}.
     *
     * <p>The generated code currently contains calls to {@link #sync} after
     * entering the decision state of a closure block ({@code (...)*} or
     * {@code (...)+}).</p>
     *
     * <p>For an implementation based on Jim Idle's "magic sync" mechanism, see
     * {@link DefaultErrorStrategy#sync}.</p>
     *
     * @param recognizer the parser instance
     * @throws RecognitionException if an error is detected by the error
     *                              strategy but cannot be automatically recovered at the current state in
     *                              the parsing process
     * @see DefaultErrorStrategy#sync
     */
    @Override
    public void sync(Parser recognizer) throws RecognitionException {
        super.sync(recognizer);
    }

    /**
     * Tests whether or not {@code recognizer} is in the process of recovering
     * from an error. In error recovery mode, {@link Parser#consume} adds
     * symbols to the parse tree by calling
     * {@link ParserRuleContext#addErrorNode(Token)} instead of
     * {@link ParserRuleContext#addChild(Token)}.
     *
     * @param recognizer the parser instance
     * @return {@code true} if the parser is currently recovering from a parse
     * error, otherwise {@code false}
     */
    @Override
    public boolean inErrorRecoveryMode(Parser recognizer) {
        return super.inErrorRecoveryMode(recognizer);
    }

    /**
     * This method is called by when the parser successfully matches an input
     * symbol.
     *
     * @param recognizer the parser instance
     */
    @Override
    public void reportMatch(Parser recognizer) {
        super.reportMatch(recognizer);
    }

    /**
     * Report any kind of {@link RecognitionException}. This method is called by
     * the default exception handler generated for a rule method.
     *
     * @param recognizer the parser instance
     * @param e          the recognition exception to report
     */
    @Override
    public void reportError(Parser recognizer, RecognitionException e) {
        isErrorOcurred = true;
        super.reportError(recognizer, e);
    }
}
