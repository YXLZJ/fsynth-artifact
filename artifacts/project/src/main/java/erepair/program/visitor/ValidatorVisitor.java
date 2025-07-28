package erepair.program.visitor;

import erepair.program.Logging;
import org.antlr.v4.runtime.tree.*;

import javax.annotation.Nonnull;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 **/
public abstract class ValidatorVisitor implements ParseTreeVisitor<Boolean> {
    final Logger logger = Logging.antlrLogger;
    private final boolean skipErrorTokens;

    /**
     * Instantiate a new Validator Visitor.
     *
     * @param skipErrorTokens If true, try to skip through error tokens.
     *                        This is useful to verify if a file is in a certain file format.
     *                        Not that using this default implementation, the visitor might always accept a file.
     */
    public ValidatorVisitor(boolean skipErrorTokens) {
        this.skipErrorTokens = skipErrorTokens;
    }

    @Override
    public Boolean visit(ParseTree tree) {
        throwUseGeneric();
        return false;
    }

    @Override
    public Boolean visitChildren(RuleNode node) {
        throwUseGeneric();
        return false;
    }

    @Override
    public Boolean visitTerminal(TerminalNode node) {
        logger.log(Level.FINER, this.getClass().getSimpleName() + " visited Terminal Node " + node.getText());
        return true;
    }

    @Override
    public Boolean visitErrorNode(ErrorNode node) {
        logger.log(Level.FINE, this.getClass().getSimpleName() + " visited Error Node " + node.getText());
        return this.skipErrorTokens;
    }

    /**
     * Log a log message with the {@link Level#FINE} level
     *
     * @param message Message to log
     */
    void logFine(String message) {
        logger.fine(this.getClass().getSimpleName() + ": " + message);
    }

    /**
     * Log a log message with the {@link Level#FINER} level
     *
     * @param message Message to log
     */
    void logFiner(String message) {
        logger.finer(this.getClass().getSimpleName() + ": " + message);
    }

    /**
     * Throw a "better use generic visitor" exception
     *
     * @throws RuntimeException Exception that is thrown
     */
    void throwUseGeneric() throws RuntimeException {
        throw new RuntimeException("The " + this.getClass().getCanonicalName() + " is not made for traversal of other parse trees than JSON. Please use " + GenericValidatorVisitor.class.getCanonicalName() + " instead.");
    }

    /**
     * Log an occurrence of a tree whose children are NULL
     *
     * @param context The visited tree
     */
    void logNullChildren(@Nonnull ParseTree context) {
        logger.log(Level.FINE, this.getClass().getSimpleName() + " got a subtree with a NULL child! Tree: " + context);
    }

    /**
     * Log a occurrence of a tree that is NULL itself.
     */
    void logNullTree() {
        logger.log(Level.FINE, this.getClass().getSimpleName() + " got a NULL subtree");
    }
}
