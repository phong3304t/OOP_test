package com.model.documents;

public abstract class Document {
    protected static int idCounter = 1;
    protected int id;
    protected String title;
    protected String author;
    protected int quantity;

    public Document(String title, String author, int quantity) {
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public abstract String getType();
}
