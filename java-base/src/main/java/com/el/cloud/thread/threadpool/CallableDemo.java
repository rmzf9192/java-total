package com.el.cloud.thread.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        System.out.println(Thread.currentThread().getName()+":come in ");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }
}
/**
 * @author Roman.Zhang
 * @date 2020/4/23
 * @description: 多线程，第3种获得多线程的方式 :并发和异步
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread2 myThread2 = new MyThread2();
        FutureTask<Integer> futureTask=new FutureTask<>(new MyThread2());
        FutureTask<Integer> futureTask1=new FutureTask<>(new MyThread2());

        Thread thread = new Thread(futureTask, "AA");
        Thread thread1 = new Thread(futureTask1, "BB");
        thread.start();

        System.out.println(Thread.currentThread().getName()+"....");

        int result=100;
        Integer integer = futureTask.get();//建议放在最后：要求获得callable线程的计算结果
        //如果没有完成，就要求去强求，会导致阻塞，直到计算完成。
   /*while(!futureTask.isDone()){

   }*/
        System.out.println(integer+result);

    }
}
