package ru.itis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SetupController implements Initializable {

    private Stage stage;

    @FXML
    private Button testButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        testButton.setOnAction(event -> System.out.println("Test button pressed"));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
