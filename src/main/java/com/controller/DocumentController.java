package com.controller;

import javafx.scene.control.Label;
import com.model.documents.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import com.service.DocumentService;
import com.util.CloseWindow;

import java.util.List;

public class DocumentController {

    @FXML private TextField idField;
    @FXML private TextField typeField;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField quantityField;
    @FXML private TextField genreField;
    @FXML private TextField issueNumberField;
    @FXML private TextField topicField;
    @FXML private Label documentStatusLabel;
    @FXML private ListView<String> documentListView;

    private final DocumentService documentService;

    public DocumentController() {
        documentService = new DocumentService();
    }

    public void handleAddDocumentAction() {
        try {
            String type = typeField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            String genre = genreField.getText();
            int issueNumber = Integer.parseInt(issueNumberField.getText());
            String topic = topicField.getText();
            Document doc = null;
            if ("Book".equals(type)) {
                doc = new Book(title, author, quantity, genre);
            } else if ("Magazine".equals(type)) {
                doc = new Magazine(title, author, quantity, issueNumber);
            } else if ("Thesis".equals(type)) {
                doc = new Thesis(title, author, quantity, topic);
            }
            documentService.addDocument(doc);
            documentStatusLabel.setText("Document added successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleRemoveDocumentAction() {
        try {
            int id = Integer.parseInt(idField.getText());
            documentService.removeDocument(id);
            documentStatusLabel.setText("Document removed successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleUpdateDocumentAction() {
        try {
            String type = typeField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            String genre = genreField.getText();
            int issueNumber = Integer.parseInt(issueNumberField.getText());
            String topic = topicField.getText();
            Document doc = null;
            if ("Book".equals(type)) {
                doc = new Book(title, author, quantity, genre);
            } else if ("Magazine".equals(type)) {
                doc = new Magazine(title, author, quantity, issueNumber);
            } else if ("Thesis".equals(type)) {
                doc = new Thesis(title, author, quantity, topic);
            }
            documentService.updateDocument(doc);
            documentStatusLabel.setText("Document updated successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleFindDocumentAction(){
        try {
            String keyword = titleField.getText();
            List<Document> documents = documentService.findDocument(keyword);
            documentListView.getItems().clear();

            for (Document doc : documents) {
                if (doc instanceof Book) {
                    String info = String.format("ID: %s | Type: %s | Title: %s " +
                                    "| Author: %s | Quantity: %d | Genre: %s",
                            doc.getId(), doc.getType(), doc.getTitle(),
                            doc.getAuthor(), doc.getQuantity(), ((Book) doc).getGenre());
                    documentListView.getItems().add(info);
                } else if (doc instanceof Magazine) {
                    String info = String.format("ID: %s | Type: %s | Title: %s " +
                                    "| Author: %s | Quantity: %d | Issue Number: %d",
                            doc.getId(), doc.getType(), doc.getTitle(),
                            doc.getAuthor(), doc.getQuantity(), ((Magazine) doc).getIssueNumber());
                    documentListView.getItems().add(info);
                } else if (doc instanceof Thesis) {
                    String info = String.format("ID: %s | Type: %s | Title: %s " +
                                    "| Author: %s | Quantity: %d | Topic: %s",
                            doc.getId(), doc.getType(), doc.getTitle(),
                            doc.getAuthor(), doc.getQuantity(), ((Thesis) doc).getTopic());
                    documentListView.getItems().add(info);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleGetAllDocumentAction() {
        try {
            List<Document> documents = documentService.getDocuments();
            documentListView.getItems().clear();

            for (Document doc : documents) {
                if (doc instanceof Book) {
                    String info = String.format("ID: %s | Type: %s | Title: %s " +
                                    "| Author: %s | Quantity: %d | Genre: %s",
                            doc.getId(), doc.getType(), doc.getTitle(),
                            doc.getAuthor(), doc.getQuantity(), ((Book) doc).getGenre());
                    documentListView.getItems().add(info);
                } else if (doc instanceof Magazine) {
                    String info = String.format("ID: %s | Type: %s | Title: %s " +
                                    "| Author: %s | Quantity: %d | Issue Number: %d",
                            doc.getId(), doc.getType(), doc.getTitle(),
                            doc.getAuthor(), doc.getQuantity(), ((Magazine) doc).getIssueNumber());
                    documentListView.getItems().add(info);
                } else if (doc instanceof Thesis) {
                    String info = String.format("ID: %s | Type: %s | Title: %s " +
                                    "| Author: %s | Quantity: %d | Topic: %s",
                            doc.getId(), doc.getType(), doc.getTitle(),
                            doc.getAuthor(), doc.getQuantity(), ((Thesis) doc).getTopic());
                    documentListView.getItems().add(info);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleCancelAction() {
        CloseWindow.closeWindow(idField);
        CloseWindow.closeWindow(typeField);
        CloseWindow.closeWindow(titleField);
        CloseWindow.closeWindow(authorField);
        CloseWindow.closeWindow(quantityField);
        CloseWindow.closeWindow(genreField);
        CloseWindow.closeWindow(issueNumberField);
        CloseWindow.closeWindow(topicField);
    }
}