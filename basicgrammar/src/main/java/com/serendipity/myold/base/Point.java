package com.serendipity.myold.base;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/4 23:48
 * FileName: Point
 * Description: com.base
 */
/*
不可以变类

 */
public final class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //final 修饰了x,所以没有办法set
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static void main(String[] args) {
        Point p = new Point(123, 4556);
        System.out.println(p.getX());
        System.out.println(p.getY());
        System.out.println(p);
    }
}

