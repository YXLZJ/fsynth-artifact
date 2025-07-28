
// Generated from /Users/jack/fsynth-artifact/obj/WavefrontOBJ.g4 by ANTLR 4.13.2

#pragma once


#include "antlr4-runtime.h"
#include "WavefrontOBJParser.h"


/**
 * This interface defines an abstract listener for a parse tree produced by WavefrontOBJParser.
 */
class  WavefrontOBJListener : public antlr4::tree::ParseTreeListener {
public:

  virtual void enterStart_(WavefrontOBJParser::Start_Context *ctx) = 0;
  virtual void exitStart_(WavefrontOBJParser::Start_Context *ctx) = 0;

  virtual void enterStatement(WavefrontOBJParser::StatementContext *ctx) = 0;
  virtual void exitStatement(WavefrontOBJParser::StatementContext *ctx) = 0;

  virtual void enterCall(WavefrontOBJParser::CallContext *ctx) = 0;
  virtual void exitCall(WavefrontOBJParser::CallContext *ctx) = 0;

  virtual void enterCsh(WavefrontOBJParser::CshContext *ctx) = 0;
  virtual void exitCsh(WavefrontOBJParser::CshContext *ctx) = 0;

  virtual void enterVertex(WavefrontOBJParser::VertexContext *ctx) = 0;
  virtual void exitVertex(WavefrontOBJParser::VertexContext *ctx) = 0;

  virtual void enterVertex_parameter(WavefrontOBJParser::Vertex_parameterContext *ctx) = 0;
  virtual void exitVertex_parameter(WavefrontOBJParser::Vertex_parameterContext *ctx) = 0;

  virtual void enterVertex_normal(WavefrontOBJParser::Vertex_normalContext *ctx) = 0;
  virtual void exitVertex_normal(WavefrontOBJParser::Vertex_normalContext *ctx) = 0;

  virtual void enterVertex_texture(WavefrontOBJParser::Vertex_textureContext *ctx) = 0;
  virtual void exitVertex_texture(WavefrontOBJParser::Vertex_textureContext *ctx) = 0;

  virtual void enterCurve_surface_type(WavefrontOBJParser::Curve_surface_typeContext *ctx) = 0;
  virtual void exitCurve_surface_type(WavefrontOBJParser::Curve_surface_typeContext *ctx) = 0;

  virtual void enterDegree(WavefrontOBJParser::DegreeContext *ctx) = 0;
  virtual void exitDegree(WavefrontOBJParser::DegreeContext *ctx) = 0;

  virtual void enterBasis_matrix(WavefrontOBJParser::Basis_matrixContext *ctx) = 0;
  virtual void exitBasis_matrix(WavefrontOBJParser::Basis_matrixContext *ctx) = 0;

  virtual void enterStep(WavefrontOBJParser::StepContext *ctx) = 0;
  virtual void exitStep(WavefrontOBJParser::StepContext *ctx) = 0;

  virtual void enterPoints(WavefrontOBJParser::PointsContext *ctx) = 0;
  virtual void exitPoints(WavefrontOBJParser::PointsContext *ctx) = 0;

  virtual void enterLines(WavefrontOBJParser::LinesContext *ctx) = 0;
  virtual void exitLines(WavefrontOBJParser::LinesContext *ctx) = 0;

  virtual void enterFaces(WavefrontOBJParser::FacesContext *ctx) = 0;
  virtual void exitFaces(WavefrontOBJParser::FacesContext *ctx) = 0;

  virtual void enterFree_form_surface(WavefrontOBJParser::Free_form_surfaceContext *ctx) = 0;
  virtual void exitFree_form_surface(WavefrontOBJParser::Free_form_surfaceContext *ctx) = 0;

  virtual void enterCurve(WavefrontOBJParser::CurveContext *ctx) = 0;
  virtual void exitCurve(WavefrontOBJParser::CurveContext *ctx) = 0;

  virtual void enterCurve2d(WavefrontOBJParser::Curve2dContext *ctx) = 0;
  virtual void exitCurve2d(WavefrontOBJParser::Curve2dContext *ctx) = 0;

  virtual void enterSurface(WavefrontOBJParser::SurfaceContext *ctx) = 0;
  virtual void exitSurface(WavefrontOBJParser::SurfaceContext *ctx) = 0;

  virtual void enterParameter(WavefrontOBJParser::ParameterContext *ctx) = 0;
  virtual void exitParameter(WavefrontOBJParser::ParameterContext *ctx) = 0;

  virtual void enterOuter_trimming_loop(WavefrontOBJParser::Outer_trimming_loopContext *ctx) = 0;
  virtual void exitOuter_trimming_loop(WavefrontOBJParser::Outer_trimming_loopContext *ctx) = 0;

  virtual void enterInner_trimming_loop(WavefrontOBJParser::Inner_trimming_loopContext *ctx) = 0;
  virtual void exitInner_trimming_loop(WavefrontOBJParser::Inner_trimming_loopContext *ctx) = 0;

  virtual void enterSpecial_curve(WavefrontOBJParser::Special_curveContext *ctx) = 0;
  virtual void exitSpecial_curve(WavefrontOBJParser::Special_curveContext *ctx) = 0;

  virtual void enterSpecial_point(WavefrontOBJParser::Special_pointContext *ctx) = 0;
  virtual void exitSpecial_point(WavefrontOBJParser::Special_pointContext *ctx) = 0;

  virtual void enterEnd(WavefrontOBJParser::EndContext *ctx) = 0;
  virtual void exitEnd(WavefrontOBJParser::EndContext *ctx) = 0;

  virtual void enterConnectivity(WavefrontOBJParser::ConnectivityContext *ctx) = 0;
  virtual void exitConnectivity(WavefrontOBJParser::ConnectivityContext *ctx) = 0;

  virtual void enterGroup(WavefrontOBJParser::GroupContext *ctx) = 0;
  virtual void exitGroup(WavefrontOBJParser::GroupContext *ctx) = 0;

  virtual void enterSmoothing_group(WavefrontOBJParser::Smoothing_groupContext *ctx) = 0;
  virtual void exitSmoothing_group(WavefrontOBJParser::Smoothing_groupContext *ctx) = 0;

  virtual void enterMerging_group(WavefrontOBJParser::Merging_groupContext *ctx) = 0;
  virtual void exitMerging_group(WavefrontOBJParser::Merging_groupContext *ctx) = 0;

  virtual void enterObject_name(WavefrontOBJParser::Object_nameContext *ctx) = 0;
  virtual void exitObject_name(WavefrontOBJParser::Object_nameContext *ctx) = 0;

  virtual void enterBevel(WavefrontOBJParser::BevelContext *ctx) = 0;
  virtual void exitBevel(WavefrontOBJParser::BevelContext *ctx) = 0;

  virtual void enterColor_interpolation(WavefrontOBJParser::Color_interpolationContext *ctx) = 0;
  virtual void exitColor_interpolation(WavefrontOBJParser::Color_interpolationContext *ctx) = 0;

  virtual void enterDissolve_interpolation(WavefrontOBJParser::Dissolve_interpolationContext *ctx) = 0;
  virtual void exitDissolve_interpolation(WavefrontOBJParser::Dissolve_interpolationContext *ctx) = 0;

  virtual void enterLevel_of_detail(WavefrontOBJParser::Level_of_detailContext *ctx) = 0;
  virtual void exitLevel_of_detail(WavefrontOBJParser::Level_of_detailContext *ctx) = 0;

  virtual void enterMap_library(WavefrontOBJParser::Map_libraryContext *ctx) = 0;
  virtual void exitMap_library(WavefrontOBJParser::Map_libraryContext *ctx) = 0;

  virtual void enterUse_map(WavefrontOBJParser::Use_mapContext *ctx) = 0;
  virtual void exitUse_map(WavefrontOBJParser::Use_mapContext *ctx) = 0;

  virtual void enterMaterial_library(WavefrontOBJParser::Material_libraryContext *ctx) = 0;
  virtual void exitMaterial_library(WavefrontOBJParser::Material_libraryContext *ctx) = 0;

  virtual void enterUse_material(WavefrontOBJParser::Use_materialContext *ctx) = 0;
  virtual void exitUse_material(WavefrontOBJParser::Use_materialContext *ctx) = 0;

  virtual void enterShadow_object(WavefrontOBJParser::Shadow_objectContext *ctx) = 0;
  virtual void exitShadow_object(WavefrontOBJParser::Shadow_objectContext *ctx) = 0;

  virtual void enterTrace_object(WavefrontOBJParser::Trace_objectContext *ctx) = 0;
  virtual void exitTrace_object(WavefrontOBJParser::Trace_objectContext *ctx) = 0;

  virtual void enterCurve_approximation_technique(WavefrontOBJParser::Curve_approximation_techniqueContext *ctx) = 0;
  virtual void exitCurve_approximation_technique(WavefrontOBJParser::Curve_approximation_techniqueContext *ctx) = 0;

  virtual void enterSurface_approximation_technique(WavefrontOBJParser::Surface_approximation_techniqueContext *ctx) = 0;
  virtual void exitSurface_approximation_technique(WavefrontOBJParser::Surface_approximation_techniqueContext *ctx) = 0;

  virtual void enterDecimal(WavefrontOBJParser::DecimalContext *ctx) = 0;
  virtual void exitDecimal(WavefrontOBJParser::DecimalContext *ctx) = 0;


};

