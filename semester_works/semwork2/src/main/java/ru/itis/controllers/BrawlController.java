package ru.itis.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import ru.itis.sockets.ReceiveAttributesTask;
import ru.itis.sockets.SocketClient;
import ru.itis.utils.CharacterMaintainer;
import ru.itis.utils.ScreenNavigator;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BrawlController implements Initializable {

    private ScheduledExecutorService service;
    private SocketClient client;
    private CharacterMaintainer playerMaintainer;
    private CharacterMaintainer enemyMaintainer;
    private Random random;
    private Boolean isHit;

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
    public CheckBox enemyHitCheck;

    @FXML
    public Label enemyDamageLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        random = new Random();
        isHit = false;

        playerMaintainer = MainController.getPlayerMaintainer();
        enemyMaintainer = MainController.getEnemyMaintainer();

        client = MainController.getClient();
        ReceiveAttributesTask receiveAttributesTask = new ReceiveAttributesTask(client.getFromServer(), this);
        service = Executors.newScheduledThreadPool(2);
        service.schedule(receiveAttributesTask, 100, TimeUnit.MILLISECONDS);
        service.schedule(() -> Platform.runLater(() -> tryToHit()), 3, TimeUnit.SECONDS);
        service.schedule(() -> Platform.runLater(() -> dealDamage()), 5, TimeUnit.SECONDS);
        service.schedule(() -> client.sendAttribute("end",0), 7, TimeUnit.SECONDS);
        service.schedule(() -> Platform.runLater(() -> switchToSetup()), 8, TimeUnit.SECONDS);


        Platform.runLater(() -> updatePlayerAttributes());
        Platform.runLater(() -> updateEnemyAttributes());
    }

    public void updateEnemyAttribute(String attrName, Integer value) {
        try {
            enemyMaintainer.setAttribute(attrName, value);
            updateEnemyAttributes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateEnemyAttributes() {
        Integer hp = enemyMaintainer.getAttribute("hp").orElse(1);
        enemyHp.setProgress(Double.valueOf(hp) / enemyMaintainer.getAttribute("maxHp").orElse(1));
    }

    private void updatePlayerAttributes() {
        Integer hp = playerMaintainer.getAttribute("hp").orElse(1);
        playerHp.setProgress(Double.valueOf(hp) / playerMaintainer.getAttribute("maxHp").orElse(1));
        client.sendAttribute("hp", hp);
    }

    public void dealDamageToSelf(Integer damage) {
        try {
            playerMaintainer.setAttribute("hp",
                    playerMaintainer.getAttribute("hp").orElse(1) - damage);
            playerMaintainer.setAttribute("gold",
                    playerMaintainer.getAttribute("gold").orElse(0) + damage);
            enemyDamageLabel.setText(damage.toString());
            updatePlayerAttributes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tryToHit() {
        if (random.nextInt(100) + 1 <= playerMaintainer.getAttribute("hitChance").orElse(50)) {
            playerHitCheck.setSelected(true);
            client.sendAttribute("hit", 1);
            isHit = true;
        } else {
            playerHitCheck.setIndeterminate(true);
            client.sendAttribute("hit", 0);
        }
    }

    private void dealDamage() {
        Integer damage = 0;
        if (isHit) {
            damage = playerMaintainer.getAttribute("damage").orElse(5);
        }
        playerDamageLabel.setText(damage.toString());
        client.sendAttribute("damage", damage);
    }

    private void switchToSetup() {
        service.shutdownNow();
        ScreenNavigator.loadScreen(ScreenNavigator.SETUP);
    }
}
