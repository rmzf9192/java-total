package com.el.cloud.thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Roman.Zhang
 * @date 2020/4/24
 * @description:
 *  使用submit向线程池提交任务与execute提交不同，submit会有Future类型的返回值。通过返回返回值可以用于判断线程任务的执行状态。
 */
public class ThreadPoolTest03 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 16, 200,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

//        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> submit = executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println(Thread.currentThread().getName()+" 执行");
                    Thread.currentThread().join();
                    return "hello world" + Thread.currentThread().getName();
                }
            });
//            list.add(submit);
        }


        executor.shutdown();
        System.out.println(Thread.currentThread().getName() +" 主线程执行了");
    }
}
