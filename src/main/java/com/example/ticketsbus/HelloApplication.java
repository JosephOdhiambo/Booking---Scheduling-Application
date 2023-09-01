package com.example.ticketsbus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Font.loadFont(getClass().getResourceAsStream("Claston.otf"), 40);
        Font.loadFont(getClass().getResourceAsStream("Absolutely Vintage.ttf"), 120);
        Font.loadFont(getClass().getResourceAsStream("Super Funtime.ttf"), 120);
        Image icon16 = new Image(String.valueOf(HelloApplication.class.getResource("img/Compass_16px.png")));
        Image icon32 = new Image(String.valueOf(HelloApplication.class.getResource("img/Compass_32px.png")));
        Image icon48 = new Image(String.valueOf(HelloApplication.class.getResource("img/Compass_64px.png")));

        stage.getIcons().addAll(icon16, icon32, icon48);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setTitle("User Login");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch();
    }
}