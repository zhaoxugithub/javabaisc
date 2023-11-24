package com.serendipity.structdesign.adapter.demo2;

//适配器，将现有的适配接口220v转变成5v
public class VoltageAdapter implements Voltage5V {

    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        int srcV = voltage220V.output220V();
        int dstV = srcV / 44;
        return dstV;
    }
}
