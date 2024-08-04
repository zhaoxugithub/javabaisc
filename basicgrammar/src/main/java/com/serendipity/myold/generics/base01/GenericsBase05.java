package com.serendipity.myold.generics.base01;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class GenericsBase05 {

    class A {
    }

    class B extends A {
    }

    public static void funA(A a) {

    }

    public static void funB(B b) {
        funA(b);
    }

    // 以下funD 方法会报错
//    public static void funC(List<A> listA) {
//
//    }
//
//    public static void funD(List<B> listB) {
//        funC(listB);
//    }


    public static void funC(List<? extends A> listA) {

    }

    public static void funD(List<B> listB) {
        funC(listB);
    }

    // 由此引出泛型上下限

    // 上限
    class Info1<T extends Number> {    // 此处泛型只能是数字类型
        private T var;        // 定义泛型变量

        public void setVar(T var) {
            this.var = var;
        }

        public T getVar() {
            return this.var;
        }

        public String toString() {    // 直接打印
            return this.var.toString();
        }
    }

    public void funInfo1(Info1<Integer> info1) {
        System.out.println(info1.getVar());
    }


    @Test
    public void testInfo1() {
        Info1<Integer> info1 = new Info1<>();
        info1.setVar(111);
        // info1.setVar(111L); 报错
        System.out.println("info1.getVar() = " + info1.getVar());
        funInfo1(info1);
    }


    // 下限
    class Info2<T> {    // 此处泛型只能是数字类型
        private T var;        // 定义泛型变量

        public void setVar(T var) {
            this.var = var;
        }

        public T getVar() {
            return this.var;
        }

        public String toString() {    // 直接打印
            return this.var.toString();
        }
    }

    // 上限
    public void funInfo2_1(Info2<? extends Number> info2) {
        System.out.println(info2.getVar());
    }

    public void funInfo2_2(Info2<? super String> info2) {
        System.out.println(info2.getVar());
    }

    @Test
    public void testInfo2() {
        Info2<String> stringInfo2 = new Info2<>();
        // 会报错
        // /funInfo2_1(stringInfo2);
        funInfo2_2(stringInfo2);
        Info2<Object> objectInfo2 = new Info2<>();
        objectInfo2.setVar("str");
        funInfo2_2(objectInfo2);
    }


    private <E extends Comparable<? super E>> E max(List<? extends E> e1) {
        if (e1 == null) {
            return null;
        }
        //迭代器返回的元素属于 E 的某个子类型
        Iterator<? extends E> iterator = e1.iterator();
        E result = iterator.next();
        while (iterator.hasNext()) {
            E next = iterator.next();
            if (next.compareTo(result) > 0) {
                result = next;
            }
        }
        return result;
    }


    // 多个限制
    interface Staff {

    }

    interface Passenger {
    }

    class Me implements Staff, Passenger {
        Integer getSalary() {
            return 5000;
        }

        boolean isStanding() {
            return true;
        }
    }

    class Client {
        public static <T extends Staff & Passenger> void discount(T t) {

        }
    }
}
