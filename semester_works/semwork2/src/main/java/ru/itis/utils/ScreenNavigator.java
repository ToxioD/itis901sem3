package ru.itis.utils;

import javafx.fxml.FXMLLoader;
import ru.itis.controllers.MainController;

import java.io.IOException;

public class ScreenNavigator {

    public static final String MAIN = "/fxml/Main.fxml";
    public static final String CHAT = "/fxml/Chat.fxml";
    public static final String SETUP = "/fxml/Setup.fxml";

    private static MainController mainController;

    public static void setMainController(MainController mainController) {
        ScreenNavigator.mainController = mainController;
    }

    public static void loadScreen(String fxml) {
        try {
            System.out.println("Changing...");
            mainController.setScreen(
                    FXMLLoader.load(
                            ScreenNavigator.class.getResource(
                                    fxml
                            )
                    )
            );
            System.out.println("Screen loaded");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
