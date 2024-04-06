package com.serendipity;

import com.github.jsonzou.jmockdata.JMockData;
import com.serendipity.pojo.Person;

/**
 * @author: 11931
 * @createTime: 2024/04/01 23:24
 */
public class Main {
    public static void main(String[] args) {
        Person mock = JMockData.mock(Person.class);
        System.out.println(mock);
        System.out.println("Hello world!");
    }
}