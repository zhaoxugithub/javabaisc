package com.serendipity.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel04 {
    public static void main(String[] args) throws IOException {
        File file = new File("netty/src/NIOFIleChannel01.txt");
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel inputStreamChannel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        FileOutputStream outputStream = new FileOutputStream("netty/src/NIOFIleChannel02.txt");
        FileChannel outputStreamChannel = outputStream.getChannel();
        while (true) {
            byteBuffer.clear();
            int read = inputStreamChannel.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            outputStreamChannel.write(byteBuffer);

        }
        inputStream.close();
        outputStream.close();
    }
}
