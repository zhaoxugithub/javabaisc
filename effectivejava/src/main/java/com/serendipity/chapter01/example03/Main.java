package com.serendipity.chapter01.example03;

public class Main {
    public static void main(String[] args) {
        Shape circle = Shape.createCircle(5.0);
        Shape rectangle = Shape.createRectangle(4.0, 6.0);
        System.out.println("Circle area: " + circle.area());
        System.out.println("Rectangle area: " + rectangle.area());
    }
}
