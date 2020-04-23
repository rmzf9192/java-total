package com.el.cloud.thread.threadpool;

import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Roman.Zhang
 * @date 2020/4/23
 * @description:
 */
public class TestFixedThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(6);

        List<Future<Integer>> asList = new ArrayList();

        for (int i = 0; i < 10; i++) {
            Future<Integer> future = threadPool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for (int i = 0; i < 100; i++) {
                        sum += i;
                    }
                    return sum;
                }
            });
            asList.add(future);
        }
//        threadPool.shutdown();

        for (val v : asList) {
            System.out.println(v.get());
        }
        ThreadPoolDemo tpd = new ThreadPoolDemo();

		//2. 为线程池中的线程分配任务
		for (int i = 0; i < 10; i++) {
            threadPool.submit(tpd);
		}

		//3. 关闭线程池
        threadPool.shutdown();
    }

}


class ThreadPoolDemo implements Runnable {

    private int i = 0;

    @Override
    public void run() {
        while (i <= 100) {
            System.out.println(Thread.currentThread().getName() + " : " + i++);
        }
    }

}