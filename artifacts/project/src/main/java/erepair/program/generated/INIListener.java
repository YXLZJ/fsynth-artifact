// Generated from INI.g4 by ANTLR 4.9.2
package erepair.program.generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link INIParser}.
 */
public interface INIListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link INIParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(INIParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link INIParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(INIParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link INIParser#config}.
	 * @param ctx the parse tree
	 */
	void enterConfig(INIParser.ConfigContext ctx);
	/**
	 * Exit a parse tree produced by {@link INIParser#config}.
	 * @param ctx the parse tree
	 */
	void exitConfig(INIParser.ConfigContext ctx);
	/**
	 * Enter a parse tree produced by {@link INIParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(INIParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link INIParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(INIParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link INIParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(INIParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link INIParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(INIParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link INIParser#item}.
	 * @param ctx the parse tree
	 */
	void enterItem(INIParser.ItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link INIParser#item}.
	 * @param ctx the parse tree
	 */
	void exitItem(INIParser.ItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link INIParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(INIParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link INIParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(INIParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link INIParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(INIParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link INIParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(INIParser.ValueContext ctx);
}