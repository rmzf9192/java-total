package com.el.cloud.thread.callable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Roman.Zhang
 * @date 2020/4/22
 * @description:
 */
public class FutureTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor2= Executors.newFixedThreadPool(5);
        class Task implements Callable<String> {
            @Override
            public String call() throws Exception {

                Random rand = new Random();
                TimeUnit.SECONDS.sleep(rand.nextInt(10));
                return  Thread.currentThread().getName();
            }
        }

        List<Future<String>> results = new ArrayList<Future<String>>();
        for(int i=0;i<5;i++){
            Future<String> f =  executor2.submit(new Task());
            results.add(f);
        }

        boolean flag =true;
        while(flag) {

            for(Iterator<Future<String>> iter = results.iterator(); iter.hasNext();){
                Future<String> f =iter.next();
                if(f.isDone()){
                    System.out.println(f.get());
                    iter.remove();

                }
            }
            if(results.size()==0){
                flag =false;
            }

        }

        System.out.println("执行完毕");

        executor2.shutdownNow();
    }
}
