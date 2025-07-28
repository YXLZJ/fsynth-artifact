// Generated from tinyc.g4 by ANTLR 4.9.2
package erepair.program.generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link tinycParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface tinycVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link tinycParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(tinycParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link tinycParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(tinycParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link tinycParser#paren_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen_expr(tinycParser.Paren_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link tinycParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(tinycParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link tinycParser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest(tinycParser.TestContext ctx);
	/**
	 * Visit a parse tree produced by {@link tinycParser#sum_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSum_(tinycParser.Sum_Context ctx);
	/**
	 * Visit a parse tree produced by {@link tinycParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(tinycParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link tinycParser#id_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_(tinycParser.Id_Context ctx);
	/**
	 * Visit a parse tree produced by {@link tinycParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(tinycParser.IntegerContext ctx);
}