package com.controller;

import javafx.scene.control.Label;
import com.model.transactions.BorrowTransaction;
import com.model.transactions.ReturnTransaction;
import com.util.CloseWindow;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import com.service.TransactionService;

import java.time.LocalDate;

public class TransactionController {

    @FXML private TextField documentIdField;
    @FXML private TextField userIdField;
    @FXML private DatePicker borrowDatePicker;
    @FXML private DatePicker dueDatePicker;
    @FXML private DatePicker returnDatePicker;
    @FXML private TextField fineField;
    @FXML private Label statusLabel;

    private final TransactionService transactionService;

    public TransactionController() {
        transactionService = new TransactionService();
    }

    @FXML
    public void handleBorrowDocumentAction() {
        try {
            int documentId = Integer.parseInt(documentIdField.getText());
            int userId = Integer.parseInt(userIdField.getText());
            LocalDate borrowDate = borrowDatePicker.getValue();
            LocalDate dueDate = dueDatePicker.getValue();

            if (borrowDate == null || dueDate == null) {
                statusLabel.setText("Please select borrow and due dates.");
                return;
            }

            BorrowTransaction transaction = new BorrowTransaction(documentId, userId, borrowDate, dueDate);
            transactionService.borrowDocument(transaction);
            statusLabel.setText("Borrow successful!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid document or user ID.");
        } catch (Exception e) {
            statusLabel.setText("An error occurred: " + e.getMessage());
        }
    }

    @FXML
    public void handleReturnDocumentAction() {
        try {
            int documentId = Integer.parseInt(documentIdField.getText());
            int userId = Integer.parseInt(userIdField.getText());
            LocalDate borrowDate = borrowDatePicker.getValue();
            LocalDate returnDate = returnDatePicker.getValue();
            double fine = fineField.getText().isEmpty() ? 0.0 : Double.parseDouble(fineField.getText());

            if (borrowDate == null || returnDate == null) {
                statusLabel.setText("Please select borrow and return dates.");
                return;
            }

            ReturnTransaction transaction = new ReturnTransaction(documentId, userId, borrowDate, returnDate, fine);
            transactionService.returnDocument(transaction);
            statusLabel.setText("Return successful!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid document, user ID, or fine amount.");
        } catch (Exception e) {
            statusLabel.setText("An error occurred: " + e.getMessage());
        }
    }

    @FXML
    public void handleCancelAction() {
        CloseWindow.closeWindow(documentIdField);
        CloseWindow.closeWindow(userIdField);
        CloseWindow.closeWindow(borrowDatePicker);
        CloseWindow.closeWindow(dueDatePicker);
        CloseWindow.closeWindow(returnDatePicker);
        CloseWindow.closeWindow(fineField);
    }
}
