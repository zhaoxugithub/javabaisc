package com.serendipity.sysio.mynetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class HttpNetty {

    public static void main(String[] args) {
        try {
            NioEventLoopGroup boss = new NioEventLoopGroup(1);
            NioEventLoopGroup worker = new NioEventLoopGroup(3);
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            ServerBootstrap bootstrap = serverBootstrap.group(boss, worker).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {
                // 初始化通道
                @Override
                protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                    ChannelPipeline pipeline = nioSocketChannel.pipeline();
                    pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
                    pipeline.addLast("MyTestHttpServerHandler", new TestHttpServerHandler());
                }
            });
            ChannelFuture sync = bootstrap.bind(8888).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * SimpleChannelInboundHandler 继承ChannelInboundHandlerAdapter
     * HttpObject 客户端和服务器端相互通讯的数据被封装成 HttpObject
     */
    private static class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
        // HttpRequest 继承HttpObject
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
            if (httpObject instanceof HttpRequest) {
                HttpRequest request = (HttpRequest) httpObject;
                URI uri = new URI(request.uri());
                if ("/favico.ico".equals(uri.getPath())) {
                    System.out.println("请求了favico,不做响应");
                    return;
                }
                ByteBuf content = Unpooled.copiedBuffer("hello ,我是服务器", CharsetUtil.UTF_8);
                DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
                channelHandlerContext.writeAndFlush(response);
            }
        }
    }
}
