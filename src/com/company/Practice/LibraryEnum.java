package com.company.Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum LibraryEnum {

    INSTANCE;

    static { System.out.println("Static Initializer"); }


    private List<Book> books = new ArrayList<>();
    private HashMap<String, Book> map = new HashMap<>();

    public HashMap<String, Book> getBooks() { return this.map; }

//    public void addBook(Book b) {
//        if (!books.stream().filter(book -> book.isbn.equals(b.isbn)).findFirst().isPresent()) {
//            System.out.println("added book to library");
//            books.add(b);
//        } else {
//            System.out.println("already exists in library");
//        }
//    }
//
//    public void addAllBooks(List<Book> newBooks) {
//        for (Book b : newBooks) {
//            if (!books.stream().filter(book -> book.isbn.equals(b.isbn)).findFirst().isPresent()) {
//                books.add(b);
//            }
//        }
//    }

    public void addBook(Book b) {

        if (map.containsKey(b.isbn)) {
            Book book = map.get(b.isbn);
            if (book.equals(b)) {
                b.addCount();
            }
        }
        map.putIfAbsent(b.isbn, b);
    }

    public void addAllBooks(List<Book> books) {
        for (Book b : books) {
            addBook(b);
        }
    }

    public int getBooksCount() {
        int res = 0;
        for (Map.Entry<String, Book> es : this.map.entrySet()) {
            res += es.getValue().getCount();
        }
        return res;
    }

    public List<Book> searchBook(String keyword) {
        List<Book> res = new ArrayList<>();

        for (Book b : books) {
            if (b.isbn.equals(keyword) || b.author.equals(keyword) || b.title.equals(keyword))
                res.add(b);
        }

        return res;
    }
}
