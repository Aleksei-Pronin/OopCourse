package ru.academits.pronin.temperature.model;

public class TemperatureModelImpl implements TemperatureModel {
    private static final double ABSOLUTE_ZERO_CELSIUS = -273.15;
    private static final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;
    private static final double ABSOLUTE_ZERO_KELVIN = 0;

    @Override
    public double convertTemperature(double inputTemperature, String inputScale, String outputScale) {
        validateTemperature(inputTemperature, inputScale);

        double celsius = switch (inputScale) {
            case "Kelvin (K)" -> kelvinToCelsius(inputTemperature);
            case "Fahrenheit (°F)" -> fahrenheitToCelsius(inputTemperature);
            default -> inputTemperature;
        };

        return switch (outputScale) {
            case "Kelvin (K)" -> celsiusToKelvin(celsius);
            case "Fahrenheit (°F)" -> celsiusToFahrenheit(celsius);
            default -> celsius;
        };
    }

    private void validateTemperature(double value, String scale) {
        boolean isBelowAbsoluteZero = switch (scale) {
            case "Kelvin (K)" -> value < ABSOLUTE_ZERO_KELVIN;
            case "Fahrenheit (°F)" -> value < ABSOLUTE_ZERO_FAHRENHEIT;
            case "Celsius (°C)" -> value < ABSOLUTE_ZERO_CELSIUS;
            default -> false;
        };

        if (isBelowAbsoluteZero) {
            throw new IllegalArgumentException();
        }
    }

    private static double celsiusToKelvin(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }

    private static double celsiusToFahrenheit(double celsiusTemperature) {
        return celsiusTemperature * 1.8 + 32;
    }

    private static double kelvinToCelsius(double kelvinTemperature) {
        return kelvinTemperature - 273.15;
    }

    private static double fahrenheitToCelsius(double fahrenheitTemperature) {
        return (fahrenheitTemperature - 32) / 1.8;
    }
}