package com.serendipity.learn.zuo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName Code_01_PrintAllSort
 * Description TODO
 * Author 11931
 * Date 2023-04-13:22:58
 * Version 1.0
 **/
public class Code_01_PrintAllSort {

    static List<List<Integer>> res = new ArrayList<>();

    // 1 2 3
    public static void allSort(int[] nums) {
        if (nums == null) {
            return;
        }
        LinkedList<Integer> tracks = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        process(tracks, visited, nums);
    }

    public static void process(LinkedList<Integer> tracks, boolean[] visited, int[] nums) {
        if (tracks.size() == nums.length) {
            res.add(new LinkedList<>(tracks));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // if this element has already visited
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            tracks.add(nums[i]);
            process(tracks, visited, nums);
            tracks.removeLast();
            visited[i] = false;
        }
    }


    static LinkedList<String> result = new LinkedList<>();

    public static void allSort(String s) {
        if (s == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[s.length()];
        process(sb, s, visited);
    }

    public static void process(StringBuilder sb, String s, boolean[] visited) {
        if (sb.length() == s.length()) {
            result.add(new String(sb));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            sb.append(s.charAt(i));
            process(sb, s, visited);
            sb.replace(sb.length() - 1, sb.length(), "");
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        // int[] arr = {1, 2, 3};
        // allSort(arr);
        // System.out.println(Arrays.deepToString(res.toArray()));
        allSort("abcd");
        System.out.println(Arrays.toString(result.toArray()));
        System.out.println(result.size());

        // 4 3 2 1
        int abs = Math.abs(-1);
        System.out.println(abs);

        int[][] arr = new int[10][10];
        if (arr.length == 0) {
        }
        if (arr[0].length == 0) {
        }
    }
}
