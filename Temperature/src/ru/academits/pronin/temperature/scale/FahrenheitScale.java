package ru.academits.pronin.temperature.scale;

public class FahrenheitScale implements Scale {
    private static final double ABSOLUTE_ZERO = -469.67;
    private static final String NAME = "Fahrenheit";
    private static final String SYMBOL = "°F";

    @Override
    public double toCelsius(double temperature) {
        return (temperature - 32) / 1.8;
    }

    @Override
    public double fromCelsius(double celsiusTemperature) {
        return celsiusTemperature * 1.8 + 32;
    }

    @Override
    public double getAbsoluteZero() {
        return ABSOLUTE_ZERO;
    }

    @Override
    public String getSymbol() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", NAME, SYMBOL);
    }
}