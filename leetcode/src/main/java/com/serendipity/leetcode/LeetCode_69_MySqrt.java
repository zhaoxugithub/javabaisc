package com.serendipity.leetcode;

public class LeetCode_69_MySqrt {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x < 4) {
            return 1;
        }
        for (int i = 1; i <= x / 2; i++) {

            if (x / i >= i && x / (i + 1) < i + 1) {
                return i;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
    }
}
