package ru.academits.pronin.temperature.scale;

public class KelvinScale implements Scale {
    private static final double ABSOLUTE_ZERO = 0;
    private static final String NAME = "Kelvin";
    private static final String SYMBOL = "K";

    @Override
    public double toCelsius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double fromCelsius(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
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