package com.el.cloud.designpattern.interpreter;

import java.util.HashMap;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 */
public class AddExpression  extends SymbolExpression {
    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpreter(HashMap<String,Integer> var){
        System.out.println( super.left.interpreter(var));;
        System.out.println( super.right.interpreter(var));;
        super.right.interpreter(var);

        return super.left.interpreter(var)+super.right.interpreter(var);
    }
}
