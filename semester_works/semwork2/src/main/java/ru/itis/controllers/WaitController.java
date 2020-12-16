package ru.itis.controllers;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import ru.itis.sockets.CheckConnectionTask;
import ru.itis.sockets.OnWaitTask;
import ru.itis.sockets.SocketClient;
import ru.itis.utils.ScreenNavigator;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitController implements Initializable {

    private ExecutorService service;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SocketClient client = MainController.getClient();
        OnWaitTask onWaitTask = new OnWaitTask(client.getFromServer(), this);
        service = Executors.newFixedThreadPool(1);
        service.execute(onWaitTask);
        client.pingServer();
    }

    public void switchToBrawl() {
        service.shutdownNow();
        Platform.runLater(() -> ScreenNavigator.loadScreen(ScreenNavigator.BRAWL));
    }
}
