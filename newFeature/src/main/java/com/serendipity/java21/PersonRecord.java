package com.serendipity.java21;

// jdk 14 record 类型
public record PersonRecord(String name, String age) {
    public void study() {
        System.out.println("study");
    }
}
