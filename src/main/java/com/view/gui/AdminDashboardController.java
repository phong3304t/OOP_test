package com.view.gui;

import com.controller.DocumentController;
import com.controller.TransactionController;
import com.controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminDashboardController {

    @FXML private Button addDocumentButton;
    @FXML private Button removeDocumentButton;
    @FXML private Button updateDocumentButton;
    @FXML private Button findDocumentButton;
    @FXML private Button viewAllDocumentButton;
    @FXML private Button addUserButton;
    @FXML private Button borrowDocumentButton;
    @FXML private Button returnDocumentButton;
    @FXML private Button viewAllUserButton;

    @FXML
    private void handleAddDocumentAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/com/app/AddDocumentView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Document");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        DocumentController controller = new DocumentController();
        controller.handleAddDocumentAction();
        System.out.println("Navigating to Add Document View");
    }

    @FXML
    private void handleRemoveDocumentAction() {
        try {
            FXMLLoader loader = new FXMLLoader
                    (getClass().getResource("/com/app/RemoveDocumentView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Remove Document");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        DocumentController controller = new DocumentController();
        controller.handleRemoveDocumentAction();
        System.out.println("Navigating to Remove Document View");
    }

    @FXML
    private void handleFindDocumentAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/com/app/FindDocumentView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Find Document");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        DocumentController controller = new DocumentController();
        controller.handleFindDocumentAction();
        System.out.println("Navigating to Find Document View");
    }

    @FXML
    private void handleUpdateDocumentAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/com/app/UpdateDocumentView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Update Document");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        DocumentController controller = new DocumentController();
        controller.handleUpdateDocumentAction();
        System.out.println("Navigating to Update Document View");
    }

    @FXML
    private void handleViewDocumentsAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/com/app/GetAllDocumentView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("List All Document");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        DocumentController controller = new DocumentController();
        controller.handleGetAllDocumentAction();
        System.out.println("Navigating to View Documents");
    }

    @FXML
    private void handleAddUserAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/AddUserView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add User");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        UserController controller = new UserController();
        controller.handleAddUserAction();
        System.out.println("Navigating to Add User View");
    }

    @FXML
    private void handleBorrowDocumentAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/BorrowDocumentView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Borrow Document");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        TransactionController controller = new TransactionController();
        controller.handleBorrowDocumentAction();
        System.out.println("Navigating to Borrow Document View");
    }

    @FXML
    private void handleReturnDocumentAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/ReturnDocumentView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Return Document");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        TransactionController controller = new TransactionController();
        controller.handleReturnDocumentAction();
        System.out.println("Navigating to Return Document View");
    }

    @FXML
    private void handleViewUsersAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/GetAllUserView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("List All User");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        UserController controller = new UserController();
        controller.handleGetAllUserAction();
        System.out.println("Navigating to View Users");
    }
}

