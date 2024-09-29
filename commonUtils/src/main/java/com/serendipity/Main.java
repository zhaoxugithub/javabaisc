package com.serendipity;

import com.github.jsonzou.jmockdata.JMockData;
import com.serendipity.pojo.Person;
import org.junit.Test;

/**
 * @author: 11931
 * @createTime: 2024/04/01 23:24
 */
public class Main {
    public static void main(String[] args) {
        Person mock = JMockData.mock(Person.class);
        System.out.println(mock);
        System.out.println("Hello world!");
        System.out.println("test2");

        Integer mock1 = JMockData.mock(Integer.class);


        System.out.println(mock1);
    }

    @Test
    public void test() {
        process1();
    }

    private void process1() {;
        System.out.println("test1");
        System.out.println("test2");
        System.out.println("test3");
        System.out.println("test4");
        System.out.println("test5");
    }

}
