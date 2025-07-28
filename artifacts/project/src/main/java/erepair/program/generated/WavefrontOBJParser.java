// Generated from WavefrontOBJ.g4 by ANTLR 4.9.2
package erepair.program.generated;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WavefrontOBJParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, INTEGER_PAIR=52, 
		INTEGER_TRIPLET=53, INTEGER=54, DECIMAL=55, COMMENT=56, NAME=57, FILENAME=58, 
		WS=59, NL=60, NON_NL=61, NON_WS=62;
	public static final int
		RULE_start_ = 0, RULE_statement = 1, RULE_call = 2, RULE_csh = 3, RULE_vertex = 4, 
		RULE_vertex_parameter = 5, RULE_vertex_normal = 6, RULE_vertex_texture = 7, 
		RULE_curve_surface_type = 8, RULE_degree = 9, RULE_basis_matrix = 10, 
		RULE_step = 11, RULE_points = 12, RULE_lines = 13, RULE_faces = 14, RULE_free_form_surface = 15, 
		RULE_curve = 16, RULE_curve2d = 17, RULE_surface = 18, RULE_parameter = 19, 
		RULE_outer_trimming_loop = 20, RULE_inner_trimming_loop = 21, RULE_special_curve = 22, 
		RULE_special_point = 23, RULE_end = 24, RULE_connectivity = 25, RULE_group = 26, 
		RULE_smoothing_group = 27, RULE_merging_group = 28, RULE_object_name = 29, 
		RULE_bevel = 30, RULE_color_interpolation = 31, RULE_dissolve_interpolation = 32, 
		RULE_level_of_detail = 33, RULE_map_library = 34, RULE_use_map = 35, RULE_material_library = 36, 
		RULE_use_material = 37, RULE_shadow_object = 38, RULE_trace_object = 39, 
		RULE_curve_approximation_technique = 40, RULE_surface_approximation_technique = 41, 
		RULE_decimal = 42;
	private static String[] makeRuleNames() {
		return new String[] {
			"start_", "statement", "call", "csh", "vertex", "vertex_parameter", "vertex_normal", 
			"vertex_texture", "curve_surface_type", "degree", "basis_matrix", "step", 
			"points", "lines", "faces", "free_form_surface", "curve", "curve2d", 
			"surface", "parameter", "outer_trimming_loop", "inner_trimming_loop", 
			"special_curve", "special_point", "end", "connectivity", "group", "smoothing_group", 
			"merging_group", "object_name", "bevel", "color_interpolation", "dissolve_interpolation", 
			"level_of_detail", "map_library", "use_map", "material_library", "use_material", 
			"shadow_object", "trace_object", "curve_approximation_technique", "surface_approximation_technique", 
			"decimal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-'", "'v'", "'vp'", "'vn'", "'vt'", "'cstype'", "'rat'", "'bmatrix'", 
			"'bezier'", "'bspline'", "'cardinal'", "'taylor'", "'deg'", "'bmat'", 
			"'u'", "'step'", "'p'", "'l'", "'f'", "'curv'", "'curv2'", "'surf'", 
			"'parm'", "'trim'", "'hole'", "'scrv'", "'sp'", "'end'", "'con'", "'g'", 
			"'s'", "'off'", "'mg'", "'o'", "'bevel'", "'on'", "'c_interp'", "'d_interp'", 
			"'lod'", "'maplib'", "'usemap'", "'mtllib'", "'usemtl'", "'shadow_obj'", 
			"'trace_obj'", "'ctech'", "'cparm'", "'cspace'", "'stech'", "'cparma'", 
			"'cparmb'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "INTEGER_PAIR", "INTEGER_TRIPLET", "INTEGER", 
			"DECIMAL", "COMMENT", "NAME", "FILENAME", "WS", "NL", "NON_NL", "NON_WS"
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

	@Override
	public String getGrammarFileName() { return "WavefrontOBJ.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public WavefrontOBJParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class Start_Context extends ParserRuleContext {
		public List<TerminalNode> NL() { return getTokens(WavefrontOBJParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(WavefrontOBJParser.NL, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> EOF() { return getTokens(WavefrontOBJParser.EOF); }
		public TerminalNode EOF(int i) {
			return getToken(WavefrontOBJParser.EOF, i);
		}
		public Start_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterStart_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitStart_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitStart_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Start_Context start_() throws RecognitionException {
		Start_Context _localctx = new Start_Context(_ctx, getState());
		enterRule(_localctx, 0, RULE_start_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(86);
				match(NL);
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(101); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(92);
				statement();
				setState(99);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NL:
					{
					setState(94); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(93);
						match(NL);
						}
						}
						setState(96); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==NL );
					}
					break;
				case EOF:
					{
					setState(98);
					match(EOF);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(103); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__12) | (1L << T__13) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << T__48) | (1L << FILENAME))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public CshContext csh() {
			return getRuleContext(CshContext.class,0);
		}
		public VertexContext vertex() {
			return getRuleContext(VertexContext.class,0);
		}
		public Vertex_normalContext vertex_normal() {
			return getRuleContext(Vertex_normalContext.class,0);
		}
		public Vertex_textureContext vertex_texture() {
			return getRuleContext(Vertex_textureContext.class,0);
		}
		public Vertex_parameterContext vertex_parameter() {
			return getRuleContext(Vertex_parameterContext.class,0);
		}
		public PointsContext points() {
			return getRuleContext(PointsContext.class,0);
		}
		public LinesContext lines() {
			return getRuleContext(LinesContext.class,0);
		}
		public FacesContext faces() {
			return getRuleContext(FacesContext.class,0);
		}
		public Curve_surface_typeContext curve_surface_type() {
			return getRuleContext(Curve_surface_typeContext.class,0);
		}
		public DegreeContext degree() {
			return getRuleContext(DegreeContext.class,0);
		}
		public Basis_matrixContext basis_matrix() {
			return getRuleContext(Basis_matrixContext.class,0);
		}
		public StepContext step() {
			return getRuleContext(StepContext.class,0);
		}
		public Free_form_surfaceContext free_form_surface() {
			return getRuleContext(Free_form_surfaceContext.class,0);
		}
		public ConnectivityContext connectivity() {
			return getRuleContext(ConnectivityContext.class,0);
		}
		public GroupContext group() {
			return getRuleContext(GroupContext.class,0);
		}
		public Smoothing_groupContext smoothing_group() {
			return getRuleContext(Smoothing_groupContext.class,0);
		}
		public Merging_groupContext merging_group() {
			return getRuleContext(Merging_groupContext.class,0);
		}
		public Object_nameContext object_name() {
			return getRuleContext(Object_nameContext.class,0);
		}
		public BevelContext bevel() {
			return getRuleContext(BevelContext.class,0);
		}
		public Color_interpolationContext color_interpolation() {
			return getRuleContext(Color_interpolationContext.class,0);
		}
		public Dissolve_interpolationContext dissolve_interpolation() {
			return getRuleContext(Dissolve_interpolationContext.class,0);
		}
		public Level_of_detailContext level_of_detail() {
			return getRuleContext(Level_of_detailContext.class,0);
		}
		public Map_libraryContext map_library() {
			return getRuleContext(Map_libraryContext.class,0);
		}
		public Use_mapContext use_map() {
			return getRuleContext(Use_mapContext.class,0);
		}
		public Material_libraryContext material_library() {
			return getRuleContext(Material_libraryContext.class,0);
		}
		public Use_materialContext use_material() {
			return getRuleContext(Use_materialContext.class,0);
		}
		public Shadow_objectContext shadow_object() {
			return getRuleContext(Shadow_objectContext.class,0);
		}
		public Trace_objectContext trace_object() {
			return getRuleContext(Trace_objectContext.class,0);
		}
		public Curve_approximation_techniqueContext curve_approximation_technique() {
			return getRuleContext(Curve_approximation_techniqueContext.class,0);
		}
		public Surface_approximation_techniqueContext surface_approximation_technique() {
			return getRuleContext(Surface_approximation_techniqueContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(136);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(105);
				call();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
				csh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(107);
				vertex();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(108);
				vertex_normal();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(109);
				vertex_texture();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(110);
				vertex_parameter();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(111);
				points();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(112);
				lines();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(113);
				faces();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(114);
				curve_surface_type();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(115);
				degree();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(116);
				basis_matrix();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(117);
				step();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(118);
				free_form_surface();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(119);
				connectivity();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(120);
				group();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(121);
				smoothing_group();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(122);
				merging_group();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(123);
				object_name();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(124);
				bevel();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(125);
				color_interpolation();
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(126);
				dissolve_interpolation();
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(127);
				level_of_detail();
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(128);
				map_library();
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(129);
				use_map();
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(130);
				material_library();
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(131);
				use_material();
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 28);
				{
				setState(132);
				shadow_object();
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 29);
				{
				setState(133);
				trace_object();
				}
				break;
			case 30:
				enterOuterAlt(_localctx, 30);
				{
				setState(134);
				curve_approximation_technique();
				}
				break;
			case 31:
				enterOuterAlt(_localctx, 31);
				{
				setState(135);
				surface_approximation_technique();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallContext extends ParserRuleContext {
		public Token filename;
		public Token args;
		public TerminalNode FILENAME() { return getToken(WavefrontOBJParser.FILENAME, 0); }
		public List<TerminalNode> NON_WS() { return getTokens(WavefrontOBJParser.NON_WS); }
		public TerminalNode NON_WS(int i) {
			return getToken(WavefrontOBJParser.NON_WS, i);
		}
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			((CallContext)_localctx).filename = match(FILENAME);
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NON_WS) {
				{
				{
				setState(139);
				((CallContext)_localctx).args = match(NON_WS);
				}
				}
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CshContext extends ParserRuleContext {
		public Token command;
		public Token args;
		public TerminalNode FILENAME() { return getToken(WavefrontOBJParser.FILENAME, 0); }
		public List<TerminalNode> NON_WS() { return getTokens(WavefrontOBJParser.NON_WS); }
		public TerminalNode NON_WS(int i) {
			return getToken(WavefrontOBJParser.NON_WS, i);
		}
		public CshContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_csh; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterCsh(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitCsh(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitCsh(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CshContext csh() throws RecognitionException {
		CshContext _localctx = new CshContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_csh);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			((CshContext)_localctx).command = match(FILENAME);
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(146);
				match(T__0);
				}
			}

			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NON_WS) {
				{
				{
				setState(149);
				((CshContext)_localctx).args = match(NON_WS);
				}
				}
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VertexContext extends ParserRuleContext {
		public DecimalContext x;
		public DecimalContext y;
		public DecimalContext z;
		public DecimalContext w;
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public VertexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vertex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterVertex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitVertex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitVertex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VertexContext vertex() throws RecognitionException {
		VertexContext _localctx = new VertexContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_vertex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(T__1);
			setState(156);
			((VertexContext)_localctx).x = decimal();
			setState(157);
			((VertexContext)_localctx).y = decimal();
			setState(158);
			((VertexContext)_localctx).z = decimal();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTEGER || _la==DECIMAL) {
				{
				setState(159);
				((VertexContext)_localctx).w = decimal();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Vertex_parameterContext extends ParserRuleContext {
		public DecimalContext u;
		public DecimalContext v;
		public DecimalContext w;
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public Vertex_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vertex_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterVertex_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitVertex_parameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitVertex_parameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Vertex_parameterContext vertex_parameter() throws RecognitionException {
		Vertex_parameterContext _localctx = new Vertex_parameterContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_vertex_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(T__2);
			setState(163);
			((Vertex_parameterContext)_localctx).u = decimal();
			setState(165);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(164);
				((Vertex_parameterContext)_localctx).v = decimal();
				}
				break;
			}
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTEGER || _la==DECIMAL) {
				{
				setState(167);
				((Vertex_parameterContext)_localctx).w = decimal();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Vertex_normalContext extends ParserRuleContext {
		public DecimalContext x;
		public DecimalContext y;
		public DecimalContext z;
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public Vertex_normalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vertex_normal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterVertex_normal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitVertex_normal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitVertex_normal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Vertex_normalContext vertex_normal() throws RecognitionException {
		Vertex_normalContext _localctx = new Vertex_normalContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_vertex_normal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(T__3);
			setState(171);
			((Vertex_normalContext)_localctx).x = decimal();
			setState(172);
			((Vertex_normalContext)_localctx).y = decimal();
			setState(173);
			((Vertex_normalContext)_localctx).z = decimal();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Vertex_textureContext extends ParserRuleContext {
		public DecimalContext u;
		public DecimalContext v;
		public DecimalContext w;
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public Vertex_textureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vertex_texture; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterVertex_texture(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitVertex_texture(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitVertex_texture(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Vertex_textureContext vertex_texture() throws RecognitionException {
		Vertex_textureContext _localctx = new Vertex_textureContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_vertex_texture);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(T__4);
			setState(176);
			((Vertex_textureContext)_localctx).u = decimal();
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(177);
				((Vertex_textureContext)_localctx).v = decimal();
				}
				break;
			}
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTEGER || _la==DECIMAL) {
				{
				setState(180);
				((Vertex_textureContext)_localctx).w = decimal();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Curve_surface_typeContext extends ParserRuleContext {
		public Token rational;
		public Token cstype;
		public Curve_surface_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_curve_surface_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterCurve_surface_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitCurve_surface_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitCurve_surface_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Curve_surface_typeContext curve_surface_type() throws RecognitionException {
		Curve_surface_typeContext _localctx = new Curve_surface_typeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_curve_surface_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(T__5);
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(184);
				((Curve_surface_typeContext)_localctx).rational = match(T__6);
				}
			}

			setState(187);
			((Curve_surface_typeContext)_localctx).cstype = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) ) {
				((Curve_surface_typeContext)_localctx).cstype = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DegreeContext extends ParserRuleContext {
		public Token u;
		public Token v;
		public List<TerminalNode> INTEGER() { return getTokens(WavefrontOBJParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(WavefrontOBJParser.INTEGER, i);
		}
		public DegreeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_degree; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterDegree(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitDegree(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitDegree(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DegreeContext degree() throws RecognitionException {
		DegreeContext _localctx = new DegreeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_degree);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(T__12);
			setState(190);
			((DegreeContext)_localctx).u = match(INTEGER);
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTEGER) {
				{
				setState(191);
				((DegreeContext)_localctx).v = match(INTEGER);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Basis_matrixContext extends ParserRuleContext {
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public Basis_matrixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basis_matrix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterBasis_matrix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitBasis_matrix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitBasis_matrix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Basis_matrixContext basis_matrix() throws RecognitionException {
		Basis_matrixContext _localctx = new Basis_matrixContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_basis_matrix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(T__13);
			setState(195);
			_la = _input.LA(1);
			if ( !(_la==T__1 || _la==T__14) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(197); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(196);
				decimal();
				}
				}
				setState(199); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INTEGER || _la==DECIMAL );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StepContext extends ParserRuleContext {
		public Token u;
		public Token v;
		public List<TerminalNode> INTEGER() { return getTokens(WavefrontOBJParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(WavefrontOBJParser.INTEGER, i);
		}
		public StepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_step; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StepContext step() throws RecognitionException {
		StepContext _localctx = new StepContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_step);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(T__15);
			setState(202);
			((StepContext)_localctx).u = match(INTEGER);
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTEGER) {
				{
				setState(203);
				((StepContext)_localctx).v = match(INTEGER);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointsContext extends ParserRuleContext {
		public Token v;
		public List<TerminalNode> INTEGER() { return getTokens(WavefrontOBJParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(WavefrontOBJParser.INTEGER, i);
		}
		public PointsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_points; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterPoints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitPoints(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitPoints(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PointsContext points() throws RecognitionException {
		PointsContext _localctx = new PointsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_points);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(T__16);
			setState(208); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(207);
				((PointsContext)_localctx).v = match(INTEGER);
				}
				}
				setState(210); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INTEGER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LinesContext extends ParserRuleContext {
		public Token v;
		public List<TerminalNode> INTEGER() { return getTokens(WavefrontOBJParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(WavefrontOBJParser.INTEGER, i);
		}
		public List<TerminalNode> INTEGER_PAIR() { return getTokens(WavefrontOBJParser.INTEGER_PAIR); }
		public TerminalNode INTEGER_PAIR(int i) {
			return getToken(WavefrontOBJParser.INTEGER_PAIR, i);
		}
		public LinesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lines; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterLines(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitLines(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitLines(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinesContext lines() throws RecognitionException {
		LinesContext _localctx = new LinesContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_lines);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(T__17);
			setState(214); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(213);
				((LinesContext)_localctx).v = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==INTEGER_PAIR || _la==INTEGER) ) {
					((LinesContext)_localctx).v = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(216); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INTEGER_PAIR || _la==INTEGER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FacesContext extends ParserRuleContext {
		public Token v;
		public List<TerminalNode> INTEGER() { return getTokens(WavefrontOBJParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(WavefrontOBJParser.INTEGER, i);
		}
		public List<TerminalNode> INTEGER_PAIR() { return getTokens(WavefrontOBJParser.INTEGER_PAIR); }
		public TerminalNode INTEGER_PAIR(int i) {
			return getToken(WavefrontOBJParser.INTEGER_PAIR, i);
		}
		public List<TerminalNode> INTEGER_TRIPLET() { return getTokens(WavefrontOBJParser.INTEGER_TRIPLET); }
		public TerminalNode INTEGER_TRIPLET(int i) {
			return getToken(WavefrontOBJParser.INTEGER_TRIPLET, i);
		}
		public FacesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_faces; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterFaces(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitFaces(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitFaces(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FacesContext faces() throws RecognitionException {
		FacesContext _localctx = new FacesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_faces);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			match(T__18);
			setState(220); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(219);
				((FacesContext)_localctx).v = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER_PAIR) | (1L << INTEGER_TRIPLET) | (1L << INTEGER))) != 0)) ) {
					((FacesContext)_localctx).v = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(222); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER_PAIR) | (1L << INTEGER_TRIPLET) | (1L << INTEGER))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Free_form_surfaceContext extends ParserRuleContext {
		public EndContext end() {
			return getRuleContext(EndContext.class,0);
		}
		public CurveContext curve() {
			return getRuleContext(CurveContext.class,0);
		}
		public Curve2dContext curve2d() {
			return getRuleContext(Curve2dContext.class,0);
		}
		public SurfaceContext surface() {
			return getRuleContext(SurfaceContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(WavefrontOBJParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(WavefrontOBJParser.NL, i);
		}
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<Outer_trimming_loopContext> outer_trimming_loop() {
			return getRuleContexts(Outer_trimming_loopContext.class);
		}
		public Outer_trimming_loopContext outer_trimming_loop(int i) {
			return getRuleContext(Outer_trimming_loopContext.class,i);
		}
		public List<Inner_trimming_loopContext> inner_trimming_loop() {
			return getRuleContexts(Inner_trimming_loopContext.class);
		}
		public Inner_trimming_loopContext inner_trimming_loop(int i) {
			return getRuleContext(Inner_trimming_loopContext.class,i);
		}
		public List<Special_curveContext> special_curve() {
			return getRuleContexts(Special_curveContext.class);
		}
		public Special_curveContext special_curve(int i) {
			return getRuleContext(Special_curveContext.class,i);
		}
		public List<Special_pointContext> special_point() {
			return getRuleContexts(Special_pointContext.class);
		}
		public Special_pointContext special_point(int i) {
			return getRuleContext(Special_pointContext.class,i);
		}
		public Free_form_surfaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_free_form_surface; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterFree_form_surface(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitFree_form_surface(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitFree_form_surface(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Free_form_surfaceContext free_form_surface() throws RecognitionException {
		Free_form_surfaceContext _localctx = new Free_form_surfaceContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_free_form_surface);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__19:
				{
				setState(224);
				curve();
				}
				break;
			case T__20:
				{
				setState(225);
				curve2d();
				}
				break;
			case T__21:
				{
				setState(226);
				surface();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(230); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(229);
				match(NL);
				}
				}
				setState(232); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NL );
			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26))) != 0)) {
				{
				{
				setState(239);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__22:
					{
					setState(234);
					parameter();
					}
					break;
				case T__23:
					{
					setState(235);
					outer_trimming_loop();
					}
					break;
				case T__24:
					{
					setState(236);
					inner_trimming_loop();
					}
					break;
				case T__25:
					{
					setState(237);
					special_curve();
					}
					break;
				case T__26:
					{
					setState(238);
					special_point();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(242); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(241);
					match(NL);
					}
					}
					setState(244); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NL );
				}
				}
				setState(250);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(251);
			end();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CurveContext extends ParserRuleContext {
		public DecimalContext u0;
		public DecimalContext u1;
		public Token v;
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public List<TerminalNode> INTEGER() { return getTokens(WavefrontOBJParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(WavefrontOBJParser.INTEGER, i);
		}
		public CurveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_curve; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterCurve(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitCurve(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitCurve(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CurveContext curve() throws RecognitionException {
		CurveContext _localctx = new CurveContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_curve);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			match(T__19);
			setState(254);
			((CurveContext)_localctx).u0 = decimal();
			setState(255);
			((CurveContext)_localctx).u1 = decimal();
			setState(257); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(256);
				((CurveContext)_localctx).v = match(INTEGER);
				}
				}
				setState(259); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INTEGER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Curve2dContext extends ParserRuleContext {
		public Token vp1;
		public Token vp2;
		public List<TerminalNode> INTEGER() { return getTokens(WavefrontOBJParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(WavefrontOBJParser.INTEGER, i);
		}
		public Curve2dContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_curve2d; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterCurve2d(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitCurve2d(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitCurve2d(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Curve2dContext curve2d() throws RecognitionException {
		Curve2dContext _localctx = new Curve2dContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_curve2d);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			match(T__20);
			setState(262);
			((Curve2dContext)_localctx).vp1 = match(INTEGER);
			setState(264); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(263);
				((Curve2dContext)_localctx).vp2 = match(INTEGER);
				}
				}
				setState(266); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INTEGER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SurfaceContext extends ParserRuleContext {
		public DecimalContext s0;
		public DecimalContext s1;
		public DecimalContext t0;
		public DecimalContext t1;
		public Token v;
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public List<TerminalNode> INTEGER() { return getTokens(WavefrontOBJParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(WavefrontOBJParser.INTEGER, i);
		}
		public List<TerminalNode> INTEGER_PAIR() { return getTokens(WavefrontOBJParser.INTEGER_PAIR); }
		public TerminalNode INTEGER_PAIR(int i) {
			return getToken(WavefrontOBJParser.INTEGER_PAIR, i);
		}
		public List<TerminalNode> INTEGER_TRIPLET() { return getTokens(WavefrontOBJParser.INTEGER_TRIPLET); }
		public TerminalNode INTEGER_TRIPLET(int i) {
			return getToken(WavefrontOBJParser.INTEGER_TRIPLET, i);
		}
		public SurfaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_surface; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterSurface(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitSurface(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitSurface(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SurfaceContext surface() throws RecognitionException {
		SurfaceContext _localctx = new SurfaceContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_surface);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(T__21);
			setState(269);
			((SurfaceContext)_localctx).s0 = decimal();
			setState(270);
			((SurfaceContext)_localctx).s1 = decimal();
			setState(271);
			((SurfaceContext)_localctx).t0 = decimal();
			setState(272);
			((SurfaceContext)_localctx).t1 = decimal();
			setState(274); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(273);
				((SurfaceContext)_localctx).v = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER_PAIR) | (1L << INTEGER_TRIPLET) | (1L << INTEGER))) != 0)) ) {
					((SurfaceContext)_localctx).v = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(276); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER_PAIR) | (1L << INTEGER_TRIPLET) | (1L << INTEGER))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public DecimalContext p;
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			match(T__22);
			setState(279);
			_la = _input.LA(1);
			if ( !(_la==T__1 || _la==T__14) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(281); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(280);
				((ParameterContext)_localctx).p = decimal();
				}
				}
				setState(283); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INTEGER || _la==DECIMAL );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Outer_trimming_loopContext extends ParserRuleContext {
		public DecimalContext u0;
		public DecimalContext u1;
		public Token curv2d;
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public List<TerminalNode> INTEGER() { return getTokens(WavefrontOBJParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(WavefrontOBJParser.INTEGER, i);
		}
		public Outer_trimming_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outer_trimming_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterOuter_trimming_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitOuter_trimming_loop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitOuter_trimming_loop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Outer_trimming_loopContext outer_trimming_loop() throws RecognitionException {
		Outer_trimming_loopContext _localctx = new Outer_trimming_loopContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_outer_trimming_loop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(T__23);
			setState(290); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(286);
				((Outer_trimming_loopContext)_localctx).u0 = decimal();
				setState(287);
				((Outer_trimming_loopContext)_localctx).u1 = decimal();
				setState(288);
				((Outer_trimming_loopContext)_localctx).curv2d = match(INTEGER);
				}
				}
				setState(292); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INTEGER || _la==DECIMAL );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Inner_trimming_loopContext extends ParserRuleContext {
		public DecimalContext u0;
		public DecimalContext u1;
		public Token curv2d;
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public List<TerminalNode> INTEGER() { return getTokens(WavefrontOBJParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(WavefrontOBJParser.INTEGER, i);
		}
		public Inner_trimming_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inner_trimming_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterInner_trimming_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitInner_trimming_loop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitInner_trimming_loop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Inner_trimming_loopContext inner_trimming_loop() throws RecognitionException {
		Inner_trimming_loopContext _localctx = new Inner_trimming_loopContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_inner_trimming_loop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			match(T__24);
			setState(299); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(295);
				((Inner_trimming_loopContext)_localctx).u0 = decimal();
				setState(296);
				((Inner_trimming_loopContext)_localctx).u1 = decimal();
				setState(297);
				((Inner_trimming_loopContext)_localctx).curv2d = match(INTEGER);
				}
				}
				setState(301); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INTEGER || _la==DECIMAL );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Special_curveContext extends ParserRuleContext {
		public DecimalContext u0;
		public DecimalContext u1;
		public Token curv2d;
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public List<TerminalNode> INTEGER() { return getTokens(WavefrontOBJParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(WavefrontOBJParser.INTEGER, i);
		}
		public Special_curveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_special_curve; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterSpecial_curve(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitSpecial_curve(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitSpecial_curve(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Special_curveContext special_curve() throws RecognitionException {
		Special_curveContext _localctx = new Special_curveContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_special_curve);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			match(T__25);
			setState(308); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(304);
				((Special_curveContext)_localctx).u0 = decimal();
				setState(305);
				((Special_curveContext)_localctx).u1 = decimal();
				setState(306);
				((Special_curveContext)_localctx).curv2d = match(INTEGER);
				}
				}
				setState(310); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INTEGER || _la==DECIMAL );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Special_pointContext extends ParserRuleContext {
		public Token vp;
		public List<TerminalNode> INTEGER() { return getTokens(WavefrontOBJParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(WavefrontOBJParser.INTEGER, i);
		}
		public Special_pointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_special_point; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterSpecial_point(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitSpecial_point(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitSpecial_point(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Special_pointContext special_point() throws RecognitionException {
		Special_pointContext _localctx = new Special_pointContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_special_point);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			match(T__26);
			setState(314); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(313);
				((Special_pointContext)_localctx).vp = match(INTEGER);
				}
				}
				setState(316); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INTEGER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EndContext extends ParserRuleContext {
		public EndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_end; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitEnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitEnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndContext end() throws RecognitionException {
		EndContext _localctx = new EndContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_end);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			match(T__27);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConnectivityContext extends ParserRuleContext {
		public Token surf_1;
		public DecimalContext q0_1;
		public DecimalContext q1_1;
		public Token curv2d_1;
		public Token surf_2;
		public DecimalContext q0_2;
		public DecimalContext q1_2;
		public Token curv2d_2;
		public List<TerminalNode> INTEGER() { return getTokens(WavefrontOBJParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(WavefrontOBJParser.INTEGER, i);
		}
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public ConnectivityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_connectivity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterConnectivity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitConnectivity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitConnectivity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConnectivityContext connectivity() throws RecognitionException {
		ConnectivityContext _localctx = new ConnectivityContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_connectivity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			match(T__28);
			setState(321);
			((ConnectivityContext)_localctx).surf_1 = match(INTEGER);
			setState(322);
			((ConnectivityContext)_localctx).q0_1 = decimal();
			setState(323);
			((ConnectivityContext)_localctx).q1_1 = decimal();
			setState(324);
			((ConnectivityContext)_localctx).curv2d_1 = match(INTEGER);
			setState(325);
			((ConnectivityContext)_localctx).surf_2 = match(INTEGER);
			setState(326);
			((ConnectivityContext)_localctx).q0_2 = decimal();
			setState(327);
			((ConnectivityContext)_localctx).q1_2 = decimal();
			setState(328);
			((ConnectivityContext)_localctx).curv2d_2 = match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupContext extends ParserRuleContext {
		public Token group_name;
		public List<TerminalNode> NAME() { return getTokens(WavefrontOBJParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(WavefrontOBJParser.NAME, i);
		}
		public GroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupContext group() throws RecognitionException {
		GroupContext _localctx = new GroupContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_group);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			match(T__29);
			setState(332); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(331);
				((GroupContext)_localctx).group_name = match(NAME);
				}
				}
				setState(334); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Smoothing_groupContext extends ParserRuleContext {
		public Token group_number;
		public TerminalNode INTEGER() { return getToken(WavefrontOBJParser.INTEGER, 0); }
		public Smoothing_groupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_smoothing_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterSmoothing_group(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitSmoothing_group(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitSmoothing_group(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Smoothing_groupContext smoothing_group() throws RecognitionException {
		Smoothing_groupContext _localctx = new Smoothing_groupContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_smoothing_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			match(T__30);
			setState(339);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				{
				setState(337);
				((Smoothing_groupContext)_localctx).group_number = match(INTEGER);
				}
				break;
			case T__31:
				{
				setState(338);
				match(T__31);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Merging_groupContext extends ParserRuleContext {
		public Token group_number;
		public DecimalContext res;
		public TerminalNode INTEGER() { return getToken(WavefrontOBJParser.INTEGER, 0); }
		public DecimalContext decimal() {
			return getRuleContext(DecimalContext.class,0);
		}
		public Merging_groupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_merging_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterMerging_group(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitMerging_group(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitMerging_group(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Merging_groupContext merging_group() throws RecognitionException {
		Merging_groupContext _localctx = new Merging_groupContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_merging_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			match(T__32);
			setState(345);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				{
				setState(342);
				((Merging_groupContext)_localctx).group_number = match(INTEGER);
				setState(343);
				((Merging_groupContext)_localctx).res = decimal();
				}
				break;
			case T__31:
				{
				setState(344);
				match(T__31);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Object_nameContext extends ParserRuleContext {
		public Token name;
		public TerminalNode NAME() { return getToken(WavefrontOBJParser.NAME, 0); }
		public Object_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterObject_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitObject_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitObject_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Object_nameContext object_name() throws RecognitionException {
		Object_nameContext _localctx = new Object_nameContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_object_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347);
			match(T__33);
			setState(348);
			((Object_nameContext)_localctx).name = match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BevelContext extends ParserRuleContext {
		public BevelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bevel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterBevel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitBevel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitBevel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BevelContext bevel() throws RecognitionException {
		BevelContext _localctx = new BevelContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_bevel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(T__34);
			setState(351);
			_la = _input.LA(1);
			if ( !(_la==T__31 || _la==T__35) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Color_interpolationContext extends ParserRuleContext {
		public Color_interpolationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_color_interpolation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterColor_interpolation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitColor_interpolation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitColor_interpolation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Color_interpolationContext color_interpolation() throws RecognitionException {
		Color_interpolationContext _localctx = new Color_interpolationContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_color_interpolation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			match(T__36);
			setState(354);
			_la = _input.LA(1);
			if ( !(_la==T__31 || _la==T__35) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Dissolve_interpolationContext extends ParserRuleContext {
		public Dissolve_interpolationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dissolve_interpolation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterDissolve_interpolation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitDissolve_interpolation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitDissolve_interpolation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dissolve_interpolationContext dissolve_interpolation() throws RecognitionException {
		Dissolve_interpolationContext _localctx = new Dissolve_interpolationContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_dissolve_interpolation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			match(T__37);
			setState(357);
			_la = _input.LA(1);
			if ( !(_la==T__31 || _la==T__35) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Level_of_detailContext extends ParserRuleContext {
		public Token level;
		public TerminalNode INTEGER() { return getToken(WavefrontOBJParser.INTEGER, 0); }
		public Level_of_detailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_level_of_detail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterLevel_of_detail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitLevel_of_detail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitLevel_of_detail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Level_of_detailContext level_of_detail() throws RecognitionException {
		Level_of_detailContext _localctx = new Level_of_detailContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_level_of_detail);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			match(T__38);
			setState(360);
			((Level_of_detailContext)_localctx).level = match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Map_libraryContext extends ParserRuleContext {
		public Token filename;
		public List<TerminalNode> FILENAME() { return getTokens(WavefrontOBJParser.FILENAME); }
		public TerminalNode FILENAME(int i) {
			return getToken(WavefrontOBJParser.FILENAME, i);
		}
		public Map_libraryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_map_library; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterMap_library(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitMap_library(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitMap_library(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Map_libraryContext map_library() throws RecognitionException {
		Map_libraryContext _localctx = new Map_libraryContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_map_library);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			match(T__39);
			setState(364); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(363);
				((Map_libraryContext)_localctx).filename = match(FILENAME);
				}
				}
				setState(366); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==FILENAME );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Use_mapContext extends ParserRuleContext {
		public Token map_name;
		public TerminalNode NAME() { return getToken(WavefrontOBJParser.NAME, 0); }
		public Use_mapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_use_map; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterUse_map(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitUse_map(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitUse_map(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Use_mapContext use_map() throws RecognitionException {
		Use_mapContext _localctx = new Use_mapContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_use_map);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			match(T__40);
			setState(371);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				{
				setState(369);
				((Use_mapContext)_localctx).map_name = match(NAME);
				}
				break;
			case T__31:
				{
				setState(370);
				match(T__31);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Material_libraryContext extends ParserRuleContext {
		public Token filename;
		public TerminalNode FILENAME() { return getToken(WavefrontOBJParser.FILENAME, 0); }
		public Material_libraryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_material_library; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterMaterial_library(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitMaterial_library(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitMaterial_library(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Material_libraryContext material_library() throws RecognitionException {
		Material_libraryContext _localctx = new Material_libraryContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_material_library);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			match(T__41);
			setState(374);
			((Material_libraryContext)_localctx).filename = match(FILENAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Use_materialContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(WavefrontOBJParser.NAME, 0); }
		public Use_materialContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_use_material; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterUse_material(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitUse_material(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitUse_material(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Use_materialContext use_material() throws RecognitionException {
		Use_materialContext _localctx = new Use_materialContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_use_material);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			match(T__42);
			setState(377);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Shadow_objectContext extends ParserRuleContext {
		public Token filename;
		public TerminalNode FILENAME() { return getToken(WavefrontOBJParser.FILENAME, 0); }
		public Shadow_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shadow_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterShadow_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitShadow_object(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitShadow_object(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Shadow_objectContext shadow_object() throws RecognitionException {
		Shadow_objectContext _localctx = new Shadow_objectContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_shadow_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			match(T__43);
			setState(380);
			((Shadow_objectContext)_localctx).filename = match(FILENAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Trace_objectContext extends ParserRuleContext {
		public Token filename;
		public TerminalNode FILENAME() { return getToken(WavefrontOBJParser.FILENAME, 0); }
		public Trace_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trace_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterTrace_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitTrace_object(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitTrace_object(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Trace_objectContext trace_object() throws RecognitionException {
		Trace_objectContext _localctx = new Trace_objectContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_trace_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			match(T__44);
			setState(383);
			((Trace_objectContext)_localctx).filename = match(FILENAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Curve_approximation_techniqueContext extends ParserRuleContext {
		public DecimalContext res;
		public DecimalContext maxlength;
		public DecimalContext maxdist;
		public DecimalContext maxangle;
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public Curve_approximation_techniqueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_curve_approximation_technique; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterCurve_approximation_technique(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitCurve_approximation_technique(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitCurve_approximation_technique(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Curve_approximation_techniqueContext curve_approximation_technique() throws RecognitionException {
		Curve_approximation_techniqueContext _localctx = new Curve_approximation_techniqueContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_curve_approximation_technique);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(385);
			match(T__45);
			setState(394);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__46:
				{
				setState(386);
				match(T__46);
				setState(387);
				((Curve_approximation_techniqueContext)_localctx).res = decimal();
				}
				break;
			case T__47:
				{
				setState(388);
				match(T__47);
				setState(389);
				((Curve_approximation_techniqueContext)_localctx).maxlength = decimal();
				}
				break;
			case T__19:
				{
				setState(390);
				match(T__19);
				setState(391);
				((Curve_approximation_techniqueContext)_localctx).maxdist = decimal();
				setState(392);
				((Curve_approximation_techniqueContext)_localctx).maxangle = decimal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Surface_approximation_techniqueContext extends ParserRuleContext {
		public DecimalContext ures;
		public DecimalContext vres;
		public DecimalContext uvres;
		public DecimalContext maxlength;
		public DecimalContext maxdist;
		public DecimalContext maxangle;
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public Surface_approximation_techniqueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_surface_approximation_technique; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterSurface_approximation_technique(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitSurface_approximation_technique(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitSurface_approximation_technique(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Surface_approximation_techniqueContext surface_approximation_technique() throws RecognitionException {
		Surface_approximation_techniqueContext _localctx = new Surface_approximation_techniqueContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_surface_approximation_technique);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			match(T__48);
			setState(409);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__49:
				{
				setState(397);
				match(T__49);
				setState(398);
				((Surface_approximation_techniqueContext)_localctx).ures = decimal();
				setState(399);
				((Surface_approximation_techniqueContext)_localctx).vres = decimal();
				}
				break;
			case T__50:
				{
				setState(401);
				match(T__50);
				setState(402);
				((Surface_approximation_techniqueContext)_localctx).uvres = decimal();
				}
				break;
			case T__47:
				{
				setState(403);
				match(T__47);
				setState(404);
				((Surface_approximation_techniqueContext)_localctx).maxlength = decimal();
				}
				break;
			case T__19:
				{
				setState(405);
				match(T__19);
				setState(406);
				((Surface_approximation_techniqueContext)_localctx).maxdist = decimal();
				setState(407);
				((Surface_approximation_techniqueContext)_localctx).maxangle = decimal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecimalContext extends ParserRuleContext {
		public TerminalNode DECIMAL() { return getToken(WavefrontOBJParser.DECIMAL, 0); }
		public TerminalNode INTEGER() { return getToken(WavefrontOBJParser.INTEGER, 0); }
		public DecimalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).enterDecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WavefrontOBJListener ) ((WavefrontOBJListener)listener).exitDecimal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WavefrontOBJVisitor ) return ((WavefrontOBJVisitor<? extends T>)visitor).visitDecimal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecimalContext decimal() throws RecognitionException {
		DecimalContext _localctx = new DecimalContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_decimal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(411);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==DECIMAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3@\u01a0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\3\2\7\2Z\n\2\f\2\16\2]\13\2\3\2\3\2\6\2a\n\2\r\2\16\2b\3\2\5\2f\n"+
		"\2\6\2h\n\2\r\2\16\2i\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\5\3\u008b\n\3\3\4\3\4\7\4\u008f\n\4\f\4\16\4\u0092\13\4\3\5\3\5"+
		"\5\5\u0096\n\5\3\5\7\5\u0099\n\5\f\5\16\5\u009c\13\5\3\6\3\6\3\6\3\6\3"+
		"\6\5\6\u00a3\n\6\3\7\3\7\3\7\5\7\u00a8\n\7\3\7\5\7\u00ab\n\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\5\t\u00b5\n\t\3\t\5\t\u00b8\n\t\3\n\3\n\5\n\u00bc"+
		"\n\n\3\n\3\n\3\13\3\13\3\13\5\13\u00c3\n\13\3\f\3\f\3\f\6\f\u00c8\n\f"+
		"\r\f\16\f\u00c9\3\r\3\r\3\r\5\r\u00cf\n\r\3\16\3\16\6\16\u00d3\n\16\r"+
		"\16\16\16\u00d4\3\17\3\17\6\17\u00d9\n\17\r\17\16\17\u00da\3\20\3\20\6"+
		"\20\u00df\n\20\r\20\16\20\u00e0\3\21\3\21\3\21\5\21\u00e6\n\21\3\21\6"+
		"\21\u00e9\n\21\r\21\16\21\u00ea\3\21\3\21\3\21\3\21\3\21\5\21\u00f2\n"+
		"\21\3\21\6\21\u00f5\n\21\r\21\16\21\u00f6\7\21\u00f9\n\21\f\21\16\21\u00fc"+
		"\13\21\3\21\3\21\3\22\3\22\3\22\3\22\6\22\u0104\n\22\r\22\16\22\u0105"+
		"\3\23\3\23\3\23\6\23\u010b\n\23\r\23\16\23\u010c\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\6\24\u0115\n\24\r\24\16\24\u0116\3\25\3\25\3\25\6\25\u011c\n"+
		"\25\r\25\16\25\u011d\3\26\3\26\3\26\3\26\3\26\6\26\u0125\n\26\r\26\16"+
		"\26\u0126\3\27\3\27\3\27\3\27\3\27\6\27\u012e\n\27\r\27\16\27\u012f\3"+
		"\30\3\30\3\30\3\30\3\30\6\30\u0137\n\30\r\30\16\30\u0138\3\31\3\31\6\31"+
		"\u013d\n\31\r\31\16\31\u013e\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\34\3\34\6\34\u014f\n\34\r\34\16\34\u0150\3\35\3\35"+
		"\3\35\5\35\u0156\n\35\3\36\3\36\3\36\3\36\5\36\u015c\n\36\3\37\3\37\3"+
		"\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\6$\u016f\n$\r$\16$\u0170"+
		"\3%\3%\3%\5%\u0176\n%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3(\3)\3)\3)\3*\3*\3*"+
		"\3*\3*\3*\3*\3*\3*\5*\u018d\n*\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+"+
		"\5+\u019c\n+\3,\3,\3,\2\2-\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$"+
		"&(*,.\60\62\64\668:<>@BDFHJLNPRTV\2\b\3\2\n\16\4\2\4\4\21\21\4\2\66\66"+
		"88\3\2\668\4\2\"\"&&\3\289\2\u01c0\2[\3\2\2\2\4\u008a\3\2\2\2\6\u008c"+
		"\3\2\2\2\b\u0093\3\2\2\2\n\u009d\3\2\2\2\f\u00a4\3\2\2\2\16\u00ac\3\2"+
		"\2\2\20\u00b1\3\2\2\2\22\u00b9\3\2\2\2\24\u00bf\3\2\2\2\26\u00c4\3\2\2"+
		"\2\30\u00cb\3\2\2\2\32\u00d0\3\2\2\2\34\u00d6\3\2\2\2\36\u00dc\3\2\2\2"+
		" \u00e5\3\2\2\2\"\u00ff\3\2\2\2$\u0107\3\2\2\2&\u010e\3\2\2\2(\u0118\3"+
		"\2\2\2*\u011f\3\2\2\2,\u0128\3\2\2\2.\u0131\3\2\2\2\60\u013a\3\2\2\2\62"+
		"\u0140\3\2\2\2\64\u0142\3\2\2\2\66\u014c\3\2\2\28\u0152\3\2\2\2:\u0157"+
		"\3\2\2\2<\u015d\3\2\2\2>\u0160\3\2\2\2@\u0163\3\2\2\2B\u0166\3\2\2\2D"+
		"\u0169\3\2\2\2F\u016c\3\2\2\2H\u0172\3\2\2\2J\u0177\3\2\2\2L\u017a\3\2"+
		"\2\2N\u017d\3\2\2\2P\u0180\3\2\2\2R\u0183\3\2\2\2T\u018e\3\2\2\2V\u019d"+
		"\3\2\2\2XZ\7>\2\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\g\3\2\2\2"+
		"][\3\2\2\2^e\5\4\3\2_a\7>\2\2`_\3\2\2\2ab\3\2\2\2b`\3\2\2\2bc\3\2\2\2"+
		"cf\3\2\2\2df\7\2\2\3e`\3\2\2\2ed\3\2\2\2fh\3\2\2\2g^\3\2\2\2hi\3\2\2\2"+
		"ig\3\2\2\2ij\3\2\2\2j\3\3\2\2\2k\u008b\5\6\4\2l\u008b\5\b\5\2m\u008b\5"+
		"\n\6\2n\u008b\5\16\b\2o\u008b\5\20\t\2p\u008b\5\f\7\2q\u008b\5\32\16\2"+
		"r\u008b\5\34\17\2s\u008b\5\36\20\2t\u008b\5\22\n\2u\u008b\5\24\13\2v\u008b"+
		"\5\26\f\2w\u008b\5\30\r\2x\u008b\5 \21\2y\u008b\5\64\33\2z\u008b\5\66"+
		"\34\2{\u008b\58\35\2|\u008b\5:\36\2}\u008b\5<\37\2~\u008b\5> \2\177\u008b"+
		"\5@!\2\u0080\u008b\5B\"\2\u0081\u008b\5D#\2\u0082\u008b\5F$\2\u0083\u008b"+
		"\5H%\2\u0084\u008b\5J&\2\u0085\u008b\5L\'\2\u0086\u008b\5N(\2\u0087\u008b"+
		"\5P)\2\u0088\u008b\5R*\2\u0089\u008b\5T+\2\u008ak\3\2\2\2\u008al\3\2\2"+
		"\2\u008am\3\2\2\2\u008an\3\2\2\2\u008ao\3\2\2\2\u008ap\3\2\2\2\u008aq"+
		"\3\2\2\2\u008ar\3\2\2\2\u008as\3\2\2\2\u008at\3\2\2\2\u008au\3\2\2\2\u008a"+
		"v\3\2\2\2\u008aw\3\2\2\2\u008ax\3\2\2\2\u008ay\3\2\2\2\u008az\3\2\2\2"+
		"\u008a{\3\2\2\2\u008a|\3\2\2\2\u008a}\3\2\2\2\u008a~\3\2\2\2\u008a\177"+
		"\3\2\2\2\u008a\u0080\3\2\2\2\u008a\u0081\3\2\2\2\u008a\u0082\3\2\2\2\u008a"+
		"\u0083\3\2\2\2\u008a\u0084\3\2\2\2\u008a\u0085\3\2\2\2\u008a\u0086\3\2"+
		"\2\2\u008a\u0087\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u0089\3\2\2\2\u008b"+
		"\5\3\2\2\2\u008c\u0090\7<\2\2\u008d\u008f\7@\2\2\u008e\u008d\3\2\2\2\u008f"+
		"\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\7\3\2\2\2"+
		"\u0092\u0090\3\2\2\2\u0093\u0095\7<\2\2\u0094\u0096\7\3\2\2\u0095\u0094"+
		"\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u009a\3\2\2\2\u0097\u0099\7@\2\2\u0098"+
		"\u0097\3\2\2\2\u0099\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2"+
		"\2\2\u009b\t\3\2\2\2\u009c\u009a\3\2\2\2\u009d\u009e\7\4\2\2\u009e\u009f"+
		"\5V,\2\u009f\u00a0\5V,\2\u00a0\u00a2\5V,\2\u00a1\u00a3\5V,\2\u00a2\u00a1"+
		"\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\13\3\2\2\2\u00a4\u00a5\7\5\2\2\u00a5"+
		"\u00a7\5V,\2\u00a6\u00a8\5V,\2\u00a7\u00a6\3\2\2\2\u00a7\u00a8\3\2\2\2"+
		"\u00a8\u00aa\3\2\2\2\u00a9\u00ab\5V,\2\u00aa\u00a9\3\2\2\2\u00aa\u00ab"+
		"\3\2\2\2\u00ab\r\3\2\2\2\u00ac\u00ad\7\6\2\2\u00ad\u00ae\5V,\2\u00ae\u00af"+
		"\5V,\2\u00af\u00b0\5V,\2\u00b0\17\3\2\2\2\u00b1\u00b2\7\7\2\2\u00b2\u00b4"+
		"\5V,\2\u00b3\u00b5\5V,\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\u00b7\3\2\2\2\u00b6\u00b8\5V,\2\u00b7\u00b6\3\2\2\2\u00b7\u00b8\3\2\2"+
		"\2\u00b8\21\3\2\2\2\u00b9\u00bb\7\b\2\2\u00ba\u00bc\7\t\2\2\u00bb\u00ba"+
		"\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\t\2\2\2\u00be"+
		"\23\3\2\2\2\u00bf\u00c0\7\17\2\2\u00c0\u00c2\78\2\2\u00c1\u00c3\78\2\2"+
		"\u00c2\u00c1\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\25\3\2\2\2\u00c4\u00c5"+
		"\7\20\2\2\u00c5\u00c7\t\3\2\2\u00c6\u00c8\5V,\2\u00c7\u00c6\3\2\2\2\u00c8"+
		"\u00c9\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\27\3\2\2"+
		"\2\u00cb\u00cc\7\22\2\2\u00cc\u00ce\78\2\2\u00cd\u00cf\78\2\2\u00ce\u00cd"+
		"\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\31\3\2\2\2\u00d0\u00d2\7\23\2\2\u00d1"+
		"\u00d3\78\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d2\3\2"+
		"\2\2\u00d4\u00d5\3\2\2\2\u00d5\33\3\2\2\2\u00d6\u00d8\7\24\2\2\u00d7\u00d9"+
		"\t\4\2\2\u00d8\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00d8\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\35\3\2\2\2\u00dc\u00de\7\25\2\2\u00dd\u00df\t\5\2"+
		"\2\u00de\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1"+
		"\3\2\2\2\u00e1\37\3\2\2\2\u00e2\u00e6\5\"\22\2\u00e3\u00e6\5$\23\2\u00e4"+
		"\u00e6\5&\24\2\u00e5\u00e2\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e4\3\2"+
		"\2\2\u00e6\u00e8\3\2\2\2\u00e7\u00e9\7>\2\2\u00e8\u00e7\3\2\2\2\u00e9"+
		"\u00ea\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00fa\3\2"+
		"\2\2\u00ec\u00f2\5(\25\2\u00ed\u00f2\5*\26\2\u00ee\u00f2\5,\27\2\u00ef"+
		"\u00f2\5.\30\2\u00f0\u00f2\5\60\31\2\u00f1\u00ec\3\2\2\2\u00f1\u00ed\3"+
		"\2\2\2\u00f1\u00ee\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f0\3\2\2\2\u00f2"+
		"\u00f4\3\2\2\2\u00f3\u00f5\7>\2\2\u00f4\u00f3\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f9\3\2\2\2\u00f8"+
		"\u00f1\3\2\2\2\u00f9\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2"+
		"\2\2\u00fb\u00fd\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fd\u00fe\5\62\32\2\u00fe"+
		"!\3\2\2\2\u00ff\u0100\7\26\2\2\u0100\u0101\5V,\2\u0101\u0103\5V,\2\u0102"+
		"\u0104\78\2\2\u0103\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0103\3\2"+
		"\2\2\u0105\u0106\3\2\2\2\u0106#\3\2\2\2\u0107\u0108\7\27\2\2\u0108\u010a"+
		"\78\2\2\u0109\u010b\78\2\2\u010a\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c"+
		"\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d%\3\2\2\2\u010e\u010f\7\30\2\2"+
		"\u010f\u0110\5V,\2\u0110\u0111\5V,\2\u0111\u0112\5V,\2\u0112\u0114\5V"+
		",\2\u0113\u0115\t\5\2\2\u0114\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116"+
		"\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117\'\3\2\2\2\u0118\u0119\7\31\2"+
		"\2\u0119\u011b\t\3\2\2\u011a\u011c\5V,\2\u011b\u011a\3\2\2\2\u011c\u011d"+
		"\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e)\3\2\2\2\u011f"+
		"\u0124\7\32\2\2\u0120\u0121\5V,\2\u0121\u0122\5V,\2\u0122\u0123\78\2\2"+
		"\u0123\u0125\3\2\2\2\u0124\u0120\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0124"+
		"\3\2\2\2\u0126\u0127\3\2\2\2\u0127+\3\2\2\2\u0128\u012d\7\33\2\2\u0129"+
		"\u012a\5V,\2\u012a\u012b\5V,\2\u012b\u012c\78\2\2\u012c\u012e\3\2\2\2"+
		"\u012d\u0129\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130"+
		"\3\2\2\2\u0130-\3\2\2\2\u0131\u0136\7\34\2\2\u0132\u0133\5V,\2\u0133\u0134"+
		"\5V,\2\u0134\u0135\78\2\2\u0135\u0137\3\2\2\2\u0136\u0132\3\2\2\2\u0137"+
		"\u0138\3\2\2\2\u0138\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139/\3\2\2\2"+
		"\u013a\u013c\7\35\2\2\u013b\u013d\78\2\2\u013c\u013b\3\2\2\2\u013d\u013e"+
		"\3\2\2\2\u013e\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\61\3\2\2\2\u0140"+
		"\u0141\7\36\2\2\u0141\63\3\2\2\2\u0142\u0143\7\37\2\2\u0143\u0144\78\2"+
		"\2\u0144\u0145\5V,\2\u0145\u0146\5V,\2\u0146\u0147\78\2\2\u0147\u0148"+
		"\78\2\2\u0148\u0149\5V,\2\u0149\u014a\5V,\2\u014a\u014b\78\2\2\u014b\65"+
		"\3\2\2\2\u014c\u014e\7 \2\2\u014d\u014f\7;\2\2\u014e\u014d\3\2\2\2\u014f"+
		"\u0150\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151\67\3\2\2"+
		"\2\u0152\u0155\7!\2\2\u0153\u0156\78\2\2\u0154\u0156\7\"\2\2\u0155\u0153"+
		"\3\2\2\2\u0155\u0154\3\2\2\2\u01569\3\2\2\2\u0157\u015b\7#\2\2\u0158\u0159"+
		"\78\2\2\u0159\u015c\5V,\2\u015a\u015c\7\"\2\2\u015b\u0158\3\2\2\2\u015b"+
		"\u015a\3\2\2\2\u015c;\3\2\2\2\u015d\u015e\7$\2\2\u015e\u015f\7;\2\2\u015f"+
		"=\3\2\2\2\u0160\u0161\7%\2\2\u0161\u0162\t\6\2\2\u0162?\3\2\2\2\u0163"+
		"\u0164\7\'\2\2\u0164\u0165\t\6\2\2\u0165A\3\2\2\2\u0166\u0167\7(\2\2\u0167"+
		"\u0168\t\6\2\2\u0168C\3\2\2\2\u0169\u016a\7)\2\2\u016a\u016b\78\2\2\u016b"+
		"E\3\2\2\2\u016c\u016e\7*\2\2\u016d\u016f\7<\2\2\u016e\u016d\3\2\2\2\u016f"+
		"\u0170\3\2\2\2\u0170\u016e\3\2\2\2\u0170\u0171\3\2\2\2\u0171G\3\2\2\2"+
		"\u0172\u0175\7+\2\2\u0173\u0176\7;\2\2\u0174\u0176\7\"\2\2\u0175\u0173"+
		"\3\2\2\2\u0175\u0174\3\2\2\2\u0176I\3\2\2\2\u0177\u0178\7,\2\2\u0178\u0179"+
		"\7<\2\2\u0179K\3\2\2\2\u017a\u017b\7-\2\2\u017b\u017c\7;\2\2\u017cM\3"+
		"\2\2\2\u017d\u017e\7.\2\2\u017e\u017f\7<\2\2\u017fO\3\2\2\2\u0180\u0181"+
		"\7/\2\2\u0181\u0182\7<\2\2\u0182Q\3\2\2\2\u0183\u018c\7\60\2\2\u0184\u0185"+
		"\7\61\2\2\u0185\u018d\5V,\2\u0186\u0187\7\62\2\2\u0187\u018d\5V,\2\u0188"+
		"\u0189\7\26\2\2\u0189\u018a\5V,\2\u018a\u018b\5V,\2\u018b\u018d\3\2\2"+
		"\2\u018c\u0184\3\2\2\2\u018c\u0186\3\2\2\2\u018c\u0188\3\2\2\2\u018dS"+
		"\3\2\2\2\u018e\u019b\7\63\2\2\u018f\u0190\7\64\2\2\u0190\u0191\5V,\2\u0191"+
		"\u0192\5V,\2\u0192\u019c\3\2\2\2\u0193\u0194\7\65\2\2\u0194\u019c\5V,"+
		"\2\u0195\u0196\7\62\2\2\u0196\u019c\5V,\2\u0197\u0198\7\26\2\2\u0198\u0199"+
		"\5V,\2\u0199\u019a\5V,\2\u019a\u019c\3\2\2\2\u019b\u018f\3\2\2\2\u019b"+
		"\u0193\3\2\2\2\u019b\u0195\3\2\2\2\u019b\u0197\3\2\2\2\u019cU\3\2\2\2"+
		"\u019d\u019e\t\7\2\2\u019eW\3\2\2\2*[bei\u008a\u0090\u0095\u009a\u00a2"+
		"\u00a7\u00aa\u00b4\u00b7\u00bb\u00c2\u00c9\u00ce\u00d4\u00da\u00e0\u00e5"+
		"\u00ea\u00f1\u00f6\u00fa\u0105\u010c\u0116\u011d\u0126\u012f\u0138\u013e"+
		"\u0150\u0155\u015b\u0170\u0175\u018c\u019b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}