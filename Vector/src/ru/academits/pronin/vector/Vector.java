package ru.academits.pronin.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0, size = " + size);
        }

        coordinates = new double[size];
    }

    public Vector(Vector vector) {
        coordinates = Arrays.copyOf(vector.coordinates, vector.coordinates.length);
    }

    public Vector(double[] coordinates) {
        if (coordinates.length == 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0, size = " + coordinates.length);
        }

        this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
    }

    public Vector(int size, double[] coordinates) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0, size = " + size);
        }

        this.coordinates = Arrays.copyOf(coordinates, size);
    }

    public int getSize() {
        return coordinates.length;
    }

    @Override
    public String toString() {
        int lastCoordinateIndex = coordinates.length - 1;

        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < lastCoordinateIndex; i++) {
            stringBuilder.append(coordinates[i]).append(", ");
        }

        stringBuilder.append(coordinates[lastCoordinateIndex]).append('}');

        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        int thisSize = coordinates.length;
        int vectorSize = vector.coordinates.length;

        if (thisSize < vectorSize) {
            coordinates = Arrays.copyOf(coordinates, vectorSize);
        }

        for (int i = 0; i < vectorSize; i++) {
            coordinates[i] += vector.coordinates[i];
        }
    }

    public void subtract(Vector vector) {
        int thisSize = coordinates.length;
        int vectorSize = vector.coordinates.length;

        if (thisSize < vectorSize) {
            coordinates = Arrays.copyOf(coordinates, vectorSize);
        }

        for (int i = 0; i < vectorSize; i++) {
            coordinates[i] -= vector.coordinates[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        int size = coordinates.length;

        for (int i = 0; i < size; i++) {
            coordinates[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1.0);
    }

    public double getLength() {
        double squaredLength = 0;

        for (double coordinate : coordinates) {
            squaredLength += coordinate * coordinate;
        }

        return Math.sqrt(squaredLength);
    }

    public double getCoordinate(int index) {
        return coordinates[index];
    }

    public void setCoordinate(int index, double coordinate) {
        coordinates[index] = coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return Arrays.equals(coordinates, vector.coordinates);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(coordinates);
        return hash;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        result.add(vector2);

        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        result.subtract(vector2);

        return result;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double result = 0;

        int minSize = Math.min(vector1.coordinates.length, vector2.coordinates.length);

        for (int i = 0; i < minSize; i++) {
            result += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return result;
    }
}