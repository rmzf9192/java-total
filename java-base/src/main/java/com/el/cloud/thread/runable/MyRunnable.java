package com.el.cloud.thread.runable;

/**
 * @author Roman.Zhang
 * @date 2020/4/21
 * @description:
 */
public class MyRunnable  implements Runnable{
    private int i = 0;

    @Override
    public void run() {
        for (i = 0; i < 30; i++) {
            System.out.println(Thread.currentThread().getName() + ",,," + i);
        }
    }

    public static void main(String[] agrs) {
        for (int i = 0; i < 1; i++) {
            System.out.println(Thread.currentThread().getName() + ",,,," + i);
            Thread.currentThread().setPriority(1);
            MyRunnable myRunnable = new MyRunnable();
            Thread thread = new Thread(myRunnable, "Thread-A");
            thread.setPriority(1);
            Thread thread1 = new Thread(myRunnable, "Thread-B");
            thread1.setPriority(10);
            //匿名内部类
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("匿名线程运行");
                }
            };
            new Thread(runnable).start();

            //lambda
            Runnable runnable1 = ()-> System.out.println("lambda线程运行");
            runnable1.run();
            new Thread(runnable1).start();

            thread.start();
            thread1.start();
        }
    }
}
