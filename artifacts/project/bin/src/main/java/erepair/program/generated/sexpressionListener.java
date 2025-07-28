// Generated from sexpression.g4 by ANTLR 4.9.2
package erepair.program.generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link sexpressionParser}.
 */
public interface sexpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link sexpressionParser#sexpr}.
	 * @param ctx the parse tree
	 */
	void enterSexpr(sexpressionParser.SexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link sexpressionParser#sexpr}.
	 * @param ctx the parse tree
	 */
	void exitSexpr(sexpressionParser.SexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link sexpressionParser#item}.
	 * @param ctx the parse tree
	 */
	void enterItem(sexpressionParser.ItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link sexpressionParser#item}.
	 * @param ctx the parse tree
	 */
	void exitItem(sexpressionParser.ItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link sexpressionParser#list_}.
	 * @param ctx the parse tree
	 */
	void enterList_(sexpressionParser.List_Context ctx);
	/**
	 * Exit a parse tree produced by {@link sexpressionParser#list_}.
	 * @param ctx the parse tree
	 */
	void exitList_(sexpressionParser.List_Context ctx);
	/**
	 * Enter a parse tree produced by {@link sexpressionParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(sexpressionParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link sexpressionParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(sexpressionParser.AtomContext ctx);
}