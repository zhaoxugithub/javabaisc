package com.serendipity.create_design.builder.demo04;

public interface ComputerBuilder {

    ComputerBuilder setKeyBoard();

    ComputerBuilder setUsbCount();

    ComputerBuilder setDisplay();

    Computer build();

}
