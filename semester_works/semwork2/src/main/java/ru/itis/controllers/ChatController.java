package ru.itis.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ru.itis.sockets.CheckConnectionTask;
import ru.itis.sockets.ReceiveMessageTask;
import ru.itis.sockets.SocketClient;
import ru.itis.utils.ScreenNavigator;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.*;

public class ChatController implements Initializable {

    private int readyCount;
    private ExecutorService service;

    @FXML
    public Button sendButton;

    @FXML
    public Button readyButton;

    @FXML
    public Label helloLabel;

    @FXML
    private TextField messagesTextField;

    @FXML
    public AnchorPane chatPane;

    @FXML
    public ScrollPane scrollPane;

    @FXML
    public CheckBox connectedCheck;

    @FXML
    public CheckBox readyCheck;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readyCount = 0;

        SocketClient client = MainController.getClient();
        CheckConnectionTask checkConnectionTask = new CheckConnectionTask(client.getFromServer(), this);
        ReceiveMessageTask receiveMessageTask = new ReceiveMessageTask(client.getFromServer(), this);
        service = Executors.newFixedThreadPool(1);
        service.submit(checkConnectionTask);
        client.pingServer();
        service.execute(receiveMessageTask);

        sendButton.setOnAction(event -> client.sendMessage(messagesTextField.getText()));
        readyButton.setOnAction(event -> client.setReady());
    }

    public void playerReady() {
        readyCount++;
        if (readyCount == 2) Platform.runLater(() -> switchToSetup());
    }

    private void switchToSetup() {
        service.shutdownNow();
        ScreenNavigator.loadScreen(ScreenNavigator.SETUP);
    }
}