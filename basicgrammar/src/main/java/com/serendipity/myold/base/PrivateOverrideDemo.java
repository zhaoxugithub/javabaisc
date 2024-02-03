package com.serendipity.myold.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author 11931
 */
@SuppressWarnings("all")
@Slf4j
public class PrivateOverrideDemo {
    class PrivateOverride {
        private void f() {
            System.out.println("private f()");
        }

        private void d() {
            System.out.println("private d()");
        }

        public void m() {
            System.out.println("super public");
        }

        public void c() {
            PrivateOverride po = new Derived();
            po.f();// private f()
            po.m();// child public
            po.d();
        }
    }

    class Derived extends PrivateOverride {
        public void f() {
            System.out.println("public f()");// private f()
        }

        @Override
        public void m() {
            System.out.println("child public");
        }
    }

    @Test
    public void test() {
        // 报错
        /*
            PrivateOverride po = new Derived();
            po.f();// private f()
            po.m();// child public
            po.d();
        */
        PrivateOverride po = new PrivateOverride();
        po.c();
    }
}
