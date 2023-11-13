package com.serendipity.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

@Slf4j
public class NIOClient {

    private static final String HOSTNAME = "127.0.0.1";
    private static final int PORT = 6666;

    public static void main(String[] args) throws IOException {
        // 定义一个SocketChannel,获取一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        // 设置成非阻塞
        socketChannel.configureBlocking(false);
        // 提供服务器的ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress(HOSTNAME, PORT);
        // 连接服务器
        // If this channel is in blocking mode then an invocation of this
        // method will block until the connection is established or an I/O error
        // occurs.
        boolean connect = socketChannel.connect(inetSocketAddress);
        // 如果没有连接成功
        if (!connect) {
            // Finishes the process of connecting a socket channel.
            // If this channel is already connected then this method will not block
            // and will immediately return <tt>true</tt>.
            while (!socketChannel.finishConnect()) {
                log.info("client no blocking,can do other thing...");
            }
        }
        // 连接成功之后的逻辑
        String str = "hello,尚硅谷。。";
        // 申请与字符串大小相同的buffer
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        // 将buffer写入到socketChannel中
        socketChannel.write(buffer);
        System.in.read();
    }
}
