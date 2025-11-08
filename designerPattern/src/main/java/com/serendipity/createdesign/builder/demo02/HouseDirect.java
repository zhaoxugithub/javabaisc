package com.serendipity.createdesign.builder.demo02;

import lombok.Setter;

@Setter
public class HouseDirect {

    private HouseBuilder houseBuilder;

    public HouseDirect(HouseBuilder builder) {
        this.houseBuilder = builder;
    }

    public House constructHouse() {
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        return houseBuilder.buildHouse();
    }

}
