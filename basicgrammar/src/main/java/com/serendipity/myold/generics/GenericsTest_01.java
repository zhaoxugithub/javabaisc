package com.serendipity.myold.generics;

public class GenericsTest_01 {

    //再返回类型之前
    public static <T> void test01(T d) {
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
        }
    }
}
