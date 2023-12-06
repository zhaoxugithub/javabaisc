package com.serendipity.myold.generics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("all")
public class GenericsTest_05 {
    public static void main(String args[]) {
        // 返回泛型数组
        Integer[] i = fun1(1, 2, 3, 4, 5, 6);
        fun2(i);
        String[] str = fun1("a", "b", "c", "d", "e", "f");
        fun2(str);
    }

    // 返回泛型数组
    // 接收可变参数
    public static <T> T[] fun1(T... arg) {
        return arg;
    }

    // 输出
    public static <T> void fun2(T[] param) {
        log.info("接收泛型数组：");
        for (T t : param) {
            log.info("t={}", t);
        }
    }
}
