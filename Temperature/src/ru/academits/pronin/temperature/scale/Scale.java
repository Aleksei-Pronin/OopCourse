package ru.academits.pronin.temperature.scale;

import java.util.function.DoubleUnaryOperator;

public abstract class Scale {
    private final double absoluteZero;
    private final String name;
    private final DoubleUnaryOperator toCelsius;
    private final DoubleUnaryOperator fromCelsius;

    public Scale(double absoluteZero, String name, DoubleUnaryOperator toCelsius, DoubleUnaryOperator fromCelsius) {
        this.absoluteZero = absoluteZero;
        this.name = name;
        this.toCelsius = toCelsius;
        this.fromCelsius = fromCelsius;
    }

    public double toCelsius(double temperature) {
        return toCelsius.applyAsDouble(temperature);
    }

    public double fromCelsius(double celsiusTemperature) {
        return fromCelsius.applyAsDouble(celsiusTemperature);
    }

    public double getAbsoluteZero() {
        return absoluteZero;
    }

    @Override
    public String toString() {
        return name;
    }
}