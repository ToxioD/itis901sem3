package ru.itis.sockets;

import javafx.application.Platform;
import javafx.concurrent.Task;
import ru.itis.controllers.ChatController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class CheckConnectionTask implements Callable<Boolean> {
    private BufferedReader fromServer;
    private ChatController controller;

    public CheckConnectionTask(BufferedReader fromServer, ChatController controller) {
        this.fromServer = fromServer;
        this.controller = controller;
    }

    @Override
    public Boolean call() throws Exception {
        while (true) {
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer.equals("ping")) {
                    Platform.runLater(() -> controller.connectedCheck.setSelected(true));
                    Platform.runLater(() -> controller.readyButton.setVisible(true));
                    Platform.runLater(() -> controller.readyButton.setDisable(false));
                    return true;
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
