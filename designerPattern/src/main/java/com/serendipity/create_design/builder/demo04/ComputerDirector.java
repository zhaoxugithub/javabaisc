package com.serendipity.create_design.builder.demo04;

public class ComputerDirector {
    public static void makeComputer(ComputerBuilder builder) {
        builder.setDisplay().setUsbCount().setKeyBoard().build();
    }
}
