package com.serendipity.myold.reflect.staticproxy;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/8 0:56
 * FileName: Main
 * Description: com.reflect.staticproxy
 */
/*
静态代理：
        程序员要手动为每一个目标类编写对应的代理类。如果当前系统已经有成百上千个类，工作量太大了。
        所以，现在我们的努力方向是：如何少写或者不写代理类，却能完成代理功能？
 */
public class Main {
    public static void main(String[] args) {
        InterfaceImplProxy proxy = new InterfaceImplProxy(new InterfaceImpl());
//        proxy.run();
        proxy.play();
    }
}
