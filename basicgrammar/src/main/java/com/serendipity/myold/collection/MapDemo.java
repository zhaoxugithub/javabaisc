package com.serendipity.myold.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.BitSet;

/**
 * ClassName MapDemo
 * Description TODO
 * Author 11931
 * Date 2023-12-03:14:23
 * Version 1.0
 **/
@Slf4j
@SuppressWarnings("all")
public class MapDemo {
    class BitMapDemo {
        public void testBitSet() {
            BitSet bitSet = new BitSet();
            for (int i = 0; i < 10000 * 10000 * 2; i++) {
                if (i % 5 == 0) {
                    bitSet.set(1);
                } else {
                    bitSet.set(0);
                }
            }
            log.info("bitSet.length:{}", bitSet.length());
        }
    }

    @Test
    public void test() {
        BitMapDemo bitMapDemo = new BitMapDemo();
        bitMapDemo.testBitSet();
    }
}
