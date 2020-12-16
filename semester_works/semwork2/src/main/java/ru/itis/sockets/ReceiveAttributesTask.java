package ru.itis.sockets;

import javafx.application.Platform;
import javafx.concurrent.Task;
import ru.itis.controllers.BrawlController;

import java.io.BufferedReader;
import java.io.IOException;

public class ReceiveAttributesTask extends Task<Void> {
    private BufferedReader fromServer;
    private BrawlController controller;

    public ReceiveAttributesTask(BufferedReader fromServer, BrawlController controller) {
        this.fromServer = fromServer;
        this.controller = controller;
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer != null && !messageFromServer.equals("ping")) {
                    String[] attribute = messageFromServer.split(",");
                    if (attribute[0].equals("hit")) {
                        if (Integer.parseInt(attribute[1]) == 0) {
                            Platform.runLater(() -> controller.enemyHitCheck.setIndeterminate(true));
                        } else {
                            Platform.runLater(() -> controller.enemyHitCheck.setSelected(true));
                        }
                    } else if (attribute[0].equals("damage")) {
                        Platform.runLater(() -> controller.dealDamageToSelf(Integer.parseInt(attribute[1])));
                    } else if (attribute[0].equals("end")) {
                        this.cancel();
                    } else Platform.runLater(() -> controller.updateEnemyAttribute(attribute[0], Integer.parseInt(attribute[1])));
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
