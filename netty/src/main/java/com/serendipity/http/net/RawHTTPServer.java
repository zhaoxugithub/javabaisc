package com.serendipity.http.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RawHTTPServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9990);
        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("socket created");

            DataInputStream inputStream = new DataInputStream(client.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();
            String line = "";

            while ((line = bufferedReader.readLine()) != null && line.length() != 0) {
                stringBuilder.append(line);
            }

            String request = bufferedReader.toString();
            System.out.println(request);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bufferedWriter.write("HTTP/1.1 200 ok\n\nhello world\n");
            bufferedWriter.flush();
            client.close();
        }
    }
}
