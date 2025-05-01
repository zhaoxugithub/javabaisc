package com.serendipity.nio.serverdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

@Slf4j
public class Server {
    /*
    使用 nio 来理解  阻塞模式
    这里使用 单线程
    */

    @Test
    public void testServer1() throws IOException {
        // 0. 创建全局的 ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 1. 创建一个 服务器 对象
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 2. 绑定一个 监听端口
        ssc.bind(new InetSocketAddress(8080));
        // 3. 连接 集合
        ArrayList<SocketChannel> channels = new ArrayList<>();
        // accept 能 多次调用 使用 wile循环
        while (true) {
            log.debug("connecting    #########################################################################################################################################################################################");
            // 4. 建立 客户端连接,通过 TCP三次握手， accept
            // 返回一个 SocketChannel 读写通道，方便与客户端通信 进行数据读写
            SocketChannel sc = ssc.accept(); // accept 是阻塞的方法，让线程暂停 【等待客户端建立连接】
            log.debug("已连接... {}", sc);
            channels.add(sc);
            // 5. 接收 客户端的 数据， 进行遍历处理
            // 这里有个 设计缺陷，按 连接通道 顺序遍历，如果 第一个没数据一直阻塞，第二个有数据 也 读不到
            for (SocketChannel channel : channels) {
                log.debug("before read ----------------------------------------  {}", channel);
                int len = channel.read(buffer); // read 是阻塞的方法，让线程暂停 【等待客户端发送数据】

                //  读模式 (position下标 = 0) 进行 从头get
                buffer.flip();
                //                debugAll(buffer);
                final CharBuffer string = Charset.defaultCharset().decode(buffer);
                System.out.println("读取内容： " + string);
                buffer.clear(); //  写模式(position下标 = 容量)，继续 循环写
                log.debug("after read ----------------------------------------  {}", channel);
                /**
                 * 当执行 这行以后 ，将进入下一个循环
                 * 打印： 20:06:38 [DEBUG] [main] c.a.n.c.Server - connecting...
                 * 进行 阻塞中，线程暂停
                 */
            }
            log.debug("connecting end #########################################################################################################################################################################################");
        }
    }
}
