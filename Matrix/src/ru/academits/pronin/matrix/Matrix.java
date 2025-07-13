package ru.academits.pronin.matrix;

import ru.academits.pronin.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

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
        if (matrix == null) {
            throw new NullPointerException("Матрица не должна быть null");
        }

        if (matrix.length == 0) {
            throw new IllegalArgumentException("В матрице должна быть минимум одна строка");
        }

        int columnsCount = 0;

        for (double[] row : matrix) {
            if (row == null) {
                throw new NullPointerException("Строки в матрице не должны быть null");
            }

            columnsCount = Math.max(columnsCount, row.length);
        }

        if (columnsCount == 0) {
            throw new NullPointerException("В матрице должен быть минимум один столбец");
        }

        int rowsCount = matrix.length;
        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount, matrix[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Размер матрицы должен быть больше 0, size = " + vectors.length);
        }

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

    private String getDimensions() {
        return rows.length + " на " + getColumnsCount();
    }

    public Vector getRow(int index) {
        int rowsCount = rows.length;

        if (index < 0 || index >= rowsCount) {
            throw new IndexOutOfBoundsException("Индекс должен входить в диапазон: [0, " + (rowsCount - 1) + "], index = " + index);
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        int columnsCount = getColumnsCount();

        if (vector.getSize() != columnsCount) {
            throw new IllegalArgumentException("Размерность вектора должна быть равна " + columnsCount + ", текущая размерность - " + vector.getSize());
        }

        int rowsCount = rows.length;

        if (index < 0 || index >= rowsCount) {
            throw new IndexOutOfBoundsException("Индекс должен входить в диапазон: [0, " + (rowsCount - 1) + "], index = " + index);
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        int columnsCount = getColumnsCount();

        if (index < 0 || index >= columnsCount) {
            throw new IndexOutOfBoundsException("Индекс должен входить в диапазон: [0, " + (columnsCount - 1) + "], index = " + index);
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
        int lastRowIndex = rows.length - 1;

        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < lastRowIndex; i++) {
            stringBuilder.append(rows[i]).append(", ");
        }

        stringBuilder.append(rows[lastRowIndex]).append('}');

        return stringBuilder.toString();
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new IllegalStateException("Матрица должна быть квадратной, текущие размеры: " + getDimensions());
        }

        int matrixSize = rows.length;

        if (matrixSize == 1) {
            return rows[0].getCoordinate(0);
        }

        double determinant = 0;

        for (int i = 0; i < matrixSize; i++) {
            determinant += Math.pow(-1, i) * rows[0].getCoordinate(i) * getSubMatrix(i).getDeterminant();
        }

        return determinant;
    }

    private Matrix getSubMatrix(int columnIndex) {
        int matrixSize = rows.length;
        double[][] subMatrix = new double[matrixSize - 1][matrixSize - 1];

        for (int i = 1; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (j == columnIndex) {
                    continue;
                }

                if (j < columnIndex) {
                    subMatrix[i - 1][j] = rows[i].getCoordinate(j);
                } else {
                    subMatrix[i - 1][j - 1] = rows[i].getCoordinate(j);
                }
            }
        }

        return new Matrix(subMatrix);
    }

    public void transpose() {
        int newRowsCount = getColumnsCount();
        Vector[] newRows = new Vector[newRowsCount];

        for (int i = 0; i < newRowsCount; i++) {
            newRows[i] = getColumn(i);
        }

        rows = newRows;
    }

    public void multiplyByScalar(double scalar) {
        if (scalar == 1.0) {
            return;
        }

        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public Vector multiplyByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Размерность вектора должна быть равна " + getColumnsCount() + ", текущая размерность - " + vector.getSize());
        }

        int resultVectorSize = rows.length;
        double[] result = new double[resultVectorSize];

        for (int i = 0; i < resultVectorSize; i++) {
            result[i] = Vector.getScalarProduct(rows[i], vector);
        }

        return new Vector(result);
    }

    public void add(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Размеры матриц при сложении должны совпадать, текущие размеры:" + getDimensions() + " и " + matrix.getDimensions());
        }

        int rowsCount = getRowsCount();

        for (int i = 0; i < rowsCount; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Размеры матриц при вычитании должны совпадать, текущие размеры:" + getDimensions() + " и " + matrix.getDimensions());
        }

        int rowsCount = getRowsCount();

        for (int i = 0; i < rowsCount; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Размеры матриц при сложении должны совпадать, текущие размеры:" + matrix1.getDimensions() + " и " + matrix2.getDimensions());
        }

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Размеры матриц при вычитании должны совпадать, текущие размеры:" + matrix1.getDimensions() + " и " + matrix2.getDimensions());
        }

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно совпадать с количеством строк второй матрицы.");
        }

        int matrix1RowsCount = matrix1.rows.length;
        int matrix2ColumnsCount = matrix2.getColumnsCount();
        Matrix result = new Matrix(matrix1RowsCount, matrix2ColumnsCount);

        for (int i = 0; i < matrix1RowsCount; i++) {
            for (int j = 0; j < matrix2ColumnsCount; j++) {
                result.rows[i].setCoordinate(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) o;

        if (getColumnsCount() != matrix.getColumnsCount()) {
            return false;
        }

        return Arrays.equals(rows, matrix.rows);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(rows);
        return hash;
    }
}