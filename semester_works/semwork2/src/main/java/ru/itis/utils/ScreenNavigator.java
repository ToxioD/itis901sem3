package ru.itis.utils;

import javafx.fxml.FXMLLoader;
import ru.itis.controllers.MainController;

import java.io.IOException;

public class ScreenNavigator {

    public static final String MAIN = "/fxml/Main.fxml";
    public static final String CHAT = "/fxml/Chat.fxml";
    public static final String SETUP = "/fxml/Setup.fxml";
    public static final String WAIT = "/fxml/Wait.fxml";
    public static final String BRAWL = "/fxml/Brawl.fxml";
    public static final String END = "/fxml/End.fxml";

    private static MainController mainController;

    public static void setMainController(MainController mainController) {
        ScreenNavigator.mainController = mainController;
    }

    public static void loadScreen(String fxml) {
        try {
            mainController.setScreen(
                    FXMLLoader.load(
                            ScreenNavigator.class.getResource(
                                    fxml
                            )
                    )
            );
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
