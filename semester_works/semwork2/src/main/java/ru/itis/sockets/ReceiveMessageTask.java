package ru.itis.sockets;

import javafx.application.Platform;
import javafx.concurrent.Task;
import ru.itis.controllers.ChatController;

import java.io.BufferedReader;
import java.io.IOException;

public class ReceiveMessageTask extends Task<Void> {
    private BufferedReader fromServer;
    private ChatController controller;

    public ReceiveMessageTask(BufferedReader fromServer, ChatController controller) {
        this.fromServer = fromServer;
        this.controller = controller;
        Platform.runLater(() -> controller.scrollPane.vvalueProperty().bind(controller.chatPane.heightProperty()));
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer != null) {
                    if (messageFromServer.equals("ready")) {
                        Platform.runLater(() -> controller.readyCheck.setSelected(true));
                        controller.playerReady();
                    } else if (messageFromServer.equals("disable")) {
                        Platform.runLater(() -> controller.readyButton.setDisable(true));
                        controller.playerReady();
                    } else {
                        Platform.runLater(() -> controller.helloLabel.setPrefHeight(
                                controller.helloLabel.getPrefHeight() + 17));
                        Platform.runLater(() -> controller.helloLabel.setText(controller.helloLabel.getText() + "\n"
                                + messageFromServer));
                        Platform.runLater(() -> controller.chatPane.setPrefHeight(
                                controller.helloLabel.getPrefHeight() + 14));
                    }
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
