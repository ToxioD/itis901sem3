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
import javafx.stage.Stage;
import ru.itis.sockets.CheckConnectionTask;
import ru.itis.sockets.ReceiveMessageTask;
import ru.itis.sockets.SocketClient;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.*;

public class ChatController implements Initializable {

    //@FXML
    //private AnchorPane pane;

    private Stage stage;
    private int readyCount;
    private ExecutorService service;

    @FXML
    private Button sendButton;

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

    /*public EventHandler<KeyEvent> keyEventEventHandler = event -> {
        if (event.getCode() == KeyCode.LEFT) {
            player.setLayoutX(player.getLayoutX() - 5);
        } else if (event.getCode() == KeyCode.RIGHT) {
            player.setLayoutX(player.getLayoutX() + 5);
        } else if (event.getCode() == KeyCode.UP) {
            player.setLayoutY(player.getLayoutY() - 5);
        } else if (event.getCode() == KeyCode.DOWN) {
            player.setLayoutY(player.getLayoutY() + 5);
        } else if (event.getCode() == KeyCode.CONTROL) {
            Circle bullet = new Circle(player.getLayoutX(), player.getLayoutY(), 5, Color.RED);
            pane.getChildren().add(bullet);

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.005), animation -> {
                bullet.setLayoutX(bullet.getLayoutX() + 2);
            }));

            timeline.setCycleCount(500);
            timeline.play();
        }

    };*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readyCount = 0;

        SocketClient client = new SocketClient("localhost", 7777);
        CheckConnectionTask checkConnectionTask = new CheckConnectionTask(client.getFromServer(), this);
        ReceiveMessageTask receiveMessageTask = new ReceiveMessageTask(client.getFromServer(), this);
        service = Executors.newFixedThreadPool(1);
        service.submit(checkConnectionTask);
        client.pingServer();
        service.execute(receiveMessageTask);

        sendButton.setOnAction(event -> client.sendMessage(messagesTextField.getText()));
        readyButton.setOnAction(event -> client.setReady());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void playerReady() {
        readyCount++;
        if (readyCount == 2) shutdown();
    }

    public void shutdown() {
        service.shutdownNow();
        Platform.exit();
        stage.close();
    }
}