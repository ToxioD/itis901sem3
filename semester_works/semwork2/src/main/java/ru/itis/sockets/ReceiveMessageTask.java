package ru.itis.sockets;

import javafx.application.Platform;
import javafx.concurrent.Task;
import ru.itis.controllers.MainController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReceiveMessageTask extends Task<Void> {
    private BufferedReader fromServer;
    private MainController controller;

    public ReceiveMessageTask(BufferedReader fromServer, MainController controller) {
        this.fromServer = fromServer;
        this.controller = controller;
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer != null) {
                    Platform.runLater(() -> controller.helloLabel.setText(messageFromServer));
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
