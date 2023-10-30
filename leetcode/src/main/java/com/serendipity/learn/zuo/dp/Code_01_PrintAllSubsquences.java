package com.serendipity.learn.zuo.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassName Code_01_PrintAllSubsquences
 * Description TODO
 * Author 11931
 * Date 2022-12-05:23:28
 * Version 1.0
 **/
public class Code_01_PrintAllSubsquences {

    private static List<String> subs(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        char[] chars = s.toCharArray();
        List<String> res = new ArrayList<>();
        String path = "";
        process(res, 0, path, chars);
        return res;
    }

    private static void process(List<String> res, int index, String path, char[] chars) {
        if (index == chars.length) {
            res.add(path);
            return;
        }
        String no = path;
        process(res, index + 1, no, chars);
        String yes = path + chars[index];
        process(res, index + 1, yes, chars);
    }

    public static List<String> allSort(String str) {
        if (str == null || str.length() == 0) {
            return new ArrayList<>();
        }
        boolean[] visited = new boolean[str.length()];
        char[] charArray = str.toCharArray();
        Set<String> res = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        processAllSort(res, charArray, visited, sb);
        return new ArrayList<>(res);
    }

    private static void processAllSort(Set<String> res, char[] charArray, boolean[] visited, StringBuilder sb) {
        if (sb.length() == charArray.length) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < charArray.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            sb.append(charArray[i]);
            processAllSort(res, charArray, visited, sb);
            // 下面这两步是用来恢复现场！！！ 很重要
            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static int removeDuplicates(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; ) {
            int j = i + 1;
            while (j < nums.length && nums[i] == nums[j]) {
                count++;
                j++;
            }
            i = j;
        }
        return nums.length - count;
    }

    public static void main(String[] args) {
        // List<String> abc = allSort("abc");
        // abc.forEach(System.out::println);
        System.out.println(removeDuplicates(new int[]{1, 1, 3}));
    }
}
