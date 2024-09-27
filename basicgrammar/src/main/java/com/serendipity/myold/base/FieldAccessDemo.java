package com.serendipity.myold.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author 11931
 */
@Slf4j
@SuppressWarnings("all")
public class FieldAccessDemo {
    class SuperClass {
        public int field = -1;

        public int getField() {
            return field;
        }
    }

    class Sub extends SuperClass {
        public int field = 1;

        @Override
        public int getField() {
            return field;
        }

        public int getSuperField() {
            return super.field;
        }
    }

    @Test
    public void test() {
        SuperClass sup = new Sub();
        log.info("sup.field ={},sup.getField()={} ", sup.field, sup.getField());
        Sub sub = new Sub();
        log.info("sub.field = {},sub.getField()={},sub.getSuperField()={}", sub.field, sub.getField(), sub.getSuperField());
    }
}
