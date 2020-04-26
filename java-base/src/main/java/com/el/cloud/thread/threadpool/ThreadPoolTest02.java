package com.el.cloud.thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Roman.Zhang
 * @date 2020/4/24
 * @description:
 *  优点：
 *   操作相对简便，可以把等待线程池中任务完成后的后续工作做成任务，同样放到线程池中运行，简单来说，就是可以控制线程池中任务执行的顺序。
 *  缺点：
 * （1）需要提前知道任务的数量。
 * （2）大规模任务下影响并发的性能（可能）
 */
public class ThreadPoolTest02 {
    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 16, 200,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        CountDownLatch cdl = new CountDownLatch(5);
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
                    cdl.countDown();
                }
            });
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

        //这里的sysout输出是在main线程中的，其list的size根据当时这个list的情况只输出一次，可能是任意小于500的值
        System.out.println(Thread.currentThread().getName()+"---------"+vector.size());
    }
}
