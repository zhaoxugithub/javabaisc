package com.serendipity.actiondesign.strategy.demo03;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/7 22:50
 * FileName: Role
 * Description: actiondesign.strategy.demo03
 */
public abstract class Role {

    protected String name;

    protected abstract void display();

    protected abstract void run();

    protected abstract void attack();

    protected abstract void defend();

}
