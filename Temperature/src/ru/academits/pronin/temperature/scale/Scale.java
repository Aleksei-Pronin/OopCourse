package ru.academits.pronin.temperature.scale;

public interface Scale {
    double toCelsius(double temperature);

    double fromCelsius(double celsiusTemperature);

    double getAbsoluteZero();

    String getSymbol();
}