package com.model.transactions;

import java.time.LocalDate;

public class ReturnTransaction {
    private int documentId;
    private int userId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private double fine;

    public ReturnTransaction(int documentId, int userId, LocalDate borrowDate, LocalDate returnDate, double fine) {
        this.documentId = documentId;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.fine = fine;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public String getType() {
        return "Return";
    }
}