package ru.academits.pronin.temperature.scale;

public class Fahrenheit extends Scale {
    public Fahrenheit() {
        super(-469.67, "Fahrenheit (°F)", t -> (t - 32) / 1.8, t -> t * 1.8 + 32);
    }
}