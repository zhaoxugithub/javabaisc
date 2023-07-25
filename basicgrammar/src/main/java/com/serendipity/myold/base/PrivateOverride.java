package com.serendipity.myold.base;
//父类中申明私有方法，则调用父类中的方法（调用方法的方法在类内部的时候）
public class PrivateOverride {
    private void f() {
        System.out.println("private f()");
    }

    private void d() {
        System.out.println("private d()");
    }

    public void m() {
        System.out.println("super public");
    }

//    public static void main(String[] args) {
//        PrivateOverride po = new Derived();
//        po.f();//private f()
//        po.m();//child public
//        po.d();
//    }

    public void c(){
        PrivateOverride po = new Derived();
        po.f();//private f()
        po.m();//child public
        po.d();
    }
}

class Derived extends PrivateOverride {

    public void f() {
        System.out.println("public f()");//private f()
    }

    public void m() {
        System.out.println("child public");
    }
}

