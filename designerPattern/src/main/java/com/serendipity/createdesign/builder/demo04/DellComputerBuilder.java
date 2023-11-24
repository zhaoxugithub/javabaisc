package com.serendipity.createdesign.builder.demo04;

public class DellComputerBuilder implements ComputerBuilder {

    private Computer computer;

    public DellComputerBuilder(String cpu, String ram) {
        computer = new Computer(cpu, ram);
    }

    @Override
    public ComputerBuilder setKeyBoard() {
        computer.setKeyboard("dell keyborad");
        return this;
    }

    @Override
    public ComputerBuilder setUsbCount() {
        computer.setUsbCount(10);
        return this;
    }

    @Override
    public ComputerBuilder setDisplay() {
        computer.setDisplay("dell display");
        return this;
    }

    @Override
    public Computer build() {
        return computer;
    }
}
