package ru.academits.pronin.temperature.model;

public interface TemperatureModel {
    void convertTemperature(double celsiusTemperature);

    double getCelsiusTemperature();

    double getKelvinTemperature();

    double getFahrenheitTemperature();
}