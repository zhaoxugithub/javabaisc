package com.serendipity.createdesign.builder.demo02;

public abstract class HouseBuilder {

    private final House house = new House();

    public abstract void buildBasic();

    public abstract void buildWalls();

    public abstract void roofed();

    public House buildHouse() {
        return house;
    }
}
