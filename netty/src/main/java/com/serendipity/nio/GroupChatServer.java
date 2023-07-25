package com.serendipity.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public GroupChatServer() {
        try {
            //创建一个serverSocketChannel
            serverSocketChannel = ServerSocketChannel.open();
            //创建一个selector
            selector = Selector.open();
            //绑定一个端口
            serverSocketChannel.socket().bind(new InetSocketAddress(6667));
            //设置非阻塞
            serverSocketChannel.configureBlocking(false);
            //serverSocket绑定selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            System.out.println("初始化错误");
            e.printStackTrace();
        }
    }

    public void listen() {
        System.out.println("当前线程：" + Thread.currentThread().getName());
        while (true) {
            try {
                //阻塞两秒
                int select = selector.select(2000);
                //说明有客户端进行连接
                if (select > 0) {
                    //获取客户端连接关系对象集合
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isAcceptable()) {
                            //todo 打印selectionKey.channel
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                            //提示
                            System.out.println(socketChannel.getRemoteAddress() + "上线了。。");
                        }
                        if (selectionKey.isReadable()) {
                            readData(selectionKey);
                        }

                        //如果这个selectionKey已经被处理过之后就可以remove了
                        selectionKeys.remove(selectionKey);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }

    public void readData(SelectionKey selectionKey) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
            //todo ? 如果buffer中的内存不够怎么办？
            int read = channel.read(byteBuffer);
            if (read > 0) {
                String msg = new String(byteBuffer.array());
                System.out.println("from 客户端：" + msg);
                sendToOtherClient(selectionKey, msg);
            }
        } catch (IOException e) {
            try {
                //提示了
                System.out.println(channel.getRemoteAddress() + "下线了。。");
                selectionKey.cancel();
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * 发送到其他客户端
     *
     * @param selectionKey
     * @param msg
     */
    public void sendToOtherClient(SelectionKey selectionKey, String msg) throws IOException {
        System.out.println("服务器转发消息中。。。");
        System.out.println("服务器转发数据给客户端线程：" + Thread.currentThread().getName());
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
