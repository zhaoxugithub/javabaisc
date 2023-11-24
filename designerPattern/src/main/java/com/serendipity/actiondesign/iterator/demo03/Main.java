package com.serendipity.actiondesign.iterator.demo03;


public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf();
        bookShelf.addBook(new Book("Around the World in 80 Days"));
        bookShelf.addBook(new Book("Bible"));
        bookShelf.addBook(new Book("Cinderella"));
        bookShelf.addBook(new Book("Daddy-Long-Legs"));
        bookShelf.addBook(new Book("Daddy-Long-Legs1"));
        bookShelf.addBook(new Book("Daddy-Long-Legs2"));
        bookShelf.addBook(new Book("Daddy-Long-Legs3"));
        Iterator it = bookShelf.iterator();
        while (it.hasNext()) {
            Book book = (Book) it.next();
            System.out.println(book.getName());
        }
    }
}
