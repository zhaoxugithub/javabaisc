package com.serendipity.action_design.iterator.demo03;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/2/16 0:30
 * FileName: BookShelfIterator
 * Description: action_design.iterator.demo03
 */
public class BookShelfIterator implements Iterator {

    private BookShelf bookShelf;
    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    @Override
    public Object next() {
        return bookShelf.getBookByIndex(index++);
    }

    @Override
    public boolean hasNext() {
        return index < bookShelf.getLength();
    }
}
