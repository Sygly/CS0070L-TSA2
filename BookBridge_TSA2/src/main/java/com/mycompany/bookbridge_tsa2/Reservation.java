package com.mycompany.bookbridge_tsa2;

public class Reservation {
    private final Member member;
    private final LibraryBook book;
    private boolean active;

    public Reservation(Member member, LibraryBook book) {
        if (member == null || book == null) {
            throw new IllegalArgumentException("Member and book are required.");
        }
        if (book.isAvailable()) {
            throw new IllegalStateException("Available books do not need reservation.");
        }
        this.member = member;
        this.book = book;
        this.active = true;
    }

    public Member getMember() {
        return member;
    }

    public LibraryBook getBook() {
        return book;
    }

    public boolean isActive() {
        return active;
    }

    public void cancel() {
        active = false;
    }
}