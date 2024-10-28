package com.model.transactions;

import java.time.LocalDate;

public class BorrowTransaction {
    private int documentId;
    private int userId;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public BorrowTransaction(int documentId, int userId, LocalDate borrowDate, LocalDate dueDate) {
        this.documentId = documentId;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getType() {
        return "Borrow";
    }
}
