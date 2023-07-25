package com.serendipity.myold.reflect;

public class Person {
    public String name;
    public int age;

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Person() {
    }

    public Person(String name, int age) {

    }

    public String show() {
        return "name:" + name + ",age" + age;
    }
}
