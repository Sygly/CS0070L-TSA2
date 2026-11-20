package com.mycompany.bookbridge_tsa2;

public class LibraryBook {
    private final String isbn;
    private final String title;
    private boolean available;

    public LibraryBook(String isbn, String title, boolean available) {
        if (isbn == null || isbn.trim().isEmpty()
                || title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Book details cannot be blank.");
        }
        this.isbn = isbn.trim();
        this.title = title.trim();
        this.available = available;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void markBorrowed() {
        if (!available) {
            throw new IllegalStateException("Book is not available.");
        }
        available = false;
    }

    public void markReturned() {
        available = true;
    }
}