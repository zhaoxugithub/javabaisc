package com.serendipity.apachecommons;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringUtilsDemo01 {

    private String words = "hello world";

    @Test
    public void testContains() {
        // 判断是否为空
        System.out.println(StringUtils.contains(words, "hello"));
        System.out.println(StringUtils.containsIgnoreCase(words, "HELLO"));
        // 这个方法用于检查一个字符串是否不包含任何指定的字符。如果字符串中不包含任何指定的字符，则返回 true；否则返回 false。
        System.out.println(StringUtils.containsNone(words, "abc"));
        System.out.println(StringUtils.containsNone(words, "abc"));
        // 这个方法用于检查一个字符串是否包含任何指定的字符。如果字符串中包含任何指定的字符，则返回 true；否则返回 false。
        System.out.println(StringUtils.containsAny(words, "haaaa"));
        System.out.println(StringUtils.containsAnyIgnoreCase(words, "Haa", "hhhh"));
        // 这个方法用于检查一个字符串是否仅包含指定的字符。如果字符串中仅包含指定的字符（允许重复），则返回 true；否则返回 false。
        System.out.println(StringUtils.containsOnly(words, "helo"));
        System.out.println(StringUtils.containsWhitespace(words));
    }


    @Test
    public void testReplace() {
        System.out.println(StringUtils.replace(words, "hello", "world"));
        System.out.println(StringUtils.replaceChars(words, 'h', 'w'));
        System.out.println(StringUtils.replaceEach(words, new String[]{"hello", "world"}, new String[]{"world",
                "hello"}));
        System.out.println(StringUtils.replaceEachRepeatedly(words, new String[]{"hello", "world"}, new String[]{
                "world", "hello"}));
        System.out.println(StringUtils.replaceIgnoreCase(words, "hello", "world"));
        System.out.println(StringUtils.replaceOnceIgnoreCase(words, "hello", "world"));
        System.out.println(StringUtils.replaceOnce(words, "hello", "world"));
    }

    @Test
    public void testSubstring() {
        System.out.println(StringUtils.substring(words, 0, 5));
        System.out.println(StringUtils.substringAfter(words, "hello"));
        System.out.println(StringUtils.substringAfterLast(words, "hello"));
        System.out.println(StringUtils.substringBefore(words, "world"));
        System.out.println(StringUtils.substringBeforeLast(words, "world"));
        System.out.println(StringUtils.substringBetween(words, "hello", "world"));
        System.out.println(StringUtils.substringBetween(words, "hello", "world"));
    }

    @Test
    public void testEmpty() {
        System.out.println(StringUtils.isBlank(null));  // true
        System.out.println(StringUtils.isBlank(""));    // true
        System.out.println(StringUtils.isBlank(" "));   // true

        System.out.println(StringUtils.isNotBlank(null)); // false
        System.out.println(StringUtils.isNotBlank(""));   // false
        System.out.println(StringUtils.isNotBlank(" "));  // false

        System.out.println(StringUtils.isEmpty(null));  // true
        System.out.println(StringUtils.isEmpty(""));   // true
        System.out.println(StringUtils.isEmpty(" "));  // false

        System.out.println(StringUtils.isNotEmpty(null)); // false
        System.out.println(StringUtils.isNotEmpty(""));   // false
        System.out.println(StringUtils.isNotEmpty(" "));  // true

        System.out.println(StringUtils.isAnyEmpty(null, " ", "", "sss"));

        System.out.println(StringUtils.isNumeric(null));   // false
        System.out.println(StringUtils.isNumeric(""));      // false
        System.out.println(StringUtils.isNumeric("123"));   // true
        System.out.println(StringUtils.isNumeric("12 3"));  // false
        System.out.println(StringUtils.isNumeric("123a"));  // false

        System.out.println(StringUtils.isWhitespace(null)); // false
        System.out.println(StringUtils.isWhitespace(""));   // true
        System.out.println(StringUtils.isWhitespace(" "));  // true
        System.out.println(StringUtils.isWhitespace(" a ")); // false
    }

    @Test
    public void testStart() {
        StringUtils.startsWith("hello", "he");   // true
        StringUtils.startsWith("hello", "Hi");   // false

        StringUtils.startsWithAny("hello", "he", "Hi"); // true
        StringUtils.startsWithAny("hello", "Hi", "ho"); // false

        StringUtils.endsWith("hello", "lo");     // true
        StringUtils.endsWith("hello", "L");      // false

        StringUtils.endsWithIgnoreCase("hello", "LO");  // true
        StringUtils.endsWithIgnoreCase("hello", "L");  // false
    }

    @Test
    public void testAbb() {
        // abbreviate(String str, int maxWidth): 缩短字符串到指定长度，通常在末尾加上省略号
        System.out.println(StringUtils.abbreviate("Hello World", 8));  // "Hello..."
        // capitalize(String str): 将字符串的首字母大写。
        System.out.println(StringUtils.capitalize("hello"));  // "Hello"
        // repeat(String str, int repeat): 重复字符串指定次数。
        System.out.println(StringUtils.repeat("ha", 3));  // "hahaha"
        // leftPad(String str, int size): 左填充，使字符串达到指定长度。
        System.out.println(StringUtils.leftPad("abc", 5));  // "  abc"
        // rightPad(String str, int size): 右填充，使字符串达到指定长度。
        System.out.println(StringUtils.rightPad("abc", 5));  // "abc  "
        // center(String str, int size): 居中填充，使字符串达到指定长度。
        System.out.println(StringUtils.center("abc", 7));  // "  abc  "
    }
}
