// Generated from sexpression.g4 by ANTLR 4.9.2
package erepair.program.generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link sexpressionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface sexpressionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link sexpressionParser#sexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSexpr(sexpressionParser.SexprContext ctx);
	/**
	 * Visit a parse tree produced by {@link sexpressionParser#item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItem(sexpressionParser.ItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link sexpressionParser#list_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_(sexpressionParser.List_Context ctx);
	/**
	 * Visit a parse tree produced by {@link sexpressionParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(sexpressionParser.AtomContext ctx);
}