package com.serendipity.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer02 {

    public static void main(String[] args) throws IOException {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        final ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务端启动...");
    }
}
