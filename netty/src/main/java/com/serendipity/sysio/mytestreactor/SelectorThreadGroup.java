package com.serendipity.sysio.mytestreactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/5/30 18:47
 * FileName: SelectorThreadGroup
 * Description: io.mytestreactor
 */
public class SelectorThreadGroup {

    private SelectorThread[] selectorThreads;
    private ServerSocketChannel server;
    private AtomicInteger xid = new AtomicInteger(0);
    private SelectorThreadGroup stg = this;

    public void setWorker(SelectorThreadGroup stg) {
        this.stg = stg;
    }

    public SelectorThreadGroup(int num) {
        selectorThreads = new SelectorThread[num];
        for (int i = 0; i < num; i++) {
            selectorThreads[i] = new SelectorThread(this);
            new Thread(selectorThreads[i]).start();
        }
    }

    public void bind(int port) {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            //server注册到哪个selector上呢？？？
            nextSelectorV3(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //无论serverSocket socket 都直接复用这个方法
    public void nextSelector(Channel channel) {
        SelectorThread st = next();
        // 通过队列传递消息
        st.lbq.add(channel);
        //2.通过打断阻塞，让对应的线程去自己的在打断后的完成注册selector
        //这样就可以让wakeup和select方法变成线性的
        st.selector.wakeup();
        //下面这个方法是采用上面这个方法的原因
       /* //重点channel 有可能是server也可能是client
        ServerSocketChannel server = (ServerSocketChannel) channel;
        try {
            //呼应上， int nums = selector.select();//阻塞，wakeup
            server.register(st.selector, SelectionKey.OP_ACCEPT);
            //唤醒selector的select() 方法，立刻放回不阻塞，注意一定要放在register方法下面

            //但是这样写还是有问题，如果wakeup方法比select()方法先调用，则还是会阻塞！！！！
            //所以需要采用其他的方式，采用一个队列进行通信
            st.selector.wakeup();
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }*/
    }


    public void nextSelectorV2(Channel channel) {
        try {
            if (channel instanceof ServerSocketChannel) {
                selectorThreads[0].lbq.put(channel);
                selectorThreads[0].selector.wakeup();
            } else {
                SelectorThread st = nextV2();
                // 通过队列传递消息
                st.lbq.add(channel);
                //2.通过打断阻塞，让对应的线程去自己的在打断后的完成注册selector
                //这样就可以让wakeup和select方法变成线性的
                st.selector.wakeup();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    //server组应该在boss组选择，而不是在work组选择
    public void nextSelectorV3(Channel channel) {
        try {
            if (channel instanceof ServerSocketChannel) {
                //boss 分组里面的线程
                SelectorThread selectorThread = next();
                selectorThread.lbq.put(channel);
                //注册server的过程也是从Boss分组里面拿出一个线程进行注册。。。
                selectorThread.setWorker(stg);
                selectorThread.selector.wakeup();
            } else {
                //如果是客户端进行注册
                SelectorThread stv3 = nextV3();
                stv3.lbq.put(channel);
                stv3.selector.wakeup();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public SelectorThread next() {
        //轮询就会很尴尬，线程可能会倾斜
        int index = xid.incrementAndGet() % selectorThreads.length;
        return selectorThreads[index];
    }


    //下面这个版本只会调用返回index[1] 和index[2]
    //可以把index[0]单独拿出来做服务端的注册
    public SelectorThread nextV2() {
        //轮询就会很尴尬，线程可能会倾斜
        int index = xid.incrementAndGet() % (selectorThreads.length - 1);
        return selectorThreads[index + 1];
    }

    public SelectorThread nextV3() {
        //轮询就会很尴尬，线程可能会倾斜
        int index = xid.incrementAndGet() % stg.selectorThreads.length;
        return stg.selectorThreads[index];
    }

}
