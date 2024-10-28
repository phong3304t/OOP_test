package com.model.users;

public class User extends Admin {
    private int borrowedDocuments;

    public User(String name, String email, String password, int borrowedDocuments) {
        super(name, email, password);
        this.borrowedDocuments = borrowedDocuments;
    }

    public int getBorrowedDocuments() {
        return borrowedDocuments;
    }

    public void setBorrowedDocuments(int borrowedDocuments) {
        this.borrowedDocuments = borrowedDocuments;
    }

    @Override
    public String getPosition() {
        return "User";
    }
}
