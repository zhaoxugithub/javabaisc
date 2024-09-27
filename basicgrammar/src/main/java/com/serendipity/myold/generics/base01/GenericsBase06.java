package com.serendipity.myold.generics.base01;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 泛型数组
 */
public class GenericsBase06 {


    @Test
    public void test01() {
        // 编译错误，非法创建
//        List<String>[] list = new ArrayList<String>[10];


    }


    class Pair<T> {

        private T value;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    class DateInter extends Pair<Date> {
        @Override
        public Date getValue() {
            return super.getValue();
        }

        @Override
        public void setValue(Date value) {
            super.setValue(value);
        }
    }

    @Test
    public void test02() {
        DateInter dateInter = new DateInter();
        dateInter.setValue(new Date());
        // 报错
        // dateInter.setValue(new Object());
    }

}
