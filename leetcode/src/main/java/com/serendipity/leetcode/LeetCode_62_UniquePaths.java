package com.serendipity.leetcode;

/**
 * ClassName LeetCode_62_UniquePaths
 * Description TODO
 * Author 11931
 * Date 2022-12-05:0:28
 * Version 1.0
 **/
public class LeetCode_62_UniquePaths {

    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        // 第i行
        for (int i = 1; i < m; i++) {
            // 第j列
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
