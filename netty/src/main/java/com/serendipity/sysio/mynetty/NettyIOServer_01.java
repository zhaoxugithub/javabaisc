package com.serendipity.sysio.mynetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

public class NettyIOServer_01 {
    private static int port = 9991;

    public static void main(String[] args) {
        try {
            NioEventLoopGroup boss = new NioEventLoopGroup(1);
            NioEventLoopGroup worker = new NioEventLoopGroup();
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //为什么要返回sync,因为netty本身方法是异步的，为了得到的方法是否结束，加了sync,表示直到异步方法执行结束主线程才会继续往下执行
            ChannelFuture cf = serverBootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                    //设置线程队列等待连接个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //设置保持活动连接状态boss = {NioEventLoopGroup@1497}
                    .childOption(ChannelOption.SO_KEEPALIVE, true).childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道初始化对象（匿名对象）
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            System.out.println("客户端socketchannel hashcode=" + socketChannel.hashCode() + " 连接线程的名字" + Thread.currentThread().getName());
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                        //绑定一个端口并且同步生成一个ChannelFuture对象（也就是立马返回这样一个对象）
                        //启动服务器并且绑定端口
                    }).bind(port).sync();
            System.out.println("服务端启动。。。");
            //给cf注册监听器，监控我们关心的事件
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("监听端口 9991 成功");
                    } else {
                        System.out.println("监听端口 9991 失败");
                    }
                }
            });
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 说明：
     * 1.我们自定义一个Handler需要继承netty,规定好的某个HandlerAdapter(规范)
     * 2.这是我们自定义一个Handler，才能称为一个Handler
     */
    private static class NettyServerHandler extends ChannelInboundHandlerAdapter {

        /**
         * 读取客户端发送到额数据事件
         *
         * @param ctx 上下文对象，含有管道pipeline,通道channel,地址
         * @param msg 就是客户端发送的数据 默认为object
         * @throws Exception
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("服务读取线程 " + Thread.currentThread().getName() + " channel =" + ctx.channel());
            System.out.println("server ctx= " + ctx);
            System.out.println("看看channel和pipeLine的查看关系");
            Channel channel = ctx.channel();
            ChannelPipeline pipeline = ctx.pipeline();
            ByteBuf buf = (ByteBuf) msg;
            System.out.println("客户端发送的信息是:" + buf.toString(CharsetUtil.UTF_8));
            System.out.println("客户端地址:" + channel.remoteAddress());
        }

        //当数据嘟嘟去结束的时候
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello client<^_^>\n", CharsetUtil.UTF_8));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }
}
