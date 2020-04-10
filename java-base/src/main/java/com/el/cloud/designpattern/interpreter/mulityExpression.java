package com.el.cloud.designpattern.interpreter;

import java.util.HashMap;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 */
public class mulityExpression  extends SymbolExpression {

    public mulityExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpreter(HashMap<String, Integer> var){
        return super.left.interpreter(var) * super.right.interpreter(var);
    }
}