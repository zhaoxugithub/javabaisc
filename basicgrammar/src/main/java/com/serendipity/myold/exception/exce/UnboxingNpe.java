package com.serendipity.myold.exception.exce;

/**
 * ClassName UnboxingNpe
 * Description TODO
 * Author 11931
 * Date 2022-10-22:10:22
 * Version 1.0
 * <p>
 * 自动拆装箱引起空指针问题
 **/
@SuppressWarnings("all")
public class UnboxingNpe {
    private static int add(int x, int y) {
        return x + y;
    }

    private static boolean compare(long x, long y) {
        return x >= y;
    }

    public static void main(String[] args) {
        // 1. 变量赋值自动拆箱出现的空指针
        // javac UnboxingNpe.java
        // javap -c UnboxingNpe.class
        Long count = null;
        long count_ = count;
        long l = count.longValue();
        /*
            这里要明白一个问题，就是Integer -> int 具体的做法是：Long.LongValue
           eg：long l = count.longValue();
         */
        // 2. 方法传参时自动拆箱引发的空指针
//        Integer left = null;
//        Integer right = null;
//        System.out.println(add(left, right));
        // 3. 用于大小比较的场景
//        Long left = 10L;
//        Long right = null;
//        System.out.println(compare(left, right));
    }
}
