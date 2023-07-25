package com.serendipity.sysio.mytestreactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/5/30 18:46
 * FileName: SelectorThread
 * Description: io.mytestreactor
 */
public class SelectorThread implements Runnable {
    /*
        每个线程对应一个Selector.
        多线程的情况下，该主机，该程序的并发客户端被分配到多个selector上
        注意：每个客户端，只能绑定到其中一个selector上
        其实不会有交互问题
     */

    //wake up 和select的通信队列
    LinkedBlockingQueue<Channel> lbq = new LinkedBlockingQueue<Channel>();
    Selector selector = null;
    private SelectorThreadGroup stg;

    SelectorThread(SelectorThreadGroup stg) {
        try {
            selector = Selector.open();
            this.stg = stg;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //Loop
        while (true) {
            try {
//                System.out.println(Thread.currentThread().getName() + "before:" + selector.keys().size());
                //1.select
                int nums = selector.select();//阻塞，wakeup
//                System.out.println(Thread.currentThread().getName() + "after:" + selector.keys().size());
                //2.处理selectKeys
                if (nums > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {  //线程处理过程
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) {  //复杂，接受 客户端的过程（接受之后，要注册，多线程下，新的客户端，注册到哪里呢？？// ）
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readHandler(key);
                        }
                    }
                }
                //3.处理一些task,Server端和客户端注册
                if (!lbq.isEmpty()) {
                    Channel channel = lbq.take();
                    //server端进行注册
                    if (channel instanceof ServerSocketChannel) {
                        System.out.println(Thread.currentThread().getName() + ":register....");
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) channel;
                        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                    } else if (channel instanceof SocketChannel) {
                        //客户端连接进行注册
                        System.out.println(Thread.currentThread().getName() + ":client register...");
                        SocketChannel client = (SocketChannel) channel;
                        ByteBuffer buffer = ByteBuffer.allocate(8192);
                        client.register(selector, SelectionKey.OP_READ, buffer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void readHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName() + ":readHandler");
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
        SocketChannel client = (SocketChannel) key.channel();
        byteBuffer.clear();
        while (true) {
            try {
                int read = client.read(byteBuffer);
                if (read > 0) {
                    byteBuffer.flip();
                    while (byteBuffer.hasRemaining()) {
                        client.write(byteBuffer);
                    }
                    byteBuffer.clear();
                } else if (read == 0) {
                    break;
                } else {
                    //客户端断开连接了
                    System.out.println("client:" + client.getRemoteAddress() + "closed....");
                    client.close();
                    key.cancel();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void acceptHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName() + ":accept  handler...");
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        try {
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            //如果是单线程的话，这里应该是client.register()到当前的这个selector上
            //但是是多线程，所以这里应该注册到其他selector上。。
            //choose a selector and register
            stg.nextSelectorV2(client);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setWorker(SelectorThreadGroup stg) {
        this.stg = stg;
    }
}
