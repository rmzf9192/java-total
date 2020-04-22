package com.el.cloud.java8.lambda;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

/**
 * @author Roman.Zhang
 * @date 2020/4/13
 * @description:
 *  Lambda表达式：匿名类到Lambda的转换
 */
public class TestLambda01 {
    public static void main(String[] args) {

        final Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World 我是匿名内部类");
            }
        };
        //Lambda表达式 ,语法格式一：无参，无返回值，Lambda体只需一条语句
        final Runnable r2 = () -> System.out.println("Hello World 我是Lambda表达式");

        TreeSet<String> ts1 = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        TreeSet<String> ts2 = new TreeSet<>(
                (o1, o2) -> Integer.compare(o1.length(), o2.length())
        );
//        String str= "hello world";
        //语法格式二：一个参数 （消费者接口）
        Consumer<String> fun = (str) -> System.out.println(str);
        //语法格式三：Lambda只需要一个参数时，参数的小括号可以省略
        Consumer<String> fun1 = str -> System.out.println(str);
        //语法格式四：Lambda需要两个参数，并且有返回值
        BinaryOperator<Long> bo1 = (x, y) -> {
            System.out.println("Hello BinaryOperator");
            return x + y;
        };
        //语法格式五：Lambda只有一条语句时，return与大括号可以省略
        BinaryOperator<Long> bo2 = (x, y) -> x + y;
        //语法格式六：数据类型可以省略，因为可由编译器推断得出：称为类型推断。
        BinaryOperator<Long> bo3 = (Long x, Long y) -> {
            System.out.println("Hello BinaryOperator");
            return x + y;
        };
        /**
         * 上述 Lambda 表达式中的参数类型都是由编译器推断 得出的。
         * Lambda 表达式中无需指定类型，程序依然可 以编译，
         * 这是因为 javac 根据程序的上下文，
         * 在后台 推断出了参数的类型。Lambda 表达式的类型依赖于上 下文环境，
         * 是由编译器推断出来的。这就是所谓的 “类型推断”
         */
    }
}
