package com.serendipity.chapter01.example03;

public abstract class Shape {
    // 静态工厂方法
    public static Shape createCircle(double radius) {
        return new Circle(radius);
    }

    public static Shape createRectangle(double width, double height) {
        return new Rectangle(width, height);
    }

    // 抽象方法
    public abstract double area();
}
