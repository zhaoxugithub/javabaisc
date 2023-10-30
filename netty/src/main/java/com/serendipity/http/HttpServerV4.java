package com.serendipity.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/11/28 0:13
 * FileName: HttpServerV4
 * Description: myhttp.net
 */
public class HttpServerV4 {
    private ServerSocketChannel channel;

    public HttpServerV4(int port) throws IOException {
        channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(port));
        channel.configureBlocking(false);
    }

    public void listen() throws IOException {
        for (; ; ) {
            Socket socket = channel.accept().socket();
            if (socket == null) {
                // TODO 没有客户端连接
            }
            handle(socket);
        }
    }

    public void handle(Socket socket) {
    }
}
