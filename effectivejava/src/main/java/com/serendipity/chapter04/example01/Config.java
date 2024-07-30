package com.serendipity.chapter04.example01;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
    private Config() {
        throw new RuntimeException("");
    }


    // 静态属性和方法
    private static final Properties practice = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("111111");
            }
            practice.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return practice.getProperty(key);
    }

    public static void main(String[] args) {
        String property = Config.getProperty("");
        System.out.println("property = " + property);
    }
}
