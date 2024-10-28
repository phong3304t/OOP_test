package com.model.documents;

public class Magazine extends Document {
    private int issueNumber;

    public Magazine(String title, String author, int quantity, int issueNumber) {
        super(title, author, quantity);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public String getType() {
        return "Magazine";
    }
}
