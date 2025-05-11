package erepair.program.visitor;

import erepair.program.generated.JSONParser;
import erepair.program.generated.JSONVisitor;

/**
 **/
public class ApacheEChartValidatorVisitor extends ValidatorVisitor implements JSONVisitor<Boolean> {
    private JSONParser.ValueContext hasXAxisTag;
    private JSONParser.ValueContext hasYAxisTag;
    private JSONParser.ValueContext hasLegendTag;

    public ApacheEChartValidatorVisitor(boolean skipErrorTokens) {
        super(skipErrorTokens);
        this.hasXAxisTag = null;
        this.hasYAxisTag = null;
        this.hasLegendTag = null;
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
        return b && this.hasXAxisTag != null && this.hasYAxisTag != null && this.hasLegendTag != null;
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
        if (ctx.children == null) {
            super.logNullChildren(ctx);
            return false;
        }
        if (ctx.STRING() == null || ctx.value() == null) {
            return false;
        }
        if ("\"xAxis\"".equals(ctx.STRING().getText())) {
            logFine("Found X Axis Data: " + ctx.value().toString());
            this.hasXAxisTag = ctx.value();
        } else if ("\"yAxis\"".equals(ctx.STRING().getText())) {
            logFine("Found Y Axis Tag: " + ctx.value().toString());
            this.hasYAxisTag = ctx.value();
        } else if ("\"legend\"".equals(ctx.STRING().getText())) {
            logFine("Found legend Tag: " + ctx.value().toString());
            this.hasLegendTag = ctx.value();
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
