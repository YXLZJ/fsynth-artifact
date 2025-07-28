package erepair.program.visitor;

import erepair.program.Logging;
import erepair.program.generated.JSONParser;
import erepair.program.generated.JSONVisitor;

import java.util.List;

/**
 * GeoJSON Validator Visitor implemented using https://geojson.org/geojson-spec.html
 *
 **/
public class GeoJSONValidatorVisitor extends ValidatorVisitor implements JSONVisitor<Boolean> {
    private final List<String> geoJsonTypes = List.of(
            "Point",
            "MultiPoint",
            "LineString",
            "MultiLineString",
            "Polygon",
            "MultiPolygon",
            "GeometryCollection",
            "Feature",
            "FeatureCollection"
    );
    private JSONParser.ValueContext hasTypeTag;
    private JSONParser.ValueContext hasCoordinatesTag;

    public GeoJSONValidatorVisitor(boolean skipErrorTokens) {
        super(skipErrorTokens);
        this.hasTypeTag = null;
        this.hasCoordinatesTag = null;
    }


    @Override
    public Boolean visitJson(JSONParser.JsonContext ctx) {
        if (ctx == null) {
            super.logNullTree();
            return false;
        }
        if (ctx.children == null) {
            super.logNullChildren(ctx);
            return false;
        }
        final boolean b = ctx.children.stream().allMatch(child -> child != null && child.accept(this));
        return b && this.hasTypeTag != null && this.hasCoordinatesTag != null;
    }

    @Override
    public Boolean visitObj(JSONParser.ObjContext ctx) {
        if (ctx == null) {
            super.logNullTree();
            return false;
        }
        if (ctx.children == null) {
            super.logNullChildren(ctx);
            return false;
        }
        return ctx.children.stream().allMatch(child -> child != null && child.accept(this));
    }

    @Override
    public Boolean visitPair(JSONParser.PairContext ctx) {
        if (ctx == null) {
            super.logNullTree();
            return false;
        }
        if (ctx.children == null || ctx.value() == null) {
            super.logNullChildren(ctx);
            return false;
        }
        if (this.hasTypeTag == null && "\"type\"".equals(ctx.STRING().getText())) {
            //TODO valid types are: Polygon, Feature, FeatureCollection
            logFine("Found type: " + ctx.value().toString() + " with RuleIndex " + ctx.value().getRuleIndex());
            if (ctx.value().getRuleIndex() == JSONParser.RULE_value) {
                final boolean accept = geoJsonTypes.stream().anyMatch(keyword -> ctx.value().getText().equals("\"" + keyword + "\""));
                if (accept) {
                    logFine("Accepted " + ctx.value().getText() + " as valid GeoJSON Type");
                    this.hasTypeTag = ctx.value();
                } else {
                    logFine(ctx.value().getText() + " was not a valid keyword! Valid keywords: " + String.join(", ", geoJsonTypes));
                }
            }
        } else if (this.hasCoordinatesTag == null && "\"coordinates\"".equals(ctx.STRING().getText().toLowerCase())) {
            Logging.antlrLogger.fine("Found coordinates: " + ctx.value().toString());
            this.hasCoordinatesTag = ctx.value();
        }
        return ctx.value().accept(this);
    }

    @Override
    public Boolean visitArray(JSONParser.ArrayContext ctx) {
        if (ctx == null) {
            super.logNullTree();
            return false;
        }
        if (ctx.children == null) {
            super.logNullChildren(ctx);
            return false;
        }
        return ctx.children.stream().allMatch(child -> child != null && child.accept(this));
    }

    @Override
    public Boolean visitValue(JSONParser.ValueContext ctx) {
        if (ctx == null) {
            super.logNullTree();
            return false;
        }
        if (ctx.children == null) {
            super.logNullChildren(ctx);
            return false;
        }
        return ctx.children.stream().allMatch(child -> child != null && child.accept(this));
    }

}
