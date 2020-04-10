package com.el.cloud.designpattern.state;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 *  奖品发放完的状态
 */
public class DispenseOutState extends State {
    RaffleActivity activity;

    public DispenseOutState(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void deductMoney() {
        System.out.println("奖品发送完了，请下次在参加");
    }

    @Override
    public boolean raffle() {
        System.out.println("奖品发送完了，请下次在参加");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("奖品发送完了，请下次在参加");
    }
}
