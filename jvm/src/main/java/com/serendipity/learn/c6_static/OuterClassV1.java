package com.serendipity.learn.c6_static;

import java.util.Random;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/7 11:07 上午
 * FileName: OutClassV1
 * Description: com.mashibing.jvm.c6_static
 *
 * 外部类静态块加载时间：  1630985040753
 * 外部类构造函数时间：   1630985041434
 * 外部类静态变量加载时间 1630985040753
 * 非静态内部类加载时间   1630985042410
 * 静态内部类加载时间：1630985042413
 *
 * 结论：静态内部类和非静态内部类一样，都是在被调用时才会被加载
 *
 * 触发类加载时机：含有main, 调用静态方法，静态变量，反射，new，初始化类的子类
 */
public class OuterClassV1 {

    public static long OUTER_DATE = System.currentTimeMillis();

    static {
        System.out.println("外部类静态块加载时间：" + System.currentTimeMillis());
    }

    public OuterClassV1() {
        timeElapsed();
        System.out.println("外部类构造函数时间：" + System.currentTimeMillis());
    }

    static class InnerStaticClass {
        public static long INNER_STATIC_DATE = System.currentTimeMillis();
    }

    class InnerClass {
        public long INNER_DATE = 0;

        public InnerClass() {
            timeElapsed();
            INNER_DATE = System.currentTimeMillis();
        }
    }

    public static void main(String[] args) {
//        OuterClassV1 outer = new OuterClassV1();
//        System.out.println("外部类静态变量加载时间：" + outer.OUTER_DATE);
//        System.out.println("非静态内部类加载时间" + outer.new InnerClass().INNER_DATE);
//        System.out.println("静态内部类加载时间：" + InnerStaticClass.INNER_STATIC_DATE);
    }

    //单纯的为了耗时，来扩大时间差异
    private void timeElapsed() {
        for (int i = 0; i < 10000000; i++) {
            int a = new Random(100).nextInt(), b = new Random(100).nextInt();
            a = a + b;
        }
    }

}
