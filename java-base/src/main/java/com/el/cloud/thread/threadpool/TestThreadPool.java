package com.el.cloud.thread.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Roman.Zhang
 * @date 2020/4/23
 * @description: 自定义线程池
 */
public class TestThreadPool {
    private static final int coreThreadNum = 3;//核心线程数
    private static final int maxThreadNum = 8;//最大线程数

    private boolean working = true;// 打开/关闭线程池
    private BlockingQueue<Thread> workThreads = new LinkedBlockingDeque<>(maxThreadNum);//当前工作线程
    private BlockingQueue<Runnable> tasks = new LinkedBlockingDeque<>(10);//任务队列

    public void execute(Runnable task) {

        //判读是否为空
        if (task == null) throw new NullPointerException();
        //获取当前工作的线程数量
        int workNum = workThreads.size();
        //如果当前工作线程少于核心线程，则开启新的线程
        if (workNum < coreThreadNum) {
            Worker worker = new Worker(task);
            Thread thread = new Thread(worker);
            System.out.println("开启了新的线程:" + thread.getName());
            workThreads.offer(thread);
            thread.start();
        } else if (tasks.size() < 10) {//线程池还没有满，放到线程池
            tasks.offer(task);
        } else if (workNum < maxThreadNum && tasks.size() == 10) {//任务队列满了，开启新的线程完成任务
            Worker worker = new Worker(task);
            Thread thread = new Thread(worker);
            System.out.println("任务队列满了，开启新的线程：" + thread.getName());
            thread.start();
        } else {
            System.out.println("放弃了该线程任务");
            return;
        }


    }

    //关闭线程池
    public void shutDown() {
        this.working = false;
        for (Thread thread : workThreads) {
            System.out.println("终止了线程：" + thread.getName());
            thread.interrupt();
        }
        System.out.println("终止线程池线程");
        Thread.currentThread().interrupt();
    }


    //任务包装类
    private class Worker implements Runnable {
        private Runnable task_origal;

        public Worker() {

        }

        public Worker(Runnable task) {
            this.task_origal = task;
        }

        @Override
        public void run() {
            if (task_origal != null) {
                task_origal.run();
                while (working) {
                    //提取阻塞任务，阻塞状态下不会真的中断
                    try {
                        Runnable take = tasks.take();
                    } catch (InterruptedException e) {
                        System.out.println("线程真的终止了");
                        Thread.currentThread().interrupt();
                    }
                }
            }

        }
    }
}
