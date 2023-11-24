package com.serendipity.structdesign.composite.demo01;

public class File extends Entry {

    private int size;
    private String name;

    public File(int size, String name) {
        this.size = size;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix + "/" + this);
    }
}
