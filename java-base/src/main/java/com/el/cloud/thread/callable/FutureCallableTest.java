package com.el.cloud.thread.callable;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Roman.Zhang
 * @date 2020/4/22
 * @description:
 */
public class FutureCallableTest {
    public static void main(String[] args)  {
        ExecutorService executor = Executors.newCachedThreadPool();
        //lambda
        Future<Integer> result = (Future<Integer>) executor.submit(() -> {
          return  new Random().nextInt();
        });
        //匿名函数
        Future<Integer> result1 = executor.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });
        executor.shutdown();

        try {
            System.out.println("result:" + result.get());
            System.out.println("result:" + result1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
