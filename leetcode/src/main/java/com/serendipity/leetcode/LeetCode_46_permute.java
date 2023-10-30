package com.serendipity.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName LeetCode_46_ermute
 * Description TODO
 * Author 11931
 * Date 2023-10-24:23:24
 * Version 1.0
 **/
public class LeetCode_46_permute {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return result;
        }
        boolean[] visited = new boolean[nums.length];
        process(nums, new ArrayList<>(), visited);
        return result;
    }

    private void process(int[] nums, List<Integer> tempList, boolean[] visited) {
        if (nums.length == tempList.size()) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 如果被访问过了，就继续
            if (visited[i]) {
                continue;
            }
            tempList.add(nums[i]);
            visited[i] = true;
            process(nums, tempList, visited);
            visited[i] = false;
            tempList.remove(tempList.size() - 1);
        }
    }

    @Test
    public void test() {
        int[] arr = new int[3];
        for (int i = 0; i < 3; i++) {
            arr[i] = i + 1;
        }
        permute(arr);
        System.out.println(result);
    }
}
