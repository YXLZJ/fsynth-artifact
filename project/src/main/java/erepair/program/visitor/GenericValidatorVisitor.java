package erepair.program.visitor;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;

/**
 * Simple visitor that returns false if there was an error node in the parse tree
 *
 **/
public class GenericValidatorVisitor extends ValidatorVisitor implements ParseTreeVisitor<Boolean> {

    public GenericValidatorVisitor(boolean skipErrorTokens) {
        super(skipErrorTokens);
    }

    @Override
    public Boolean visit(ParseTree tree) {
        if (tree == null) {
            super.logNullTree();
            return false;
        }
        boolean yes = true;
        for (int i = 0; i < tree.getChildCount(); i++) {
            yes = yes && tree.getChild(i) != null && tree.getChild(i).accept(this);
        }
        return yes;
    }

    @Override
    public Boolean visitChildren(RuleNode node) {
        if (node == null) {
            super.logNullTree();
            return false;
        }
        boolean yes = true;
        for (int i = 0; i < node.getChildCount(); i++) {
            yes = yes && node.getChild(i) != null && node.getChild(i).accept(this);
        }
        return yes;
    }
}
