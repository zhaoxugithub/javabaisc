package com.serendipity.myold.generics;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/16 6:46 下午
 * FileName: GenericsTest_03
 * Description: com.base
 */

@SuppressWarnings("all")
public class GenericsTest_03 {

    // 首先，我们泛型数组相关的申明：
    public void test() {
//        List<String>[] list11 = new ArrayList<String>[10]; //编译错误，非法创建
//        List<String>[] list12 = new ArrayList<?>[10]; //编译错误，需要强转类型
//        List<String>[] list13 = (List<String>[]) new ArrayList<?>[10]; //OK，但是会有警告
//        List<?>[] list14 = new ArrayList<String>[10]; //编译错误，非法创建
//        List<?>[] list15 = new ArrayList<?>[10]; //OK
//        List<String>[] list6 = new ArrayList[10]; //OK，但是会有警告
    }

    public void test2() {
        // list 里面装的是string
        List<String> strings = new ArrayList<>();
        // list 里面装的是string[]
        List<String[]> li = new ArrayList<>();

        // 数组元素是一个list
//        List<String>[] lii = new ArrayList<String>[100];
        List<?>[] list15 = new ArrayList<?>[10]; // OK
        list15[1] = new LinkedList<>();
        List<String>[] list6 = new ArrayList[10]; // OK，但是会有警告
    }

    // 证明Java是类型擦除
    public void test3() {
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("abc");

        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(123);

        System.out.println(list1.getClass() == list2.getClass()); // true

    }


    // 在相同类型的list里面添加不同类型的数据
    public void test4() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> strings = new ArrayList<>();
        strings.add(11);
        // 报错
//        strings.add(22);
        Method add = strings.getClass().getMethod("add", Object.class);
        add.invoke(strings, "qweqwr");
//        add.invoke(strings,new Date());
        for (int i = 0; i < strings.size(); i++) {
            System.out.println(strings.get(i));
        }
    }


    public void test5() {
        // 不指定范型
        Integer add = GenericsTest_03.add(1, 2);
        Number add1 = GenericsTest_03.add(1, 2.22);
        Object sdas = GenericsTest_03.add(1, "sdas");

        // 指定范型
        int a = GenericsTest_03.<Integer>add(1, 2); // 指定了Integer，所以只能为Integer类型或者其子类
//        int b = GenericsTest_03.<Integer>add(1, 2.2); //编译错误，指定了Integer，不能为Float
        Number c = GenericsTest_03.<Number>add(1, 2.2); // 指定为Number，所以可以为Integer和Float
    }

    public static <T> T add(T a, T b) {
        return b;
    }


    /**
     * 重点！！！！
     * 类型检查就是针对引用的，谁是一个引用，用这个引用调用泛型方法，就会对这个引用调用的方法进行类型检测，而无关它真正引用的对象。
     */
    public void test06() {


        ArrayList<String> list1 = new ArrayList();
        list1.add("1"); // 编译通过
//        list1.add(1); //编译错误
        String str1 = list1.get(0); // 返回类型就是String

        ArrayList list2 = new ArrayList<String>();
        list2.add("1"); // 编译通过
        list2.add(1); // 编译通过
        Object object = list2.get(0); // 返回类型就是Object

        new ArrayList<String>().add("11"); // 编译通过
//        new ArrayList<String>().add(22); //编译错误

        String str2 = new ArrayList<String>().get(0); // 返回类型就是String

    }


    private <E extends Comparable<? super E>> E max(List<? extends E> e1) {
        if (e1 == null) {
            return null;
        }
        // 迭代器返回的元素属于 E 的某个子类型
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

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//        new GenericsTest_03().max(new ArrayList<E>())
        GenericsTest_03 genericsTest_03 = new GenericsTest_03();
    }
}
