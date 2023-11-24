package com.serendipity.createdesign.builder.demo05;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/23 上午11:07
 * FileName: HuaWeiComputerBuilder
 * Description: createdesign.builder.demo05
 */
public class HuaWeiComputerBuilder implements ComputerBuilder {

    private Computer computer;

    public HuaWeiComputerBuilder(String cpu, String ram) {
        computer = new Computer(cpu, ram);
    }

    @Override
    public ComputerBuilder setKeyBoard() {
        computer.setKeyboard("huawei key board");
        return this;
    }

    @Override
    public ComputerBuilder setUsbCount() {
        computer.setUsbCount(10);
        return this;
    }

    @Override
    public ComputerBuilder setDisplay() {
        computer.setDisplay("huawei Display");
        return this;
    }

    @Override
    public Computer build() {
        return computer;
    }
}
