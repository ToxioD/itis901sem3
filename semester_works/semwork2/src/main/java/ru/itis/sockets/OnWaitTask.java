package ru.itis.sockets;

import javafx.application.Platform;
import javafx.concurrent.Task;
import ru.itis.controllers.WaitController;
import ru.itis.utils.CharacterMaintainer;
import ru.itis.utils.EffectNavigator;

import java.io.BufferedReader;
import java.io.IOException;

public class OnWaitTask extends Task<Void> {
    private BufferedReader fromServer;
    private WaitController controller;
    private EffectNavigator navigator;
    private CharacterMaintainer maintaner;

    public OnWaitTask(BufferedReader fromServer, WaitController controller, EffectNavigator navigator, CharacterMaintainer maintaner) {
        this.fromServer = fromServer;
        this.controller = controller;
        this.navigator = navigator;
        this.maintaner = maintaner;
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer != null) {
                    if (!messageFromServer.equals("ping")) {
                        maintaner.addEffect(navigator.parseEffect(messageFromServer.split(",")));
                    }
                    Platform.runLater(() -> controller.switchToBrawl());
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
