package com.serendipity.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering :将数据写入到buffer时，采用的时buffer数组，依次写入
 * Gathering:从buffer读取数据时，可以采用buffer数组，依次读
 *
 * @author 11931
 */
@Slf4j
public class ScatteringAndGatheringTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        // 将服务端的socket 与 地址进行绑定
        serverSocketChannel.socket()
                           .bind(inetSocketAddress);
        // 定义buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        // 等待客户端去链接
        SocketChannel socketChannel = serverSocketChannel.accept();
        // 假定从客户端接收8个字节
        int messageLength = 8;
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long read = socketChannel.read(byteBuffers);
                // 累计读取的字节数
                byteRead += (int) read;
                log.info("byteRead={}", byteRead);
                // 使用流打印，看看当前的这个buffer的position 和limit
                Arrays.stream(byteBuffers)
                      .map(buffer -> "position:" + buffer.position() + ",limit:" + buffer.limit())
                      .forEach(System.out::print);
            }
            // 将buffer进行翻转
            Arrays.stream(byteBuffers)
                  .forEach(ByteBuffer::flip);
            long byteWrite = 0;
            while (byteWrite < messageLength) {
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }
            Arrays.asList(byteBuffers)
                  .forEach(ByteBuffer::clear);
            log.info("byteRead={},byteWrite={},messageLength={}", byteRead, byteWrite, messageLength);
        }
    }
}
