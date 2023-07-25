package com.serendipity.myold.annotation.practice.ut;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

/**
 * Junit3 实现UT
 */
@Slf4j
public class HelloWorldUt3 extends TestCase {

    private HelloWorld hw;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        hw = new HelloWorld();
    }

    // 测试没有返回值
    public void testHello() {
        try {
            hw.sayHello();
        } catch (Exception e) {
            log.debug("cause one exception = {}", e.getMessage());
        }
    }

    public void testSay() {
        assertEquals("success", hw.say(), "hello world!");
    }

    public void testObj() {
        assertNull("test Obj not null", null);
        assertNotNull("test Obj is null", new String());
    }

    @Override
    protected void tearDown() throws Exception {

        super.tearDown();
        hw = null;
    }
}
