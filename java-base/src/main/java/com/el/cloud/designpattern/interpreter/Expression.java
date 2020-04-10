package com.el.cloud.designpattern.interpreter;

import java.util.HashMap;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 */
public abstract class Expression {
    public abstract int interpreter(HashMap<String,Integer> var);
}
