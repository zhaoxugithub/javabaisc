package com.serendipity.action_design.templatemethod.demo01;

public abstract class Base {
    abstract void op1();

    abstract void op2();

    public void make() {
        op1();
        op2();
    }
}
