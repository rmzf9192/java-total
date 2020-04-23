package com.el.cloud.thread.threadpool;


import java.util.concurrent.TimeUnit;

/**
 * @author Roman.Zhang
 * @date 2020/4/23
 * @description:
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        TestThreadPool threadPool = new TestThreadPool();
        for (int i = 0; i < 20; i++) {
            threadPool.execute(new Task("任务" + i));
        }
        try {
            TimeUnit.SECONDS.sleep(4);
            threadPool.shutDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
