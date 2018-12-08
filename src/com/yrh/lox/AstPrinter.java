package com.yrh.lox;

class AstPrinter implements Expr.Visitor<String> {

    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitAssignExpr(Expr.Assign expr) {
        return String.format("(assign %s %s)", expr.name, expr.value.accept(this));
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitCallExpr(Expr.Call expr) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        if (expr.arguments.size() > 0) {
            for (int i = 0, count = expr.arguments.size(); i < count - 1; i++) {
                Expr expr1 = expr.arguments.get(i);
                builder.append(expr1.accept(this)).append(" ");
            }
            builder.append(expr.arguments.get(expr.arguments.size() - 1).accept(this));
        }
        builder.append("]");
        return String.format("(call %s %s)", expr.callee.accept(this), builder.toString());
    }

    @Override
    public String visitGetExpr(Expr.Get expr) {
        return String.format("(get %s %s)", expr.name.lexeme, expr.object.accept(this));
    }

    @Override
    public String visitSetExpr(Expr.Set expr) {
        return String.format("(set %s %s)", expr.object.accept(this), expr.value.accept(this));
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitLogicalExpr(Expr.Logical expr) {
        return String.format("(%s %s %s)",
                expr.operator.lexeme,
                expr.left.accept(this),
                expr.right.accept(this));
    }

    @Override
    public String visitThisExpr(Expr.This expr) {
        return "this";
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    @Override
    public String visitVariableExpr(Expr.Variable expr) {
        return expr.name.lexeme;
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

}
