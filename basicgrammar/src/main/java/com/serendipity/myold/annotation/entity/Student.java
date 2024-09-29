package com.serendipity.myold.annotation.entity;

public class Student extends Person {
    private String name = "StudentName";
    public static int age = 18;
    public String remark = "StudentRemark";

    public void sayHello() {
        System.out.println("Student Hello, " + name + "!");
    }

    private void fun() {
        System.out.println("Student fun");
    }
}

