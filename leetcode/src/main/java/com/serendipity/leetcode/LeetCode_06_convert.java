package com.serendipity.leetcode;


public class LeetCode_06_convert {
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Object[] objects = new Object[numRows];
        int len = s.length();
        char[] chars = s.toCharArray();
        // PAYPALISHIRING
        for (int i = 0; i < len; i++) {
            int rand = i / numRows;
            int yu = i % numRows;
        }
        return "";
    }

    public static void main(String[] args) {
    }
}
