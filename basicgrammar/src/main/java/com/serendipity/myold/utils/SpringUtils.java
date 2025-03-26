package com.serendipity.myold.utils;

import com.serendipity.myold.annotation.entity.Person;
import com.serendipity.myold.annotation.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

@Slf4j
public class SpringUtils {
    @Test
    public void test() {
        Assert.notNull(null, "null value is not allowed");
    }

    @Test
    public void test2() {
        Person person = new Person();
        person.setEmail("test");
        mock(person);
        mock(new Student());
    }

    public void mock(Object p) {
        if (p instanceof Person person) {
            System.out.println(person.getEmail());
        } else if (p instanceof String s) {
            System.out.println(s);
        } else {
            System.out.println("unknown type");
        }
    }
}
