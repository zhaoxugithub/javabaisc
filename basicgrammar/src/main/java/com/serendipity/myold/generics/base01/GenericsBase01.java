package com.serendipity.myold.generics.base01;


/*
    泛型可以用在： 接口，方法，类


 */
public class GenericsBase01 {

    private static int add(int a, int b) {
        return a + b;
    }

    private static float add(float a, float b) {
        return a + b;
    }

    private static double add(double a, double b) {
        return a + b;
    }

    // 上述三个方式可以合并成一个方法
    private static <T extends Number> T add(T a, T b) {
        return a;
    }
}
