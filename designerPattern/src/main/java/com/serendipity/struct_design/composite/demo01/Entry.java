package com.serendipity.struct_design.composite.demo01;

public abstract class Entry {
    public abstract String getName();

    public abstract int getSize();

    public void printList() {
        printList("");
    }

    protected abstract void printList(String prefix);

    public Entry add(Entry entry) throws FileTreatmentException {
        throw new FileTreatmentException();
    }

    @Override
    public String toString() {
        return getName() + "(" + getSize()+")";
    }
}
