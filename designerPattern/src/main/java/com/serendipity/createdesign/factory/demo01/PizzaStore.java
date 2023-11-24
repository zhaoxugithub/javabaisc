package com.serendipity.createdesign.factory.demo01;

/**
 * 优点：比较好理解，简单易操作
 * 缺点：违反了设计模式ocp原则，即对扩展开放，对修改关闭，就是我们给类添加新功能时，尽量少修改代码
 */
public class PizzaStore {
    public static void main(String[] args) {
        new OrderPizza();
    }
}
