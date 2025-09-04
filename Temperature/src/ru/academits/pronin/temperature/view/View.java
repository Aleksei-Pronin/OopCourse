package ru.academits.pronin.temperature.view;

import ru.academits.pronin.temperature.scale.Scale;

import java.awt.event.ActionListener;

public interface View {
    void start();

    void addConvertListener(ActionListener actionListener);

    Scale getInputScale();

    Scale getOutputScale();

    double getInputTemperature();

    void showOutputTemperature(double outputTemperature);
}