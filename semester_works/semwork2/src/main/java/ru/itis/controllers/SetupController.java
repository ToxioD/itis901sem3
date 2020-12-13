package ru.itis.controllers;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SetupController implements Initializable {

    private Stage stage;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //test button press
        refreshButton.setOnAction(event -> System.out.println("Refresh button pressed"));
        submitButton.setOnAction(event -> System.out.println("Submit button pressed"));

        //test image insertion
        Image naoto = new Image("/img/naoto.jpg",400, 400, false, true);
        buffImage.setImage(naoto);

        buffPane.setOnMouseClicked(event -> choose(true));
        debuffPane.setOnMouseClicked(event -> choose(false));

        //test value insertion
        goldLabel.setText("5");
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
