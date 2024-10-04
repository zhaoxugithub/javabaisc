package com.serendipity.java21;

/**
 * 接口支持普通方法，私有方法，默认方法，静态方法
 */
interface MyInterface {

    // 普通方法
    void commonMethod();

    // 静态方法
    static void staticMethod() {
        System.out.println("static method");
    }

    // 默认方法
    default void defaultMethod() {
        System.out.println("default method");
    }

    // 私有方法
    private void privateMethod() {
        System.out.println("private method");
    }
}

public class TestInterface {

    public void test() {
        MyInterface commonMethod = () -> System.out.println("common method");
        commonMethod.defaultMethod();
        // commonMethod.privateMethod(); 会报错
    }

    public static void main(String[] args) {
        MyInterface commonMethod = () -> System.out.println("common method");
        commonMethod.defaultMethod();
        MyInterface.staticMethod();
    }

}

