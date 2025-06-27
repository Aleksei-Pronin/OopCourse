package ru.academits.pronin.matrix;

import ru.academits.pronin.vector.Vector;

public class Matrix {
    private final Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Размеры матрицы должны быть больше 0, rowsCount = " + rowsCount + ", columnsCount = " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        int rowsCount = matrix.rows.length;

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Матрица не должна быть пустой или нулевой");
        }

        int columnsCount = 0;

        for (double[] row : matrix) {
            if (row == null) {
                throw new IllegalArgumentException("Строки в матрице не должны быть пустыми");
            }

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
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        int rowsCount = rows.length;

        if (index < 0 || index >= rowsCount) {
            throw new IllegalArgumentException("Индекс должен входить в диапазон: [0, " + (rowsCount - 1) + "], index = " + index);
        }

        return new Vector(rows[index]);
    }

    public void setRow(Vector vector, int index) {
        int rowsCount = rows.length;

        if (index < 0 || index >= rowsCount) {
            throw new IllegalArgumentException("Индекс должен входить в диапазон: [0, " + (rowsCount - 1) + "], index = " + index);
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        int columnsCount = rows[0].getSize();

        if (index < 0 || index >= columnsCount) {
            throw new IllegalArgumentException("Индекс должен входить в диапазон: [0, " + (columnsCount - 1) + "], index = " + index);
        }

        int rowsCount = rows.length;

        double[] column = new double[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            column[i] = rows[i].getCoordinate(index);
        }

        return new Vector(column);
    }

    @Override
    public String toString() {
        int lastCoordinateIndex = rows.length - 1;

        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < lastCoordinateIndex; i++) {
            stringBuilder.append(rows[i]).append(", ");
        }

        stringBuilder.append(rows[lastCoordinateIndex]).append('}');

        return stringBuilder.toString();
    }


    public double getDeterminant() {
        if (rows.length != rows[0].getSize()) {
            throw new IllegalArgumentException("Матрица должна быть квадратной, текущие размеры: " + rows.length + " на " + rows[0].getSize());
        }

        int matrixSize = rows.length;

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
        int matrixSize = rows.length;

        double[][] minor = new double[matrixSize - 1][matrixSize - 1];

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (i == row || j == column) {
                    continue;
                }

                if (i < row && j < column) {
                    minor[i][j] = rows[i].getCoordinate(j);
                } else if (i < row) {
                    minor[i][j - 1] = rows[i].getCoordinate(j);
                } else if (j < column) {
                    minor[i - 1][j] = rows[i].getCoordinate(j);
                } else {
                    minor[i - 1][j - 1] = rows[i].getCoordinate(j);
                }
            }
        }

        return new Matrix(minor);
    }

    public Matrix transpose() {
        int newColumnsCount = rows.length;
        int newRowsCount = rows[0].getSize();

        Matrix transposedMatrix = new Matrix(newRowsCount, newColumnsCount);

        for (int i = 0; i < newRowsCount; i++) {
            transposedMatrix.rows[i] = new Vector(getColumn(i));
        }

        return transposedMatrix;
    }

    public void multiplyByScalar(double scalar) {
        int rowsCount = rows.length;

        for (int i = 0; i < rowsCount; i++) {
            Vector row = new Vector(rows[i]);
            row.multiplyByScalar(scalar);
            setRow(row, i);
        }
    }

    public Vector multiplyByVector(Vector vector) {
        if (rows.length != vector.getSize()) {
            throw new IllegalArgumentException("Длина вектора должна быть равна " + rows.length + ", текущая длина - " + vector.getSize());
        }

        int resultVectorSize = rows[0].getSize();
        double[] result = new double[resultVectorSize];

        for (int i = 0; i < resultVectorSize; i++) {
            result[i] = Vector.getScalarProduct(getColumn(i), vector);
        }

        return new Vector(result);
    }

    public void add(Matrix matrix) {
        if (rows.length != matrix.rows.length || rows[0].getSize() != matrix.rows[0].getSize()) {
            throw new IllegalArgumentException("Размеры матриц при сложении должны совпадать.");
        }

        int rowsCount = getRowsCount();

        for (int i = 0; i < rowsCount; i++) {
            rows[i].add(new Vector(matrix.rows[i]));
        }
    }

    public void subtract(Matrix matrix) {
        if (rows.length != matrix.rows.length || rows[0].getSize() != matrix.rows[0].getSize()) {
            throw new IllegalArgumentException("Размеры матриц при вычитании должны совпадать.");
        }

        int rowsCount = getRowsCount();

        for (int i = 0; i < rowsCount; i++) {
            rows[i].subtract(new Vector(matrix.rows[i]));
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        Matrix result = new Matrix(matrix1);

        result.add(matrix2);

        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix result = new Matrix(matrix1);

        result.subtract(matrix2);

        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows[0].getSize() != matrix2.rows.length) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно совпадать с количеством строк второй матрицы.");
        }

        int matrix1RowsCount = matrix1.rows.length;
        int matrix2ColumnsCount = matrix2.rows[0].getSize();

        Matrix result = new Matrix(matrix1RowsCount, matrix2ColumnsCount);
        Matrix transposedMatrix2 = matrix2.transpose();

        for (int i = 0; i < matrix1RowsCount; i++) {
            for (int j = 0; j < matrix2ColumnsCount; j++) {
                result.rows[i].setCoordinate(j, Vector.getScalarProduct(matrix1.rows[i], transposedMatrix2.rows[j]));
            }
        }

        return result;
    }
}