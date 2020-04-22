package com.el.cloud.thread.thread;

/**
 * @author Roman.Zhang
 * @date 2020/4/21
 * @description:
 */
public class MyThread extends Thread{
    public MyThread(String name) {
        super(name);
    }

    private int i = 0;

    public void run() {
        for (i = 0; i < 30; i++) {
            System.out.println(Thread.currentThread().getName() + "    " + i);
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            MyThread thread1 = new MyThread("Thtrea-A");

            MyThread thread2 = new MyThread("Thtrea-B");
            //匿名函数形式
            new Thread("匿名线程"){
                @Override
                public void run() {
                    System.out.println("匿名线程运行");
                }
            }.start();

            new Thread(()-> System.out.println("hello thread")).start();

            thread1.start();
            thread2.start();

        }

    }
}
