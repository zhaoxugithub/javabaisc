package com.serendipity.hot100;


public class LeetCode_11_maxArea {

    // 方法一：暴力破解， 时间复杂度太高过不去
    public int maxArea01(int[] height) {
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i; j < height.length; j++) {
                int left = height[i];
                int right = height[j];
                int temp = Math.min(left, right) * (j - i);
                result = Math.max(temp, result);
            }
        }
        return result;
    }

    // 双指针
    public int maxArea02(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int res = Math.min(height[l], height[r]) * (r - l);
        while (l < r) {
            // 左边比价哦小
            if (height[l] < height[r]) {
                l++;
                res = Math.max(res, Math.min(height[l], height[r]) * (r - l));
            } else {
                r--;
                res = Math.max(res, Math.min(height[l], height[r]) * (r - l));
            }
        }
        return res;
    }
}
