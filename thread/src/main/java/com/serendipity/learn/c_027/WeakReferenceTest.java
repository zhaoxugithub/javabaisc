package com.serendipity.learn.c_027;

import java.lang.ref.WeakReference;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/11 15:26
 * FileName: WeakReference
 * Description: com.java.thread.c_027
 * <p>
 * <p>
 * Java 对引用的概念进行了扩充，将引用分为了：强引用（Strong Reference）、软引用（Soft Reference）
 * 、弱引用（Weak Reference）、虚引用（Phantom Reference）4 种，这 4 种引用的强度依次减弱。
 * <p>
 * <p>
 * Java中默认声明的就是强引用，比如：
 * <p>
 * Object obj = new Object(); //只要obj还指向Object对象，Object对象就不会被回收
 * obj = null;  //手动置null
 * 只要强引用存在，垃圾回收器将永远不会回收被引用的对象，哪怕内存不足时，JVM也会直接抛出OutOfMemoryError，不会去回收。
 * 如果想中断强引用与对象之间的联系，可以显示的将强引用赋值为null，这样一来，JVM就可以适时的回收对象了
 * 参考博客:
 * https://www.cnblogs.com/liyutian/p/9690974.html
 * <p>
 * <p>
 * threadLocal 中为什么要熬使用弱引用
 * <p>
 * https://zhuanlan.zhihu.com/p/91579723
 * <p>
 * 强软弱虚
 * 弱：只要产生GC就会释放
 * <p>
 * 引用类型	被回收时间	用途	生存时间
 * 强引用	从来不会	对象的一般状态	JVM停止运行时
 * 软引用	内存不足时	对象缓存	内存不足时
 * 弱引用	jvm垃圾回收时	对象缓存	gc运行后
 * 虚引用	未知	未知	未知
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        // 一般用于容器  WeakHashMap
        WeakReference<M> weakReference = new WeakReference(new M());
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());

        //tl 强引用指向ThreadLocal 对象，而ThreadLocalMap 中的key （ThreadLocal对象） 是由于弱引用指向的
        /*
            假设： 如果ThreadLocalMap中的key是由强引用指向的，那么当tl=null,gc回收ThreadLocal回回收不了，因为被另外一个强引用指向着；
            所以结论：tl 强引用指向ThreadLocal 对象，而ThreadLocalMap 中的key （ThreadLocal对象） 是由于弱引用指向的
            第二：使用ThreadLocal 一定要手动remove掉不用的value,因为当ThreadLocal 对象（key）被回收了，但是value依旧存在,如果不remove会产生内存泄漏

            ThreadLocal: 线程与线程之间是相互隔离的
                         一个线程 对应的是第一个 ThreadLocalMap
                         一个ThreadLocalMap 有多个Entry<ThreadLocal,Value>
                         一个Entry 的key = ThreadLocal对象, Value 是实际值
         */
        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
    }
}
