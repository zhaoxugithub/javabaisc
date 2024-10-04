package com.serendipity.java21;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 改进了try-with-resources语句，可以在try外进行初始化，在括号内填写引用名，即可实现资源自动关闭
 */
public class TryWithResource {

    public static void main(String[] args) throws FileNotFoundException {
        // jdk8 以前
        try (FileInputStream fis = new FileInputStream("test.txt"); FileOutputStream fos = new FileOutputStream("test"
                + ".txt")) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // jdk9
        FileInputStream fis = new FileInputStream("test.txt");
        FileOutputStream fos = new FileOutputStream("test.txt");
        // 多资源分号隔开
        try (fis; fos) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
