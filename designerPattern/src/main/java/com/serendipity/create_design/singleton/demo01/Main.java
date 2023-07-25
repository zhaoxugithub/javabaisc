package com.serendipity.create_design.singleton.demo01;

/**
 * 总结：
 * 1. 推荐使用 饿汉式、双重检查、静态内部类、枚举 四种方式实现单例
 */

public class Main {

    public static void main(String[] args) throws InterruptedException {
//
//        Singleton1 instance = Singleton1.getInstance();
//        Singleton1 instance1 = Singleton1.getInstance();
//        System.out.println(instance == instance1);
//
//        Runtime runtime = Runtime.getRuntime();
        //Runtime这个类的是用饿汉式单例模式

        //饿汉式测试
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {

                Singleton1 instance1 = Singleton1.getInstance();
                System.out.println(instance1.hashCode());

            }).start();
        }


        //懒汉式测试
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {

                Singleton2 instance3 = Singleton2.getInstance();
                System.out.println(instance3.hashCode());

            }).start();
        }


    }

    public void moreThread() {

    }
}
