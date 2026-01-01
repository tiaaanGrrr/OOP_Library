package model;

import java.time.LocalDate;

public class Loan {

    private String loanId;
    private String memberId;
    private String isbn;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private boolean returned;

    public Loan(String loanId, String memberId, String isbn) {
        this.loanId = loanId;
        this.memberId = memberId;
        this.isbn = isbn;
        this.loanDate = LocalDate.now();
        this.returned = false;
    }

    // ===== GETTER =====
    public String getLoanId() {
        return loanId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    // ===== SETTER (UNTUK FILE LOAD) =====
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void markReturned() {
        this.returned = true;
    }

    // ===== ACTION =====
    public void returnBook() {
        this.returned = true;
        this.returnDate = LocalDate.now();
    }
}
