package com.serendipity.sysio.mynetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyIOClient_01 {


    public static void main(String[] args) {

        try {
            //客户端需要一个事件循环组
            NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
            //创建客户端启动对象
            //注意客户端使用的不是serverBootstrap 而是bootstrap
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventExecutors).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //可以通过socketChannel 获取
                    socketChannel.pipeline().addLast(null);
                }
            });
            System.out.println("客户端 OK....");
            //启动客户端去连接服务端
            //关于ChannelFuture 要分析，设计到netty的异步模型
            ChannelFuture channelFuture = bootstrap.connect("192.168.1.100", 9991).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
