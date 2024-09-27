package com.serendipity.myold.utils;

import cn.hutool.aop.ProxyUtil;
import com.serendipity.myold.mock.MockDataBase;
import com.serendipity.myold.utils.annotation.LogAspect;

public class AnnotationUtils {

    public void test() {
        MockDataBase proxy = ProxyUtil.proxy(new MockDataBase(), LogAspect.class);
        proxy.mockBaseData();
    }
}
