package ru.itis.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import ru.itis.sockets.SocketClient;

import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

public class MainController {

    private static SocketClient client = new SocketClient("localhost", 7777);

    @FXML
    private AnchorPane screenHolder;

    public void setScreen(Node node) {
        screenHolder.getChildren().setAll(node);
    }

    public static SocketClient getClient() {
        return client;
    }

}