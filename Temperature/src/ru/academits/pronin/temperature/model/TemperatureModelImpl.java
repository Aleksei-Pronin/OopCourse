package ru.academits.pronin.temperature.model;

import ru.academits.pronin.temperature.scale.Scale;

public class TemperatureModelImpl implements TemperatureModel {
    @Override
    public double convertTemperature(double inputTemperature, Scale inputScale, Scale outputScale) {
        if (inputTemperature < inputScale.getAbsoluteZero()) {
            throw new IllegalArgumentException(
                    String.format("The temperature cannot be below absolute zero. Current value: %.2f %s. ",
                            inputTemperature,
                            inputScale.getSymbol())
            );
        }

        return outputScale.fromCelsius(inputScale.toCelsius(inputTemperature));
    }
}