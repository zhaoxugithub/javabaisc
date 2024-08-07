package com.serendipity.learn.myjvm;

public class Test2 {
    private static String str = "abc";
    static {
        System.out.println(str);
        System.out.println("静态初始化块执行了..");
    }

    private static void run(){
        System.out.println("run");
    }
}
