package com.uopeople.weatherapp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class WeatherService {
    private static final String API_KEY = "27ef15b04a18a474388bd34d90f8874d";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    private final OkHttpClient client = new OkHttpClient();

    public WeatherData getWeather(String location) throws IOException {
        String url = BASE_URL + "?q=" + location + "&appid=" + API_KEY + "&units=metric";
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                JsonObject json = JsonParser.parseString(response.body().string()).getAsJsonObject();
                return parseWeatherData(json);
            } else {
                throw new IOException("Failed to retrieve data");
            }
        }
    }

    private WeatherData parseWeatherData(JsonObject json) {
        double temp = json.getAsJsonObject("main").get("temp").getAsDouble();
        int humidity = json.getAsJsonObject("main").get("humidity").getAsInt();
        double windSpeed = json.getAsJsonObject("wind").get("speed").getAsDouble();
        String description = json.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();

        return new WeatherData(temp, humidity, windSpeed, description);
    }
}
