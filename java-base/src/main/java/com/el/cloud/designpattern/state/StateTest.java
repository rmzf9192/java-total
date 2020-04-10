package com.el.cloud.designpattern.state;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 */
public class StateTest {
    public static void main(String[] args) {
        RaffleActivity raffleActivity = new RaffleActivity(1);

        for (int i = 0; i <30 ; i++) {
            System.out.println("-----第"+(i+1)+"次，抽奖");

            raffleActivity.debuctMoney();

            raffleActivity.raffle();
        }
    }
}
