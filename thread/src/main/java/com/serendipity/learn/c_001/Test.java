package com.serendipity.learn.c_001;

public class Test {


    public void toS() {
        System.out.println("toS...");
    }

    public void toC() {
        System.out.println("toC...");
    }

    public static void main(String[] args) {
        Test test = new Test();
        new Thread(test::toC).start();
        new Thread(test::toS).start();

    }
}
