module com.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com to javafx.fxml;
    exports com;

    opens com.controller to javafx.fxml;
    exports com.controller;

    opens com.view.gui to javafx.fxml;
    exports com.view.gui;
}