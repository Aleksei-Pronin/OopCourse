package ru.academits.pronin.temperature.scale;

public class Celsius extends Scale {
    public Celsius() {
        super(-273.15, "Celsius (°C)", t -> t, t -> t);
    }
}