
// Generated from /Users/jack/fsynth-artifact/obj/WavefrontOBJ.g4 by ANTLR 4.13.2

#pragma once


#include "antlr4-runtime.h"




class  WavefrontOBJParser : public antlr4::Parser {
public:
  enum {
    T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, 
    T__7 = 8, T__8 = 9, T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, 
    T__14 = 15, T__15 = 16, T__16 = 17, T__17 = 18, T__18 = 19, T__19 = 20, 
    T__20 = 21, T__21 = 22, T__22 = 23, T__23 = 24, T__24 = 25, T__25 = 26, 
    T__26 = 27, T__27 = 28, T__28 = 29, T__29 = 30, T__30 = 31, T__31 = 32, 
    T__32 = 33, T__33 = 34, T__34 = 35, T__35 = 36, T__36 = 37, T__37 = 38, 
    T__38 = 39, T__39 = 40, T__40 = 41, T__41 = 42, T__42 = 43, T__43 = 44, 
    T__44 = 45, T__45 = 46, T__46 = 47, T__47 = 48, T__48 = 49, T__49 = 50, 
    T__50 = 51, INTEGER_PAIR = 52, INTEGER_TRIPLET = 53, INTEGER = 54, DECIMAL = 55, 
    COMMENT = 56, NAME = 57, FILENAME = 58, WS = 59, NL = 60, NON_NL = 61, 
    NON_WS = 62
  };

  enum {
    RuleStart_ = 0, RuleStatement = 1, RuleCall = 2, RuleCsh = 3, RuleVertex = 4, 
    RuleVertex_parameter = 5, RuleVertex_normal = 6, RuleVertex_texture = 7, 
    RuleCurve_surface_type = 8, RuleDegree = 9, RuleBasis_matrix = 10, RuleStep = 11, 
    RulePoints = 12, RuleLines = 13, RuleFaces = 14, RuleFree_form_surface = 15, 
    RuleCurve = 16, RuleCurve2d = 17, RuleSurface = 18, RuleParameter = 19, 
    RuleOuter_trimming_loop = 20, RuleInner_trimming_loop = 21, RuleSpecial_curve = 22, 
    RuleSpecial_point = 23, RuleEnd = 24, RuleConnectivity = 25, RuleGroup = 26, 
    RuleSmoothing_group = 27, RuleMerging_group = 28, RuleObject_name = 29, 
    RuleBevel = 30, RuleColor_interpolation = 31, RuleDissolve_interpolation = 32, 
    RuleLevel_of_detail = 33, RuleMap_library = 34, RuleUse_map = 35, RuleMaterial_library = 36, 
    RuleUse_material = 37, RuleShadow_object = 38, RuleTrace_object = 39, 
    RuleCurve_approximation_technique = 40, RuleSurface_approximation_technique = 41, 
    RuleDecimal = 42
  };

  explicit WavefrontOBJParser(antlr4::TokenStream *input);

  WavefrontOBJParser(antlr4::TokenStream *input, const antlr4::atn::ParserATNSimulatorOptions &options);

  ~WavefrontOBJParser() override;

  std::string getGrammarFileName() const override;

  const antlr4::atn::ATN& getATN() const override;

  const std::vector<std::string>& getRuleNames() const override;

  const antlr4::dfa::Vocabulary& getVocabulary() const override;

  antlr4::atn::SerializedATNView getSerializedATN() const override;


  class Start_Context;
  class StatementContext;
  class CallContext;
  class CshContext;
  class VertexContext;
  class Vertex_parameterContext;
  class Vertex_normalContext;
  class Vertex_textureContext;
  class Curve_surface_typeContext;
  class DegreeContext;
  class Basis_matrixContext;
  class StepContext;
  class PointsContext;
  class LinesContext;
  class FacesContext;
  class Free_form_surfaceContext;
  class CurveContext;
  class Curve2dContext;
  class SurfaceContext;
  class ParameterContext;
  class Outer_trimming_loopContext;
  class Inner_trimming_loopContext;
  class Special_curveContext;
  class Special_pointContext;
  class EndContext;
  class ConnectivityContext;
  class GroupContext;
  class Smoothing_groupContext;
  class Merging_groupContext;
  class Object_nameContext;
  class BevelContext;
  class Color_interpolationContext;
  class Dissolve_interpolationContext;
  class Level_of_detailContext;
  class Map_libraryContext;
  class Use_mapContext;
  class Material_libraryContext;
  class Use_materialContext;
  class Shadow_objectContext;
  class Trace_objectContext;
  class Curve_approximation_techniqueContext;
  class Surface_approximation_techniqueContext;
  class DecimalContext; 

  class  Start_Context : public antlr4::ParserRuleContext {
  public:
    Start_Context(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<antlr4::tree::TerminalNode *> NL();
    antlr4::tree::TerminalNode* NL(size_t i);
    std::vector<StatementContext *> statement();
    StatementContext* statement(size_t i);
    std::vector<antlr4::tree::TerminalNode *> EOF();
    antlr4::tree::TerminalNode* EOF(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Start_Context* start_();

  class  StatementContext : public antlr4::ParserRuleContext {
  public:
    StatementContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    CallContext *call();
    CshContext *csh();
    VertexContext *vertex();
    Vertex_normalContext *vertex_normal();
    Vertex_textureContext *vertex_texture();
    Vertex_parameterContext *vertex_parameter();
    PointsContext *points();
    LinesContext *lines();
    FacesContext *faces();
    Curve_surface_typeContext *curve_surface_type();
    DegreeContext *degree();
    Basis_matrixContext *basis_matrix();
    StepContext *step();
    Free_form_surfaceContext *free_form_surface();
    ConnectivityContext *connectivity();
    GroupContext *group();
    Smoothing_groupContext *smoothing_group();
    Merging_groupContext *merging_group();
    Object_nameContext *object_name();
    BevelContext *bevel();
    Color_interpolationContext *color_interpolation();
    Dissolve_interpolationContext *dissolve_interpolation();
    Level_of_detailContext *level_of_detail();
    Map_libraryContext *map_library();
    Use_mapContext *use_map();
    Material_libraryContext *material_library();
    Use_materialContext *use_material();
    Shadow_objectContext *shadow_object();
    Trace_objectContext *trace_object();
    Curve_approximation_techniqueContext *curve_approximation_technique();
    Surface_approximation_techniqueContext *surface_approximation_technique();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  StatementContext* statement();

  class  CallContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *filename = nullptr;
    antlr4::Token *args = nullptr;
    CallContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *FILENAME();
    std::vector<antlr4::tree::TerminalNode *> NON_WS();
    antlr4::tree::TerminalNode* NON_WS(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  CallContext* call();

  class  CshContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *command = nullptr;
    antlr4::Token *args = nullptr;
    CshContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *FILENAME();
    std::vector<antlr4::tree::TerminalNode *> NON_WS();
    antlr4::tree::TerminalNode* NON_WS(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  CshContext* csh();

  class  VertexContext : public antlr4::ParserRuleContext {
  public:
    WavefrontOBJParser::DecimalContext *x = nullptr;
    WavefrontOBJParser::DecimalContext *y = nullptr;
    WavefrontOBJParser::DecimalContext *z = nullptr;
    WavefrontOBJParser::DecimalContext *w = nullptr;
    VertexContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<DecimalContext *> decimal();
    DecimalContext* decimal(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  VertexContext* vertex();

  class  Vertex_parameterContext : public antlr4::ParserRuleContext {
  public:
    WavefrontOBJParser::DecimalContext *u = nullptr;
    WavefrontOBJParser::DecimalContext *v = nullptr;
    WavefrontOBJParser::DecimalContext *w = nullptr;
    Vertex_parameterContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<DecimalContext *> decimal();
    DecimalContext* decimal(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Vertex_parameterContext* vertex_parameter();

  class  Vertex_normalContext : public antlr4::ParserRuleContext {
  public:
    WavefrontOBJParser::DecimalContext *x = nullptr;
    WavefrontOBJParser::DecimalContext *y = nullptr;
    WavefrontOBJParser::DecimalContext *z = nullptr;
    Vertex_normalContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<DecimalContext *> decimal();
    DecimalContext* decimal(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Vertex_normalContext* vertex_normal();

  class  Vertex_textureContext : public antlr4::ParserRuleContext {
  public:
    WavefrontOBJParser::DecimalContext *u = nullptr;
    WavefrontOBJParser::DecimalContext *v = nullptr;
    WavefrontOBJParser::DecimalContext *w = nullptr;
    Vertex_textureContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<DecimalContext *> decimal();
    DecimalContext* decimal(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Vertex_textureContext* vertex_texture();

  class  Curve_surface_typeContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *rational = nullptr;
    antlr4::Token *cstype = nullptr;
    Curve_surface_typeContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Curve_surface_typeContext* curve_surface_type();

  class  DegreeContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *u = nullptr;
    antlr4::Token *v = nullptr;
    DegreeContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<antlr4::tree::TerminalNode *> INTEGER();
    antlr4::tree::TerminalNode* INTEGER(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  DegreeContext* degree();

  class  Basis_matrixContext : public antlr4::ParserRuleContext {
  public:
    Basis_matrixContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<DecimalContext *> decimal();
    DecimalContext* decimal(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Basis_matrixContext* basis_matrix();

  class  StepContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *u = nullptr;
    antlr4::Token *v = nullptr;
    StepContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<antlr4::tree::TerminalNode *> INTEGER();
    antlr4::tree::TerminalNode* INTEGER(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  StepContext* step();

  class  PointsContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *v = nullptr;
    PointsContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<antlr4::tree::TerminalNode *> INTEGER();
    antlr4::tree::TerminalNode* INTEGER(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  PointsContext* points();

  class  LinesContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *v = nullptr;
    LinesContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<antlr4::tree::TerminalNode *> INTEGER();
    antlr4::tree::TerminalNode* INTEGER(size_t i);
    std::vector<antlr4::tree::TerminalNode *> INTEGER_PAIR();
    antlr4::tree::TerminalNode* INTEGER_PAIR(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  LinesContext* lines();

  class  FacesContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *v = nullptr;
    FacesContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<antlr4::tree::TerminalNode *> INTEGER();
    antlr4::tree::TerminalNode* INTEGER(size_t i);
    std::vector<antlr4::tree::TerminalNode *> INTEGER_PAIR();
    antlr4::tree::TerminalNode* INTEGER_PAIR(size_t i);
    std::vector<antlr4::tree::TerminalNode *> INTEGER_TRIPLET();
    antlr4::tree::TerminalNode* INTEGER_TRIPLET(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  FacesContext* faces();

  class  Free_form_surfaceContext : public antlr4::ParserRuleContext {
  public:
    Free_form_surfaceContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    EndContext *end();
    CurveContext *curve();
    Curve2dContext *curve2d();
    SurfaceContext *surface();
    std::vector<antlr4::tree::TerminalNode *> NL();
    antlr4::tree::TerminalNode* NL(size_t i);
    std::vector<ParameterContext *> parameter();
    ParameterContext* parameter(size_t i);
    std::vector<Outer_trimming_loopContext *> outer_trimming_loop();
    Outer_trimming_loopContext* outer_trimming_loop(size_t i);
    std::vector<Inner_trimming_loopContext *> inner_trimming_loop();
    Inner_trimming_loopContext* inner_trimming_loop(size_t i);
    std::vector<Special_curveContext *> special_curve();
    Special_curveContext* special_curve(size_t i);
    std::vector<Special_pointContext *> special_point();
    Special_pointContext* special_point(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Free_form_surfaceContext* free_form_surface();

  class  CurveContext : public antlr4::ParserRuleContext {
  public:
    WavefrontOBJParser::DecimalContext *u0 = nullptr;
    WavefrontOBJParser::DecimalContext *u1 = nullptr;
    antlr4::Token *v = nullptr;
    CurveContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<DecimalContext *> decimal();
    DecimalContext* decimal(size_t i);
    std::vector<antlr4::tree::TerminalNode *> INTEGER();
    antlr4::tree::TerminalNode* INTEGER(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  CurveContext* curve();

  class  Curve2dContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *vp1 = nullptr;
    antlr4::Token *vp2 = nullptr;
    Curve2dContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<antlr4::tree::TerminalNode *> INTEGER();
    antlr4::tree::TerminalNode* INTEGER(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Curve2dContext* curve2d();

  class  SurfaceContext : public antlr4::ParserRuleContext {
  public:
    WavefrontOBJParser::DecimalContext *s0 = nullptr;
    WavefrontOBJParser::DecimalContext *s1 = nullptr;
    WavefrontOBJParser::DecimalContext *t0 = nullptr;
    WavefrontOBJParser::DecimalContext *t1 = nullptr;
    antlr4::Token *v = nullptr;
    SurfaceContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<DecimalContext *> decimal();
    DecimalContext* decimal(size_t i);
    std::vector<antlr4::tree::TerminalNode *> INTEGER();
    antlr4::tree::TerminalNode* INTEGER(size_t i);
    std::vector<antlr4::tree::TerminalNode *> INTEGER_PAIR();
    antlr4::tree::TerminalNode* INTEGER_PAIR(size_t i);
    std::vector<antlr4::tree::TerminalNode *> INTEGER_TRIPLET();
    antlr4::tree::TerminalNode* INTEGER_TRIPLET(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  SurfaceContext* surface();

  class  ParameterContext : public antlr4::ParserRuleContext {
  public:
    WavefrontOBJParser::DecimalContext *p = nullptr;
    ParameterContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<DecimalContext *> decimal();
    DecimalContext* decimal(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  ParameterContext* parameter();

  class  Outer_trimming_loopContext : public antlr4::ParserRuleContext {
  public:
    WavefrontOBJParser::DecimalContext *u0 = nullptr;
    WavefrontOBJParser::DecimalContext *u1 = nullptr;
    antlr4::Token *curv2d = nullptr;
    Outer_trimming_loopContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<DecimalContext *> decimal();
    DecimalContext* decimal(size_t i);
    std::vector<antlr4::tree::TerminalNode *> INTEGER();
    antlr4::tree::TerminalNode* INTEGER(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Outer_trimming_loopContext* outer_trimming_loop();

  class  Inner_trimming_loopContext : public antlr4::ParserRuleContext {
  public:
    WavefrontOBJParser::DecimalContext *u0 = nullptr;
    WavefrontOBJParser::DecimalContext *u1 = nullptr;
    antlr4::Token *curv2d = nullptr;
    Inner_trimming_loopContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<DecimalContext *> decimal();
    DecimalContext* decimal(size_t i);
    std::vector<antlr4::tree::TerminalNode *> INTEGER();
    antlr4::tree::TerminalNode* INTEGER(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Inner_trimming_loopContext* inner_trimming_loop();

  class  Special_curveContext : public antlr4::ParserRuleContext {
  public:
    WavefrontOBJParser::DecimalContext *u0 = nullptr;
    WavefrontOBJParser::DecimalContext *u1 = nullptr;
    antlr4::Token *curv2d = nullptr;
    Special_curveContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<DecimalContext *> decimal();
    DecimalContext* decimal(size_t i);
    std::vector<antlr4::tree::TerminalNode *> INTEGER();
    antlr4::tree::TerminalNode* INTEGER(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Special_curveContext* special_curve();

  class  Special_pointContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *vp = nullptr;
    Special_pointContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<antlr4::tree::TerminalNode *> INTEGER();
    antlr4::tree::TerminalNode* INTEGER(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Special_pointContext* special_point();

  class  EndContext : public antlr4::ParserRuleContext {
  public:
    EndContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  EndContext* end();

  class  ConnectivityContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *surf_1 = nullptr;
    WavefrontOBJParser::DecimalContext *q0_1 = nullptr;
    WavefrontOBJParser::DecimalContext *q1_1 = nullptr;
    antlr4::Token *curv2d_1 = nullptr;
    antlr4::Token *surf_2 = nullptr;
    WavefrontOBJParser::DecimalContext *q0_2 = nullptr;
    WavefrontOBJParser::DecimalContext *q1_2 = nullptr;
    antlr4::Token *curv2d_2 = nullptr;
    ConnectivityContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<antlr4::tree::TerminalNode *> INTEGER();
    antlr4::tree::TerminalNode* INTEGER(size_t i);
    std::vector<DecimalContext *> decimal();
    DecimalContext* decimal(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  ConnectivityContext* connectivity();

  class  GroupContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *group_name = nullptr;
    GroupContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<antlr4::tree::TerminalNode *> NAME();
    antlr4::tree::TerminalNode* NAME(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  GroupContext* group();

  class  Smoothing_groupContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *group_number = nullptr;
    Smoothing_groupContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *INTEGER();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Smoothing_groupContext* smoothing_group();

  class  Merging_groupContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *group_number = nullptr;
    WavefrontOBJParser::DecimalContext *res = nullptr;
    Merging_groupContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *INTEGER();
    DecimalContext *decimal();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Merging_groupContext* merging_group();

  class  Object_nameContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *name = nullptr;
    Object_nameContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *NAME();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Object_nameContext* object_name();

  class  BevelContext : public antlr4::ParserRuleContext {
  public:
    BevelContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  BevelContext* bevel();

  class  Color_interpolationContext : public antlr4::ParserRuleContext {
  public:
    Color_interpolationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Color_interpolationContext* color_interpolation();

  class  Dissolve_interpolationContext : public antlr4::ParserRuleContext {
  public:
    Dissolve_interpolationContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Dissolve_interpolationContext* dissolve_interpolation();

  class  Level_of_detailContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *level = nullptr;
    Level_of_detailContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *INTEGER();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Level_of_detailContext* level_of_detail();

  class  Map_libraryContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *filename = nullptr;
    Map_libraryContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<antlr4::tree::TerminalNode *> FILENAME();
    antlr4::tree::TerminalNode* FILENAME(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Map_libraryContext* map_library();

  class  Use_mapContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *map_name = nullptr;
    Use_mapContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *NAME();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Use_mapContext* use_map();

  class  Material_libraryContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *filename = nullptr;
    Material_libraryContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *FILENAME();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Material_libraryContext* material_library();

  class  Use_materialContext : public antlr4::ParserRuleContext {
  public:
    Use_materialContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *NAME();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Use_materialContext* use_material();

  class  Shadow_objectContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *filename = nullptr;
    Shadow_objectContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *FILENAME();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Shadow_objectContext* shadow_object();

  class  Trace_objectContext : public antlr4::ParserRuleContext {
  public:
    antlr4::Token *filename = nullptr;
    Trace_objectContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *FILENAME();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Trace_objectContext* trace_object();

  class  Curve_approximation_techniqueContext : public antlr4::ParserRuleContext {
  public:
    WavefrontOBJParser::DecimalContext *res = nullptr;
    WavefrontOBJParser::DecimalContext *maxlength = nullptr;
    WavefrontOBJParser::DecimalContext *maxdist = nullptr;
    WavefrontOBJParser::DecimalContext *maxangle = nullptr;
    Curve_approximation_techniqueContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<DecimalContext *> decimal();
    DecimalContext* decimal(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Curve_approximation_techniqueContext* curve_approximation_technique();

  class  Surface_approximation_techniqueContext : public antlr4::ParserRuleContext {
  public:
    WavefrontOBJParser::DecimalContext *ures = nullptr;
    WavefrontOBJParser::DecimalContext *vres = nullptr;
    WavefrontOBJParser::DecimalContext *uvres = nullptr;
    WavefrontOBJParser::DecimalContext *maxlength = nullptr;
    WavefrontOBJParser::DecimalContext *maxdist = nullptr;
    WavefrontOBJParser::DecimalContext *maxangle = nullptr;
    Surface_approximation_techniqueContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<DecimalContext *> decimal();
    DecimalContext* decimal(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Surface_approximation_techniqueContext* surface_approximation_technique();

  class  DecimalContext : public antlr4::ParserRuleContext {
  public:
    DecimalContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *DECIMAL();
    antlr4::tree::TerminalNode *INTEGER();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  DecimalContext* decimal();


  // By default the static state used to implement the parser is lazily initialized during the first
  // call to the constructor. You can call this function if you wish to initialize the static state
  // ahead of time.
  static void initialize();

private:
};

