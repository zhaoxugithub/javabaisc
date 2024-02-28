package com.serendipity.sysio;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: 马士兵教育
 * @create: 2020-05-17 16:18
 */
public class SocketClient {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("1.15.149.196", 9090);
            // 设置客户端缓冲区大小
            Scanner scanner = new Scanner(System.in);
            client.setSendBufferSize(20);
            // 设置是否立即发送,意思是写一个发一个
            // 如果设置为flase,表示积攒一部分数据再发送，这个时候可以无视缓冲区的大小，意味着可以超过缓冲区的限制
            client.setTcpNoDelay(false);
            OutputStream out = client.getOutputStream();
            InputStream in = System.in;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while (true) {
                String line = reader.readLine();
                if (line != null) {
                    byte[] bb = line.getBytes();
                    for (byte b : bb) {
                        out.write(b);
                    }
                } else {
                    String s = scanner.nextLine();
                    out.write(s.getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
