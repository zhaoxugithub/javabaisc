package com.serendipity.learn.c6_static;

import java.util.Random;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/7 11:27 上午
 * FileName: OuterClassV2
 * Description: com.mashibing.jvm.c6_static
 */
public class OuterClassV2 {
    public static long OUTER_DATE = System.currentTimeMillis();
    public static int a = 1;

    static {
        System.out.println("外部类静态块加载时间：" + System.currentTimeMillis());
    }

    public OuterClassV2() {
        timeElapsed();
        System.out.println("外部类构造函数事件：" + System.currentTimeMillis());
    }

    static class InnerStaticClass {
        static {
            System.out.println("内部类静态块加载时间：" + System.currentTimeMillis());
        }

        public static double INNER_DATE = System.currentTimeMillis();

    }

    class InnerClass {
        public long INNER_DATE = 0;

        public InnerClass() {
            timeElapsed();
            INNER_DATE = System.currentTimeMillis();
        }
    }

    public static void Hello() {
        System.out.println("Hello");
    }

    public static void main(String[] args) {
//        System.out.println("外部类常量加载时间：" + OuterClassV2.a);
//        System.out.println("外部类常量加载时间：" + OuterClassV2.OUTER_DATE);
//        OuterClassV2.Hello();
//        OuterClassV2 outer = new OuterClassV2();
//        System.out.println("外部类静态变量加载时间：" + OuterClassV2.OUTER_DATE);
//        System.out.println("外部类静态变量加载时间：" + outer.OUTER_DATE);
    }

    //单纯的为了耗时而已
    private void timeElapsed() {
        for (int i = 0; i < 10000000; i++) {
            int a = new Random(100).nextInt(), b = new Random(100).nextInt();
            a = a + b;
        }

    }
}
