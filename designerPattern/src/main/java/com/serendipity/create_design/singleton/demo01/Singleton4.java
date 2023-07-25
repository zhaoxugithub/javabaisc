package com.serendipity.create_design.singleton.demo01;

/**
 *
 *
 * 双重检测方法，可以保证线程安全,但是必须要加上volatile,保证可见性
 */
public class Singleton4 {

    private volatile static  Singleton4 singleton4;
    private Singleton4() {
    }
    public static Singleton4 getInstance() {
        if (singleton4 == null) {
            synchronized (Singleton4.class) {
                if (singleton4 == null) {
                    singleton4 = new Singleton4();
                }
            }
        }
        return singleton4;
    }

}
