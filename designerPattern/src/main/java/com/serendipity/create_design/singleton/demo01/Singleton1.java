package com.serendipity.create_design.singleton.demo01;

/**
 * 饿汉式单利模式
 */
public class Singleton1 {

    private static Singleton1 instance = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return instance;
    }
}
