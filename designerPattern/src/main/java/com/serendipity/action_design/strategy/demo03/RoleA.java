package com.serendipity.action_design.strategy.demo03;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/7 22:52
 * FileName: RoleA
 * Description: action_design.strategy.demo03
 */
public class RoleA extends Role {
    public RoleA(String name) {
        this.name = name;
    }

    @Override
    protected void display() {
        System.out.println("样子1");
    }

    @Override
    protected void run() {
        System.out.println("金蝉脱壳");
    }

    @Override
    protected void attack() {
        System.out.println("降龙十八掌");
    }

    @Override
    protected void defend() {
        System.out.println("铁头功");
    }
}
