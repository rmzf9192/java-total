package com.el.cloud.thread.countLatch;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: roman.zhang
 * @Date: 2019/4/16 22:09
 * @Version:V1.0
 * @Description:SemaphoreDemo
 */
public class SemaphoreDemo {
    public static void main(String[] args) {

        System.out.println(1 & 1);
        Semaphore semaphore=new Semaphore(3);
        for (int i = 0; i <100; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    try{
                        TimeUnit.SECONDS.sleep(3);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"停车3秒离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
