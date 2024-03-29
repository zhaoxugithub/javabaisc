package com.serendipity.leetcode;

public class LeetCode_70_ClimbStairs {
    public int climbStairs(int n) {
        if (n < 4) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static void main(String[] args) {
    }
}
