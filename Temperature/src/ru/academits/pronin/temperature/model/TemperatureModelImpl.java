package ru.academits.pronin.temperature.model;

import ru.academits.pronin.temperature.scale.Scale;
import ru.academits.pronin.temperature.view.View;

public class TemperatureModelImpl implements TemperatureModel {
    @Override
    public double convertTemperature(View view) {
        Scale inputScale = view.getInputScale();
        Scale outputScale = view.getOutputScale();

        double inputTemperature = view.getInputTemperature();

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