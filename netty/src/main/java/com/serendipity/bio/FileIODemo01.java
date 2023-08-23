package com.serendipity.bio;

import ch.qos.logback.classic.net.SimpleSocketServer;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ClassName FileIODemo01
 * Description TODO
 * Author 11931
 * Date 2023-08-17:3:38
 * Version 1.0
 **/
public class FileIODemo01 {

    @Test
    public void test01() throws IOException {

        // Unicode 默认中文3个字节
        FileInputStream fileInputStream = new FileInputStream("D:\\document\\idea\\javabaisc\\netty\\src\\main\\java\\com\\serendipity\\bio\\file01.txt");
        // 获取可用的字节数
        int available = fileInputStream.available();
        System.out.println(available);
        byte[] bytes = fileInputStream.readAllBytes();
        String s = new String(bytes);
        System.out.println(s);
    }

    @Test
    public void test02() throws IOException {
        /*
            随机读写
         */
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\document\\idea\\javabaisc\\netty\\src\\main\\java\\com\\serendipity\\bio\\file01.txt", "rw");
        System.out.println("读取之前的偏移量：" + randomAccessFile.getFilePointer() + ",当前读取到的字符" + (char) randomAccessFile.read() + "，读取之后的偏移量：" + randomAccessFile.getFilePointer());
        randomAccessFile.seek(6);
        System.out.println("读取之前的偏移量：" + randomAccessFile.getFilePointer() + ",当前读取到的字符" + (char) randomAccessFile.read() + "，读取之后的偏移量：" + randomAccessFile.getFilePointer());
        randomAccessFile.write(new byte[]{'H', 'I', 'J', 'K'});
        randomAccessFile.seek(0);
        System.out.println("读取之前的偏移量：" + randomAccessFile.getFilePointer() + ",当前读取到的字符" + (char) randomAccessFile.read() + "，读取之后的偏移量：" + randomAccessFile.getFilePointer());
    }

    public void test03() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
    }
}
