package com.serendipity.myold.generics.base01;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/11 23:37
 * FileName: Pair01_Main
 * Description: com.generics.base01
 */
/*
因为T是Object，我们对Pair<String>和Pair<Integer>类型获取Class时，获取到的是同一个Class，也就是Pair类的Class。
换句话说，所有泛型实例，无论T的类型是什么，getClass()返回同一个Class实例，因为编译后它们全部都是Pair<Object>。
 */
public class Pair01_Main {

    class Pair01<T> {
        private T first;
        private T last;

        public Pair01(T first, T last) {
            this.first = first;
            this.last = last;
        }

        public T getFirst() {
            return first;
        }

        public T getLast() {
            return last;
        }
    }

    public void test() {
        Pair01<String> stringStringPair = new Pair01<>("key", "value");
        String key = stringStringPair.getFirst();
        String value = stringStringPair.getLast();
        System.out.println(key);
        System.out.println(value);
    }

    // 局限二：无法取得带泛型的Class。观察以下代码
    public void test02() {
        Pair01<String> p1 = new Pair01<>("Hello", "world");
        Pair01<Integer> p2 = new Pair01<>(123, 456);
        Class c1 = p1.getClass();
        Class c2 = p2.getClass();
        System.out.println(c1 == c2); // true
        System.out.println(c1 == Pair01.class); // true
        System.out.println();
    }

    // 局限一：<T>不能是基本类型，例如int，因为实际类型是Object，Object类型无法持有基本类型：
    public void test03() {
        // Pair<int> p = new Pair<>(1, 2); // compile error
    }

    // 局限三：无法判断带泛型的类型：
    public void test04() {
        // 原因和前面一样，并不存在Pair<String>.class，而是只有唯一的Pair.class。
        Pair01<Integer> integerPair01 = new Pair01<>(123, 456);
        // if (integerPair01 instanceof Pair01<String>) {
        // }
    }

    // 局限四：不能实例化T类型：
    public void test05() {
        /*
            public class Pair<T> {
            private T first;
            private T last;
            public Pair() {
                // Compile error:
                first = new T();
                last = new T();
            }
        }*/
    }
}
