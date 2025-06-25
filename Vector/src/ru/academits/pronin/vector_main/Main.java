package ru.academits.pronin.vector_main;

import ru.academits.pronin.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{3.0, 4.0});

        System.out.println(vector1.getVectorLength());


        Vector vector2 = new Vector(new double[]{2.3, 3.2, 5.0});
        Vector vector3 = new Vector(new double[]{3.0, 4.0});

        System.out.println(vector1.equals(vector3));

        vector1.setCoordinate(5, 1);

        System.out.println(vector1.getCoordinate(1));

        System.out.println(vector1);
        System.out.println(vector2);

        System.out.println(vector2);

        vector1.addVector(vector2);
        System.out.println(vector1);

        Vector vector4 = new Vector(new double[]{2.3, 3.2, 5.0});
        Vector vector5 = new Vector(new double[]{3.0, 4.0});

        vector4.subtractVector(vector5);
        System.out.println(vector4);

        vector1.scalarMultiplication(3);

        // System.out.println(vector1);


    }
}
