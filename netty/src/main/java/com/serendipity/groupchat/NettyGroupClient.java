package com.serendipity.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class NettyGroupClient {
    private int port;
    private String host;

    public NettyGroupClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void clientStart() {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        try {
            ChannelFuture future = bootstrap.group(eventExecutors).channel(NioSocketChannel.class).handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                    ChannelPipeline pipeline = nioSocketChannel.pipeline();
                    // 加入相关handler
                    pipeline.addLast("decoder", new StringDecoder());
                    pipeline.addLast("encoder", new StringEncoder());
                    // 加入自定义的handler
                    pipeline.addLast(new NettyGroupClientHandler());
                }
            }).connect(host, port).sync();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {

                    if (channelFuture.isSuccess()) {
                        System.out.println("客户端连接服务器成功..");
                    } else {
                        System.out.println("客户端连接服务器失败..");
                    }
                }
            });
            Channel channel = future.channel();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                channel.writeAndFlush(s + "\r\n");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }

    private class NettyGroupClientHandler extends SimpleChannelInboundHandler<String> {
        // 读取来自服务端的数据
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
            System.out.println("来自服务端数据:" + s);
        }
    }

    public static void main(String[] args) {
        new NettyGroupClient("192.168.1.100", 8888).clientStart();
    }
}
