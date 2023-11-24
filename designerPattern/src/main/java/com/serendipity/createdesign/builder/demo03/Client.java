package com.serendipity.createdesign.builder.demo03;

public class Client {
    public static void main(String[] args) {
        Computer c1 = new Computer.Builder("inter 3", "x86").build();
        Computer.Builder builder = new Computer.Builder("inter 4", "x64").setDisplay("xiaomi")
                                                                         .setKeyboard("leishe")
                                                                         .setUsbCount(5);
    }
}
