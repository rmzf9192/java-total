package com.el.cloud.thread.countLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: roman.zhang
 * @Date: 2019/4/16 20:50
 * @Version:V1.0
 * @Description:CountDownLatchDemo
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count=new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国被灭了");
                count.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }
        count.await();
        System.out.println(Thread.currentThread().getName()+"\t 大秦帝国，一同华夏");
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getRetCode());
        System.out.println(CountryEnum.ONE.getRetMessage());

        closeDoor();
    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch count=new CountDownLatch(6);
        for (int i = 0; i <6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 完成任务");
                count.countDown();
            }, String.valueOf(i)).start();
        }
        count.await();
        System.out.println(Thread.currentThread().getName()+"\t 最后一个");
    }

}
