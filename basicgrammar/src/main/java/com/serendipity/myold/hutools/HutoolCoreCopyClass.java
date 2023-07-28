package com.serendipity.myold.hutools;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName HutoolCoreCopyClass
 * Description TODO
 * Author 11931
 * Date 2023-07-28:23:27
 * Version 1.0
 **/
public class HutoolCoreCopyClass {

    @AllArgsConstructor
    @Data
    private class B {
        private String Nname;
    }

    @AllArgsConstructor
    @Data
    private class C {
        private String cname;
    }


    @Data
    private class A implements Cloneable {
        private String name;
        private int age;
        private B b;
        private List<C> clist;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    @Test
    public void test() throws CloneNotSupportedException {
        A a = new A();
        a.setAge(10);
        a.setName("aname");
        a.setB(new B("bname"));
        ArrayList<C> cs = new ArrayList<>();
        C cname1 = new C("cname1");
        C cname2 = new C("cname2");
        cs.add(cname1);
        cs.add(cname2);
        a.setClist(cs);
        A cloneA1 = (A) a.clone();
        A cloneA2 = (A) a.clone();
        System.out.println(cloneA1);
        System.out.println(cloneA2);

        System.out.println(cloneA1 == cloneA2);
    }
}
