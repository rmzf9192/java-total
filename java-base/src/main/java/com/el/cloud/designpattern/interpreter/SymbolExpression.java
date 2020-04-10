package com.el.cloud.designpattern.interpreter;

import java.util.HashMap;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 */
public class SymbolExpression extends Expression {
    protected Expression left;
    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return 0;
    }
}