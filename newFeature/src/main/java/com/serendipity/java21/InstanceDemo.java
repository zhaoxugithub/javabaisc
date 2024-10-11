package com.serendipity.java21;

import org.junit.Test;

// jdk14
public class InstanceDemo {

    @Test
    public void test() {
        Object obj = 1;
        // 强转之前的判断
        if (obj instanceof Integer) {
            Integer i = (Integer) obj;
            System.out.println(i);
        }

        // jdk 14 之后，可以直接强转
        if(obj instanceof Integer i){
            System.out.println(i);
        }
    }
}
