package com.serendipity.hot100;

import com.serendipity.utils.ArrayUtils;

import java.util.Arrays;

//https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=top-100-liked
public class LeetCode_283_moveZeroes {

    public void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                // 从i位置开始往后找
                int j = i + 1;
                while (j < nums.length && nums[j] == 0) {
                    j++;
                }
                if (j < nums.length && nums[j] != 0) {
                    swap(nums, i, j);
                }
            }
        }
    }

    public void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        LeetCode_283_moveZeroes leetCode283MoveZeroes = new LeetCode_283_moveZeroes();
        int[] arr = {0, 1, 0, 3, 12};
        leetCode283MoveZeroes.moveZeroes(arr);

        System.out.println(Arrays.toString(arr));
    }
}
