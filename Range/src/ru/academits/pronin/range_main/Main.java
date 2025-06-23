package ru.academits.pronin.range_main;

import ru.academits.pronin.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(1.1, 10.9);

        double number = 5.0;

        System.out.println(range.isInside(number));
    }
}
