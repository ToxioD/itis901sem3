package ru.itis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import ru.itis.utils.CharacterMaintainer;

import java.net.URL;
import java.util.ResourceBundle;

public class BrawlController implements Initializable {

    @FXML
    private ProgressBar playerHp;

    @FXML
    private ImageView playerImage;

    @FXML
    private CheckBox playerHitCheck;

    @FXML
    private Label playerDamageLabel;

    @FXML
    private ProgressBar enemyHp;

    @FXML
    private ImageView  enemyImage;

    @FXML
    private CheckBox enemyHitCheck;

    @FXML
    private Label enemyDamageLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CharacterMaintainer maintainer = MainController.getCharacterMaintainer();
        Integer hp = maintainer.getAttribute("hp").orElse(1);
        playerHp.setProgress(Double.valueOf(hp) / maintainer.getAttribute("maxHp").orElse(1));
    }
}
