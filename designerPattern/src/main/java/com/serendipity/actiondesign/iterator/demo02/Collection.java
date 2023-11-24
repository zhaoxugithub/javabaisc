package com.serendipity.actiondesign.iterator.demo02;

public interface Collection<E> {
    Iterator iterator();

    Collection add(E e);

    int size();
}
