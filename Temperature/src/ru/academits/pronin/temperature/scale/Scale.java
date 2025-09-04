package ru.academits.pronin.temperature.scale;

import java.util.function.DoubleUnaryOperator;

public abstract class Scale {
    private final double absoluteZero;
    private final String name;
    private final String symbol;
    private final DoubleUnaryOperator toCelsius;
    private final DoubleUnaryOperator fromCelsius;

    public Scale(double absoluteZero, String name, String symbol, DoubleUnaryOperator toCelsius, DoubleUnaryOperator fromCelsius) {
        this.absoluteZero = absoluteZero;
        this.name = name;
        this.symbol = symbol;
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

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, symbol);
    }
}