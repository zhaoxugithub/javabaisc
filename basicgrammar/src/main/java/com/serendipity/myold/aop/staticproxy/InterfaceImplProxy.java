package com.serendipity.myold.aop.staticproxy;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/8 0:51
 * FileName: InterfaceImplProxy
 * Description: com.reflect.staticproxy
 */
public class InterfaceImplProxy implements InterfaceA {

    InterfaceA target = null;

    public InterfaceImplProxy(InterfaceA target) {
        this.target = target;
    }

    @Override
    public void run() {
        System.out.println("打印日志前......");
        target.run();
        System.out.println("打印日志后......");
    }

    @Override
    public void play() {
        System.out.println("业务代码前。。。。");
        target.play();
        System.out.println("业务代码之后。。。。");
    }
}
