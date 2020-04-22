package com.el.cloud.thread.callable;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Roman.Zhang
 * @date 2020/4/22
 * @description:
 */
public class CompletableFutureTest {
    public static void main(String[] args){
        //变换结果
        String result = CompletableFuture.supplyAsync(()->{return "Hello ";}).thenApplyAsync(v -> v + "world").join();
        System.out.println(result);
        //消费结果
        CompletableFuture.supplyAsync(()->{return "Hello ";}).thenAccept(v -> { System.out.println("consumer: " + v);});
        //结合两个CompletionStage的结果，进行转化后返回
        String result1 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }).thenCombine(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }),(s1,s2)->{return s1 + " " + s2;}).join();
        System.out.println(result1);
        //两个CompletionStage，谁计算的快，就用那个CompletionStage的结果进行下一步的处理
        String result2 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Boy";
        }).applyToEither(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Girl";
        }),(s)->{return s;}).join();
        System.out.println(result2);
       //运行时出现了异常，可以通过exceptionally进行补偿
        String result3 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(true) {
                throw new RuntimeException("exception test!");
            }

            return "Hi Boy";
        }).exceptionally(e->{
            System.out.println(e.getMessage());
            return "Hello world!";
        }).join();
        System.out.println(result3);



    }

    public static Integer cale(Integer para) {
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para * para;
    }
    //实现Future模式那样的异步调用
    public static void test() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> cale(50));
        System.out.println(future.get());
    }

    //实现异步API
    public double getPrice(String product) {
        return calculatePrice(product);
    }
    /**
     * 同步计算商品价格的方法
     *
     * @param product 商品名称
     * @return 价格
     */
    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }
    /**
     * 模拟计算,查询数据库等耗时
     */
    public static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //异步获取
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }


}
