package com.serendipity.createdesign.factory.demo02;

public abstract class Pizza {

    private String name;

    public Pizza(String name) {
        this.name = name;
    }

    public abstract void prepare();

    public void bake() {
        System.out.println(name + "--bake");
    }

    public void cut() {
        System.out.println(name + "--cut");
    }

    public void box() {
        System.out.println(name + "--box");
    }
}
