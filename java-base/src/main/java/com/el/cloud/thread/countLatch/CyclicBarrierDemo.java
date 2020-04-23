package com.el.cloud.thread.countLatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Auther: roman.zhang
 * @Date: 2019/4/16 22:02
 * @Version:V1.0
 * @Description:CyclicBarrierDemo
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });
        for (int i = 0; i <7 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"手机神龙");
                try{
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();

        }
    }
}
