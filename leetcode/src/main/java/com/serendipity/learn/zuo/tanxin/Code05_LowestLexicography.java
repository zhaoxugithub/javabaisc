package com.serendipity.learn.zuo.tanxin;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class Code05_LowestLexicography {

    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        TreeSet<String> ans = process(strs);
        return ans.size() == 0 ? "" : ans.first();
    }

    // strs中所有字符串全排列，返回所有可能的结果
    public static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<>();
        if (strs.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nexts = removeIndexString(strs, i);
            TreeSet<String> next = process(nexts);
            for (String cur : next) {
                ans.add(first + cur);
            }
        }
        return ans;
    }

    // {"abc", "cks", "bct"}
    // 0 1 2
    // removeIndexString(arr , 1) -> {"abc", "bct"}
    public static String[] removeIndexString(String[] arr, int index) {
        int N = arr.length;
        String[] ans = new String[N - 1];
        int ansIndex = 0;
        for (int i = 0; i < N; i++) {
            if (i != index) {
                ans[ansIndex++] = arr[i];
            }
        }
        return ans;
    }


    //----------------------------------------
    public static String lowestString3(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }
        boolean[] visited = new boolean[strs.length];
        //利用treeSet 排序,从小到大
        TreeSet<String> set = new TreeSet<>();
        int sum = 0;
        for (String st : strs) {
            sum += st.length();
        }
        process3(strs, sum, "", set, visited);
        return set.size() == 0 ? "" : set.first();
    }

    private static void process3(String[] strs, int sum, String s, TreeSet<String> set, boolean[] visited) {

        if (s.length() == sum) {
            set.add(s);
            return;
        }
        for (int i = 0; i < strs.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            process3(strs, sum, s + strs[i], set, visited);
            visited[i] = false;
        }
    }
    //-------------------------------------

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestString2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            System.out.println("Arrays.toString(arr1) = " + Arrays.toString(arr1));
            String[] arr2 = copyStringArray(arr1);
            String[] arr3 = copyStringArray(arr1);
            String ls1 = lowestString1(arr1);
            String ls2 = lowestString2(arr2);
            String ls3 = lowestString3(arr3);
            System.out.println("ls1 = " + ls1);
            System.out.println("ls2 = " + ls2);
            System.out.println("ls3 = " + ls3);
            if (!ls1.equals(ls2) || !ls1.equals(ls3)) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
