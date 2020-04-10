package com.el.cloud.designpattern.strategy;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 */
public class BadFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("飞翔技术一般般");
    }
}
