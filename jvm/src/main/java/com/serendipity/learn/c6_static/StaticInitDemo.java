package com.serendipity.learn.c6_static;

public class StaticInitDemo {


    static class Parent {
        private static String str = "parent";
        private static final String str_final = "str_final";

        static {
            System.out.println(str);
            System.out.println(str_final);
            System.out.println("static...");
        }

        public static void fun() {
            System.out.println("fun..");
        }
    }

    static class Child extends Parent {

        private static String str = "child";
        private static final String str_final = "str_final";

        static {
            System.out.println(str);
            System.out.println(str_final);
            System.out.println("child...");
        }

        public static void function() {
            System.out.println("function...");
        }
    }


    /*
    类初始化时机: 只有当对类的主动使用的时候才会导致类的初始化，类的主动使用包括以下六种:
    创建类的实例，也就是new的方式
    访问某个类或接口的静态变量，或者对该静态变量赋值
    调用类的静态方法反射(如Class.forName("com.pdai.jvm.Test"))
    初始化某个类的子类，则其父类也会被初始化
    Java虚拟机启动时被标明为启动类的类(Java Test)，直接使用java.exe命令来运行某个主类
     */
    public static void main(String[] args) {

        //
//        new Child();
        //
        String str = Child.str;
//        Child.str = "ss";
//        Parent.fun();
//        Child.function();
//        Child.str = "sss";
    }


}
