package ru.academits.pronin.temperature.model;

import ru.academits.pronin.temperature.scale.Scale;

public interface TemperatureModel {
    double convertTemperature(double inputTemperature, Scale inputScale, Scale outputScale);
}