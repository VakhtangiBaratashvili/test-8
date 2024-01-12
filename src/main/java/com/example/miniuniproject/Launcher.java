package com.example.miniuniproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 656, 454);
        stage.setTitle("Students Project");
        stage.setScene(scene);
        stage.show();
        Controller controller = fxmlLoader.getController();;
        controller.updateTableView();
    }

    public static void main(String[] args) {
        launch();
    }
}
