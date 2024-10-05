package com.serendipity.java17;

// jdk17 支持switch 强转
public class SwitchDemo {

    public static void main(String[] args) {
        switchDemo(new Rabbit());
        switchDemo(new Bird());
    }

    public static void switchDemo(Animal animal) {
        switch (animal) {
            case Rabbit rabbit -> rabbit.run();
            case Bird bird -> bird.fly();
            default -> System.out.println("Unknown");
        }
    }
}
