package com.serendipity.create_design.factory.simplefactory;

import java.util.Map;

class MakeXMIPhone implements MakePhone {

    private Phone phone;

    public MakeXMIPhone(Map<String, String> params) {
        this.make(params);
    }


    @Override
    public void make(Map<String, String> params) {
        System.out.println("make xiaomiphone...");
        this.phone = Common.fullParams(params);
    }

    @Override
    public String toString() {
        return "MakeXMIPhone{" +
                "phone=" + phone +
                '}';
    }
}
