package com.serendipity.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件的拷贝
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {
        // 定义输入流
        File file = new File("netty/src/NIOFIleChannel01.txt");
        FileInputStream inputStream = new FileInputStream(file);
        // 文件数据已经在管道里面了
        FileChannel inputChannel = inputStream.getChannel();
        // 初始化一个buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        inputChannel.read(byteBuffer);
        // 定义一个输出流
        FileOutputStream outputStream = new FileOutputStream("netty/src/NIOFIleChannel02.txt");
        FileChannel outputStreamChannel = outputStream.getChannel();
        byteBuffer.flip();
        outputStreamChannel.write(byteBuffer);
        inputStream.close();
        outputStream.close();
    }
}
