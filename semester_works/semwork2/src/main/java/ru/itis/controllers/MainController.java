package ru.itis.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import ru.itis.sockets.SocketClient;
import ru.itis.utils.CharacterMaintainer;
import ru.itis.utils.EffectNavigator;

public class MainController {

    private static SocketClient client = new SocketClient("localhost", 7777);
    private static CharacterMaintainer playerMaintainer = new CharacterMaintainer();
    private static CharacterMaintainer enemyMaintainer = new CharacterMaintainer();
    private static EffectNavigator effectNavigator = new EffectNavigator();

    @FXML
    private AnchorPane screenHolder;

    public void setScreen(Node node) {
        screenHolder.getChildren().setAll(node);
    }

    public static SocketClient getClient() {
        return client;
    }

    public static CharacterMaintainer getPlayerMaintainer() {
        return playerMaintainer;
    }

    public static CharacterMaintainer getEnemyMaintainer() {
        return enemyMaintainer;
    }

    public static EffectNavigator getEffectNavigator() {
        return effectNavigator;
    }

    public static void shutdown() {
        Platform.exit();
        client.closeConnection();
    }

}