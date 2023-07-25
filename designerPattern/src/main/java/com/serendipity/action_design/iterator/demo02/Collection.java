package com.serendipity.action_design.iterator.demo02;

public interface Collection<E> {
    Iterator iterator();

    Collection add(E e);

    int size();
}
