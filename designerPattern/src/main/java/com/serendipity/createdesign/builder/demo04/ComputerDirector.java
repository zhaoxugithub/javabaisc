package com.serendipity.createdesign.builder.demo04;

public class ComputerDirector {
    public static void makeComputer(ComputerBuilder builder) {
        builder.setDisplay().setUsbCount().setKeyBoard().build();
    }
}
