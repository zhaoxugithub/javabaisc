package com.serendipity.createdesign.singleton.demo01;


/**
 * 优点：线程安全、懒加载
 */
public class Singleton6 {
    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        Singleton instance1 = Singleton.INSTANCE;
        System.out.println(instance == instance1);
        instance.sayOK();
        System.out.println(instance.n);
    }
}

enum Singleton {
    INSTANCE;
    int n = 1;

    public void sayOK() {
        System.out.println("this is Singleton");
    }
}
