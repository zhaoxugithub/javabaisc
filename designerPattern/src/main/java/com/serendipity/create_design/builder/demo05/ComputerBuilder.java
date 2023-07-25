package com.serendipity.create_design.builder.demo05;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/23 上午10:56
 * FileName: ComputerBuilder
 * Description: create_design.builder.demo05
 */
public interface ComputerBuilder {
    ComputerBuilder setKeyBoard();

    ComputerBuilder setUsbCount();

    ComputerBuilder setDisplay();

    Computer build();
}
