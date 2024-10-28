package com.model.documents;

public class Book extends Document {
    private String genre;

    public Book(String title, String author, int quantity, String genre) {
        super(title, author, quantity);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String getType() {
        return "Book";
    }
}
