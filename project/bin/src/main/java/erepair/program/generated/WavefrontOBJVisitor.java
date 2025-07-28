// Generated from WavefrontOBJ.g4 by ANTLR 4.9.2
package erepair.program.generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link WavefrontOBJParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface WavefrontOBJVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#start_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart_(WavefrontOBJParser.Start_Context ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(WavefrontOBJParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(WavefrontOBJParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#csh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsh(WavefrontOBJParser.CshContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#vertex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVertex(WavefrontOBJParser.VertexContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#vertex_parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVertex_parameter(WavefrontOBJParser.Vertex_parameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#vertex_normal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVertex_normal(WavefrontOBJParser.Vertex_normalContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#vertex_texture}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVertex_texture(WavefrontOBJParser.Vertex_textureContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#curve_surface_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurve_surface_type(WavefrontOBJParser.Curve_surface_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#degree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDegree(WavefrontOBJParser.DegreeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#basis_matrix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasis_matrix(WavefrontOBJParser.Basis_matrixContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#step}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStep(WavefrontOBJParser.StepContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#points}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPoints(WavefrontOBJParser.PointsContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#lines}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLines(WavefrontOBJParser.LinesContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#faces}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFaces(WavefrontOBJParser.FacesContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#free_form_surface}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFree_form_surface(WavefrontOBJParser.Free_form_surfaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#curve}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurve(WavefrontOBJParser.CurveContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#curve2d}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurve2d(WavefrontOBJParser.Curve2dContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#surface}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSurface(WavefrontOBJParser.SurfaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(WavefrontOBJParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#outer_trimming_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOuter_trimming_loop(WavefrontOBJParser.Outer_trimming_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#inner_trimming_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInner_trimming_loop(WavefrontOBJParser.Inner_trimming_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#special_curve}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecial_curve(WavefrontOBJParser.Special_curveContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#special_point}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecial_point(WavefrontOBJParser.Special_pointContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#end}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnd(WavefrontOBJParser.EndContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#connectivity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConnectivity(WavefrontOBJParser.ConnectivityContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroup(WavefrontOBJParser.GroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#smoothing_group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSmoothing_group(WavefrontOBJParser.Smoothing_groupContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#merging_group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMerging_group(WavefrontOBJParser.Merging_groupContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#object_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject_name(WavefrontOBJParser.Object_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#bevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBevel(WavefrontOBJParser.BevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#color_interpolation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColor_interpolation(WavefrontOBJParser.Color_interpolationContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#dissolve_interpolation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDissolve_interpolation(WavefrontOBJParser.Dissolve_interpolationContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#level_of_detail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLevel_of_detail(WavefrontOBJParser.Level_of_detailContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#map_library}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMap_library(WavefrontOBJParser.Map_libraryContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#use_map}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUse_map(WavefrontOBJParser.Use_mapContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#material_library}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaterial_library(WavefrontOBJParser.Material_libraryContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#use_material}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUse_material(WavefrontOBJParser.Use_materialContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#shadow_object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShadow_object(WavefrontOBJParser.Shadow_objectContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#trace_object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrace_object(WavefrontOBJParser.Trace_objectContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#curve_approximation_technique}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurve_approximation_technique(WavefrontOBJParser.Curve_approximation_techniqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#surface_approximation_technique}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSurface_approximation_technique(WavefrontOBJParser.Surface_approximation_techniqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link WavefrontOBJParser#decimal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimal(WavefrontOBJParser.DecimalContext ctx);
}