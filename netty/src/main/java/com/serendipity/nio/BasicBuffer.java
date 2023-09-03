package com.serendipity.nio;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class BasicBuffer {

    /*
        读写转换之后，postion会变成0,limit=position
     */
    @Test
    public void test01() {
        ByteBuffer allocate = ByteBuffer.allocate(10);
        System.out.println(allocate.position()); // 0
        System.out.println(allocate.limit());  // 10
        System.out.println(allocate.capacity()); // 10

        allocate.put("1234".getBytes());
        System.out.println("----");
        System.out.println(allocate.position()); // 4
        System.out.println(allocate.limit()); // 10
        System.out.println(allocate.capacity()); // 10

        // write -> read
        allocate.flip();
        System.out.println("----");
        System.out.println(allocate.position()); //position =0
        System.out.println(allocate.limit()); // limit = postion = 4 (要插入的️下标)
        System.out.println(allocate.capacity());

        byte b = allocate.get();
        System.out.println("----");
        System.out.println(allocate.position()); //position =  1
        System.out.println(allocate.limit()); // limit = 4 (要插入的️下标)
        System.out.println(allocate.capacity());

        // read -> write
        allocate.flip();
        System.out.println("----");
        System.out.println(allocate.position()); //position =0
        System.out.println(allocate.limit()); // limit = postion =1 (要插入的️下标)
        System.out.println(allocate.capacity());
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
            System.out.println(intBuffer.get());
        }
    }
}
