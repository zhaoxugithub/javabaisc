package com.serendipity.actiondesign.iterator.demo01;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现Aggreate 这个接口 说明这个类具备得迭代器的功能
 */
public class BookShelf implements Aggregate {

    private List<Book> bookList;

    public BookShelf() {
        this.bookList = new ArrayList<>();
    }

    public Book getBookByIndex(int index) {
        return bookList.get(index);
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public int getLength() {
        return bookList.size();
    }

    public Iterator iterator() {
        return new BookSelfIterator(this);
    }

    static class BookSelfIterator implements Iterator {

        private final BookShelf bookShelf;
        private int index;

        public BookSelfIterator(BookShelf bookShelf) {
            this.bookShelf = bookShelf;
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < bookShelf.getLength();
        }

        @Override
        public Object next() {
            Book book = bookShelf.getBookByIndex(index);
            index++;
            return book;
        }
    }

}
