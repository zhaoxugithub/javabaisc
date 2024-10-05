package com.serendipity.java21;

import org.junit.Test;

public class StringNew {

    // jdk9 String 使用了byte数组存储字符串,替代了char数组
    // 获取字符串的内存大小
    public static void main(String[] args) {
        String str = "Hello World,获取字符串的内存大小";
        char[] charArray = str.toCharArray();
    }


    public void test() {
        String str = "Hello World,获取字符串的内存大小";
        int size = str.getBytes().length;
        System.out.println(STR."字符串的内存大小为：\{size}");
    }

    @Test
    public void test03(){

        String str = """
                Hello World,
                获取字符串的内存大小
                """;
        System.out.println(str);

    }
}
