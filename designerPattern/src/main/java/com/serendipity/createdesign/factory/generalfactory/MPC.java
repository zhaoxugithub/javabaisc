package com.serendipity.createdesign.factory.generalfactory;

public class MPC implements Computer {

    public MPC() {
        this.makeComputer();
    }

    @Override
    public void makeComputer() {
        System.out.println("生产米pc");
    }
}
