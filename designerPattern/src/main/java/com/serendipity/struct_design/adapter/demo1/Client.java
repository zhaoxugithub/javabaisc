package com.serendipity.struct_design.adapter.demo1;

/**
 * 类适配器
 */
public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());
    }
}
