package com.util;

import javafx.scene.Node;
import javafx.stage.Stage;

public class CloseWindow {

    /**
     * close window.
     * @param node any node (Button, TextField, etc.)
     */
    public static void closeWindow(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
