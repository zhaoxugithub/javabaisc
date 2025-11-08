package com.serendipity.createdesign.builder.demo02;

public class Client {
    public static void main(String[] args) {
        House house = new HouseDirect(new CommonHouse()).constructHouse();
        //jdk 中 StringBuilder

        House house1 = new HouseDirect(new HighHouse()).constructHouse();
        StringBuilder sb = new StringBuilder();
    }
}
