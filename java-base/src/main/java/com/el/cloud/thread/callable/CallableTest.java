package com.el.cloud.thread.callable;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Roman.Zhang
 * @date 2020/4/21
 * @description:
 */
public class CallableTest {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        //第一种方式
        //1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        FutureTask<Integer> result = new FutureTask<>(td);

        new Thread(result).start();

        //第二种方方式
        ExecutorService executor = Executors.newSingleThreadExecutor();

        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });
        executor.submit(task);
//        executor.shutdown();
        //2.接收线程运算后的结果
        try {
            Integer sum = result.get();  //FutureTask 可用于 闭锁
            Integer sum1 = task.get();
            System.out.println(sum+"  "+sum1);
            System.out.println("------------------------------------");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}

class ThreadDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i <= 100000; i++) {
            sum += i;
        }

        return sum;
    }

}
