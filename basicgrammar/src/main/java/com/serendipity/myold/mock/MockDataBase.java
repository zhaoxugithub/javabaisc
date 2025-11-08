package com.serendipity.myold.mock;

import com.github.jsonzou.jmockdata.JMockData;
import com.serendipity.myold.mock.entity.BasicBean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class MockDataBase {

    public void mockBaseData() {
        // 基本类型模拟
        int intNum = JMockData.mock(int.class);
        int[] intArray = JMockData.mock(int[].class);
        Integer integer = JMockData.mock(Integer.class);
        Integer[] integerArray = JMockData.mock(Integer[].class);
        // 常用类型模拟
        BigDecimal bigDecimal = JMockData.mock(BigDecimal.class);
        BigInteger bigInteger = JMockData.mock(BigInteger.class);
        Date date = JMockData.mock(Date.class);
        String str = JMockData.mock(String.class);
    }

    public void mockBasicBean() {
        BasicBean mock = JMockData.mock(BasicBean.class);
        System.out.println(mock);
    }
}
