package com.serendipity.sysio.mynetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class BytebufApi {

    public static void myBytebuf() {
        // 主要记住几点：是否池化，是否是堆内还是堆外
        // ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(8, 20);
        // pool
        // ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.heapBuffer(8, 20);
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.heapBuffer(8, 20);
        print(buf);
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        print(buf);
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        print(buf);
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        print(buf);
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        print(buf);
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        print(buf);
    }

    public static void print(ByteBuf buf) {
        System.out.println("buf.isReadable()    :" + buf.isReadable());
        System.out.println("buf.readerIndex()   :" + buf.readerIndex());
        System.out.println("buf.readableBytes() " + buf.readableBytes());
        System.out.println("buf.isWritable()    :" + buf.isWritable());
        System.out.println("buf.writerIndex()   :" + buf.writerIndex());
        System.out.println("buf.writableBytes() :" + buf.writableBytes());
        System.out.println("buf.capacity()  :" + buf.capacity());
        System.out.println("buf.maxCapacity()   :" + buf.maxCapacity());
        System.out.println("buf.isDirect()  :" + buf.isDirect());
        System.out.println("--------------");
    }

    /*
  客户端
  连接别人
  1，主动发送数据
  2，别人什么时候给我发？  event  selector
   */
    public static void loopExecutor() throws Exception {
        // group  线程池
        NioEventLoopGroup selector = new NioEventLoopGroup(2);
        selector.execute(() -> {
            try {
                for (; ; ) {
                    System.out.println("hello world001");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        selector.execute(() -> {
            try {
                for (; ; ) {
                    System.out.println("hello world002");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.in.read();
    }

    public void clientMode() throws Exception {
        NioEventLoopGroup thread = new NioEventLoopGroup(1);
        // 客户端模式：
        NioSocketChannel client = new NioSocketChannel();
        thread.register(client);  // epoll_ctl(5,ADD,3)
        // 响应式：
        ChannelPipeline p = client.pipeline();
        p.addLast(new MyInHandler());
        // reactor  异步的特征
        ChannelFuture connect = client.connect(new InetSocketAddress("192.168.1.101", 9991));
        ChannelFuture sync = connect.sync();
        ByteBuf buf = Unpooled.copiedBuffer("hello server".getBytes());
        ChannelFuture send = client.writeAndFlush(buf);
        send.sync();
        // 马老师的多线程
        sync.channel().closeFuture().sync();
        System.out.println("client over....");
    }

    public void clientMode2() {
        NioEventLoopGroup thread = new NioEventLoopGroup(1);
        NioSocketChannel client = new NioSocketChannel();
        thread.register(client);
        client.connect(new InetSocketAddress("192.168.1.101", 9991));
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world".getBytes());
        client.writeAndFlush(byteBuf);
        System.out.println("client over...");
    }

    public void clientMode3() throws InterruptedException {
        NioEventLoopGroup thread = new NioEventLoopGroup(1);
        NioSocketChannel client = new NioSocketChannel();
        thread.register(client);
        // reactor 异步的特征
        ChannelFuture connect = client.connect(new InetSocketAddress("192.168.1.101", 9991));
        ChannelFuture sync = connect.sync();
        // 异步发送
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world".getBytes());
        ChannelFuture send = client.writeAndFlush(byteBuf);
        send.sync();
        // 当服务端断开连接的时候就会断开，阻塞同步
        sync.channel().closeFuture().sync();
        System.out.println("client over...");
    }


    public void serverMode01() throws Exception {
        NioEventLoopGroup thread = new NioEventLoopGroup(1);
        NioServerSocketChannel server = new NioServerSocketChannel();
        ChannelPipeline pipeline = server.pipeline();
        pipeline.addLast(new MyAcceptHandler(thread, new MyInHandler()));
        thread.register(server);
        ChannelFuture bind = server.bind(new InetSocketAddress("192.168.1.100", 9991));
        bind.sync().channel().closeFuture().sync();
    }

    public void serverModel2() {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(1);
    }

    private class MyAcceptHandler extends ChannelInboundHandlerAdapter {
        private NioEventLoopGroup selector;
        private ChannelHandler channelHandler;

        public MyAcceptHandler(NioEventLoopGroup nioEventLoopGroup, ChannelHandler channelHandler) {
            this.selector = nioEventLoopGroup;
            this.channelHandler = channelHandler;
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("server  registed...");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("server active...");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            // 服务端接收到应该是SocketChannel
            SocketChannel client = (SocketChannel) msg;
            System.out.println(client.remoteAddress() + ":连接成功。。");
            // 把客户端注册进去
            ChannelPipeline pipeline = client.pipeline();
            pipeline.addLast(channelHandler);
            selector.register(client);
        }
    }

    @ChannelHandler.Sharable
    private class MyInHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRegistered(ChannelHandlerContext ctx) {
            System.out.println("client  registed...");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println("client active...");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
//        CharSequence str = buf.readCharSequence(buf.readableBytes(), CharsetUtil.UTF_8);
            CharSequence str = buf.getCharSequence(0, buf.readableBytes(), CharsetUtil.UTF_8);
            System.out.println(str);
            ctx.writeAndFlush(buf);
        }
    }

    @Test
    public void test() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,world", StandardCharsets.UTF_8);
        System.out.println(byteBuf.readableBytes());
        int i = byteBuf.readInt();
        System.out.println(byteBuf.readableBytes());
        System.out.println(i);
    }

    public static void main(String[] args) throws Exception {
        BytebufApi bytebufApi = new BytebufApi();
        bytebufApi.serverMode01();
    }
}
