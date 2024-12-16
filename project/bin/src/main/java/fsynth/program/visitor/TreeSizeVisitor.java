package fsynth.program.visitor;

import org.antlr.v4.runtime.tree.*;

public class TreeSizeVisitor implements ParseTreeVisitor<Integer> {
    /**
     * Visit a parse tree, and return a user-defined result of the operation.
     *
     * @param tree The {@link ParseTree} to visit.
     * @return The result of visiting the parse tree.
     */
    @Override
    public Integer visit(ParseTree tree) {
        int j = 0;
        for (int i = 0; i < tree.getChildCount(); i++) {
            j += tree.getChild(i).accept(this);
        }
        return j;
    }

    /**
     * Visit the children of a node, and return a user-defined result of the
     * operation.
     *
     * @param node The {@link RuleNode} whose children should be visited.
     * @return The result of visiting the children of the node.
     */
    @Override
    public Integer visitChildren(RuleNode node) {
        int j = 0;
        for (int i = 0; i < node.getChildCount(); i++) {
            j += node.getChild(i).accept(this);
        }
        return j;
    }

    /**
     * Visit a terminal node, and return a user-defined result of the operation.
     *
     * @param node The {@link TerminalNode} to visit.
     * @return The result of visiting the node.
     */
    @Override
    public Integer visitTerminal(TerminalNode node) {
        return 1;
    }

    /**
     * Visit an error node, and return a user-defined result of the operation.
     *
     * @param node The {@link ErrorNode} to visit.
     * @return The result of visiting the node.
     */
    @Override
    public Integer visitErrorNode(ErrorNode node) {
        return 0;
    }
}
