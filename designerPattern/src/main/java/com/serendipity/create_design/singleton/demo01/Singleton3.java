package com.serendipity.create_design.singleton.demo01;

/**
 * 这种方法没有办法保证线程安全
 */
public class Singleton3 {

    private static Singleton3 singleton3;

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        if (singleton3 == null) {
            synchronized (Singleton3.class) {
                singleton3 = new Singleton3();
            }
        }
        return singleton3;
    }


}
