package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Catalog {
    private final List<Book> books = new ArrayList<>();

    public void add(Book book) {
        if (book != null) books.add(book);
    }

    public boolean removeByIsbn(String isbn) {
        return books.removeIf(b -> b.getIsbn().equalsIgnoreCase(isbn));
    }

    public List<Book> getAll() {
        return Collections.unmodifiableList(books);
    }

    public Book findByIsbn(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equalsIgnoreCase(isbn)) return b;
        }
        return null;
    }

    public List<Book> searchByTitle(String keyword) {
        List<Book> result = new ArrayList<>();
        if (keyword == null) return result;
        String k = keyword.toLowerCase();
        for (Book b : books) {
            if (b.getTitle() != null && b.getTitle().toLowerCase().contains(k)) result.add(b);
        }
        return result;
    }

    public List<Book> searchByAuthor(String keyword) {
        List<Book> result = new ArrayList<>();
        if (keyword == null) return result;
        String k = keyword.toLowerCase();
        for (Book b : books) {
            if (b.getTitle() != null && b.getTitle().toLowerCase().contains(k)) result.add(b);
        }
        return result;
    }
}
