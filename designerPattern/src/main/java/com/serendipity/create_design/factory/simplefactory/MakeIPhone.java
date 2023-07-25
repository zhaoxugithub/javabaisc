package com.serendipity.create_design.factory.simplefactory;

import java.util.Map;

class MakeIPhone implements MakePhone {

    private Phone phone;

    public MakeIPhone(Map<String, String> params) {
        this.make(params);
    }

    @Override
    public void make(Map<String, String> params) {
        System.out.println("make iphone...");
        this.phone = Common.fullParams(params);
    }

    @Override
    public String toString() {
        return "MakeIPhone{" +
                "phone=" + phone +
                '}';
    }
}
