package com.serendipity.create_design.builder.demo05;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/23 上午11:20
 * FileName: ComputerDirector
 * Description: create_design.builder.demo05
 */
public class ComputerDirector {
    public void makeComputer(ComputerBuilder builder) {
        builder.setUsbCount().setDisplay().setKeyBoard().build();
    }
}
