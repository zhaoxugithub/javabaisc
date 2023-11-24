package com.serendipity.createdesign.singleton.demo01;

/**
 * 懒汉式单例模式
 */
public class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
