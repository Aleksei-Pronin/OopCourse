package ru.academits.pronin.temperature.model;

public interface TemperatureModel {
    double convertTemperature(double inputTemperature, String inputScale, String outputScale);
}