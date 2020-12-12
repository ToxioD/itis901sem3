package ru.itis.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.itis.controllers.ChatController;
import ru.itis.controllers.MainController;
import ru.itis.controllers.SetupController;
import ru.itis.utils.ScreenNavigator;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("11-901");
        stage.setScene(
                new Scene(
                        loadMainPane()
                )
        );
        stage.show();
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
                getClass().getResourceAsStream(
                        ScreenNavigator.MAIN
                )
        );

        MainController mainController = loader.getController();

        ScreenNavigator.setMainController(mainController);
        ScreenNavigator.loadScreen(ScreenNavigator.CHAT);

        return mainPane;
    }
}
