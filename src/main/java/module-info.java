module com.uopeople.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires okhttp3;

    opens com.uopeople.weatherapp to javafx.fxml;
    exports com.uopeople.weatherapp;
}