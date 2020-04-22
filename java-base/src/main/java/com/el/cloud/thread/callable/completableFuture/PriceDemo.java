package com.el.cloud.thread.callable.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author Roman.Zhang
 * @date 2020/4/22
 * @description:
 */
public class PriceDemo {
    private List<Shop> shops = Arrays.asList(new Shop("shop1"),
            new Shop("shop2"),
            new Shop("shop3"),
            new Shop("shop4"),
            new Shop("shop5"),
            new Shop("shop6"),
            new Shop("shop7"),
            new Shop("shop8")
    );

    public List<String> findPrices(String product){
        //一条数据一秒
        /*return shops.stream().map(shop -> String.format("%s price is %.2f ",shop.getName(),shop.getPice(product)))
                .collect(Collectors.toList());*/
        /*
         * 方法一：加并行流.parallel()
         * 并行流输出结果
         * */
        return shops
                .parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPice(product)))
                .collect(Collectors.toList());
    }

    /**
     *  使用CompletableFuture
     * @param product
     * @return
     *  结果是与并行流差不多，有点失望，因为写了一大串东西，还不如并行流好用
     */
    public List<String> findPrices1(String product){
        List<CompletableFuture<String>> priceFuture = shops.stream().map(shop -> CompletableFuture
                .supplyAsync(() -> String.format("%s price is %.2f ", shop.getName(), shop.getPice(product))))
                .collect(Collectors.toList());
        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 当调用查询服务加到9个的时候，并行流和CompletableFuture都是三秒，
     * 因为CompletableFuture默认的启动机制是和并行流一样的，但是CompletableFuture这个方法可以通过配置，创建一个线程池。
     *
     */
    public List<String> findPrices2(String product){
        Executor executor = Executors.newCachedThreadPool();
        //Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),100));
        List<CompletableFuture<String>> priceFuture = shops.stream().map(shop -> CompletableFuture
                .supplyAsync(() -> String.format("%s price is %.2f ", shop.getName(), shop.getPice(product)),executor))
                .collect(Collectors.toList());

        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
    public static void main (String[] args){
        PriceDemo priceDemo = new PriceDemo();
        Long start = System.currentTimeMillis();
        System.out.println(priceDemo.findPrices2("苹果x"));
        System.out.println("服务耗时："+(System.currentTimeMillis()-start));
    }

}
