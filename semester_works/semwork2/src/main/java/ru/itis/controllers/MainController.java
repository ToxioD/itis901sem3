package ru.itis.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private AnchorPane screenHolder;


    public void setScreen(Node node) {
        screenHolder.getChildren().setAll(node);
    }

}