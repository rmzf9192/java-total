package com.el.cloud.thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * @author Roman.Zhang
 * @date 2020/4/24
 * @description:
 *  优点：操作简便；
 *  缺点：需要主线程阻塞；
 */
public class ThreadPoolTest01 {
    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 16, 200,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        //list是所有线程共享变量需要加锁避免冲突
        final List list = new ArrayList();
        final Vector vector = new Vector();
        for(int i=0;i<5;i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //j是线程内部变量，每个线程都有一份，自己会管理，不会冲突
                    int j=100;
                    while(j>0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (vector) {
                            vector.add(j);
                            System.out.println(Thread.currentThread().getName()+"---------"+vector.size());
                            j--;
                        }

                    }
                }
            });
        }
//        executor.shutdown();


        while(true){
//            if(executor.isTerminated())break;
            if(executor.getPoolSize() == executor.getCompletedTaskCount()){
                break;
            }
        }
       //这里的sysout输出是在main线程中的，其list的size根据当时这个list的情况只输出一次，可能是任意小于500的值
        System.out.println(Thread.currentThread().getName()+"---------"+vector.size());

    }
}
