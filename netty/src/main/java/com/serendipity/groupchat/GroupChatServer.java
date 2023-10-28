package com.serendipity.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private int port = 9990;

    public GroupChatServer() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server start...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void init() {
        while (true) {
            try {
                while (selector.select() > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    for (Iterator<SelectionKey> iterator = selectionKeys.iterator(); iterator.hasNext(); ) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readHandler(key);
                        }
                        iterator.remove();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void readHandler(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer bytebuffer = (ByteBuffer) key.attachment();
        bytebuffer.clear();
        try {

            while (true) {
                int read = client.read(bytebuffer);
                if (read > 0) {
                    bytebuffer.flip();
                    while (bytebuffer.hasRemaining()) {
                        byte[] bytes = new byte[bytebuffer.limit()];
                        bytebuffer.get(bytes);
                        String s = new String(bytes);
                        System.out.println(s);
                        sendToOtherClient(s, client);
                    }
                } else if (read == 0) {
                    // 读取完毕
                    break;
                } else {
                    // 客户端断开连接。。
                    System.out.println(client.getRemoteAddress() + "断开连接");
                    key.cancel();
                    client.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param msg  要发送的内容
     * @param self 客户端
     */
    private void sendToOtherClient(String msg, SocketChannel self) {
        Set<SelectionKey> keys = selector.keys();
        for (Iterator<SelectionKey> iterator = keys.iterator(); iterator.hasNext(); ) {
            SelectionKey next = iterator.next();
            SelectableChannel channel = next.channel();
            if (channel instanceof SocketChannel && channel != self) {
                SocketChannel client = (SocketChannel) next.channel();
                ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                try {
                    client.write(wrap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void acceptHandler(SelectionKey key) {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            SocketChannel client = channel.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ, byteBuffer);
            System.out.println("client " + client.getRemoteAddress() + "上线了");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new GroupChatServer().init();
    }
}
