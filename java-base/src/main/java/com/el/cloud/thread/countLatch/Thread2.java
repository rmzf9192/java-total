package com.el.cloud.thread.countLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author roman zhangfei
 * @Date 2019/10/18 14:41
 * @Version V1.0
 */
public class Thread2 {
    public static void main(String[] args) {
        CountDownLatch c0 = new CountDownLatch(0); //计数器为0
        CountDownLatch c1 = new CountDownLatch(100); //计数器为1
        CountDownLatch c2 = new CountDownLatch(100); //计数器为1

        Thread t1 = new Thread(new Work(c0, c1));
        //c0为0，t1可以执行。t1的计数器减1
        t1.setName(" t1 ");
        Thread t2 = new Thread(new Work(c1, c2));
        //t1的计数器为0时，t2才能执行。t2的计数器c2减1
        t2.setName(" t2 ");

        Thread t3 = new Thread(new Work(c1, c2));
        //t2的计数器c2为0时，t3才能执行
        t3.setName(" t3 ");

        t1.start();
        t2.start();
        t3.start();

    }

    //定义Work线程类，需要传入开始和结束的CountDownLatch参数
    static class Work implements Runnable {
        CountDownLatch c1;
        CountDownLatch c2;

        Work(CountDownLatch c1, CountDownLatch c2) {
            super();
            this.c1 = c1;
            this.c2 = c2;
        }


        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    c1.await();//前一线程为0才可以执行
                    System.out.println("thread start:" + Thread.currentThread().getName() + " " + i);
                    c2.countDown();//本线程计数器减少
                }

            } catch (InterruptedException e) {
            }

        }
    }
}
