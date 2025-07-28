// Generated from tinyc.g4 by ANTLR 4.9.2
package erepair.program.generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link tinycParser}.
 */
public interface tinycListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link tinycParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(tinycParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link tinycParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(tinycParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link tinycParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(tinycParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link tinycParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(tinycParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link tinycParser#paren_expr}.
	 * @param ctx the parse tree
	 */
	void enterParen_expr(tinycParser.Paren_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link tinycParser#paren_expr}.
	 * @param ctx the parse tree
	 */
	void exitParen_expr(tinycParser.Paren_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link tinycParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(tinycParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link tinycParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(tinycParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link tinycParser#test}.
	 * @param ctx the parse tree
	 */
	void enterTest(tinycParser.TestContext ctx);
	/**
	 * Exit a parse tree produced by {@link tinycParser#test}.
	 * @param ctx the parse tree
	 */
	void exitTest(tinycParser.TestContext ctx);
	/**
	 * Enter a parse tree produced by {@link tinycParser#sum_}.
	 * @param ctx the parse tree
	 */
	void enterSum_(tinycParser.Sum_Context ctx);
	/**
	 * Exit a parse tree produced by {@link tinycParser#sum_}.
	 * @param ctx the parse tree
	 */
	void exitSum_(tinycParser.Sum_Context ctx);
	/**
	 * Enter a parse tree produced by {@link tinycParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(tinycParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link tinycParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(tinycParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link tinycParser#id_}.
	 * @param ctx the parse tree
	 */
	void enterId_(tinycParser.Id_Context ctx);
	/**
	 * Exit a parse tree produced by {@link tinycParser#id_}.
	 * @param ctx the parse tree
	 */
	void exitId_(tinycParser.Id_Context ctx);
	/**
	 * Enter a parse tree produced by {@link tinycParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(tinycParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link tinycParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(tinycParser.IntegerContext ctx);
}