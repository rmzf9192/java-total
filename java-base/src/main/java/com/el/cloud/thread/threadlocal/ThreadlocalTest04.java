package com.el.cloud.thread.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Roman.Zhang
 * @date 2020/4/10
 * @description:
 *   利用 ThreadLocal 给每个线程分配自己的 dateFormat 对象
 *  不但保证了线程安全，还高效的利用了内存
 *   使用了ThreadLocal后不同的线程不会有共享的 SimpleDateFormat 对象，所以也就不会有线程安全问题
 */
public class ThreadlocalTest04 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            //提交任务
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadlocalTest04().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }

    public String date(int seconds) {

        //参数的单位是毫秒，从1970.1.1 00:00:00 GMT 开始计时
        Date date = new Date(1000 * seconds);
        //获取 SimpleDateFormat 对象
        SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
        return dateFormat.format(date);
    }
}

class ThreadSafeFormatter {

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new
            ThreadLocal<SimpleDateFormat>(){

                //创建一份 SimpleDateFormat 对象
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                }
            };
}
