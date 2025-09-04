package ru.academits.pronin.temperature.scale;

public class Kelvin extends Scale {
    public Kelvin() {
        super(0, "Kelvin (K)", t -> t - 273.15, t -> t + 273.15);
    }
}