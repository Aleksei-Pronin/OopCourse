package ru.academits.pronin.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }

        coordinates = new double[n];
    }

    public Vector(Vector vector) {
        this.coordinates = Arrays.copyOf(vector.coordinates, vector.getSize());
    }

    public Vector(double[] coordinates) {
        this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
    }

    public Vector(int n, double[] coordinates) {
        if (n <= 0) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }

        this.coordinates = Arrays.copyOf(coordinates, n);
    }

    public int getSize() {
        return coordinates.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(coordinates);
    }
}