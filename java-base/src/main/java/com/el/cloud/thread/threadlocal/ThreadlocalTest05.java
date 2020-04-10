package com.el.cloud.thread.threadlocal;

/**
 * @author Roman.Zhang
 * @date 2020/4/10
 * @description:
 *  每个线程内需要保存全局变量（例如在拦截器中获取用户信息），可以让不同方法直接使用，避免参数传递的麻烦
 *   1.通过方法参数传递 缺点：但是这样做会产生代码冗余问题，并且可维护性差。
 *   2.使用一个Map，在第一个方法中存储信息，后续需要使用直接get()即可， 缺点：在单线程环境下可以保证安全，但是在多线程环境下是不可以的。
 *     如果使用加锁和ConcurrentHashMap都会产生性能问题
 *   3.使用 ThreadLocal 可以避免加锁产生的性能问题，也可以避免层层传递参数来实现业务需求，就可以实现不同线程中存储不同信息的要求
 *
 */
public class ThreadlocalTest05 {
    public static void main(String[] args) {
        new Service1().process();
    }
}

class Service1 {

    public void process() {
        User user = new User("鲁毅");
        //将User对象存储到 holder 中
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2 {

    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service2拿到用户名: " + user.name);
        new Service3().process();
    }
}

class Service3 {

    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service3拿到用户名: " + user.name);
    }
}


class UserContextHolder {

    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User {

    String name;

    public User(String name) {
        this.name = name;
    }
}