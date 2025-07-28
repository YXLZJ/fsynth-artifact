// Generated from INI.g4 by ANTLR 4.9.2
package erepair.program.generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link INIParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface INIVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link INIParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(INIParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link INIParser#config}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConfig(INIParser.ConfigContext ctx);
	/**
	 * Visit a parse tree produced by {@link INIParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(INIParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link INIParser#title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle(INIParser.TitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link INIParser#item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItem(INIParser.ItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link INIParser#key}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKey(INIParser.KeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link INIParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(INIParser.ValueContext ctx);
}