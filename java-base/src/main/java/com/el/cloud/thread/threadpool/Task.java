package com.el.cloud.thread.threadpool;

/**
 * @author Roman.Zhang
 * @date 2020/4/23
 * @description:
 */
public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);
    }
}
