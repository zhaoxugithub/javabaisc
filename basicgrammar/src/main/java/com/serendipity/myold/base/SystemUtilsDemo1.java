package com.serendipity.myold.base;

import org.junit.Test;

import java.io.Console;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * ClassName SystemUtilsDemo1
 * Description TODO
 * Author 11931
 * Date 2023-04-06:1:14
 * Version 1.0
 * https://blog.csdn.net/Goodbye_Youth/article/details/80983007
 **/
@SuppressWarnings("all")
public class SystemUtilsDemo1 {
    @Test
    public void test01() {
        Map<String, String> env = System.getenv();
        System.out.println(env);
        for (Map.Entry<String, String> entry : env.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    @Test
    public void test2() {
        long currentTimeMillis = System.currentTimeMillis();
        long nanoTime = System.nanoTime();
        // 行分隔符
        String s = System.lineSeparator();
        System.out.println(s);
    }

    @Test
    public void test3() {
        int[] srcArr = {1, 2, 3, 4, 5, 6};
        int[] dscArr = new int[10];
        System.arraycopy(srcArr, 0, dscArr, 1, 5);
        System.out.println(Arrays.toString(dscArr));
    }

    @Test
    public void test4() {
        // System.gc();
        // System.load("C:\\Users\\11931\\Pictures\\mac_icon\\pad.png");
        Map<String, String> getenv = System.getenv();
        for (Map.Entry<String, String> stringStringEntry : getenv.entrySet()) {
            System.out.println(stringStringEntry.getKey() + "=" + stringStringEntry.getValue());
        }
    }

    @Test
    public void test5() {
        Properties properties = System.getProperties();
        for (Map.Entry<Object, Object> property : properties.entrySet()) {
            System.out.println(property.getKey() + "=" + property.getValue());
        }
    }


    @Test
    public void test6() {
        System.setProperty("key1", "value1");
        System.setProperty("key2", "value2");
        System.out.println(System.getProperty("key1"));
        System.out.println(System.getProperty("key2", "def"));
        // key 不存在就取默认值
        System.out.println(System.getProperty("key3", "def"));
    }

    @Test
    public void test7() {
        System.clearProperty("key1");
        // System.clearProperty("key2");
    }

    @Test
    public void test8() {
        Console console = System.console();
        System.out.println("please input your name:");
        String name = console.readLine();

        System.out.println("please input password:");
        char[] readPassword = console.readPassword();

        String password = String.valueOf(readPassword);
        System.out.println("your name:" + name + " your password:" + password);
    }


    @Test
    public void test9() {
        Point point = new Point(10, 10);
        System.out.println(point.hashCode());
        // 这两个值是一样的
        System.out.println(System.identityHashCode(point));
    }
}
