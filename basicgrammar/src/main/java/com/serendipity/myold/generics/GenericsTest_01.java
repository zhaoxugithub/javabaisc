package com.serendipity.myold.generics;

import org.junit.jupiter.api.Test;

public class GenericsTest_01 {
    // 再返回类型之前
    public static <T> T test01(T d) {
        return d;
    }

    public <T> int test(T at) {
        return 0;
    }

    //    报错
    //    public int test(T t) {
    //        return 0;
    //    }
    //    在名称之前
    class Ed<E> {
        public void test01(E d) {
            System.out.println(d);
        }
    }

    @Test
    public void test() {
        test01("01");
        test01(123);
        new Ed<>().test01("01");
    }
}
