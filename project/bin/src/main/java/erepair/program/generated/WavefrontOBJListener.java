// Generated from WavefrontOBJ.g4 by ANTLR 4.9.2
package erepair.program.generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link WavefrontOBJParser}.
 */
public interface WavefrontOBJListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#start_}.
	 * @param ctx the parse tree
	 */
	void enterStart_(WavefrontOBJParser.Start_Context ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#start_}.
	 * @param ctx the parse tree
	 */
	void exitStart_(WavefrontOBJParser.Start_Context ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(WavefrontOBJParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(WavefrontOBJParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(WavefrontOBJParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(WavefrontOBJParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#csh}.
	 * @param ctx the parse tree
	 */
	void enterCsh(WavefrontOBJParser.CshContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#csh}.
	 * @param ctx the parse tree
	 */
	void exitCsh(WavefrontOBJParser.CshContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#vertex}.
	 * @param ctx the parse tree
	 */
	void enterVertex(WavefrontOBJParser.VertexContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#vertex}.
	 * @param ctx the parse tree
	 */
	void exitVertex(WavefrontOBJParser.VertexContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#vertex_parameter}.
	 * @param ctx the parse tree
	 */
	void enterVertex_parameter(WavefrontOBJParser.Vertex_parameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#vertex_parameter}.
	 * @param ctx the parse tree
	 */
	void exitVertex_parameter(WavefrontOBJParser.Vertex_parameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#vertex_normal}.
	 * @param ctx the parse tree
	 */
	void enterVertex_normal(WavefrontOBJParser.Vertex_normalContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#vertex_normal}.
	 * @param ctx the parse tree
	 */
	void exitVertex_normal(WavefrontOBJParser.Vertex_normalContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#vertex_texture}.
	 * @param ctx the parse tree
	 */
	void enterVertex_texture(WavefrontOBJParser.Vertex_textureContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#vertex_texture}.
	 * @param ctx the parse tree
	 */
	void exitVertex_texture(WavefrontOBJParser.Vertex_textureContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#curve_surface_type}.
	 * @param ctx the parse tree
	 */
	void enterCurve_surface_type(WavefrontOBJParser.Curve_surface_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#curve_surface_type}.
	 * @param ctx the parse tree
	 */
	void exitCurve_surface_type(WavefrontOBJParser.Curve_surface_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#degree}.
	 * @param ctx the parse tree
	 */
	void enterDegree(WavefrontOBJParser.DegreeContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#degree}.
	 * @param ctx the parse tree
	 */
	void exitDegree(WavefrontOBJParser.DegreeContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#basis_matrix}.
	 * @param ctx the parse tree
	 */
	void enterBasis_matrix(WavefrontOBJParser.Basis_matrixContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#basis_matrix}.
	 * @param ctx the parse tree
	 */
	void exitBasis_matrix(WavefrontOBJParser.Basis_matrixContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#step}.
	 * @param ctx the parse tree
	 */
	void enterStep(WavefrontOBJParser.StepContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#step}.
	 * @param ctx the parse tree
	 */
	void exitStep(WavefrontOBJParser.StepContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#points}.
	 * @param ctx the parse tree
	 */
	void enterPoints(WavefrontOBJParser.PointsContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#points}.
	 * @param ctx the parse tree
	 */
	void exitPoints(WavefrontOBJParser.PointsContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#lines}.
	 * @param ctx the parse tree
	 */
	void enterLines(WavefrontOBJParser.LinesContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#lines}.
	 * @param ctx the parse tree
	 */
	void exitLines(WavefrontOBJParser.LinesContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#faces}.
	 * @param ctx the parse tree
	 */
	void enterFaces(WavefrontOBJParser.FacesContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#faces}.
	 * @param ctx the parse tree
	 */
	void exitFaces(WavefrontOBJParser.FacesContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#free_form_surface}.
	 * @param ctx the parse tree
	 */
	void enterFree_form_surface(WavefrontOBJParser.Free_form_surfaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#free_form_surface}.
	 * @param ctx the parse tree
	 */
	void exitFree_form_surface(WavefrontOBJParser.Free_form_surfaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#curve}.
	 * @param ctx the parse tree
	 */
	void enterCurve(WavefrontOBJParser.CurveContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#curve}.
	 * @param ctx the parse tree
	 */
	void exitCurve(WavefrontOBJParser.CurveContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#curve2d}.
	 * @param ctx the parse tree
	 */
	void enterCurve2d(WavefrontOBJParser.Curve2dContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#curve2d}.
	 * @param ctx the parse tree
	 */
	void exitCurve2d(WavefrontOBJParser.Curve2dContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#surface}.
	 * @param ctx the parse tree
	 */
	void enterSurface(WavefrontOBJParser.SurfaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#surface}.
	 * @param ctx the parse tree
	 */
	void exitSurface(WavefrontOBJParser.SurfaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(WavefrontOBJParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(WavefrontOBJParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#outer_trimming_loop}.
	 * @param ctx the parse tree
	 */
	void enterOuter_trimming_loop(WavefrontOBJParser.Outer_trimming_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#outer_trimming_loop}.
	 * @param ctx the parse tree
	 */
	void exitOuter_trimming_loop(WavefrontOBJParser.Outer_trimming_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#inner_trimming_loop}.
	 * @param ctx the parse tree
	 */
	void enterInner_trimming_loop(WavefrontOBJParser.Inner_trimming_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#inner_trimming_loop}.
	 * @param ctx the parse tree
	 */
	void exitInner_trimming_loop(WavefrontOBJParser.Inner_trimming_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#special_curve}.
	 * @param ctx the parse tree
	 */
	void enterSpecial_curve(WavefrontOBJParser.Special_curveContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#special_curve}.
	 * @param ctx the parse tree
	 */
	void exitSpecial_curve(WavefrontOBJParser.Special_curveContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#special_point}.
	 * @param ctx the parse tree
	 */
	void enterSpecial_point(WavefrontOBJParser.Special_pointContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#special_point}.
	 * @param ctx the parse tree
	 */
	void exitSpecial_point(WavefrontOBJParser.Special_pointContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#end}.
	 * @param ctx the parse tree
	 */
	void enterEnd(WavefrontOBJParser.EndContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#end}.
	 * @param ctx the parse tree
	 */
	void exitEnd(WavefrontOBJParser.EndContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#connectivity}.
	 * @param ctx the parse tree
	 */
	void enterConnectivity(WavefrontOBJParser.ConnectivityContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#connectivity}.
	 * @param ctx the parse tree
	 */
	void exitConnectivity(WavefrontOBJParser.ConnectivityContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#group}.
	 * @param ctx the parse tree
	 */
	void enterGroup(WavefrontOBJParser.GroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#group}.
	 * @param ctx the parse tree
	 */
	void exitGroup(WavefrontOBJParser.GroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#smoothing_group}.
	 * @param ctx the parse tree
	 */
	void enterSmoothing_group(WavefrontOBJParser.Smoothing_groupContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#smoothing_group}.
	 * @param ctx the parse tree
	 */
	void exitSmoothing_group(WavefrontOBJParser.Smoothing_groupContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#merging_group}.
	 * @param ctx the parse tree
	 */
	void enterMerging_group(WavefrontOBJParser.Merging_groupContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#merging_group}.
	 * @param ctx the parse tree
	 */
	void exitMerging_group(WavefrontOBJParser.Merging_groupContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#object_name}.
	 * @param ctx the parse tree
	 */
	void enterObject_name(WavefrontOBJParser.Object_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#object_name}.
	 * @param ctx the parse tree
	 */
	void exitObject_name(WavefrontOBJParser.Object_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#bevel}.
	 * @param ctx the parse tree
	 */
	void enterBevel(WavefrontOBJParser.BevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#bevel}.
	 * @param ctx the parse tree
	 */
	void exitBevel(WavefrontOBJParser.BevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#color_interpolation}.
	 * @param ctx the parse tree
	 */
	void enterColor_interpolation(WavefrontOBJParser.Color_interpolationContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#color_interpolation}.
	 * @param ctx the parse tree
	 */
	void exitColor_interpolation(WavefrontOBJParser.Color_interpolationContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#dissolve_interpolation}.
	 * @param ctx the parse tree
	 */
	void enterDissolve_interpolation(WavefrontOBJParser.Dissolve_interpolationContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#dissolve_interpolation}.
	 * @param ctx the parse tree
	 */
	void exitDissolve_interpolation(WavefrontOBJParser.Dissolve_interpolationContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#level_of_detail}.
	 * @param ctx the parse tree
	 */
	void enterLevel_of_detail(WavefrontOBJParser.Level_of_detailContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#level_of_detail}.
	 * @param ctx the parse tree
	 */
	void exitLevel_of_detail(WavefrontOBJParser.Level_of_detailContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#map_library}.
	 * @param ctx the parse tree
	 */
	void enterMap_library(WavefrontOBJParser.Map_libraryContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#map_library}.
	 * @param ctx the parse tree
	 */
	void exitMap_library(WavefrontOBJParser.Map_libraryContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#use_map}.
	 * @param ctx the parse tree
	 */
	void enterUse_map(WavefrontOBJParser.Use_mapContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#use_map}.
	 * @param ctx the parse tree
	 */
	void exitUse_map(WavefrontOBJParser.Use_mapContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#material_library}.
	 * @param ctx the parse tree
	 */
	void enterMaterial_library(WavefrontOBJParser.Material_libraryContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#material_library}.
	 * @param ctx the parse tree
	 */
	void exitMaterial_library(WavefrontOBJParser.Material_libraryContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#use_material}.
	 * @param ctx the parse tree
	 */
	void enterUse_material(WavefrontOBJParser.Use_materialContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#use_material}.
	 * @param ctx the parse tree
	 */
	void exitUse_material(WavefrontOBJParser.Use_materialContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#shadow_object}.
	 * @param ctx the parse tree
	 */
	void enterShadow_object(WavefrontOBJParser.Shadow_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#shadow_object}.
	 * @param ctx the parse tree
	 */
	void exitShadow_object(WavefrontOBJParser.Shadow_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#trace_object}.
	 * @param ctx the parse tree
	 */
	void enterTrace_object(WavefrontOBJParser.Trace_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#trace_object}.
	 * @param ctx the parse tree
	 */
	void exitTrace_object(WavefrontOBJParser.Trace_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#curve_approximation_technique}.
	 * @param ctx the parse tree
	 */
	void enterCurve_approximation_technique(WavefrontOBJParser.Curve_approximation_techniqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#curve_approximation_technique}.
	 * @param ctx the parse tree
	 */
	void exitCurve_approximation_technique(WavefrontOBJParser.Curve_approximation_techniqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#surface_approximation_technique}.
	 * @param ctx the parse tree
	 */
	void enterSurface_approximation_technique(WavefrontOBJParser.Surface_approximation_techniqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#surface_approximation_technique}.
	 * @param ctx the parse tree
	 */
	void exitSurface_approximation_technique(WavefrontOBJParser.Surface_approximation_techniqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link WavefrontOBJParser#decimal}.
	 * @param ctx the parse tree
	 */
	void enterDecimal(WavefrontOBJParser.DecimalContext ctx);
	/**
	 * Exit a parse tree produced by {@link WavefrontOBJParser#decimal}.
	 * @param ctx the parse tree
	 */
	void exitDecimal(WavefrontOBJParser.DecimalContext ctx);
}