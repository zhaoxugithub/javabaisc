package com.serendipity.action_design.iterator.demo03;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/2/16 0:21
 * FileName: BookShelf
 * Description: action_design.iterator.demo03
 */
public class BookShelf implements Aggregate {

    private List<Book> bookList;

    public BookShelf() {
        bookList = new ArrayList<>();
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public int getLength() {
        return bookList.size();
    }

    public Book getBookByIndex(int index) {
        return bookList.get(index);
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
