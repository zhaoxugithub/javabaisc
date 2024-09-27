package com.serendipity.myold.mock;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.TimeIntervalAspect;
import org.junit.jupiter.api.Test;

public class MockTest {
    @Test
    public void test() {
        MockDataBase proxy = ProxyUtil.proxy(new MockDataBase(), TimeIntervalAspect.class);
        proxy.mockBaseData();
    }
}
