package ru.academits.pronin.temperature.presenter;

import ru.academits.pronin.temperature.model.Scales;
import ru.academits.pronin.temperature.model.TemperatureModel;
import ru.academits.pronin.temperature.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PresenterImpl implements Presenter, ActionListener {
    private final TemperatureModel model;
    private final View view;

    public PresenterImpl(TemperatureModel model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void start() {
        view.addConvertListener(this);
        view.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Scales inputScale = view.getInputScale();
        Scales outputScale = view.getOutputScale();

        double inputTemperature = view.getInputTemperature();
        double outputTemperature = model.convertTemperature(inputTemperature, inputScale, outputScale);

        view.showOutputTemperature(outputTemperature);
    }
}