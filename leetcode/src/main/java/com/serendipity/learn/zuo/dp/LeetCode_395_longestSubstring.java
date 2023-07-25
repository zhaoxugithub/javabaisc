package com.serendipity.learn.zuo.dp;

public class LeetCode_395_longestSubstring {

    public int longestSubstring(String s, int k) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] dp = new int[s.length()];

        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            process(charArray, k, i, dp);
        }


        return 0;
    }

    public static int process(char[] charArray, int k, int i, int[] dp) {

        if (dp[i] != 0) {
            return dp[i];
        }

        for (int j = i + 1; j < charArray.length; j++) {

        }
        return 0;
    }
}
