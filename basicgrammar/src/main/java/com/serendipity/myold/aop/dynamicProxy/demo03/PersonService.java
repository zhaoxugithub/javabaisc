package com.serendipity.myold.aop.dynamicProxy.demo03;

public class PersonService {
    public void run(){
        System.out.println("run");
    }
    private void eat(){
        System.out.println("eat");
    }
    public static void play(){
        System.out.println("play");
    }
}
