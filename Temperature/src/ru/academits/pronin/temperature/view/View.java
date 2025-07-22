package ru.academits.pronin.temperature.view;

import java.awt.event.ActionListener;

public interface View {
    void start();

    void addConvertListener(ActionListener actionListener);

    String getInputScale();

    String getOutputScale();

    double getInputTemperature();

    void showOutputTemperature(double outputTemperature);
}