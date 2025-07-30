package ru.academits.pronin.temperature.view;

import ru.academits.pronin.temperature.model.Scales;

import java.awt.event.ActionListener;

public interface View {
    void start();

    void addConvertListener(ActionListener actionListener);

    Scales getInputScale();

    Scales getOutputScale();

    double getInputTemperature();

    void showOutputTemperature(double outputTemperature);
}