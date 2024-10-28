package com.view.gui;

import com.controller.DocumentController;
import com.controller.TransactionController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserDashboardController {

    @FXML private Button findDocumentButton;
    @FXML private Button viewAllDocumentButton;
    @FXML private Button borrowDocumentButton;
    @FXML private Button returnDocumentButton;

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
}