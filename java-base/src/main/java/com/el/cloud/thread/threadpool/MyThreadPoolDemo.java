package com.el.cloud.thread.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Roman.Zhang
 * @date 2020/4/23
 * @description:
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        /*//System.out.println(Runtime.getRuntime().availableProcessors());
       // ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //模拟十个人来办理业务
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try{
            for (int i = 0; i <10 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
                TimeUnit.SECONDS.sleep(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }*/
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                5,1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        try{
            //超过最大线程数+最大队列数，将执行拒绝策略
            for (int i = 0; i <9 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
                // TimeUnit.SECONDS.sleep(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
