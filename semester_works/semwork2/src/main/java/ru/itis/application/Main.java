package ru.itis.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.itis.controllers.ChatController;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/fxml/Chat.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("11-901");
        stage.setResizable(false);

        Scene scene = stage.getScene();
        ChatController controller = fxmlLoader.getController();
        controller.setStage((Stage) scene.getWindow());
        //scene.setOnKeyPressed(controller.keyEventEventHandler);
        stage.show();
    }
}
