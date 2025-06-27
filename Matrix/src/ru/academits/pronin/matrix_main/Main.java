package ru.academits.pronin.matrix_main;

import ru.academits.pronin.matrix.Matrix;
import ru.academits.pronin.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{3.0, 4.0});
        Vector vector2 = new Vector(new double[]{2.3, 3.2, 5.0});
        Vector vector3 = new Vector(new double[]{3.0, 4.0, 7.0});
        Vector vector4 = new Vector(new double[]{2.3, 3.2, 5.0, 6.6, 7.6, 6.6});

        Vector[] vectors = new Vector[]{vector1, vector2, vector3, vector4};

        Matrix matrix1 = new Matrix(3, 4);
        Matrix matrix2 = new Matrix(vectors);
        Matrix matrix3 = new Matrix(new double[][]{{1.0, 2.0, 3.0}, {2.0}, {1.0, 2.0}});
        Matrix matrix4 = new Matrix(matrix3);

        System.out.println(matrix1);
        System.out.println(matrix2);
        System.out.println(matrix3);
        System.out.println(matrix4);

        System.out.println(matrix1.getRowsCount());
        System.out.println(matrix1.getColumnsCount());

        System.out.println(matrix3.getRow(1));
        System.out.println(matrix3.getColumn(0));

        matrix4.setRow(new Vector(new double[]{1.0, 2.0, 3.0}), 1);
        System.out.println(matrix4);

        System.out.println(matrix3.getDeterminant());

        System.out.println(matrix2);
        System.out.println(matrix2.getRowsCount() + "" + matrix2.getColumnsCount());

        System.out.println(matrix2.transpose());

        System.out.println(matrix3);

        matrix3.multiplyByScalar(6);
        System.out.println(matrix3);

        System.out.println(matrix3.multiplyByVector(new Vector(new double[]{2, 3, 4})));

        Matrix matrix5 = new Matrix(new double[][]{{1.0, 2.0, 3.0, 5.0}, {2.0}, {1.0, 2.0}});
        Matrix matrix6 = new Matrix(matrix5);

        matrix5.add(matrix6);

        System.out.println(matrix5);

        matrix5.subtract(matrix6);

        System.out.println(matrix5);

        System.out.println(Matrix.getSum(matrix5, matrix6));
        System.out.println(Matrix.getDifference(matrix5, matrix6));

        Matrix matrix7 = new Matrix(new double[][]{{1.0, 2.0, 2.0, 2}, {3.0, 1.0, 1, 1}});
        Matrix matrix8 = new Matrix(new double[][]{{4.0, 2.0}, {3.0, 1.0}, {1.0, 5}});

        System.out.println(Matrix.getProduct(matrix7, matrix8));

        Vector vector = matrix7.getRow(1);

        System.out.println();
    }
}