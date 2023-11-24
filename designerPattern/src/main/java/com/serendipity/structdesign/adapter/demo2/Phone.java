package com.serendipity.structdesign.adapter.demo2;

public class Phone {

    public void charging(Voltage5V voltage5V) {
        int i = voltage5V.output5V();
        System.out.println("输出5v电压");
        System.out.println("手机充电。。");
    }
}
