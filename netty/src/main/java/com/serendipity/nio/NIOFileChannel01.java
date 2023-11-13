package com.serendipity.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 11931
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {
        // 创建输出流路径
        FileOutputStream outputStream = new FileOutputStream("netty/src/NIOFIleChannel01.txt");
        // 获取channel,这个时候的管道是空的
        FileChannel channel = outputStream.getChannel();
        String string = "aaa";
        // 创建一个buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 将字符串添加到buffer
        buffer.put(string.getBytes());
        // 翻转
        buffer.flip();
        // 将buffer中的数据添加到channel
        channel.write(buffer);
        // 关闭流
        outputStream.close();
    }
}
