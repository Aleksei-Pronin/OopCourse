package ru.academits.pronin.temperature.model;

public class TemperatureModelImpl implements TemperatureModel {
    private double celsiusTemperature;
    private double kelvinTemperature;
    private double fahrenheitTemperature;

    @Override
    public void convertTemperature(double celsiusTemperature) {
        this.celsiusTemperature = celsiusTemperature;
        kelvinTemperature = getKelvin(celsiusTemperature);
        fahrenheitTemperature = getFahrenheit(celsiusTemperature);
    }

    private static double getKelvin(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }

    private static double getFahrenheit(double celsiusTemperature) {
        return celsiusTemperature * 1.8 + 32;
    }

    @Override
    public double getCelsiusTemperature() {
        return celsiusTemperature;
    }

    @Override
    public double getKelvinTemperature() {
        return kelvinTemperature;
    }

    @Override
    public double getFahrenheitTemperature() {
        return fahrenheitTemperature;
    }
}
