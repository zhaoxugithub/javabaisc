package com.serendipity.myold.ut;

public class HelloWorld {

    public void sayHello() {
        System.out.println("hello....");
        throw new NumberFormatException();
    }

    public void sayWorld() {
        System.out.println("world....");
    }

    public String say() {
        return "hello world!";
    }
}
