package com.serendipity.myold.collection;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

import java.util.BitSet;
import java.util.HashMap;
import java.util.function.BiFunction;

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


    @Test
    public void testComputerIf() {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("A", "a1");
        map.put("B", "b1");
        map.put("C", "c1");
        map.computeIfPresent("A", new BiFunction<String, String, String>() {
            // s: key
            // s2: value
            @Override
            public String apply(String s, String s2) {
                return s + s2;
            }
        });

        // 简单写法
        map.computeIfPresent("B", (key, value) -> {
            return key + value;
        });

        System.out.println(map.toString());
    }
}
