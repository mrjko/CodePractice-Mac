package com.company.Practice;

public class DummyClass {

    LibraryEnum library = LibraryEnum.INSTANCE;

    public void modifySomething(String isbn) {

        Book book = new Book("Dummy book", "Dummy class", isbn);
        library.addBook(book);
    }

}
