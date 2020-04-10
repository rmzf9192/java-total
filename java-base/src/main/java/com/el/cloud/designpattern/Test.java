package com.el.cloud.designpattern;

/**
 * @author Roman.Zhang
 * @date 2020/3/31
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        System.out.printf(f(2)+"");
    }

    public static int f(int value) {
        try {
            return value * value;
        } finally {
            if (value == 2) {
                System.out.println("hello ");
//                return 0;
            }
        }
    }
}
