package com.serendipity.sysio.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * ClassName GroupChatServer
 * Description TODO
 * Author 11931
 * Date 2023-08-23:11:26
 * Version 1.0
 **/
public class GroupChatS {

    private class GroupChatServer {
        private int port;

        public GroupChatServer(int port) {
            this.port = port;
        }

        public void run() throws InterruptedException {
            NioEventLoopGroup boss = new NioEventLoopGroup(0);
            NioEventLoopGroup worker = new NioEventLoopGroup(0);
            try {
                ServerBootstrap serverBootstrap = new ServerBootstrap();
                serverBootstrap.group(boss, worker)
                               .channel(NioServerSocketChannel.class)
                               .option(ChannelOption.SO_BACKLOG, 127)
                               .childOption(ChannelOption.SO_KEEPALIVE, true)
                               .childHandler(new ChannelInitializer<SocketChannel>() {
                                   @Override
                                   protected void initChannel(SocketChannel socketChannel) throws Exception {
                                       // 获取pipeline
                                       ChannelPipeline pipeline = socketChannel.pipeline();
                                       pipeline.addLast("decode", new StringDecoder());
                                       pipeline.addLast("encode", new StringDecoder());
                                       // 添加自定义的处理器handler
                                       pipeline.addLast(new GroupChatServerHandler());
                                   }
                               });
                System.out.println("netty server already start....");
                ChannelFuture sync = serverBootstrap.bind(port)
                                                    .sync();

                // 监听关闭
                sync.channel()
                    .closeFuture()
                    .sync();
            } finally {
                boss.shutdownGracefully();
                worker.shutdownGracefully();
            }
        }
    }

    private class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

        private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // handlerAdded 表示连接建立完成,一旦连接,第一个被执行
        // 将当前channel加入到channelGroup
        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            // 打印和客户端建立连接的线程是不是boss
            System.out.println(Thread.currentThread()
                                     .getName());

            Channel channel = ctx.channel();
            // 将该客户加入聊天的信息推送给其它在线的客户端
            /*
            该方法会将 channelGroup 中所有的channel 遍历，并发送 消息，
            我们不需要自己遍历
            */
            channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + " 加入聊天" + sdf.format(new java.util.Date()) + " \n");
            channelGroup.add(channel);
        }


        @Override
        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
            Channel channel = ctx.channel();
            channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + " 离开了\n");
            System.out.println("channelGroup size" + channelGroup.size());
        }


        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println(ctx.channel()
                                  .remoteAddress() + " 上线了~");

        }
        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            System.out.println(ctx.channel()
                                  .remoteAddress() + " 离线了~");
        }
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            Channel channel = ctx.channel();

            channelGroup.forEach(ch -> {
                if (channel != ch) { // 不是当前的channel,转发消息
                    ch.writeAndFlush("[客户]" + channel.remoteAddress() + " 发送了消息" + msg + "\n");
                } else {// 回显自己发送的消息给自己
                    ch.writeAndFlush("[自己]发送了消息" + msg + "\n");
                }
            });
        }
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

    @Test
    public void testServer() throws InterruptedException {
        GroupChatServer groupChatServer = new GroupChatServer(9999);
        groupChatServer.run();
    }
}
