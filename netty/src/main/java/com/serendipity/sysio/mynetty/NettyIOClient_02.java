package com.serendipity.sysio.mynetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyIOClient_02 {
    public static void main(String[] args) {
        try {
            NioEventLoopGroup eventExecutors = new NioEventLoopGroup(1);
            Bootstrap bootstrap = new Bootstrap();
            ChannelFuture connect = bootstrap.group(eventExecutors)
                                             .channel(NioSocketChannel.class)
                                             .handler(new ChannelInitializer<NioSocketChannel>() {
                                                 @Override
                                                 protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                                                     ChannelPipeline pipeline = nioSocketChannel.pipeline();
                                                     pipeline.addLast(new NioClientHandler());
                                                 }
                                             })
                                             .connect("192.168.1.100", 9991);
            System.out.println("客户端已经连接。。");
            connect.sync()
                   .channel()
                   .closeFuture()
                   .sync();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class NioClientHandler extends ChannelInboundHandlerAdapter {
        // 当通道就绪就会触发这个方法
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
           /* ctx.channel().eventLoop().schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                        //如果是字符需要指定编码格式
                        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 你好", CharsetUtil.UTF_8));
//                        ctx.writeAndFlush(Unpooled.copiedBuffer("你好".getBytes()));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, 5, TimeUnit.SECONDS);*/
        }

        // 给服务端发送信息
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
        }
    }
}
