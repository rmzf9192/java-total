package com.el.cloud.designpattern.interpreter;

import java.util.HashMap;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 */
public class VarExpression extends Expression {
    private String key;

    public VarExpression(String key) {
        this.key = key;
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(this.key);
    }
}
