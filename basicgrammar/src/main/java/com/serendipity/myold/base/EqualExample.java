package com.serendipity.myold.base;

import java.util.List;
import java.util.Objects;

/**
 * ClassName EqualExample
 * Description TODO
 * Author 11931
 * Date 2023-01-04:0:15
 * Version 1.0
 **/
public class EqualExample {

    private int x;
    private int y;
    private int z;

    private List<String> list;

    private Point point;

    public EqualExample(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EqualExample that = (EqualExample) o;
        return x == that.x && y == that.y && z == that.z && list.equals(that.list) && point.equals(that.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;
    //
    //     EqualExample that = (EqualExample) o;
    //
    //     if (x != that.x) return false;
    //     if (y != that.y) return false;
    //     return z == that.z;
    // }


}
