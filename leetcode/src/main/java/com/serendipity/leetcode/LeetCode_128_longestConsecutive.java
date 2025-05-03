package com.serendipity.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

public class LeetCode_128_longestConsecutive {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);

        int tmp = nums[0] - 1;
        int count = 0;

        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (tmp + 1 == nums[i]) {
                count++;
                tmp = nums[i];
            } else {
                result = Math.max(count, result);
                count = 1;
                tmp = nums[i];
            }
        }
        return Math.max(result, count);
    }

    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 3, 2};

        int[] arr1 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};

        int[] arr2 = {1, 2, 0, 1};

        LeetCode_128_longestConsecutive c = new LeetCode_128_longestConsecutive();
        int i = c.longestConsecutive(arr2);
        System.out.println(i);
    }
}
