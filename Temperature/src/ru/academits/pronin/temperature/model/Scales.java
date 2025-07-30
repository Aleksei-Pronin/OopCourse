package ru.academits.pronin.temperature.model;

import java.util.function.DoubleUnaryOperator;

public enum Scales {
    CELSIUS(-273.15, "Celsius (°C)", t -> t, t -> t),
    FAHRENHEIT(-469.67, "Fahrenheit (°F)", t -> (t - 32) / 1.8, t -> t * 1.8 + 32),
    KELVIN(0, "Kelvin (K)", t -> t - 273.15, t -> t + 273.15);

    private final double absoluteZero;
    private final String name;
    private final DoubleUnaryOperator toCelsius;
    private final DoubleUnaryOperator fromCelsius;

    Scales(double absoluteZero, String name, DoubleUnaryOperator toCelsius, DoubleUnaryOperator fromCelsius) {
        this.absoluteZero = absoluteZero;
        this.name = name;
        this.fromCelsius = fromCelsius;
        this.toCelsius = toCelsius;
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