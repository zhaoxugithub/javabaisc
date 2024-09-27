package com.serendipity.myold.base;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * ClassName IntegerDataType
 * Description TODO
 * Author 11931
 * Date 2022-12-31:14:59
 * Version 1.0
 **/
@SuppressWarnings("all")
public class IntegerDataTypeDemo {
    public static void test1() {
        Integer x1 = new Integer(123);
        Integer x2 = new Integer(123);
        System.out.println(x1 == x2);  // false
        /*
             public static Integer valueOf(int i) {
                if (i >= IntegerCache.low && i <= IntegerCache.high)
                    return IntegerCache.cache[i + (-IntegerCache.low)];
                return new Integer(i);
            }
            在 -128 ~ 127 之间会存在Interger缓存池中
            编译器会在缓冲池范围内的基本类型自动装箱过程调用 valueOf() 方法，
            因此多个 Integer 实例使用自动装箱来创建并且值相同，那么就会引用相同的对象。
         */
        Integer y1 = Integer.valueOf(123);
        Integer y2 = Integer.valueOf(123);
        System.out.println(y2 == y1); // true

        Integer a1 = 123;
        Integer a2 = 123;
        System.out.println(a1 == a2); // true

        Integer b1 = 128;
        Integer b2 = 128;
        // 超过了127
        System.out.println(b2 == b1); // false
    }

    public static void test2() {
        String str1 = "abc";
        String str2 = "abc";
        System.out.println(str2 == str1); // true

        String s1 = new String("abc");
        String s2 = new String("abc");
        System.out.println(s1 == s2);  // false

        String x1 = String.valueOf("abcd");
        String x2 = String.valueOf("abcd");
        // 同时指向了String 常量池中
        System.out.println(x1 == x2); // true

        // 指向了String 常量池
        String s = String.valueOf("abc");
        System.out.println(s == str1); // true
    }

    public static final char[] value;

    static {
        value = new char[10];
        for (int i = 0; i < 10; i++) {
            value[i] = (char) ('a' + i);
        }
    }

    public static void test3() {
        // 可以变更
        value[0] = 'v';
        System.out.println(Arrays.toString(value));
    }

    public static void test4() {
        // 使用String.intern() 可以保证相同内容的字符串变量引用同一的内存对象。
        String s1 = new String("aaa");
        String s2 = new String("aaa");
        System.out.println(s1 == s2); // false

        String str = "aaa";
        System.out.println(str == s2); // false

        // 这个返回的字符串常量池中引用
        String s3 = s1.intern();
        System.out.println(s1 == s3); // false

        System.out.println(s3 == s1.intern()); // true
    }

    @Test
    public void test05() {
        // 在字符串常量池中初始化一个引用指向堆上的字符串对象
        // 返回是字符串对象引用
        String str = "abc";
        // 如果字符串常量池中没有abc的引用，首先安装String str = "abc"的方式创建字符串对象，然后再堆上创建另外一个对象
        // 如果字符串常量池中有abc的引用，只是在堆上创建一个对象。
        // 返回的堆上对象的引用
        String str1 = new String("abc");
        System.out.println(str == str1);  // false
        // str1.intern() 返回的字符串常量池的引用
        System.out.println(str == str1.intern()); // true
    }

    private static class Dog {
        String name;

        Dog(String name) {
            this.name = name;
        }

        String getName() {
            return this.name;
        }

        void setName(String name) {
            this.name = name;
        }

        String getObjectAddress() {
            return super.toString();
        }
    }

    public static void test5() {
        Dog dog = new Dog("A");
        System.out.println(dog.getObjectAddress()); // Dog@6b09bb57
        func(dog);

        System.out.println(dog.getObjectAddress()); // Dog@6b09bb57
        System.out.println(dog.getName());
    }

    public static void func(Dog dog) {
        System.out.println(dog.getObjectAddress()); // Dog@6b09bb57
        dog = new Dog("B");
        System.out.println(dog.getObjectAddress()); // Dog@6536e911
        System.out.println(dog.getName());
    }

    public static void test6() {
        // float f = 1.1;  会报错，默认是double类型
        float f1 = 1.1f;
        double d1 = 1.1;
        System.out.println(f1);
        System.out.println(d1);
        System.out.println(f1 == d1); // false

        float f2 = (float) 1.1;
        System.out.println(f2 == f1); // true

        double d2 = (double) f1;
        System.out.println(d2 == d1); // false
    }

    public static void test7() {
        short s1 = 1;
        // s1 = s1 + 1; 报错
        s1 += 1;
        // 上面那个相当于下面这行
        s1 = (short) (s1 + 1);
    }

    @Test
    public void test01() {
        // 装箱 xxx.valueOf()
        Integer i = Integer.valueOf(1000);
        // 拆箱 xxxValue()
        int i1 = i.intValue();
    }

    public static void main(String[] args) {
        // test1();
        // test2();
        // test3();
        test4();
        // test5();
        // test6();
    }
}
