package com.serendipity.createdesign.builder.demo04;

public class Client {
    public static void main(String[] args) {
        MacComputerBuilder macComputerBuilder = new MacComputerBuilder("A15", "x64");
        ComputerDirector.makeComputer(macComputerBuilder);
        System.out.println(macComputerBuilder.build());
        DellComputerBuilder dellComputerBuilder = new DellComputerBuilder("inter5", "x86");
        ComputerDirector.makeComputer(dellComputerBuilder);
        System.out.println(dellComputerBuilder.build());
    }
}
