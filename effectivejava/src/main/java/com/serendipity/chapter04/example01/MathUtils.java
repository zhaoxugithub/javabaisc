package com.serendipity.chapter04.example01;

/*
在《Effective Java》中，使用私有构造方法执行非实例化的一个常见场景是创建一个工具类或常量类，
这些类通常只包含静态方法和静态成员，不需要实例化。
通过将构造方法设为私有，可以防止类被实例化，从而确保类的纯静态性质。
 */
public final class MathUtils {

    private MathUtils() {
        throw new RuntimeException("cannot instantiate utility class");
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
        int add = MathUtils.add(1, 2);
        int multiply = MathUtils.multiply(2, 3);

        System.out.println("add = " + add);
        System.out.println("multiply = " + multiply);

    }

}
