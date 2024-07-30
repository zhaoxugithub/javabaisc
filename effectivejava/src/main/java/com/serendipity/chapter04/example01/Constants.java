package com.serendipity.chapter04.example01;

public final class Constants {
    // 私有构造方法，防止实例化
    private Constants() {
        throw new RuntimeException("111");
    }

    public static final String APPLICATION_NAME = "Myapp";
    public static final int MAX_USERS = 100;


    public static void main(String[] args) {
        System.out.println("APPLICATION_NAME = " + APPLICATION_NAME);
        System.out.println("MAX_USERS = " + MAX_USERS);
    }
}
