package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    private List<Book> books;
    private List<Loan> loans;
    private FileStorageService storage;

    public LibraryService() {
        storage = new FileStorageService();
        books = storage.loadBooks();
        loans = storage.loadLoans();
    }

    public List<Book> getBooks() {
        return books;
    }

    // ================= ADD BOOK (ADMIN) =================
    public void addBook(Book book) {
        books.add(book);
        storage.saveBooks(books);
    }

    // ================= BORROW BOOK =================
    public Loan borrowBook(String memberId, String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn) && b.isAvailable()) {
                b.borrow();

                Loan loan = new Loan(
                        "L" + (loans.size() + 1),
                        memberId,
                        isbn
                );

                loans.add(loan);
                storage.saveBooks(books);
                storage.saveLoans(loans);
                return loan;
            }
        }
        return null;
    }

    // ================= RETURN BOOK + FINE =================
    public Fine returnBook(String loanId) {
        for (Loan l : loans) {
            if (l.getLoanId().equals(loanId) && !l.isReturned()) {
                l.returnBook();

                for (Book b : books) {
                    if (b.getIsbn().equals(l.getIsbn())) {
                        b.returnBook();
                    }
                }

                storage.saveBooks(books);
                storage.saveLoans(loans);

                return new Fine("F-" + loanId, l);
            }
        }
        return null;
    }
}
