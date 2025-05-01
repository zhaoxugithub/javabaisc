package com.serendipity.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public GroupChatServer() {
        try {
            // 创建一个serverSocketChannel
            serverSocketChannel = ServerSocketChannel.open();
            // 创建一个selector
            selector = Selector.open();
            // 绑定一个端口
            serverSocketChannel.socket().bind(new InetSocketAddress(8889));
            // 设置非阻塞
            serverSocketChannel.configureBlocking(false);
            // serverSocket绑定selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            log.error("init error={}", e.getMessage());
        }
    }

    public void listen() {
        log.info("currentThread is ={}", Thread.currentThread().getName());
        while (true) {
            try {
                // 阻塞两秒
                int select = selector.select(2000);
                // 说明有客户端进行连接
                if (select > 0) {
                    // 获取客户端连接关系对象集合
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isAcceptable()) {
                            // todo 打印selectionKey.channel
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                            log.warn(socketChannel.getRemoteAddress() + "上线了..");
                        }
                        if (selectionKey.isReadable()) {
                            readData(selectionKey);
                        }
                        // 如果这个selectionKey已经被处理过之后就可以remove了
                        selectionKeys.remove(selectionKey);
                    }
                }
            } catch (IOException e) {
                log.error("listen error={}", e.getMessage());
            }
        }
    }

    public void readData(SelectionKey selectionKey) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
            // todo ? 如果buffer中的内存不够怎么办？
            int read = channel.read(byteBuffer);
            if (read > 0) {
                String msg = new String(byteBuffer.array());
                log.info("from client:{}", msg);
                sendToOtherClient(selectionKey, msg);
            }
        } catch (IOException e) {
            try {
                log.info("remote address={},offline", channel.getRemoteAddress());
                selectionKey.cancel();
                channel.close();
            } catch (IOException ex) {
                log.error("offline={}", ex.getMessage());
            }
            log.error("readData error:{}", e.getMessage());
        }
    }

    /**
     * 发送到其他客户端
     *
     * @param selectionKey
     * @param msg
     */
    public void sendToOtherClient(SelectionKey selectionKey, String msg) throws IOException {
        log.info("server forwards messages to client...");
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        for (SelectionKey key : selectionKeys) {
            SelectableChannel channel = key.channel();
            if (channel instanceof SocketChannel && key != selectionKey) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                socketChannel.write(byteBuffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
