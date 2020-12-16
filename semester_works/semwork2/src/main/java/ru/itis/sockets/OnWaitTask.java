package ru.itis.sockets;

import javafx.concurrent.Task;
import ru.itis.controllers.WaitController;

import java.io.BufferedReader;
import java.io.IOException;

public class OnWaitTask extends Task<Void> {
    private BufferedReader fromServer;
    private WaitController controller;

    public OnWaitTask(BufferedReader fromServer, WaitController controller) {
        this.fromServer = fromServer;
        this.controller = controller;
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer != null) {
                    if (messageFromServer.equals("ping")) {
                        controller.switchToBrawl();
                    }
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
