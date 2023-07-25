package com.serendipity.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NettyGroupServer {

    private int port;

    public NettyGroupServer(int port) {
        this.port = port;
    }


    public void serverStart() {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup(3);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            ServerBootstrap bootstrap = serverBootstrap.group(boss, worker).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                    ChannelPipeline pipeline = nioSocketChannel.pipeline();
                    pipeline.addLast("decoder", new StringDecoder());
                    pipeline.addLast("encoder", new StringEncoder());
                    pipeline.addLast(new NettyGroupServerHandler());
                }
            });
            ChannelFuture bind = bootstrap.bind(port).sync();
            bind.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    private static class NettyGroupServerHandler extends SimpleChannelInboundHandler<String> {

        //注意：加上static,全局只有一个
        private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //handlerAdded 和 handlerRemoved 方法是给客户单看的

        //当客户端一旦连接第一时间触发这个方法
        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            Channel channel = ctx.channel();
            ctx.writeAndFlush("【客户端】" + channel.remoteAddress() + "加入聊天" + sdf.format(new Date()) + "\n");
            channelGroup.add(channel);
        }


        @Override
        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
            Channel channel = ctx.channel();
            ctx.writeAndFlush("【客户端】" + channel.remoteAddress() + "离开了" + sdf.format(new Date()));
            System.out.println("Ghannel Group size is " + channelGroup.size());
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            Channel channel = ctx.channel();
            System.out.println("【客户端】" + channel.remoteAddress() + "上线了。。");

        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            Channel channel = ctx.channel();
            System.out.println("【客户端】" + channel.remoteAddress() + "离开了。。");
        }

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
            Channel channel = channelHandlerContext.channel();
            channelGroup.forEach(ch -> {
                if(channel != ch) { //不是当前的channel,转发消息
                    ch.writeAndFlush("[客户]" + channel.remoteAddress() + " 发送了消息:" + s);
                }
            });
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }


    public static void main(String[] args) {
        new NettyGroupServer(8888).serverStart();
    }

}
