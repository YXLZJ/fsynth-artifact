package fsynth.program.visitor;

import fsynth.program.Loggable;
import fsynth.program.generated.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.regex.Pattern;


public class SimpleTreeFlattener extends Loggable implements JSONVisitor<ArrayList<String>>, INIVisitor<ArrayList<String>>, sexpressionVisitor<ArrayList<String>>, tinycVisitor<ArrayList<String>> {

    private static final Pattern NEWLINE = Pattern.compile("[\\r\\n]+");
    private final TreeFlattenerJoiner<String> myPlainPPJ = new TreeFlattenerJoiner<>("");
    private final TreeFlattenerJoiner<String> myWhitespacePPJ = new TreeFlattenerJoiner<>(" ");

    public SimpleTreeFlattener() {
        super("TreeFlattener");
    }

    /**
     * Visit a parse tree, and return a user-defined result of the operation.
     *
     * @param tree The {@link ParseTree} to visit.
     * @return The result of visiting the parse tree.
     */
    @Override
    public ArrayList<String> visit(ParseTree tree) {
        var b = new ArrayList<String>();
        for (int i = 0; i < tree.getChildCount(); i++) {
            myWhitespacePPJ.accumulator().accept(b, tree.getChild(i).accept(this));
        }
        return b;
    }

    /**
     * Visit the children of a node, and return a user-defined result of the
     * operation.
     *
     * @param node The {@link RuleNode} whose children should be visited.
     * @return The result of visiting the children of the node.
     */
    @Override
    public ArrayList<String> visitChildren(RuleNode node) {
        return this.visit(node);
    }

    /**
     * Visit a terminal node, and return a user-defined result of the operation.
     *
     * @param node The {@link TerminalNode} to visit.
     * @return The result of visiting the node.
     */
    @Override
    public ArrayList<String> visitTerminal(TerminalNode node) {
        return new ArrayList<>(Collections.singletonList(node.getSymbol().getText()));
    }

    /**
     * Visit an error node, and return a user-defined result of the operation.
     *
     * @param node The {@link ErrorNode} to visit.
     * @return The result of visiting the node.
     */
    @Override
    public ArrayList<String> visitErrorNode(ErrorNode node) {
        final String nodetext = NEWLINE.matcher(node.toStringTree()).replaceAll("\\n");
        log(Level.FINE, "Visited an ErrorNode " + nodetext);
//        return new ArrayList<>(Collections.singletonList("(E " + nodetext + " )"));
        return new ArrayList<>(Collections.singletonList(""));//TURN ME OFF FOR DEBUGGING PURPOSES

    }

    @Override
    public ArrayList<String> visitJson(JSONParser.JsonContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitObj(JSONParser.ObjContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitPair(JSONParser.PairContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitArray(JSONParser.ArrayContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitValue(JSONParser.ValueContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitStart(INIParser.StartContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitConfig(INIParser.ConfigContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitSection(INIParser.SectionContext ctx) {
        var ret = this.visit(ctx);
        ret.add("\n");
        return ret;
    }

    @Override
    public ArrayList<String> visitTitle(INIParser.TitleContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitItem(INIParser.ItemContext ctx) {
        var ret = this.visit(ctx);
        ret.add("\n");
        return ret;
    }

    @Override
    public ArrayList<String> visitKey(INIParser.KeyContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitValue(INIParser.ValueContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitSexpr(sexpressionParser.SexprContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitItem(sexpressionParser.ItemContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitList_(sexpressionParser.List_Context ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitAtom(sexpressionParser.AtomContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitProgram(tinycParser.ProgramContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitStatement(tinycParser.StatementContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitParen_expr(tinycParser.Paren_exprContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitExpr(tinycParser.ExprContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitTest(tinycParser.TestContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitSum_(tinycParser.Sum_Context ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitTerm(tinycParser.TermContext ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitId_(tinycParser.Id_Context ctx) {
        return this.visit(ctx);
    }

    @Override
    public ArrayList<String> visitInteger(tinycParser.IntegerContext ctx) {
        return this.visit(ctx);
    }
}
