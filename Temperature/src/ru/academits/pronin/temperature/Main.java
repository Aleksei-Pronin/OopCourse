package ru.academits.pronin.temperature;

import ru.academits.pronin.temperature.model.TemperatureModel;
import ru.academits.pronin.temperature.model.TemperatureModelImpl;
import ru.academits.pronin.temperature.presenter.Presenter;
import ru.academits.pronin.temperature.presenter.PresenterImpl;
import ru.academits.pronin.temperature.view.DesktopView;
import ru.academits.pronin.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        TemperatureModel temperatureModel = new TemperatureModelImpl();
        View view = new DesktopView();
        Presenter presenter = new PresenterImpl(temperatureModel, view);
        presenter.start();
    }
}
