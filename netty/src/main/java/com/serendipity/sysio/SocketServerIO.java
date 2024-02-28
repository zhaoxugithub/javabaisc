package com.serendipity.sysio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerIO {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9090, 20);
        System.out.println("step1: new ServerSocket(9090) ");
        while (true) {
            // 返回的是客户端连接的fd
            Socket client = server.accept();  // 阻塞1
            System.out.printf("clientIP:%s,clientPort:%s%n\n", client.getLocalAddress()
                                                                     .getHostAddress(), client.getPort());
            new Thread(() -> {
                System.out.printf("thread:%s\n", Thread.currentThread()
                                                       .getName());
                InputStream in;
                try {
                    in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while (true) {
                        System.out.println("accept client data....");
                        String dataline = reader.readLine(); // 阻塞2
                        if (null != dataline) {
                            System.out.println(dataline);
                        } else {
                            client.close();
                            break;
                        }
                    }
                    System.out.println("客户端断开");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
