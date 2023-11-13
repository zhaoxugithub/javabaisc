package com.serendipity.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @author 11931
 */
@Slf4j
public class BasicBuffer {
    @Test
    public void test01() {
        ByteBuffer allocate = ByteBuffer.allocate(10);
        log.info("position={}", allocate.position());
        log.info("limit={}", allocate.limit());
        log.info("capacity={}", allocate.capacity());
        allocate.put("1234".getBytes());
        log.info("------------------------------------------");
        log.info("position={}", allocate.position());
        log.info("limit={}", allocate.limit());
        log.info("capacity={}", allocate.capacity());
        log.info("----------write--->read------------------");
        allocate.flip();
        log.info("position={}", allocate.position());
        log.info("limit={}", allocate.limit());
        log.info("capacity={}", allocate.capacity());
        allocate.get();
        log.info("-----------------------------------------");
        log.info("position={}", allocate.position());
        log.info("limit={}", allocate.limit());
        log.info("capacity={}", allocate.capacity());
        log.info("---------read-->write--------------------");
        allocate.flip();
        log.info("position={}", allocate.position());
        log.info("limit={}", allocate.limit());
        log.info("capacity={}", allocate.capacity());
    }

    public static void main(String[] args) {
        // 举例说明buffer的使用（简单说明）
        // 创建一个buffer，大小为5，即可以存放5个int，堆内存
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
        // 如何从buffer里面读取数据，需要进行读写转换
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            // get方法中维护一个索引，每次get方法之后索引会移动一次
            int i = intBuffer.get();
            log.info("intBuffer={}", i);
        }
    }
}
