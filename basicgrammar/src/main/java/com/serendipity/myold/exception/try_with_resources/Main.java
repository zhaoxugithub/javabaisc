package com.serendipity.myold.exception.try_with_resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ClassName Main
 * Description TODO
 * Author 11931
 * Date 2022-11-01:1:06
 * Version 1.0
 * 解决使用try finally 的资源泄漏隐患
 **/
public class Main {

    /**
     * 传统的方式实现对资源的关闭
     *
     * @return
     */
    private String traditionalTryCatch() throws IOException {
        // 1.单一资源的关闭
       /* BufferedReader bufferedReader = new BufferedReader(new FileReader(""));
        String line = null;
        try {
            line = bufferedReader.readLine();
        } finally {
            // 如果这里出现了异常就会出现异常覆盖
            // 就是finally里面的异常会覆盖掉try里面的异常
            bufferedReader.close();
        }
        return line;*/
        // 2.多个资源的关闭
        FileInputStream in = new FileInputStream("");
        try {
            FileOutputStream out = new FileOutputStream("");
            try {
                byte[] bytes = new byte[100];
                int n = 0;
                while ((n = in.read(bytes)) != -1) {
                    out.write(bytes, 0, n);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
        return null;
    }

    /**
     * java7 引入try with resources 实现自动的资源关闭
     *
     * @return
     */
    private String newTryWithResources() throws IOException {

        // 1.单个资源关闭
        // try (BufferedReader bufferedReader = new BufferedReader(new FileReader(""))) {
        //     return bufferedReader.readLine();
        // }
        // 2.多个资源关闭
        try (FileInputStream in = new FileInputStream("");
             FileOutputStream out = new FileOutputStream("")) {
            byte[] bytes = new byte[100];
            int n = 0;
            while ((n = in.read(bytes)) != -1) {
                out.write(bytes, 0, n);
            }
        }
        return null;
    }

    public static void main(String[] args) throws MyException {
    /*        AutoClose autoClose = new AutoClose();
            try {
                autoClose.work();
            } finally {
                autoClose.close();
            }*/
        // 这个可以把所有的异常全部抛出来
        try (AutoClose autoClose1 = new AutoClose()) {
            autoClose1.work();
        }
    }
}
