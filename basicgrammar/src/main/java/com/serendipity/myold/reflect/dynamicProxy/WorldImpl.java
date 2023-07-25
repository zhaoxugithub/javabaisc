package com.serendipity.myold.reflect.dynamicProxy;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/11 19:36
 * FileName: WorldImpl
 * Description: com.reflect.dynamicProxy
 */
public class WorldImpl implements Hello{
    @Override
    public void morning() {
        System.out.println("worldImpl morning....");
    }

    @Override
    public void afternoon() {
        System.out.println("worldImpl afternoon....");
    }

    @Override
    public void evening() {
        System.out.println("worldImpl evening....");
    }
}
