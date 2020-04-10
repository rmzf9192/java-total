package com.el.cloud.designpattern.strategy;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 */
public class ToyDuck extends Duck {
    public ToyDuck() {
        flyBehavior = new NoFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("玩具鸭");
    }
    @Override
    public void quack(){
        System.out.println("玩具鸭不能叫");
    }

    @Override
    public void swim(){
        System.out.println("玩具鸭不能游泳");
    }
}
