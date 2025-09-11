package ru.academits.pronin.temperature;

import ru.academits.pronin.temperature.model.TemperatureModelImpl;
import ru.academits.pronin.temperature.presenter.Presenter;
import ru.academits.pronin.temperature.presenter.PresenterImpl;
import ru.academits.pronin.temperature.scale.CelsiuScale;
import ru.academits.pronin.temperature.scale.FahrenheitScale;
import ru.academits.pronin.temperature.scale.KelvinScale;
import ru.academits.pronin.temperature.scale.Scale;
import ru.academits.pronin.temperature.view.DesktopView;

public class Main {
    public static void main(String[] args) {
        Scale[] scales = {
                new CelsiuScale(),
                new FahrenheitScale(),
                new KelvinScale()
        };

        Presenter presenter = new PresenterImpl(new TemperatureModelImpl(), new DesktopView(scales));
        presenter.start();
    }
}