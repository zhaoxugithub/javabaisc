package com.serendipity.chapter01.example02;

public class Person {
    private String name;
    private int age;
    private String country;

    // 私有构造函数，防止外部直接实例化
    private Person(String name, int age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    // 静态工厂方法
    public static Person createWithNameAndAge(String name, int age) {
        return new Person(name, age, "Unknown");
    }

    public static Person createWithNameAndCountry(String name, String country) {
        return new Person(name, 0, country);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Person person1 = Person.createWithNameAndAge("Alice", 30);
        Person person2 = Person.createWithNameAndCountry("Bob", "USA");
        System.out.println(person1);
        System.out.println(person2);
    }
}
