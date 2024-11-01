package com.uopeople.weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class WeatherController {

    @FXML
    private TextField locationField;
    @FXML
    private Label temperatureLabel;
    @FXML
    private Label humidityLabel;
    @FXML
    private Label windSpeedLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private ImageView weatherIcon;

    private final WeatherService weatherService = new WeatherService();

    @FXML
    public void fetchWeather() {
        String location = locationField.getText().trim();
        if (location.isEmpty()) {
            descriptionLabel.setText("Please enter a location");
            return;
        }

        try {
            WeatherData data = weatherService.getWeather(location);
            updateUI(data);
        } catch (IOException e) {
            descriptionLabel.setText("Error fetching weather data.");
        }
    }

    private void updateUI(WeatherData data) {
        temperatureLabel.setText("Temperature: " + data.getTemperature() + " °C");
        humidityLabel.setText("Humidity: " + data.getHumidity() + "%");
        windSpeedLabel.setText("Wind Speed: " + data.getWindSpeed() + " m/s");
        descriptionLabel.setText("Conditions: " + data.getDescription());
        setWeatherIcon(data.getDescription());
    }

    private void setWeatherIcon(String description) {
        String iconName = description.contains("clear") ? "sun.png" : "cloud.png";
        weatherIcon.setImage(new Image(getClass().getResourceAsStream("/icons/" + iconName)));
    }
}
