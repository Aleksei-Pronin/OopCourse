package ru.academits.pronin.temperature.view;

import java.awt.event.ActionListener;

public interface View {
    void start();

    void addConvertListener(ActionListener actionListener);

    double getCelsiusTemperature();

    void showResults(double kelvinTemperature, double fahrenheitTemperature);
}