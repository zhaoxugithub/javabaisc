package com.serendipity.sysio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

@Slf4j
@SuppressWarnings("all")
public class OSFileIO {
    static byte[] data = "123456789\n".getBytes();
    static String path = "out.txt";

    public static void main(String[] args) throws Exception {
        // test1();
        // test2();
        // test3();
        switch (args[0]) {
            case "0":
                test1();
                break;
            case "1":
                test2();
                break;
            case "2":
                test3();
            case "3":
                // whatByteBuffer();
            default:
        }
        // testRandomAccessFileWrite();
    }

    public static void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            File file = new File("out1.txt");
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                // 判断当前线程是否发生了中断
                while (!Thread.currentThread()
                              .isInterrupted()) {
                    fileOutputStream.write(data);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "baseWriteThread");
        thread.start();
        thread.join(1000);
        thread.interrupt();
    }


    public static void test2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            File file = new File("out2.txt");
            // 先把数据写到jvm缓存里面，然后复制到java进程，然后8k刷写到pagecache 上，然后刷写到硬盘上
            try (BufferedOutputStream out = new BufferedOutputStream(Files.newOutputStream(file.toPath()))) {
                while (!Thread.currentThread()
                              .isInterrupted()) {
                    out.write(data);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        thread.join(1000);
        thread.interrupt();
    }

    public static void test3() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try (RandomAccessFile raf = new RandomAccessFile("out3.txt", "rw")) {
                FileChannel channel = raf.getChannel();
                MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 1024);
                while (!Thread.currentThread()
                              .isInterrupted()) {
                    map.put(data);
                }
                map.force();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        thread.join(1000);
        thread.interrupt();
    }

    // 最基本的file写
    public static void testBasicFileIO() throws Exception {
        File file = new File(path);
        FileOutputStream out = new FileOutputStream(file);
        while (true) {
            Thread.sleep(10);
            // 每执行一次write就会触发一次系统调用，cpu就会不断的进行用户态和内核态切换
            // 其实是写在pageCache上面，然后在刷写到磁盘上
            out.write(data);
        }
    }

    // 测试buffer文件IO
    //  jvm  8kB   syscall  write(8KBbyte[])
    // 在jvm中开启一个8k的纯冲区,满8k 发起一次系统调用
    public static void testBufferedFileIO() throws Exception {
        File file = new File(path);
        // 先把数据写到jvm缓存里面，然后复制到java进程，然后8k刷写到pagecache 上，然后刷写到硬盘上
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        while (true) {
            Thread.sleep(10);
            out.write(data);
        }
    }

    // 测试文件NIO
    public static void testRandomAccessFileWrite() throws Exception {
        RandomAccessFile raf = new RandomAccessFile(path, "rw");
        raf.write("hello mashibing\n".getBytes());
        raf.write("hello seanzhou\n".getBytes());
        System.out.println("write------------");
        System.in.read();
        raf.seek(20);
        raf.write("111111111".getBytes());
        System.out.println("seek---------");
        System.in.read();
        FileChannel rafchannel = raf.getChannel();
        // mmap  堆外  和文件映射的   byte  not  objtect，只有文件（块）设备才会有Map,相当于将文件
        // 这个是堆外的byteBuffer(不是jvm程序内的缓冲)
        MappedByteBuffer map = rafchannel.map(FileChannel.MapMode.READ_WRITE, 10, 4096);
        map.put("@@@".getBytes());  // 不是系统调用  但是数据会到达 内核的pagecache
        // 曾经我们是需要out.write()  这样的系统调用，才能让程序的data 进入内核的pagecache
        // 曾经必须有用户态内核态切换
        // mmap的内存映射，依然是内核的pagecache体系所约束的！！！
        // 换言之，丢数据
        // 你可以去github上找一些 其他C程序员写的jni扩展库，使用linux内核的Direct IO
        // 直接IO是忽略linux的pagecache
        // 是把pagecache  交给了程序自己开辟一个字节数组当作pagecache，动用代码逻辑来维护一致性/dirty。。。一系列复杂问题
        System.out.println("map--put--------");
        System.in.read();
        // map.force(); //  flush
        raf.seek(0);
        /*
        什么情况下使用DirectByteBuffer（ByteBuffer.allocateDirect(int)）?
        1、频繁的native IO，即java程序与本地磁盘、socket传输数据。
        2、不需要经常创建和销毁DirectByteBuffer对象（执行cpp的构造和析构代价大）（使用池复用DirectByteBuffer）
        3、DirectByteBuffer不会占用堆内存。。也就是不会受到堆大小限制，只在DirectByteBuffer对象被回收后才会释放该缓冲区。
        4、大文件造成大对象，对GC负担比较重的情况
        什么情况下使用HeapByteBuffer（ByteBuffer.allocate(int)）?
        其实除了上述的DirectByteBuffer使用场景之外的，基本可以用HeapByteBuffer。。
        即：
        （1）数据仅在java程序中流转传输，不与本地进行IO
        （2）容量低，对GC负担低。快速回收
         */
        ByteBuffer buffer = ByteBuffer.allocate(8192);
//        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        // 将pageCache 内容拷贝到jvm堆内缓冲区
        int read = rafchannel.read(buffer);   // buffer.put()
        System.out.println(buffer);
        // 由写变成读
        buffer.flip();
        System.out.println(buffer);
        for (int i = 0; i < buffer.limit(); i++) {
            Thread.sleep(200);
            System.out.print(((char) buffer.get(i)));
        }
    }


    public void whatByteBuffer() {
        // 堆内字节缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 堆外字节缓冲区
        // ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        // buffer读写的偏位位置
        System.out.println("postition: " + buffer.position());
        // 数据写入的最后buffer位置
        System.out.println("limit: " + buffer.limit());
        // 缓冲区容量
        System.out.println("capacity: " + buffer.capacity());
        System.out.println("mark: " + buffer);
        buffer.put("123".getBytes());
        System.out.println("-------------put:123......");
        System.out.println("mark: " + buffer);
        // 将写 --> 读,limit 占据 postition的位置,postition 变成启始位置
        buffer.flip();   // 读写交替
        // 如果经过flip之后再进行put,数据只会存放在0-limit之间
        // buffer.put("a".getBytes());
        System.out.println("-------------flip......");
        System.out.println("mark: " + buffer);
        buffer.get();
        System.out.println("-------------get......");
        System.out.println("mark: " + buffer);
        buffer.compact();
        System.out.println("-------------compact......");
        System.out.println("mark: " + buffer);
        buffer.clear();
        System.out.println("-------------clear......");
        System.out.println("mark: " + buffer);
    }
}
