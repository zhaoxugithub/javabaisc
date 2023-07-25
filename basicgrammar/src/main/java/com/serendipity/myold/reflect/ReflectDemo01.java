package com.serendipity.myold.reflect;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/8 0:30
 * FileName: ReflectDemo10
 * Description: com.reflect
 */
@Slf4j
public class ReflectDemo01 {
    public static void test01() throws Exception {
        // String 对象
        String s = "Hello world";
        // 获取String substring(int)方法，参数为int:
        Method substring = String.class.getMethod("substring", int.class);
        // 在s对象上调用该方法并获取结果
        String r = (String) substring.invoke(s, 6);
        // 打印调用结果
        System.out.println(r);
    }

    public static void test02() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // String 对象
        String s = "Hello world";
        // 获取String substring(int)方法，参数为int:
        Method substring = String.class.getMethod("substring", int.class, int.class);
        // 在s对象上调用该方法并获取结果
        String r = (String) substring.invoke(s, 6, 9);
        // 打印调用结果
        System.out.println(r);
    }

    public static void test03() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method parseInt = Integer.class.getMethod("parseInt", String.class);
        Integer invoke = (Integer) parseInt.invoke(null, "123456");
        System.out.println(invoke);
    }

    public static void main(String[] args) throws Exception {
        test03();
    }
}
