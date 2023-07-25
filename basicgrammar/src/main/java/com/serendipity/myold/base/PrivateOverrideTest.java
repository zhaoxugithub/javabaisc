package com.serendipity.myold.base;

public class PrivateOverrideTest {
    public static void main(String[] args) {
        //报错
//        PrivateOverride po = new Derived();
//        po.f();//private f()
//        po.m();//child public
//        po.d();
        PrivateOverride po = new PrivateOverride();
        po.c();
    }
}
