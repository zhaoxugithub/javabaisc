package com.serendipity.leetcode;

/**
 * ClassName LeetCode_14_longestCommonPrefix
 * Description TODO
 * Author 11931
 * Date 2022-11-24:23:16
 * Version 1.0
 **/
public class LeetCode_14_longestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int len = strs.length;
        String str = strs[0];
        for (int i = 0; i < strs.length; i++) {
            // while (str.indexOf())
            while (strs[i].indexOf(str) != 0) {
            }
        }
        return "";
    }

    public static void main(String[] args) {

    }
}
