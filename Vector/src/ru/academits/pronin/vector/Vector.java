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
        int lastCoordinateIndex = this.getSize() - 1;

        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < lastCoordinateIndex; i++) {
            stringBuilder.append(coordinates[i]).append(", ");
        }

        stringBuilder.append(coordinates[lastCoordinateIndex]).append("}");

        return stringBuilder.toString();
    }

    public void addVector(Vector vector) {
        int thisSize = this.getSize();
        int vectorSize = vector.getSize();

        if (thisSize < vectorSize) {
            this.coordinates = Arrays.copyOf(coordinates, vectorSize);
        }

        for (int i = 0; i < vectorSize; i++) {
            this.coordinates[i] += vector.coordinates[i];
        }
    }

    public void subtractVector(Vector vector) {
        int thisSize = this.getSize();
        int vectorSize = vector.getSize();

        if (thisSize < vectorSize) {
            this.coordinates = Arrays.copyOf(coordinates, vectorSize);
        }

        for (int i = 0; i < vectorSize; i++) {
            this.coordinates[i] -= vector.coordinates[i];
        }
    }

    public void scalarMultiplication(double scalar) {
        int size = this.getSize();

        for (int i = 0; i < size; i++) {
            coordinates[i] *= scalar;
        }
    }

    public void reverseVector() {
        this.scalarMultiplication(-1.0);
    }

    public double getVectorLength() {
        double lengthSqr = 0;

        for (double coordinate : coordinates) {
            lengthSqr += Math.pow(coordinate, 2);
        }

        return Math.sqrt(lengthSqr);
    }

    public void setCoordinate(double coordinate, int index) {
        coordinates[index] = coordinate;
    }

    public double getCoordinate(int index) {
        return coordinates[index];
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

        return getSize() == vector.getSize() && Arrays.equals(coordinates, vector.coordinates);
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

        result.addVector(vector2);

        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        result.subtractVector(vector2);

        return result;
    }

    public static double getSalarProduct(Vector vector1, Vector vector2) {
        double result = 0;

        int minSize = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < minSize; i++) {
            result += vector1.getCoordinate(i) * vector1.getCoordinate(i);
        }

        return result;
    }
}