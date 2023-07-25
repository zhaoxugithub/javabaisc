package com.serendipity.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class NIOFileChannel05 {

    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = new FileInputStream("netty/src/NIOFIleChannel01.txt");
        FileChannel inputStreamChannel = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream("netty/src/NIOFIleChannel02.txt");
        FileChannel outputStreamChannel = outputStream.getChannel();

        outputStreamChannel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());

        outputStreamChannel.close();
        inputStreamChannel.close();

        outputStream.close();
        inputStreamChannel.close();
    }
}
