package com.el.cloud.thread.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Roman.Zhang
 * @date 2020/4/10
 * @description:
 */
public class ThreadlocalTest {

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadlocalTest().date(10);
                System.out.println(date);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadlocalTest().date(104707);
                System.out.println(date);
            }
        }).start();

    }

    public String date(int seconds) {

        //参数的单位是毫秒，从1970.1.1 00:00:00 GMT 开始计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
