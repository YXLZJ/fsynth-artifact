package erepair.program.visitor;

import erepair.program.generated.JSONParser;
import erepair.program.generated.JSONVisitor;

import java.util.Objects;

/**
 **/
public class JsonLDValidatorVisitor extends ValidatorVisitor implements JSONVisitor<Boolean> {
    private JSONParser.ValueContext hasIdTag;
    private JSONParser.ValueContext hasContextTag;

    public JsonLDValidatorVisitor(boolean skipErrorTokens) {
        super(skipErrorTokens);
        this.hasIdTag = null;
        this.hasContextTag = null;
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
        return b && this.hasIdTag != null && this.hasContextTag != null;
    }

    @Override
    public Boolean visitObj(JSONParser.ObjContext ctx) {
        if (ctx == null) {
            super.logNullTree();
            return false;
        }
        if (ctx.children == null || ctx.children.stream().anyMatch(Objects::isNull)) {
            super.logNullChildren(ctx);
            return false;
        }
        return ctx.children.stream().filter(Objects::nonNull).allMatch(child -> child.accept(this));
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
        if ("\"@id\"".equals(ctx.STRING().getText())) {
            logFiner("Found @id: " + ctx.value().toString());
            this.hasIdTag = ctx.value();
        } else if ("\"@context\"".equals(ctx.STRING().getText())) {
            logFiner("Found @context: " + ctx.value().toString());
            this.hasContextTag = ctx.value();
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
