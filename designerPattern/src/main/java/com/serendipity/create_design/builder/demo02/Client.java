package com.serendipity.create_design.builder.demo02;

public class Client {
    public static void main(String[] args) {
        House house = new HouseDirect(new CommonHouse()).constructHouse();
        //jdk 中 StringBuilder
        StringBuilder sb = new StringBuilder();
    }
}
