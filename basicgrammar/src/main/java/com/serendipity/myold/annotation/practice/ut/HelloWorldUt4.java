package com.serendipity.myold.annotation.practice.ut;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelloWorldUt4 {

    private HelloWorld hw;

    @Before
    public void setUp() {
        hw = new HelloWorld();
    }

    @Test(expected = NumberFormatException.class)
    public void testHello() {
        hw.sayHello();
    }

    @Test
    public void testSay() {
        assertEquals("success", hw.say(), "hello world!");
    }

    @Test
    public void testObj() {
        assertNull("test obj not null", null);
        assertNotNull("test obj is null", new String());
    }

    @After
    public void tearDown() {
        hw = null;
    }
}
