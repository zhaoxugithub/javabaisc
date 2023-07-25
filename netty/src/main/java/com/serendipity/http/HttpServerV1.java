package com.serendipity.http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基础socket 实现一个http,Http底层是基于TCP,需要按照TCP的基本格式来先进行开发
 */
public class HttpServerV1 {
    private ServerSocket serverSocket = null;

    public HttpServerV1(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动");
        //创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        while (true) {
            // BIO  accept 阻塞，获取客户端连接
            Socket client = serverSocket.accept();
            System.out.println("client [" + client.getRemoteSocketAddress() + ":" + client.getPort() + "]");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    process(client);
                }
            });
        }
    }

    public void process(Socket client) {
        try {
            analysisHttpHeader(client);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //下面操作都要严格按照HTTP写协议来操作
    //1.读取请求并且解析
    //2.解析首行，三个部分都是用空格切分
    public void analysisHttpHeader(Socket client) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String headers = bufferedReader.readLine();
        String[] headersArray = headers.split(" ");
        String method = headersArray[0];
        String url = headersArray[1];
        String version = headersArray[2];

        //解析headers,按行读取，然后按照冒号空格来分割键值对
        Map<String, String> headersMap = new HashMap<>();
        String line = "";
        //readLine() 读取一行内容，是会自动去掉换行符，对于空行来说，去掉了换行符，就变成了空字符串
        while ((line = bufferedReader.readLine()) != null && line.length() != 0) {
            String[] split = line.split(": ");
            headersMap.put(split[0], split[1]);
        }

        //请求解析完毕，加上一个日志，观察请求的内容是否正确
        System.out.printf("%s %s %s\n", method, url, version);

        for (Map.Entry<String, String> entry : headersMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        String resp = "";
        if (url.equals("/ok")) {
            // 下面这一行必须加上，才能在web页面上看到
            bufferedWriter.write(version + " 200 OK\n");
            resp = "<h1>hello</h1>";
        } else if (url.equals("/notfound")) {
            bufferedWriter.write(version + " 404 Not Found\n");
            resp = "<h1>mot found</h1>";
        } else if (url.equals("/seeother")) {
            bufferedWriter.write(version + " 303 See Other\n");
            bufferedWriter.write("Location: http://www.baidu.com\n");
            resp = "";
        } else {
            bufferedWriter.write(version + "200 OK\n");
            resp = "<h1>default</h1>";
        }
        bufferedWriter.write("Content-Type:text/html\n");
        bufferedWriter.write("Content-Length: " + resp.getBytes().length + "\n");
        bufferedWriter.write("\n");
        bufferedWriter.write(resp);

        bufferedWriter.flush();
    }


    public static void main(String[] args) throws IOException {
        HttpServerV1 httpServerV1 = new HttpServerV1(9999);
        httpServerV1.start();
    }
}
