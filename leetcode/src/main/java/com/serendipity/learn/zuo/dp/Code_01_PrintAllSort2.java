package com.serendipity.learn.zuo.dp;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Code_01_PrintAllSort2 {
    private static List<List<Integer>> result = new ArrayList<>();

    public static void allsort3(int[] nums) {
        // [1,2,3]
        if (nums == null || nums.length == 0) {
            return;
        }
        boolean[] visited = new boolean[nums.length];
        process(nums, visited, new ArrayList());
    }

    private static void process(int[] nums, boolean[] visited, List<Integer> tempList) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            tempList.add(nums[i]);
            visited[i] = true;
            process(nums, visited, tempList);
            visited[i] = false;
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        allsort3(new int[]{1, 2, 3});
        System.out.println(result);
    }
}
