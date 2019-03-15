package com.company.Practice;

public class Book {
    String title;
    String author;
    String isbn;
    int count;

    public Book(String t, String a, String id) {
        this.title = t;
        this.author = a;
        this.isbn = id;
        this.count = 1;
    }

    public Book addCount() {
        this.count++;
        return this;
    }

    public int getCount() { return this.count; };

    @Override
    public String toString() {
        return "Title: " + this.title + " | Author: " + this.author + " | ISBN: " + this.isbn;
    }

    @Override
    public boolean equals(Object book) {
        if (!(book instanceof Book)) {
            return false;
        }

        return this.title.equals(((Book) book).title) &&
                this.author.equals(((Book) book).author) &&
                this.isbn.equals(((Book) book).isbn);
    }

}
