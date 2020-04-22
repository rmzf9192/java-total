package com.el.cloud.thread.join;

/**
 * @author Roman.Zhang
 * @date 2020/4/21
 * @description:
 * 把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。
 * 比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。
 */
public class ThreadJoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new threahd());
        t1.setName("t1");
        Thread t2 = new Thread(new threahd());
        t2.setName("t2");
        Thread t3 = new Thread(new threahd());
        t3.setName("t3");
        t1.start();
        t1.join();
        t2.start();
        t2.join();

        t3.start();

        Thread.activeCount();
    }
}

class threahd implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
