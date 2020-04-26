package com.el.cloud.thread.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Roman.Zhang
 * @date 2020/4/10
 * @description:
 *  30个线程
 *   多个线程打印自己的时间（如果线程超级多就会产生性能问题），所以要使用线程池
 */
public class ThreadlocalTest01 {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadlocalTest01().date(finalI);
                    System.out.println(date);
                }
            }).start();
            //线程启动后，休眠100ms
            Thread.sleep(100);
        }
    }

    public String date(int seconds) {

        //参数的单位是毫秒，从1970.1.1 00:00:00 GMT 开始计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
