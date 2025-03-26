package com.serendipity.mock;

import com.github.jsonzou.jmockdata.JMockData;
import com.serendipity.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author: 11931
 * @createTime: 2024/04/01 23:24
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        Person mockObj = JMockData.mock(Person.class);
        log.info("mock: {}", mockObj);

        Integer mockInter = JMockData.mock(Integer.class);
        log.info("mock: {}", mockInter);
    }
}
