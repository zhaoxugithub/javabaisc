package com.serendipity.myold.reflect;

public class Student extends Person {
    public String classRoom;
    private String address;

    public Student() {
        super();
    }

    private Student(String name, int age, String className) {
        super(name, age);
        this.classRoom = className;
    }

    public Student(String name, int age, String className, String address) {
        super(name, age);
        this.classRoom = className;
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setClassName(String className) {
        this.classRoom = className;
    }

    public String getAddress() {
        return address;
    }

    public String getClassName() {
        return classRoom;
    }

    private void add(Integer a, Integer b) {
        System.out.println(a + b);
    }

    @Override
    public String toString() {
        return "Student{" + "className='" + classRoom + '\'' + ", address='" + address + '\'' + '}';
    }
}
