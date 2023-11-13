package com.serendipity.http.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 11931
 */
public class RawHTTPServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9990);
        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("socket created");
            DataInputStream inputStream = new DataInputStream(client.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                sb.append(line);
            }
            String request = br.toString();
            System.out.println(request);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bufferedWriter.write("HTTP/1.1 200 ok\n\nhello world\n");
            bufferedWriter.flush();
            client.close();
        }
    }
}
