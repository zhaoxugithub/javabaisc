package com.serendipity.structdesign.adapter.demo2;

//现有的适配类
public class Voltage220V {

    private int src = 220;

    public int output220V() {
        System.out.println("电压220V");
        return src;
    }
}
