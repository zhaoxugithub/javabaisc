package com.serendipity.chapter02.ch01;

import org.junit.Test;

public class Main {

    @Test
    public void test01() {
        Person01 person = Person01.builder()
                .age(10)
                .address("shanghai")
                .firstName("zhan")
                .lastName("si")
                .phoneNumber("123")
                .build();
        System.out.println(person);
        person.setFirstName("modify");
        System.out.println(person);


        // Person person1 = new Person();
        // 无参构造会失败
    }

    @Test
    public void test02() {
        Person02 person = new Person02();
        person.setAge(20);
        person.setFirstName("zz");
        System.out.println(person);
        // 想使用全参构造可以使用@AllArgsConstructor
        new Person02();
    }

    @Test
    public void test03() {
        Person03 per = Person03.builder()
                .address("ss")
                .age(20)
                .firstName("zx")
                .build();
        System.out.println(per);

        per.setAge(999);
        System.out.println(per);

        Person03 build = Person03.builder().build();
        build.setFirstName("sssssss");
        System.out.println(build);
    }


}
