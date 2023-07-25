package com.serendipity.struct_design.composite.demo01;

public class Directory extends Entry {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    protected void printList(String prefix) {

    }
}
