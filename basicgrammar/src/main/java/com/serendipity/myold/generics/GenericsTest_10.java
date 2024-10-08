package com.serendipity.myold.generics;

import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * ClassName GenericsTest_10
 * Description TODO
 * Author 11931
 * Date 2023-02-03:0:06
 * Version 1.0
 **/
public class GenericsTest_10 {
    private class Pair<T> {
        private T value;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    private class DateInter extends Pair<Date> {
        @Override
        public void setValue(Date value) {
            super.setValue(value);
        }

        @Override
        public Date getValue() {
            return super.getValue();
        }
    }

    @Test
    public void test() {
        DateInter dateInter = new DateInter();
        dateInter.setValue(new Date());
        // dateInter.setValue(new Object()); 编译错误
    }
}
