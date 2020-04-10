package com.el.cloud.designpattern.strategy;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 */
public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不会飞翔技术");
    }
}
