package com.serendipity.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class NIOFileChannel02 {
    public static void main(String[] args) throws IOException {
        File file = new File("netty/src/NIOFIleChannel01.txt");
        FileInputStream inputStream = new FileInputStream(file);
        // 如果是输入流，就表示文件内容已经被读取到channel管道里面
        FileChannel channel = inputStream.getChannel();
        // 分配相同大小的buffer空间
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        channel.read(byteBuffer);
        log.info(new String(byteBuffer.array()));
        inputStream.close();
    }
}
