package com.el.cloud.designpattern.state;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 */
public abstract class State {
    // 扣除积分 - 50
    public abstract void deductMoney();

    // 是否抽中奖品
    public abstract boolean raffle();

    // 发放奖品
    public abstract  void  dispensePrize();
}
