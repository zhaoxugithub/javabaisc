package com.serendipity.http;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/11/17 0:40
 * FileName: HttpServerV3
 * Description: myhttp
 */
public class HttpServerV3 {
    private ServerSocketChannel channel = null;
    private SocketChannel accept = null;

    public HttpServerV3(int port) throws IOException {
        channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(port));
        channel.configureBlocking(false);
    }

    public void listen() {
        try {
            System.out.println("服务器启动。。。");
            // 创建一个线程池
            ExecutorService service = Executors.newCachedThreadPool();
            for (; ; ) {
                // 非阻塞
                accept = channel.accept();
                if (accept != null) {
                    Socket socket = accept.socket();
                    System.out.println("client[" + socket.getRemoteSocketAddress() + ":" + socket.getPort() + "]");
                    service.submit(() -> {
                        try {
                            handle(socket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } else {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handle(Socket accept) throws IOException {
        analysisHttp(accept);
        accept.close();
    }

    // 这种模型基本上可以返回数据了
    public void analysisSocket(Socket accept) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        System.out.println(reader.readLine());
        bufferedWriter.write("HTTP/1.1 200 OK\n");
        // 协议头和body之间需要加一个/n
        bufferedWriter.write("\n");
        bufferedWriter.write("<h1>dsadsa</h1>");
        bufferedWriter.flush();
        reader.close();
    }

    public void analysisHttp(Socket accept) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        String headers = reader.readLine();
        String[] s = headers.split(" ");
        String method = s[0];
        String url = s[1];
        String version = s[2];
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        String line = "";
        while ((line = reader.readLine()) != null && line.length() != 0) {
            String[] split = line.split(":");
            stringStringHashMap.put(split[0], split[1]);
        }
        stringStringHashMap.forEach((key, value) -> System.out.println(key + "------------" + value));
        System.out.printf("%s %s %s\n", method, url, version);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        String resp = "";
        if (url.equals("/ok")) {
            bufferedWriter.write(version + " 200 OK\n");
            resp = "<h1>sucess</h1>";
        } else if (url.equals("/notfound")) {
            bufferedWriter.write(version + " 404 Not Found\n");
            resp = "<h1>page not found</h1>";
        } else if (url.equals("/seeother")) {
            bufferedWriter.write(version + " 303 See Other\n");
            bufferedWriter.write("Location: http://www.baidu.com");
            resp = "";
        } else {
            bufferedWriter.write(version + " 200 OK\n");
            resp = "<h1>default</h1>";
        }
        bufferedWriter.write("Content-Type:text/html\n");
        bufferedWriter.write("Content-Length: " + resp.getBytes().length + "\n");
        bufferedWriter.write("\n");
        bufferedWriter.write(resp);
        bufferedWriter.flush();
    }

    public static void main(String[] args) throws IOException {
        HttpServerV3 httpServerV3 = new HttpServerV3(8082);
        httpServerV3.listen();
    }
}
