package ru.academits.pronin.temperature;

import ru.academits.pronin.temperature.model.TemperatureModelImpl;
import ru.academits.pronin.temperature.presenter.Presenter;
import ru.academits.pronin.temperature.presenter.PresenterImpl;
import ru.academits.pronin.temperature.scale.Celsius;
import ru.academits.pronin.temperature.scale.Fahrenheit;
import ru.academits.pronin.temperature.scale.Kelvin;
import ru.academits.pronin.temperature.scale.Scale;
import ru.academits.pronin.temperature.view.DesktopView;

public class Main {
    public static void main(String[] args) {
        final Scale[] scales = new Scale[]{
                new Celsius(),
                new Fahrenheit(),
                new Kelvin()
        };

        Presenter presenter = new PresenterImpl(new TemperatureModelImpl(), new DesktopView(scales));
        presenter.start();
    }
}