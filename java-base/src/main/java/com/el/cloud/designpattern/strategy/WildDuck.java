package com.el.cloud.designpattern.strategy;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 */
public class WildDuck extends Duck {
    public WildDuck() {
        flyBehavior = new GoodFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("这是野鸭");
    }
}
