package com.serendipity.create_design.builder.demo05;


/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/23 上午10:58
 * FileName: MacComputerBuilder
 * Description: create_design.builder.demo05
 */
public class MacComputerBuilder implements ComputerBuilder {

    private Computer computer;

    public MacComputerBuilder(String cpu, String ram) {
        computer = new Computer(cpu, ram);
    }

    @Override
    public ComputerBuilder setKeyBoard() {
        computer.setKeyboard("mac keyboard");
        return this;
    }

    @Override
    public ComputerBuilder setUsbCount() {
        computer.setUsbCount(2);
        return this;
    }

    @Override
    public ComputerBuilder setDisplay() {
        computer.setDisplay("mac Display");
        return this;
    }

    @Override
    public Computer build() {
         return computer;
    }
}
