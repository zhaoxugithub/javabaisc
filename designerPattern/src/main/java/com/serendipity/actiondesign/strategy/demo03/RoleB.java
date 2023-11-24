package com.serendipity.actiondesign.strategy.demo03;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/7 22:53
 * FileName: RoleB
 * Description: actiondesign.strategy.demo03
 */
public class RoleB extends Role {


    public RoleB(String name) {
        this.name = name;
    }

    @Override
    protected void display() {
        System.out.println("样子2");
    }

    @Override
    protected void run() {
        System.out.println("金蝉脱壳");//从RoleA中拷贝
    }

    @Override
    protected void attack() {
        System.out.println("降龙十八掌");//从RoleA中拷贝
    }

    @Override
    protected void defend() {
        System.out.println("铁布衫");
    }

}
