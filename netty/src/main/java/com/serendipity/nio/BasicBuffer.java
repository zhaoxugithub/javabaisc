package com.serendipity.nio;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        //举例说明buffer的使用（简单说明）
        //创建一个buffer，大小为5，即可以存放5个int，堆内存
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
        //如何从buffer里面读取数据，需要进行读写转换
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            //get方法中维护一个索引，每次get方法之后索引会移动一次
            System.out.println(intBuffer.get());
        }
    }
}
