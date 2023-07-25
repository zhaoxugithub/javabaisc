package com.serendipity.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer01 {
    public static void main(String[] args) throws IOException {
        /**
         * Java通过Executors提供四种线程池，分别为：
         * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
         * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
         * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
         * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
         */
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        final ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务端启动...");
        while (true) {
            // 如果客户端没有建立连接，就会阻塞在这
            System.out.println("线程信息 id=" + Thread.currentThread().getId() + "name =" + Thread.currentThread().getName());
            System.out.println("等待客户端连接...");
            final Socket accept = serverSocket.accept();
            System.out.println("客户端已经建立连接...");
            newCachedThreadPool.execute(new Runnable() {
                public void run() {
                    handle(accept);
                }
            });
        }
    }

    public static void handle(Socket socket) {
        System.out.println("线程信息 id=" + Thread.currentThread().getId() + "name =" + Thread.currentThread().getName());
        try {
            // 获取客户端的输入流
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            while (true) {
                System.out.println("线程信息 id=" + Thread.currentThread().getId() + "name =" + Thread.currentThread().getName());
                System.out.println("真正读取客户端的数据...");

                // 系统调用，一个字节一个字节的去读取
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭socket连接...");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
