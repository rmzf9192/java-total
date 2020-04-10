package com.el.cloud.thread.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Roman.Zhang
 * @date 2020/4/10
 * @description:
 */
public class ThreadlocalTest03 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    //只创建一次 SimpleDateFormat 对象，避免不必要的资源消耗
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    //出现打印描述相同的

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            //提交任务
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadlocalTest03().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }

    public String date(int seconds) {

        //参数的单位是毫秒，从1970.1.1 00:00:00 GMT 开始计时
        Date date = new Date(1000 * seconds);
//        return dateFormat.format(date);

        //加锁解决线程安全
        /**
         * 因为添加了synchronized，所以会保证同一时间只有一条线程可以执行，这在高并发场景下肯定不是一个好的选择，所以看看其他方案吧。
         */
        String s;
        synchronized (ThreadlocalTest03.class) {
            s = dateFormat.format(date);
        }
        return s;
    }
}
