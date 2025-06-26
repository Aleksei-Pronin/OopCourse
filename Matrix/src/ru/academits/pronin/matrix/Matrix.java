package ru.academits.pronin.matrix;

import ru.academits.pronin.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] vectors;

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }

        vectors = new Vector[n];

        for (int i = 0; i < n; i++) {
            vectors[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        vectors = new Vector[matrix.vectors.length];

        for (int i = 0; i < matrix.vectors.length; i++) {
            vectors[i] = new Vector(matrix.vectors[i]);
        }
    }

    public Matrix(double[][] matrix) {
        int maxLength = 0;

        for (double[] row : matrix) {
            maxLength = Math.max(maxLength, row.length);
        }

        this.vectors = new Vector[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            this.vectors[i] = new Vector(maxLength, matrix[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        int maxLength = 0;

        for (Vector vector : vectors) {
            maxLength = Math.max(maxLength, vector.getSize());
        }

        this.vectors = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            double[] components = new double[maxLength];

            for (int j = 0; j < vectors[i].getSize(); j++) {
                components[j] = vectors[i].getCoordinate(j);
            }

            this.vectors[i] = new Vector(components);
        }
    }

    public int[] getSize() {
        return new int[]{vectors.length, vectors[0].getSize()};
    }

    public Vector getRowVector(int index) {
        return vectors[index];
    }

    public void setRowVector(Vector vector, int index) {
        vectors[index] = vector;
    }

    public Vector getColumnVector(int index) {
        double[] column = new double[vectors.length];

        for (int i = 0; i < column.length; i++) {
            column[i] = vectors[i].getCoordinate(index);
        }

        return new Vector(column);
    }

    @Override
    public String toString() {
        int lastCoordinateIndex = this.vectors.length - 1;

        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < lastCoordinateIndex; i++) {
            stringBuilder.append(vectors[i]).append(", ");
        }

        stringBuilder.append(vectors[lastCoordinateIndex]).append("}");

        return stringBuilder.toString();
    }
}