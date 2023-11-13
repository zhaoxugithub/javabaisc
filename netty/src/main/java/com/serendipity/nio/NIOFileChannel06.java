package com.serendipity.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer 可让文件直接在内存（堆外内存）进行修改，操作系统不需要进行拷贝
 *
 * @author 11931
 */
@Slf4j
public class NIOFileChannel06 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("netty/src/randomAccessFile.txt", "rw");
        // 获取对应的通道
        FileChannel channel = randomAccessFile.getChannel();
        /**
         * 参数1：FileChannel.MapMode.READ_WRITE  使用的读写模式
         * 参数2：0可以直接修改的起始位置
         * 参数3：5 是映射到内存的的大小，即将1.txt的多少个字节映射到内存可以直接修改的内存的范围是0-5
         */
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0, (byte) 'H');
        map.put(3, (byte) 'A');
        randomAccessFile.close();
        log.info("modify successful...");
    }
}
