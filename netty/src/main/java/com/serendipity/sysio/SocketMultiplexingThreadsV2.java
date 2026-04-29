package com.serendipity.sysio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Socket 多路复用多线程模型 V2 —— 模拟 Netty Reactor 主从多线程模型
 * <p>
 * 架构：
 * - boss Group（1 个线程）：负责 accept 新连接
 * - worker Group（3 个线程）：负责处理已建立连接的读写 IO
 * - 每个 EventLoop 绑定一个 Selector + 一个线程 + 一个任务队列
 * <p>
 * 工作流程：
 * 1. boss EventLoop 的 selector 注册 OP_ACCEPT，监听到新连接后，
 * 通过 chooser 选择一个 worker EventLoop，将连接注册到该 worker 的 selector 上
 * 2. worker EventLoop 的 selector 注册 OP_READ，等待客户端数据到达
 * 3. 每次 select() 返回后，先处理 IO 事件，再执行任务队列中的任务
 */
public class SocketMultiplexingThreadsV2 {
    public static void main(String[] args) throws IOException {
        // boss 组：1 个线程，负责 accept 连接
        EventLoopGroup boss = new EventLoopGroup(1);
        // worker 组：3 个线程，负责处理读写 IO
        EventLoopGroup worker = new EventLoopGroup(3);
        ServerBootStrap b = new ServerBootStrap();
        b.group(boss, worker)
                .bind(9090);
        // 阻塞主线程，保持服务运行
        System.in.read();
    }
}

/**
 * 服务端启动引导类 —— 模拟 Netty ServerBootstrap
 * <p>
 * 负责配置 boss 和 worker 两个 EventLoopGroup，将 ServerSocketChannel
 * 注册到 boss EventLoop 的 Selector 上，监听 OP_ACCEPT 事件。
 */
class ServerBootStrap {
    private EventLoopGroup group;
    private EventLoopGroup chiledGroup;
    ServerAcceptr sAcceptr;

    public ServerBootStrap group(EventLoopGroup boss, EventLoopGroup worker) {
        group = boss;
        chiledGroup = worker;
        return this;
    }

    /**
     * 绑定端口并启动服务
     * <p>
     * 1. 创建 ServerSocketChannel，配置非阻塞，绑定端口
     * 2. 创建 ServerAcceptr（accept 事件的处理器）
     * 3. 从 boss group 中选择一个 EventLoop
     * 4. 将注册操作封装为 task 提交给 EventLoop 执行（确保线程安全）
     */
    public void bind(int port) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(port));
        sAcceptr = new ServerAcceptr(chiledGroup, server);
        EventLoop eventloop = group.chosser();
        // 将注册 ServerSocketChannel 到 Selector 的操作包装成任务，提交到 EventLoop 执行。
        // 这里用了两层 execute 的写法，模拟 Netty 中 register 的异步操作
        eventloop.execute(new Runnable() {
            @Override
            public void run() {
                eventloop.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            eventloop.name = Thread.currentThread() + eventloop.name;
                            System.out.println("bind...server...to " + eventloop.name);
                            // 注册 OP_ACCEPT，attachment 为 ServerAcceptr 处理器
                            server.register(eventloop.selector, SelectionKey.OP_ACCEPT, sAcceptr);
                        } catch (ClosedChannelException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}

/**
 * EventLoop 组 —— 模拟 Netty EventLoopGroup
 * <p>
 * 管理一组 EventLoop，通过轮询（Round-Robin）方式分配连接。
 * 一个 EventLoopGroup 相当于一个固定大小的线程池，每个 EventLoop 绑定一个线程。
 */
class EventLoopGroup {
    /** 用于轮询计数的原子变量 */
    AtomicInteger cid = new AtomicInteger(0);
    /** 持有的 EventLoop 数组 */
    EventLoop[] childrens = null;

    EventLoopGroup(int nThreads) {
        childrens = new EventLoop[nThreads];
        for (int i = 0; i < nThreads; i++) {
            childrens[i] = new EventLoop("T" + i);
        }
    }

    /**
     * 轮询选择一个 EventLoop（Round-Robin）
     * @return 选中的 EventLoop
     */
    public EventLoop chosser() {
        return childrens[cid.getAndIncrement() % childrens.length];
    }
}

/**
 * IO 事件处理器接口 —— 对应 Netty 中的 ChannelHandler 概念
 * <p>
 * 将 IO 事件的业务逻辑封装到 Handler 中，作为 Selector 的 attachment，
 * select 返回后直接从 key 中取出 Handler 调用，实现事件分发。
 */
interface Handler {
    void doRead();
}

/**
 * 客户端连接读处理器 —— 处理已建立连接的读事件
 * <p>
 * 读取客户端发送的数据，打印到控制台，并回写数据。
 */
class ClientReader implements Handler {
    SocketChannel key;

    ClientReader(SocketChannel server) {
        this.key = server;
    }

    @Override
    public void doRead() {
        ByteBuffer data = ByteBuffer.allocateDirect(4096);
        try {
            key.read(data);
            data.flip();
            byte[] dd = new byte[data.limit()];
            data.get(dd);
            // 打印客户端发送的消息
            System.out.println(new String(dd));
            data.clear();
            // 回写 10 次 "a" 给客户端
            for (int i = 0; i < 10; i++) {
                data.put("a".getBytes());
                data.flip();
                key.write(data);
                data.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 服务端接受连接处理器 —— 处理新连接接入事件
 * <p>
 * 当 ServerSocketChannel 有 OP_ACCEPT 事件时：
 * 1. 接受新连接（SocketChannel）
 * 2. 从 worker group 中选一个 EventLoop
 * 3. 将客户端 SocketChannel 注册到该 EventLoop 的 Selector 上，注册 OP_READ 事件
 */
class ServerAcceptr implements Handler {
    ServerSocketChannel key;
    EventLoopGroup cGroup;

    ServerAcceptr(EventLoopGroup cGroup, ServerSocketChannel server) {
        this.key = server;
        this.cGroup = cGroup;
    }

    public void doRead() {
        try {
            final EventLoop eventLoop = cGroup.chosser();
            final SocketChannel client = key.accept();
            client.configureBlocking(false);
            // 禁用 Nagle 算法，减少延迟
            client.setOption(StandardSocketOptions.TCP_NODELAY, true);
            final ClientReader cHandler = new ClientReader(client);
            // 将注册操作提交到 worker EventLoop 执行，保证线程安全
            eventLoop.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("socket...send...to " + eventLoop.name + " client port : " + client.socket()
                                .getPort());
                        client.register(eventLoop.selector, SelectionKey.OP_READ, cHandler);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 事件循环 —— 模拟 Netty 的 NioEventLoop
 * <p>
 * 核心组件，每个 EventLoop 包含：
 * 1. 一个 Selector —— 用于 IO 多路复用
 * 2. 一个 Thread —— 执行 select 循环和任务
 * 3. 一个 BlockingQueue —— 存放外部提交的任务
 * <p>
 * 工作循环（run 方法）：
 * 1. selector.select() —— 阻塞等待 IO 事件
 * 2. 处理就绪的 SelectionKey，调用对应的 Handler
 * 3. 处理任务队列中的任务（runrTask）
 */
class EventLoop implements Executor {
    /** 当前 EventLoop 绑定的 Selector */
    Selector selector;
    /** 当前 EventLoop 绑定的线程 */
    Thread thread = null;
    /** 任务队列，存放外部提交的 Runnable */
    BlockingQueue events = new LinkedBlockingQueue();
    /** 未启动状态 */
    int NOT_STARTED = 1;
    /** 已启动状态 */
    int STARTED = 2;
    /** 线程状态标记，初始为 NOT_STARTED */
    AtomicInteger STAT = new AtomicInteger(1);
    /** EventLoop 名称 */
    String name;

    public EventLoop(String name) {
        try {
            this.name = name;
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 事件循环主方法 —— 在一个线程中运行的死循环
     * <p>
     * 每次循环：
     * 1. selector.select() 等待 IO 事件（可通过 wakeup 唤醒）
     * 2. 遍历 selectedKeys，取出 attachment（Handler）并调用 doRead()
     * 3. 执行任务队列中的任务（最多 5 个，每个最多等 10ms）
     */
    public void run() throws InterruptedException, IOException {
        System.out.println("server已经开始：");
        for (; ; ) {
            // select 阻塞，等待 IO 事件就绪；外部提交任务时会通过 wakeup() 唤醒
            int nums = selector.select();
            if (nums > 0) {
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();
                    // 获取 attachment，即注册时绑定的 Handler
                    Handler handler = (Handler) key.attachment();
                    // 这里预留了类型判断逻辑，目前统一调用 doRead()
                    handler.doRead();
                }
            }
            // 处理任务队列中积压的任务（如注册 channel、提交的业务逻辑等）
            runrTask();
        }
    }

    /**
     * 提交任务到 EventLoop 执行
     * <p>
     * 1. 将任务放入阻塞队列
     * 2. 调用 selector.wakeup() 唤醒 select() 阻塞，让任务能及时执行
     * 3. 如果不是在 EventLoop 线程内调用，且 EventLoop 尚未启动，则创建并启动新线程
     */
    @Override
    public void execute(Runnable task) {
        try {
            events.put(task);
            // 唤醒 selector，使 select() 立即返回，以便及时处理任务
            this.selector.wakeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 如果调用方不在 EventLoop 线程中，且 EventLoop 还未启动，则启动线程
        if (!inEventLoop() && STAT.incrementAndGet() == STARTED) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        thread = Thread.currentThread();
                        EventLoop.this.run();
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 运行任务队列中的任务
     * <p>
     * 最多尝试 5 次 poll，每次最多等待 10ms。
     * poll 到任务后从队列中移除并执行。
     */
    public void runrTask() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Runnable task = (Runnable) events.poll(10, TimeUnit.MILLISECONDS);
            if (task != null) {
                events.remove(task);
                task.run();
            }
        }
    }

    /**
     * 判断当前线程是否是 EventLoop 自身绑定的线程
     * @return true 表示当前在 EventLoop 线程内执行
     */
    private boolean inEventLoop() {
        return thread == Thread.currentThread();
    }
}