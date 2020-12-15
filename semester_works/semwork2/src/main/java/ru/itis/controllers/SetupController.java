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
import ru.itis.utils.ScreenNavigator;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SetupController implements Initializable {

    private Stage stage;
    private ScheduledExecutorService service;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submitButton.setOnAction(event -> ScreenNavigator.loadScreen(ScreenNavigator.CHAT));

        //test button press
        refreshButton.setOnAction(event -> System.out.println("Refresh button pressed"));

        //test image insertion
        Image naoto = new Image("/img/naoto.jpg",400, 400, false, true);
        buffImage.setImage(naoto);

        buffPane.setOnMouseClicked(event -> choose(true));
        debuffPane.setOnMouseClicked(event -> choose(false));

        //test value insertion
        goldLabel.setText("5");

        service = Executors.newScheduledThreadPool(1);
        service.schedule(() -> Platform.runLater(() -> ScreenNavigator.loadScreen(ScreenNavigator.WAIT)),
                30, TimeUnit.SECONDS);

        Timeline timer = new Timeline(new KeyFrame(Duration.millis(30), animation -> {
            timerBar.setProgress(timerBar.getProgress() - 0.001d);
        }));
        timer.setCycleCount(999);
        timer.play();
    }

    private void choose(Boolean isBuff) {
        buffPane.pseudoClassStateChanged(PseudoClass.getPseudoClass("chosen"), isBuff);
        debuffPane.pseudoClassStateChanged(PseudoClass.getPseudoClass("chosen"), !isBuff);
        submitButton.setDisable(false);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
