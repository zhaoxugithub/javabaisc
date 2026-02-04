package com.serendipity.myold.base;

/**
 * 继承demo
 */
@SuppressWarnings("all")
public class InheritanceDemo {

    /**
     * 抽象类;
     *
     */
    abstract class Shape {
        private final String color;

        protected Shape(String color) {
            this.color = color;
        }

        // 模板方法：定义算法骨架
        public final String getDescription() {
            return String.format("这是一个%s的%s，面积为：%.2f，周长为：%.2f", color, getName(), calculateArea(), calculatePerimeter());
        }

        // 抽象方法：子类必须实现
        protected abstract double calculateArea();

        protected abstract double calculatePerimeter();

        protected abstract String getName();

        // 钩子方法：子类可选择性覆盖
        protected boolean isRegular() {
            return true;
        }
    }

    class Circle extends Shape {
        private final double radius;

        public Circle(String color, double radius) {
            super(color);
            if (radius <= 0) {
                throw new IllegalArgumentException("半径必须为正数");
            }
            this.radius = radius;
        }

        @Override
        protected double calculateArea() {
            return Math.PI * radius * radius;
        }

        @Override
        protected double calculatePerimeter() {
            return 2 * Math.PI * radius;
        }

        @Override
        protected String getName() {
            return "圆形";
        }
    }

    public class Rectangle extends Shape {
        private final double width;
        private final double height;

        public Rectangle(String color, double width, double height) {
            super(color);
            this.width = width;
            this.height = height;
        }

        @Override
        protected double calculateArea() {
            return width * height;
        }

        @Override
        protected double calculatePerimeter() {
            return 2 * (width + height);
        }

        @Override
        protected String getName() {
            return "矩形";
        }

        @Override
        protected boolean isRegular() {
            return width == height; // 只有正方形是正多边形
        }
    }


    public static void main(String[] args) {

        InheritanceDemo inheritanceDemo = new InheritanceDemo();
        InheritanceDemo.Shape circle  = inheritanceDemo.new Circle("红色", 5);
        InheritanceDemo.Shape rect = inheritanceDemo.new Rectangle("蓝色", 4, 6);

        System.out.println(circle.getDescription());
        System.out.println(rect.getDescription());
    }

}
