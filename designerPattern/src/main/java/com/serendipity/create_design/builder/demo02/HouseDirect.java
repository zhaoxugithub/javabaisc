package com.serendipity.create_design.builder.demo02;

public class HouseDirect {

    private HouseBuilder houseBuilder;

    public HouseDirect(HouseBuilder builder) {
        this.houseBuilder = builder;
    }

    public void setHouseBuilder(HouseBuilder builder) {
        this.houseBuilder = builder;
    }

    public House constructHouse() {
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        House house = houseBuilder.buildHouse();
        return house;
    }

}
