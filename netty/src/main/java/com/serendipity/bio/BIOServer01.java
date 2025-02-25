package com.serendipity.bio;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Java通过Executors提供四种线程池，分别为：
 * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
 */
@Slf4j
public class BIOServer01 {

    private static final int SERVER_PORT = 6666; // 服务器端口
    private static final int THREAD_POOL_SIZE = 10; // 线程池大小

    public static void main(String[] args) {
        connectionServer();
    }

    public static void connectionServer() {
        // 创建固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        // 使用 try-with-resources 管理 ServerSocket
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            log.info("Server started on port {}...", SERVER_PORT);
            while (true) {
                log.info("Waiting for client connection, Thread ID={}, Thread Name={}", Thread.currentThread().threadId(), Thread.currentThread().getName());
                // 接受客户端连接
                Socket clientSocket = serverSocket.accept();
                log.info("Client connected: {}", clientSocket.getRemoteSocketAddress());
                // 提交任务到线程池
                threadPool.execute(() -> handle(clientSocket));
            }
        } catch (IOException e) {
            log.error("Server error: {}", e.getMessage(), e);
        } finally {
            // 优雅关闭线程池
            shutdownThreadPool(threadPool);
            log.warn("Server shut down.");
        }
    }

    private static void shutdownThreadPool(ExecutorService threadPool) {
        threadPool.shutdown(); // 停止接受新任务
        try {
            // 等待现有任务完成
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow(); // 强制终止任务
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt(); // 恢复中断状态
        }
    }

    public static void handle(Socket socket) {
        // 记录线程信息
        log.info("Thread ID={}, Thread Name={}", Thread.currentThread().threadId(), Thread.currentThread().getName());
        // 使用 try-with-resources 自动关闭资源
        try (InputStream inputStream = socket.getInputStream(); Socket autoCloseSocket = socket) { // 将 socket 也纳入自动关闭范围
            byte[] buffer = new byte[1024]; // 缓冲区大小可配置
            int bytesRead;
            // 读取客户端数据
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String msg = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8); // 指定字符集
                log.info("Thread ID={}, Thread Name={}, Received Data={}", Thread.currentThread().threadId(), Thread.currentThread().getName(), msg);
            }
        } catch (IOException e) {
            // 记录异常日志
            log.error("Error handling socket connection: {}", e.getMessage(), e);
        } finally {
            // 记录关闭日志
            log.warn("Closing socket connection...");
        }
    }
}
