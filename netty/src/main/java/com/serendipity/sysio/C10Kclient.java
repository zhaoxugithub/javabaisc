package com.serendipity.sysio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * @author: 马士兵教育
 * @create: 2020-06-06 15:12
 */
@Slf4j
@SuppressWarnings("all")
public class C10Kclient {
    public static void main(String[] args) {
        LinkedList<SocketChannel> clients = new LinkedList<>();
        InetSocketAddress serverAddr = new InetSocketAddress("192.168.1.102", 10999);
        // 端口号的问题：65535
        //  windows
        long startTime = System.currentTimeMillis();
        log.info("startTime={}", startTime);
        for (int i = 64500; i < 65000; i++) {
            try {
                SocketChannel client1 = SocketChannel.open();
                /*
                    SocketChannel client2 = SocketChannel.open();
                    linux中你看到的连接就是：
                    client...port: 10508
                    client...port: 10508
                 */
                client1.bind(new InetSocketAddress("192.168.1.100", i));
                //  192.168.150.1：10000   192.168.150.11：9090
                client1.connect(serverAddr);
                boolean c1 = client1.isOpen();
                clients.add(client1);
                /*
                    client2.bind(new InetSocketAddress("192.168.110.100", i));
                    192.168.110.100：10000  192.168.150.11：9090
                    client2.connect(serverAddr);
                    boolean c2 = client2.isOpen();
                    clients.add(client2);
               */
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("clients={}", clients.size());
        log.info(String.valueOf(System.currentTimeMillis() - startTime / 1000));
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
