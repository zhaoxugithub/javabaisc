package com.serendipity.groupchat.demo01;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ClassName ServerDemo01
 * Description TODO
 * Author 11931
 * Date 2023-08-21:22:57
 * Version 1.0
 **/
@Slf4j
public class ServerDemo01 {
    @Test
    public void client01() {
        final int DEFAULT_PORT = 8888;
        try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)) {
            log.info("serverSocket start,the port is {}", DEFAULT_PORT);
            while (true) {
                // server不停监听和端口
                Socket socket = serverSocket.accept();
                log.info("client[" + socket.getPort() + "] online");
                // 接收消息
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void server01() {
        while (true) {
        }
    }
}
