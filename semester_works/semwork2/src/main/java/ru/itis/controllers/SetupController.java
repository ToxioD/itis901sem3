package ru.itis.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.itis.models.Effect;
import ru.itis.sockets.SocketClient;
import ru.itis.utils.CharacterMaintainer;
import ru.itis.utils.EffectNavigator;
import ru.itis.utils.ScreenNavigator;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SetupController implements Initializable {

    private Stage stage;
    private ScheduledExecutorService service;
    private CharacterMaintainer maintainer;
    private EffectNavigator effects;
    private Effect buff;
    private Effect debuff;
    private Boolean isBuff;
    private SocketClient client;

    @FXML
    private Pane buffPane;

    @FXML
    private Label buffCost;

    @FXML
    private Label buffEffect;

    @FXML
    private ImageView buffImage;

    @FXML
    private Pane debuffPane;

    @FXML
    private Label debuffCost;

    @FXML
    private Label debuffEffect;

    @FXML
    private ImageView debuffImage;

    @FXML
    private Button refreshButton;

    @FXML
    private Label goldLabel;

    @FXML
    private Button submitButton;

    @FXML
    private ProgressBar timerBar;

    @FXML
    private Button skipButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isBuff = false;

        client = MainController.getClient();

        maintainer = MainController.getPlayerMaintainer();
        effects = MainController.getEffectNavigator();

        Platform.runLater(() -> getPair());

        ImageView refreshImage = new ImageView(new Image("/img/refresh.png", 48, 48, false, true));
        refreshButton.setGraphic(refreshImage);
        refreshButton.setOnAction(event -> Platform.runLater(() -> refresh()));

        submitButton.setOnAction(event -> Platform.runLater(() -> submit()));

        buffPane.setOnMouseClicked(event -> {
             if (Integer.parseInt(goldLabel.getText()) >= buff.getCost()) choose(true);
        });
        debuffPane.setOnMouseClicked(event -> {
            if (Integer.parseInt(goldLabel.getText()) >= debuff.getCost()) choose(false);
        });

        goldLabel.setText(maintainer.getAttribute("gold").orElse(0).toString());

        skipButton.setOnAction(event -> {
            client.pingServer();
            switchToWait();
        });

        service = Executors.newScheduledThreadPool(1);
        service.schedule(() -> Platform.runLater(() -> {
                client.pingServer();
                switchToWait();
            }), 30, TimeUnit.SECONDS);

        Timeline timer = new Timeline(new KeyFrame(Duration.millis(30), animation -> {
            timerBar.setProgress(timerBar.getProgress() - 0.001d);
        }));
        timer.setCycleCount(999);
        timer.play();
    }

    private void choose(Boolean isBuff) {
        this.isBuff = isBuff;
        buffPane.pseudoClassStateChanged(PseudoClass.getPseudoClass("chosen"), isBuff);
        debuffPane.pseudoClassStateChanged(PseudoClass.getPseudoClass("chosen"), !isBuff);
        submitButton.setDisable(false);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void switchToWait() {
        service.shutdownNow();
        ScreenNavigator.loadScreen(ScreenNavigator.WAIT);
    }

    private void getPair() {
        buff = effects.getRandomBuff();
        buffCost.setText(buff.getCost().toString() + " gold");
        buffEffect.setText(buff.getEffectText());
        Image buffImg = new Image(buff.getImagePath(),200, 200, false, true);
        buffImage.setImage(buffImg);

        debuff = effects.getRandomDebuff();
        debuffCost.setText(debuff.getCost().toString() + " gold");
        debuffEffect.setText(debuff.getEffectText());
        Image debuffImg = new Image(debuff.getImagePath(),200, 200, false, true);
        debuffImage.setImage(debuffImg);
    }

    private void refresh() {
        try {
            Integer gold = maintainer.getAttribute("gold").orElse(0);
            if (gold > 0) {
                gold--;
                maintainer.setAttribute("gold", gold);
                goldLabel.setText(gold.toString());
                getPair();
                submitButton.setDisable(true);
                buffPane.pseudoClassStateChanged(PseudoClass.getPseudoClass("chosen"), false);
                debuffPane.pseudoClassStateChanged(PseudoClass.getPseudoClass("chosen"), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void submit() {
        if (isBuff) {
            maintainer.payForEffect(buff);
            maintainer.addEffect(buff);
            client.pingServer();
            switchToWait();
        } else {
            maintainer.payForEffect(debuff);
            client.sendString(effects.encodeEffect(debuff));
            switchToWait();
        }
    }
}
