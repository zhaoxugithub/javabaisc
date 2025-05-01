package com.serendipity.nio.buffer;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 11931
 */
@Slf4j
public class TestByteBuffer {
    /**
     * 注意：文件可能 很大， 缓冲区不能跟随文件的大小 设置的很大
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(System.getProperty("user.dir"));
        // FIleChannel   一个数据的读写通道
        // 1. 输入输出流， 2. RandomAccessFIle 随机读写文件类
        try (FileChannel channel = new FileInputStream("./netty-1-nio/data.txt").getChannel()) {
            // 准备缓冲区 存储 读取数据
            ByteBuffer buffer = ByteBuffer.allocate(10); // 划分一个十个字节 的内存【单位：字节】
            while (true) {
                // 从 channel 里读取数据，准备 向 buffer 写入
                int len = channel.read(buffer); // 返回值：读到的实际字节数 ， -1则是没有数据了
                log.debug("此次 读取到的字节长度是 {}", len);
                if (len == -1) break;
                //
                buffer.flip(); // 切换至 读模式 [ position指针指向开头， limit指向写入的最后位置 (内存长度) ]
                while (buffer.hasRemaining())
                    // 检查是否有剩余的数据
                {
                    byte b = buffer.get();  // 一次读一个字节
                    System.out.println((char) b); // 强转字符 并打印
                }
                // buffer.clear();// 一次 循环后  切换为 写 模式 [ 复位了 position limit 指针 (动不了 实际数据) ]
                buffer.compact(); // 从 上次未读完的地方 向前移动，并沿着 未读完数据的最后一位往后写
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
