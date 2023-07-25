package com.serendipity.create_design.builder.demo02;

public class HighHouse extends HouseBuilder {
    @Override
    public void buildBasic() {
        System.out.println("高楼打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("高楼砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("高楼盖房顶");
    }
}
