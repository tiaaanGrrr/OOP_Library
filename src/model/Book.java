package model;

public class Book {
    private String isbn;
    private String title;
    private boolean available;

    public Book(String isbn, String title, boolean available) {
        this.isbn = isbn;
        this.title = title;
        this.available = available;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return available; }

    public void borrow() { available = false; }
    public void returnBook() { available = true; }
}
