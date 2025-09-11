package ru.academits.pronin.temperature.scale;

public class CelsiuScale implements Scale {
    private static final double ABSOLUTE_ZERO = -273.15;
    private static final String NAME = "Celsius";
    private static final String SYMBOL = "°C";

    @Override
    public double toCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double fromCelsius(double celsiusTemperature) {
        return celsiusTemperature;
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