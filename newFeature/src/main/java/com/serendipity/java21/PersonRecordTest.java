package com.serendipity.java21;

public class PersonRecordTest {

    public static void main(String[] args) {
        PersonRecord john = new PersonRecord("John", "25");

        System.out.println(john);
        System.out.println(john.name());
        System.out.println(john.age());
        john.study();
    }
}
