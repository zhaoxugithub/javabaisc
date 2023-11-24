package com.serendipity.createdesign.singleton.demo01;

/**
 * 静态内部类实现静态内部类
 */
public class Singleton5 {


    private Singleton5() {
    }

    /**
     * 当进行类加载的时候，这里的静态内部类不会被加载
     */
    private static class SingletonInstance {
        private static Singleton5 singleton5 = new Singleton5();
    }

    public static Singleton5 getInstance() {
        return SingletonInstance.singleton5;
    }
}
