package com.uopeople.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WeatherApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WeatherView.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Weather Information App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}