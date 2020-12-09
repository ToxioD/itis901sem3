package ru.itis.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import ru.itis.sockets.ReceiveMessageTask;
import ru.itis.sockets.SocketClient;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private Button helloButton;

    @FXML
    public Label helloLabel;

    @FXML
    private Circle player;

    @FXML
    private TextField messagesTextField;

    public EventHandler<KeyEvent> keyEventEventHandler = event -> {
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

    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SocketClient client = new SocketClient("localhost", 7777);
        ReceiveMessageTask receiveMessageTask = new ReceiveMessageTask(client.getFromServer(), this);
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(receiveMessageTask);

        helloButton.setOnAction(event -> {
            client.sendMessage(messagesTextField.getText());
        });
    }
}
