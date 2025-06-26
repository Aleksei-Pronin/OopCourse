package ru.academits.pronin.matrix;

import ru.academits.pronin.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }

        rows = new Vector[n];

        for (int i = 0; i < n; i++) {
            rows[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] matrix) {
        int columnsCount = 0;

        for (double[] row : matrix) {
            columnsCount = Math.max(columnsCount, row.length);
        }

        int rowsCount = matrix.length;
        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount, matrix[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        int columnsCount = 0;

        for (Vector vector : vectors) {
            columnsCount = Math.max(columnsCount, vector.getSize());
        }

        int rowsCount = vectors.length;
        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
            rows[i].addVector(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows[0].getSize();
    }

    public int getColumnsCount() {
        return rows.length;
    }

    public Vector getRow(int index) {
        return new Vector(rows[index]);
    }

    public void setRow(Vector vector, int index) {
        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        double[] column = new double[rows.length];

        for (int i = 0; i < column.length; i++) {
            column[i] = rows[i].getCoordinate(index);
        }

        return new Vector(column);
    }

    @Override
    public String toString() {
        int lastCoordinateIndex = this.rows.length - 1;

        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < lastCoordinateIndex; i++) {
            stringBuilder.append(rows[i]).append(", ");
        }

        stringBuilder.append(rows[lastCoordinateIndex]).append("}");

        return stringBuilder.toString();
    }


    public double getDeterminant() {
        if (getColumnsCount() != getRowsCount()) {
            return 0.0;
        }

        int matrixSize = getColumnsCount();

        if (matrixSize == 1) {
            return rows[0].getCoordinate(0);
        }

        double determinant = 0;

        for (int i = 0; i < matrixSize; i++) {
            determinant += Math.pow(-1, i) * rows[0].getCoordinate(i) * getMinor(0, i).getDeterminant();
        }

        return determinant;
    }

    public Matrix getMinor(int row, int column) {
        if (getColumnsCount() != getRowsCount()) {
            return null;
        }

        int matrixSize = getColumnsCount();

        double[][] minor = new double[matrixSize - 1][matrixSize - 1];

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (i == row || j == column) {
                    continue;
                }

                if (i < row && j < column) {
                    minor[i][j] = this.rows[i].getCoordinate(j);
                } else if (i < row) {
                    minor[i][j - 1] = this.rows[i].getCoordinate(j);
                } else if (j < column) {
                    minor[i - 1][j] = this.rows[i].getCoordinate(j);
                } else {
                    minor[i - 1][j - 1] = this.rows[i].getCoordinate(j);
                }
            }
        }

        return new Matrix(minor);
    }
}