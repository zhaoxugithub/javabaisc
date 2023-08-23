package com.serendipity.sysio.mynetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class NettyIOServer_02 {
    public static void main(String[] args) {
        try {
            NioEventLoopGroup boss = new NioEventLoopGroup(1);
            NioEventLoopGroup worker = new NioEventLoopGroup(2);
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            ChannelFuture future = serverBootstrap.group(boss, worker)
                                                  .channel(NioServerSocketChannel.class)
                                                  .childHandler(new ChannelInitializer<NioSocketChannel>() {
                                                      @Override
                                                      protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                                                          ChannelPipeline pipeline = nioSocketChannel.pipeline();
                                                          pipeline.addLast(new AcceptClientHandler());
                                                      }
                                                  })
                                                  .bind(new InetSocketAddress("192.168.1.100", 9991));
            // 对上述添加监听
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("server register successful...");
                    } else {
                        System.out.println("server register failed...");
                    }
                }
            });
            System.out.println("服务端9991启动成功...");
            future.sync()
                  .channel()
                  .closeFuture()
                  .sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // 监听客户端事件
    private static class AcceptClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("客户端注册成功...");
        }

        // 监听客户端读入事件，只有当客户端发送数据的时候才会触发这个方法
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            /*Channel channel = ctx.channel();
            SocketChannel client = (SocketChannel) channel;
            ByteBuf buf = (ByteBuf) msg;
            System.out.println(buf.toString(CharsetUtil.UTF_8));
            ctx.writeAndFlush(Unpooled.copiedBuffer(("hello world" + client.remoteAddress()+"\n").getBytes()));*/
/*            ctx.channel().eventLoop().schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        //这里是wile死循环，如果worker
//                        while (true) {
                            Thread.sleep(5000);
                            //如果是字符需要指定编码格式
//                           ctx.writeAndFlush(Unpooled.copiedBuffer("hello 你好", CharsetUtil.UTF_8));
                            String msg = Thread.currentThread().getName() + "-" + ctx.channel().remoteAddress() + "\n";
                            ctx.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
//                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, 1, TimeUnit.SECONDS);
        */
           /* ctx.channel().eventLoop().schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        //这里是wile死循环，如果worker
                        Thread.sleep(1000);
                        //如果是字符需要指定编码格式
                        String msg = Thread.currentThread().getName() + "-" + ctx.channel().remoteAddress() + "\n";
                        ctx.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, 1, TimeUnit.SECONDS);*/

            ctx.channel()
               .eventLoop()
               .execute(new Runnable() {
                   @Override
                   public void run() {
                       // 这里是wile死循环，如果worker
                       try {
                           Thread.sleep(1000);
                           // 如果是字符需要指定编码格式
                           String msg = Thread.currentThread()
                                              .getName() + "-" + ctx.channel()
                                                                    .remoteAddress() + "\n";
                           ctx.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
                       } catch (InterruptedException e) {
                           throw new RuntimeException(e);
                       }
                   }
               });
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.copiedBuffer(("hello" + ctx.channel()
                                                                  .remoteAddress() + "\n").getBytes()));
        }
    }
}
