package com.serendipity.createdesign.builder.demo05;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/23 上午11:21
 * FileName: Client
 * Description: createdesign.builder.demo05
 */
public class Client {

    public static void main(String[] args) {

        MacComputerBuilder macComputerBuilder = new MacComputerBuilder("A1", "x86");
        ComputerDirector computerDirector = new ComputerDirector();
        computerDirector.makeComputer(macComputerBuilder);
        System.out.println(macComputerBuilder.build());

        HuaWeiComputerBuilder huaWeiComputerBuilder = new HuaWeiComputerBuilder("inter 9", "x64");
        ComputerDirector computerDirector2 = new ComputerDirector();
        computerDirector.makeComputer(huaWeiComputerBuilder);
        System.out.println(huaWeiComputerBuilder.build());


    }
}
