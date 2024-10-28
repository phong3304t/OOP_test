package com.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.model.users.User;
import com.service.UserService;
import com.util.CloseWindow;

import java.util.List;

public class UserController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField borrowedDocumentsField;
    @FXML private ListView<String> userListView;
    @FXML private Label statusLabel;

    private final UserService userService;

    public UserController() {
        userService = new UserService();
    }

    public void handleAddUserAction() {
        try {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            int borrowedDocuments = 0;
            User user = new User(name, email, password, borrowedDocuments);
            userService.addUser(user);
            statusLabel.setText("Document added successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleGetAllUserAction() {
        try {
            List<User> users = userService.getUsers();
            userListView.getItems().clear();

            for (User user : users) {
                String info = String.format("ID: %d | Position: %s | Name: %s " +
                                "| Email: %s| Borrowed Documents:%d",
                        user.getId(), user.getPosition(), user.getName(), user.getEmail(), user.getBorrowedDocuments());
                userListView.getItems().add(info);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleCancelAction() {
        CloseWindow.closeWindow(nameField);
        CloseWindow.closeWindow(emailField);
        CloseWindow.closeWindow(passwordField);
        CloseWindow.closeWindow(borrowedDocumentsField);
    }
}
