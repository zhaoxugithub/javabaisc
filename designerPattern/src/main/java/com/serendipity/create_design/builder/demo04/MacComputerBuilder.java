package com.serendipity.create_design.builder.demo04;

public class MacComputerBuilder implements ComputerBuilder {

    private Computer computer;

    public MacComputerBuilder(String cpu, String ram) {
        computer = new Computer(cpu, ram);
    }

    @Override
    public ComputerBuilder setKeyBoard() {
        computer.setKeyboard("leishe");
        return this;
    }

    @Override
    public ComputerBuilder setUsbCount() {
        computer.setUsbCount(20);
        return this;
    }

    @Override
    public ComputerBuilder setDisplay() {
        computer.setDisplay("xianshiqi");
        return this;
    }

    @Override
    public Computer build() {
        return computer;
    }
}
