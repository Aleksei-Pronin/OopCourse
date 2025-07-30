package ru.academits.pronin.temperature.model;

public class TemperatureModelImpl implements TemperatureModel {
    @Override
    public double convertTemperature(double inputTemperature, Scales inputScale, Scales outputScale) {
        if (inputTemperature < inputScale.getAbsoluteZero()) {
            throw new IllegalArgumentException("The temperature cannot be below absolute zero");
        }

        return outputScale.fromCelsius(inputScale.toCelsius(inputTemperature));
    }
}