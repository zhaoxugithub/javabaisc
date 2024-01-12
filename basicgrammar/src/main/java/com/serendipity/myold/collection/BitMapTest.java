package com.serendipity.myold.collection;

import java.util.BitSet;

/**
 * ClassName BitMapTest
 * Description TODO
 * Author 11931
 * Date 2023-10-22:16:12
 * Version 1.0
 **/
public class BitMapTest {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < 10000 * 10000 * 2; i++) {
            bitSet.set(i, i % 5 == 0);
        }
        System.out.println(bitSet.length());
        System.out.println(bitSet.cardinality());
    }
}
