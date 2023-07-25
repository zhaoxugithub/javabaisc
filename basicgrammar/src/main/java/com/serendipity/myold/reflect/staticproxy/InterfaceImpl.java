package com.serendipity.myold.reflect.staticproxy;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/8 0:50
 * FileName: InterfaceImpl
 * Description: com.reflect.staticproxy
 */
public class InterfaceImpl implements InterfaceA {
    @Override
    public void run() {
        System.out.println("process on .....");
    }

    @Override
    public void play() {
        System.out.println("play on .....");
    }
}
