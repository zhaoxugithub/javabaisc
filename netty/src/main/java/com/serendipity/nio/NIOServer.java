package com.serendipity.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 模拟的是客户端往服务端发送数据
 * 1.OP_ACCEPT:有新的网络连接可以accept,值为16
 * 2.OP_CONNECT:代表连接已经建立，值为8
 * 3.OP_READ:代表读操作，值为1
 * 4.OP_WRITE:代表写操作：值为4
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 创建一个服务端ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 创建一个Selector
        Selector selector = Selector.open();
        // 将ServerSocketChannel设置成非阻塞
        serverSocketChannel.configureBlocking(false);
        // 指明服务端的端口地址
        serverSocketChannel.socket()
                           .bind(new InetSocketAddress(8889));
        // 将ServerSocketChannel注册到Selector中，关心事件为是是否有连接OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 进行循环监听
        while (true) {
            // 如果监听没有客户端进行连接selector==0
            if (selector.select(3000) == 0) {
                System.out.println("暂时没有客户端连接。。。。");
                continue;
            }
            // 说明有客户端连接到服务端
            // selectionKeys里面包含着所有的客户端连接信息socketChannel
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 创建迭代器
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            // 遍历所有的selectionKey
            while (iterator.hasNext()) {
                // 获取一个selectionKey(里面包含channel的连接事件类型)
                SelectionKey selectionKey = iterator.next();
                // 如果是连接类型的事件
                if (selectionKey.isAcceptable()) {
                    // 创建一个socketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 设置成非阻塞
                    socketChannel.configureBlocking(false);
                    // 将socketChannel注册到selector中，并且绑定这个socketChannel为只读事件，为这个channel分配缓存
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                // 如果是可读的连接事件
                if (selectionKey.isReadable()) {
                    // 获取这个SocketChannel
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    // 获取这个socketChannel的内存
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    // 读取到buffer
                    channel.read(byteBuffer);
                    System.out.println("获取客户端发送过来的数据:" + new String(byteBuffer.array()));
                }
                iterator.remove();
            }
        }
    }
}
