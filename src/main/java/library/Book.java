package library;

import java.util.Objects;

public class Book {
    private final String isbn;
    private final String title;
    private final int year;

    Book(String isbn, String title, int year) {
        if (isbn == null || isbn.isBlank()) throw new IllegalArgumentException("ISBN is required");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title is required");
        this.isbn = isbn;
        this.title = title;
        this.year = year;
    }

    String getIsbn()  { return isbn; }
    String getTitle() { return title; }
    int getYear()     { return year; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        return isbn.equals(((Book) o).isbn);
    }

    @Override
    public int hashCode() { return Objects.hash(isbn); }

    @Override
    public String toString() { return title + " (" + year + ", ISBN " + isbn + ")"; }
}
