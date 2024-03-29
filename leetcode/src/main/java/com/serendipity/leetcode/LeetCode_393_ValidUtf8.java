package com.serendipity.leetcode;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * ClassName LeetCode_393_ValidUtf8
 * Description TODO
 * Author 11931
 * Date 2022-09-21:22:34
 * Version 1.0
 **/
public class LeetCode_393_ValidUtf8 {
    public static boolean validUtf8(int[] data) {
        int oneNum = 0;
        for (int i = 0; i < data.length; i++) {
            String s = Integer.toBinaryString(data[i]);
            System.out.println(s);
            if (i == 0) {
                // 如果长度小于8 表示只有一个字节
                if (s.length() < 8) {
                    return data.length == 1;
                } else {
                    // 如果他的长度大于8
                    oneNum = getOneNum(s);
                    if (data.length < oneNum) {
                        return false;
                    }
                }
                continue;
            }

            if (i < oneNum) {
                if (!s.startsWith("10") || s.length() < 8) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int getOneNum(String str) {
        int count = 0;
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if (aChar == '1') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char s1 = s.charAt(i);
            char s2 = s.charAt(j);
            if (!Character.isLetter(s1) && !Character.isDigit(s1)) {
                i++;
                continue;
            }
            if (!Character.isLetter(s2) && !Character.isDigit(s2)) {
                j--;
                continue;
            }
            if (Character.toLowerCase(s1) != Character.toLowerCase(s2)) {
                return false;
            } else {
                i++;
                j--;
            }

        }
        return true;
    }

    public static boolean isP(String s) {

        if (s == null || s.length() == 0) {
            return false;
        }
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c)) {
                stack.add(c);
            }
        }
        for (int i = 0; i < s.toCharArray().length; i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c) || Character.isDigit(c)) {
                if (Character.toLowerCase(c) != Character.toLowerCase(stack.pop())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] chars = s.toCharArray();
        List<List<String>> result = new ArrayList<>();
        return result;
    }

    public boolean isHuiWen(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean palindrome = isPalindrome("A man, a plan, a canal: Panama");
        System.out.println(palindrome);
    }
}
