package com.serendipity.mythread.test;

import org.junit.Test;

/**
 * ClassName Test01
 * Description TODO
 * Author 11931
 * Date 2023/9/9:9:26
 * Version 1.0
 **/
public class Test01 {

    class A {
        public void sayHello() throws InterruptedException {
            synchronized (this) {
                System.out.println(Thread.currentThread()
                                         .getName());
                System.out.println("Hello,Base");
            }
        }
    }

    class B extends A {
        @Override
        public void sayHello() throws InterruptedException {
            synchronized (this) {
                super.sayHello();
                Thread.sleep(4 * 1000);
                System.out.println("child:hello");

            }
        }
    }

    @Test
    public void test01() throws InterruptedException {
        B b = new B();
        new Thread(() -> {
            try {
                b.sayHello();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        A a = new A();
        new Thread(() -> {
            try {
                b.sayHello();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        Thread.sleep(10 * 1000);
    }
}
