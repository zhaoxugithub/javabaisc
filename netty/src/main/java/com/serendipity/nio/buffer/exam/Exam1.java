package com.serendipity.nio.buffer.exam;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static com.serendipity.utils.ByteBufferUtil.debugAll;


@Slf4j
public class Exam1 {

    /*
网络上有多条数据发送给服务端，数据之间使用 \n 进行分隔
但由于某种原因这些数据在接收时，被进行了重新组合，例如原始数据有3条为

* Hello,world\n
* I'm zhangsan\n
* How are you?\n

变成了下面的两个 byteBuffer (黏包，半包)

* Hello,world\nI'm zhangsan\nHo
* w are you?\n

现在要求你编写程序，将错乱的数据恢复成原始的按 \n 分隔的数据
     */

    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        //                     11            24
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);
        source.put("w are you?\nhaha!\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {
        // 1. 切换到读模式
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            byte b = source.get(i);
            if (b == '\n') {
                log.debug("-------------------- 有 \\n 的一个循环[pos:{}, lim:{}, cap:{}] -----------------------", source.position(), source.limit(), source.capacity());
                System.out.println("IIIII i  = " + i);
                int length = i + 1 - source.position();
                System.out.println("LLLLL 可读长度 length  = " + length);
                // 完整 消息 存入 新 buffer
                ByteBuffer target = ByteBuffer.allocate(length);
                System.out.print("position=[");
                // 从 source 读 ，写入 target
                for (int j = 0; j < length; j++) {
                    target.put(source.get()); // 每一次 get 时， position++
                    System.out.print(source.position() + ",");
                }
                System.out.println("]");
                // debugAll(target);

            }
        }
        log.debug("--------------------  循环结束：[pos:{}, lim:{}, cap:{}] -----------------------", source.position(), source.limit(), source.capacity());
        // 未读完部分向前压缩，以便在下一次调用该方法时继续读取剩余的数据。
        source.compact();
        log.debug("-------------------- 未读完部分向前压缩：[pos:{}, lim:{}, cap:{}] -----------------------", source.position(), source.limit(), source.capacity());
    }


    @Test
    public void onlyTest1() {
        // String s = "Hello,world\n";
        String s = "\n";
        byte[] b1 = s.getBytes();
        System.out.println(Arrays.toString(b1));
    }

    //  byte 赋值方式有  空字符,字符形式，十进制、十六进制
    @Test
    public void onlyTest2() {
        ByteBuffer buffer = ByteBuffer.allocate(16);

        byte b1 = 'a';
        buffer.put(b1);
        System.out.println(buffer.position());

        byte b2 = 0;
        buffer.put(b2); // 十进制  0  是 空字符
        System.out.println(buffer.position());

        byte b3 = ' ';
        buffer.put(b3); // 空格字符 ' '  0  是 （空格）
        System.out.println(buffer.position());

        byte b4 = '\n';
        buffer.put(b4); // 字符 '\n'   是 换行键
        System.out.println(buffer.position());

        byte b5 = 0x62; // 十六进制 0x62 是 [b]
        buffer.put(b5);
        System.out.println(buffer.position());

        byte b6 = 63;  // 十进制    63   是 [?]
        // buffer.put(b6);
        System.out.println(buffer.position());
        debugAll(buffer);
    }
}
