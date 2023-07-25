package com.serendipity.leetcode;

import java.util.HashSet;

public class LeetCode_545_DistributeCandies {
    public int distributeCandies(int[] candyType) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < candyType.length; i++) {
            set.add(candyType[i]);
        }
        int mid = candyType.length / 2;
        return Math.min(set.size(), mid);
    }
}
