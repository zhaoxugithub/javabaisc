package com.serendipity.hot100;

import com.serendipity.utils.ArrayUtils;

import java.util.Arrays;

// https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=top-100-liked
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

    // nums = [0,1,0,3,12]
    public void moveZeroes_2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            //
            if (nums[i] == 0) {

            }
        }
        System.out.println(nums);
    }

    private void moveFront(int[] nums, int j) {
        for (int i = j; i < nums.length - 1; i++) {
            nums[i] = nums[i + 1];
        }
        swap(nums, j, nums.length - 1);
        System.out.println(nums);
    }

    public void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /*
    1,2,3,0,4,0,3,4,5
     */
    public void moveZeroes1(int[] nums) {
        // i 一定小于 end
        for (int i = nums.length - 1; i >= 0; i--) {
            int end = i;
            if (nums[i] == 0) {
                end --;
                continue;
            }

        }
    }


    public static void main(String[] args) {
        LeetCode_283_moveZeroes leetCode283MoveZeroes = new LeetCode_283_moveZeroes();
        int[] arr = {0, 1, 0, 3, 12};
        // leetCode283MoveZeroes.moveZeroes_2(arr);

        leetCode283MoveZeroes.moveFront(arr, 1);
        System.out.println(Arrays.toString(arr));
    }
}
