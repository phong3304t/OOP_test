package com.view.gui;

import com.controller.AuthController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.model.exceptions.UserNotFoundException;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML private TextField nameField;
    @FXML private TextField userEmailField;
    @FXML private PasswordField userPasswordField;
    @FXML private Label userLoginStatusLabel;
    @FXML private TextField adminEmailField;
    @FXML private PasswordField adminPasswordField;
    @FXML private Label adminLoginStatusLabel;
    @FXML private Label statusLabel;

    private final AuthController authController;

    public LoginController() {
        authController = new AuthController();
    }

    @FXML
    private void handleUserLoginAction() {
        try {
            String userEmail = userEmailField.getText();
            String password = userPasswordField.getText();
            boolean isAuthenticated = authController.loginAsUser(userEmail, password);
            if (isAuthenticated) {
                userLoginStatusLabel.setText("Login successful!");
                loadUserDashboard();
            } else {
                userLoginStatusLabel.setText("Invalid email or password.");
            }
        } catch (UserNotFoundException | SQLException e) {
            userLoginStatusLabel.setText("Error: " + e.getMessage());
        }
    }

    public void loadUserDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/com/app/UserDashboardView.fxml"));
            Parent root = loader.load();

            Stage primaryStage = (Stage) userEmailField.getScene().getWindow();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("User Dashboard");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleAdminLoginAction() {
        String adminEmail = adminEmailField.getText();
        String password = adminPasswordField.getText();
        boolean isAuthenticated = authController.loginAsAdmin(adminEmail, password);
        if (isAuthenticated) {
            adminLoginStatusLabel.setText("Login successful!");
            loadAdminDashboard();
        } else {
            adminLoginStatusLabel.setText("Invalid email or password.");
        }
    }

    public void loadAdminDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/com/app/AdminDashboardView.fxml"));
            Parent root = loader.load();

            Stage primaryStage = (Stage) adminEmailField.getScene().getWindow();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Admin Dashboard");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //// ấn vào register -> chuyển trang register
    @FXML
    public void handleUserRegisterAction() {
        loadUserRegister();
    }

    public void loadUserRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/com/app/UserRegister.fxml"));
            Parent root = loader.load();

            Stage primaryStage = (Stage) userEmailField.getScene().getWindow();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("User Registration");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleRegisterAction(ActionEvent actionEvent) {
        String email = userEmailField.getText();
        String password = userPasswordField.getText();
        String name = nameField.getText();

        try {
            boolean isAuthenticated = authController.registerUserC(email, password, name);
            if (isAuthenticated) {
                statusLabel.setText("User registered successfully!");

            } else {
                statusLabel.setText("Cannot register");
            }
            loadLoginDashboard();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadLoginDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/com/app/LoginView.fxml"));
            Parent root = loader.load();

            Stage primaryStage = (Stage) userEmailField.getScene().getWindow();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("User Dashboard");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}