// Generated from INI.g4 by ANTLR 4.9.2
package erepair.program.generated;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class INILexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, STRING=2, LBRACKET=3, RBRACKET=4, EQUALS=5, NOQUOTEDSTRING=6, 
		WHITESPACE=7;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMENT", "STRING", "ESC", "UNICODE", "HEX", "SAFECODEPOINT", "LBRACKET", 
			"RBRACKET", "EQUALS", "NOQUOTEDSTRING", "WHITESPACE", "LINEBREAK"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'['", "']'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMENT", "STRING", "LBRACKET", "RBRACKET", "EQUALS", "NOQUOTEDSTRING", 
			"WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public INILexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "INI.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\tX\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\7\2\36\n\2\f\2\16\2!\13\2\3\2\3\2\3\3\3\3"+
		"\3\3\7\3(\n\3\f\3\16\3+\13\3\3\3\3\3\3\4\3\4\3\4\5\4\62\n\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\7\13E\n\13"+
		"\f\13\16\13H\13\13\3\13\3\13\3\f\3\f\6\fN\n\f\r\f\16\fO\3\f\3\f\3\r\5"+
		"\rU\n\r\3\r\3\r\2\2\16\3\3\5\4\7\2\t\2\13\2\r\2\17\5\21\6\23\7\25\b\27"+
		"\t\31\2\3\2\t\4\2\f\f\17\17\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\5\2\2"+
		"!$$^^\t\2\f\f\17\17$$==??]]__\n\2\f\f\17\17\"\"$$==??]]__\4\2\13\13\""+
		"\"\2Z\2\3\3\2\2\2\2\5\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\3\33\3\2\2\2\5$\3\2\2\2\7.\3\2\2\2\t\63\3\2\2"+
		"\2\139\3\2\2\2\r;\3\2\2\2\17=\3\2\2\2\21?\3\2\2\2\23A\3\2\2\2\25F\3\2"+
		"\2\2\27M\3\2\2\2\31T\3\2\2\2\33\37\7=\2\2\34\36\n\2\2\2\35\34\3\2\2\2"+
		"\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 \"\3\2\2\2!\37\3\2\2\2\"#\b\2\2"+
		"\2#\4\3\2\2\2$)\7$\2\2%(\5\7\4\2&(\5\r\7\2\'%\3\2\2\2\'&\3\2\2\2(+\3\2"+
		"\2\2)\'\3\2\2\2)*\3\2\2\2*,\3\2\2\2+)\3\2\2\2,-\7$\2\2-\6\3\2\2\2.\61"+
		"\7^\2\2/\62\t\3\2\2\60\62\5\t\5\2\61/\3\2\2\2\61\60\3\2\2\2\62\b\3\2\2"+
		"\2\63\64\7w\2\2\64\65\5\13\6\2\65\66\5\13\6\2\66\67\5\13\6\2\678\5\13"+
		"\6\28\n\3\2\2\29:\t\4\2\2:\f\3\2\2\2;<\n\5\2\2<\16\3\2\2\2=>\7]\2\2>\20"+
		"\3\2\2\2?@\7_\2\2@\22\3\2\2\2AB\7?\2\2B\24\3\2\2\2CE\n\6\2\2DC\3\2\2\2"+
		"EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2\2IJ\n\7\2\2J\26\3\2\2"+
		"\2KN\t\b\2\2LN\5\31\r\2MK\3\2\2\2ML\3\2\2\2NO\3\2\2\2OM\3\2\2\2OP\3\2"+
		"\2\2PQ\3\2\2\2QR\b\f\2\2R\30\3\2\2\2SU\7\17\2\2TS\3\2\2\2TU\3\2\2\2UV"+
		"\3\2\2\2VW\7\f\2\2W\32\3\2\2\2\13\2\37\')\61FMOT\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}