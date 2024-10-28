package com.model.documents;

public class Thesis extends Document {
    private String topic;

    public Thesis(String title, String author, int quantity, String topic) {
        super(title, author, quantity);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String getType() {
        return "Thesis";
    }
}
