package com.serendipity.myold.reflect;

public class CreateClassObject {

    public static void main(String[] args) throws Exception {
        //创建反射对象的三种方式
        //1.通过Class.forName()
        Class aClass = Class.forName("com.old.reflect.Student");
        //2.通过类名.class
        Class<Student> studentClass = Student.class;
        //3.通过对象.class
        Class<? extends Student> aClass1 = new Student().getClass();
        System.out.println(studentClass.getCanonicalName());
        System.out.println(studentClass.getName());
        System.out.println(studentClass.getPackage());
        System.out.println(studentClass.getSimpleName());
        //如果是一个基本类型，可以通过Type的方式来获取Class 对象
        Class<Integer> type = Integer.TYPE;
        System.out.println(type.getName());
        System.out.println(type.getCanonicalName());
    }
}
