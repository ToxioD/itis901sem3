package ru.itis.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EndController implements Initializable {

    @FXML
    private Label winnerLabel;

    @FXML
    private Label textLabel;

    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!MainController.getIsDraw()) {
            if (MainController.getIsWinner()) {
                winnerLabel.setText("You");
                textLabel.setText("Congratulations!");
            } else {
                winnerLabel.setText("Your enemy");
                textLabel.setText("Better luck next time...");
            }
        } else {
            winnerLabel.setText("Nobody");
            textLabel.setText("It is a draw...");
        }

        exitButton.setOnAction(event -> Platform.runLater(() -> MainController.shutdown()));
    }
}
