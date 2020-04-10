package com.el.cloud.designpattern.strategy;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 */
public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public abstract void display();

    public void quack(){
        System.out.println("鸭子嘎嘎嘎叫");
    }

    public void swim(){
        System.out.println("鸭子在游泳");
    }

    public void fly(){
        if(flyBehavior!=null){
            flyBehavior.fly();
        }
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
