package com.serendipity.java21;

import com.github.jsonzou.jmockdata.JMockData;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class StringTemplates {

    @Test
    public void test() {
        // 以前的方法比较难以阅读和冗长
        Person person = JMockData.mock(Person.class);
        System.out.println(person);
        String message1 = "Greeting " + person + "!";
        System.out.println(message1);
        String message2 = String.format("Greetings %s!", person);
        System.out.println(message2);
        String message3 = new StringBuilder().append("Greetings ").append(person).append("!").toString();
        System.out.println(message3);

        // 新的方式
        String mess = STR."Greeings \{person.getName()},\{person.getCity()},\{person.getAge()}";
        System.out.println(mess);
    }

    @Test
    public void test2() {
        String time = STR."The current time is \{
                DateTimeFormatter
                        .ofPattern("HH:mm:ss")
                        .format(LocalTime.now())
                }.";

        System.out.println(time);
    }

    public static void main(String[] args) {
    }

}
