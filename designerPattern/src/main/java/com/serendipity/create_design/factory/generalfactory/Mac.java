package com.serendipity.create_design.factory.generalfactory;

public class Mac implements Computer {

    public Mac() {
        this.makeComputer();
    }

    @Override
    public void makeComputer() {
        System.out.println("生产mac");
    }
}
