
// Generated from DOT.g4 by ANTLR 4.13.2

#pragma once


#include "antlr4-runtime.h"




class  DOTParser : public antlr4::Parser {
public:
  enum {
    T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, 
    T__7 = 8, T__8 = 9, T__9 = 10, STRICT = 11, GRAPH = 12, DIGRAPH = 13, 
    NODE = 14, EDGE = 15, SUBGRAPH = 16, NUMBER = 17, STRING = 18, ID = 19, 
    HTML_STRING = 20, COMMENT = 21, LINE_COMMENT = 22, PREPROC = 23, WS = 24
  };

  enum {
    RuleGraph = 0, RuleStmt_list = 1, RuleStmt = 2, RuleAttr_stmt = 3, RuleAttr_list = 4, 
    RuleA_list = 5, RuleEdge_stmt = 6, RuleEdgeRHS = 7, RuleEdgeop = 8, 
    RuleNode_stmt = 9, RuleNode_id = 10, RulePort = 11, RuleSubgraph = 12, 
    RuleId_ = 13
  };

  explicit DOTParser(antlr4::TokenStream *input);

  DOTParser(antlr4::TokenStream *input, const antlr4::atn::ParserATNSimulatorOptions &options);

  ~DOTParser() override;

  std::string getGrammarFileName() const override;

  const antlr4::atn::ATN& getATN() const override;

  const std::vector<std::string>& getRuleNames() const override;

  const antlr4::dfa::Vocabulary& getVocabulary() const override;

  antlr4::atn::SerializedATNView getSerializedATN() const override;


  class GraphContext;
  class Stmt_listContext;
  class StmtContext;
  class Attr_stmtContext;
  class Attr_listContext;
  class A_listContext;
  class Edge_stmtContext;
  class EdgeRHSContext;
  class EdgeopContext;
  class Node_stmtContext;
  class Node_idContext;
  class PortContext;
  class SubgraphContext;
  class Id_Context; 

  class  GraphContext : public antlr4::ParserRuleContext {
  public:
    GraphContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    Stmt_listContext *stmt_list();
    antlr4::tree::TerminalNode *EOF();
    antlr4::tree::TerminalNode *GRAPH();
    antlr4::tree::TerminalNode *DIGRAPH();
    antlr4::tree::TerminalNode *STRICT();
    Id_Context *id_();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  GraphContext* graph();

  class  Stmt_listContext : public antlr4::ParserRuleContext {
  public:
    Stmt_listContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<StmtContext *> stmt();
    StmtContext* stmt(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Stmt_listContext* stmt_list();

  class  StmtContext : public antlr4::ParserRuleContext {
  public:
    StmtContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    Node_stmtContext *node_stmt();
    Edge_stmtContext *edge_stmt();
    Attr_stmtContext *attr_stmt();
    std::vector<Id_Context *> id_();
    Id_Context* id_(size_t i);
    SubgraphContext *subgraph();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  StmtContext* stmt();

  class  Attr_stmtContext : public antlr4::ParserRuleContext {
  public:
    Attr_stmtContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    Attr_listContext *attr_list();
    antlr4::tree::TerminalNode *GRAPH();
    antlr4::tree::TerminalNode *NODE();
    antlr4::tree::TerminalNode *EDGE();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Attr_stmtContext* attr_stmt();

  class  Attr_listContext : public antlr4::ParserRuleContext {
  public:
    Attr_listContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<A_listContext *> a_list();
    A_listContext* a_list(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Attr_listContext* attr_list();

  class  A_listContext : public antlr4::ParserRuleContext {
  public:
    A_listContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<Id_Context *> id_();
    Id_Context* id_(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  A_listContext* a_list();

  class  Edge_stmtContext : public antlr4::ParserRuleContext {
  public:
    Edge_stmtContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    EdgeRHSContext *edgeRHS();
    Node_idContext *node_id();
    SubgraphContext *subgraph();
    Attr_listContext *attr_list();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Edge_stmtContext* edge_stmt();

  class  EdgeRHSContext : public antlr4::ParserRuleContext {
  public:
    EdgeRHSContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<EdgeopContext *> edgeop();
    EdgeopContext* edgeop(size_t i);
    std::vector<Node_idContext *> node_id();
    Node_idContext* node_id(size_t i);
    std::vector<SubgraphContext *> subgraph();
    SubgraphContext* subgraph(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  EdgeRHSContext* edgeRHS();

  class  EdgeopContext : public antlr4::ParserRuleContext {
  public:
    EdgeopContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  EdgeopContext* edgeop();

  class  Node_stmtContext : public antlr4::ParserRuleContext {
  public:
    Node_stmtContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    Node_idContext *node_id();
    Attr_listContext *attr_list();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Node_stmtContext* node_stmt();

  class  Node_idContext : public antlr4::ParserRuleContext {
  public:
    Node_idContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    Id_Context *id_();
    PortContext *port();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Node_idContext* node_id();

  class  PortContext : public antlr4::ParserRuleContext {
  public:
    PortContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    std::vector<Id_Context *> id_();
    Id_Context* id_(size_t i);

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  PortContext* port();

  class  SubgraphContext : public antlr4::ParserRuleContext {
  public:
    SubgraphContext(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    Stmt_listContext *stmt_list();
    antlr4::tree::TerminalNode *SUBGRAPH();
    Id_Context *id_();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  SubgraphContext* subgraph();

  class  Id_Context : public antlr4::ParserRuleContext {
  public:
    Id_Context(antlr4::ParserRuleContext *parent, size_t invokingState);
    virtual size_t getRuleIndex() const override;
    antlr4::tree::TerminalNode *ID();
    antlr4::tree::TerminalNode *STRING();
    antlr4::tree::TerminalNode *HTML_STRING();
    antlr4::tree::TerminalNode *NUMBER();

    virtual void enterRule(antlr4::tree::ParseTreeListener *listener) override;
    virtual void exitRule(antlr4::tree::ParseTreeListener *listener) override;
   
  };

  Id_Context* id_();


  // By default the static state used to implement the parser is lazily initialized during the first
  // call to the constructor. You can call this function if you wish to initialize the static state
  // ahead of time.
  static void initialize();

private:
};

