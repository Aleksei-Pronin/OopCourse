package ru.academits.pronin.vector_main;

import ru.academits.pronin.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{3.0, 4.0});
        Vector vector2 = new Vector(new double[]{2.3, 3.2, 5.0});
        Vector vector3 = new Vector(new double[]{3.0, 4.0, 7.0});
        Vector vector4 = new Vector(new double[]{2.3, 3.2, 5.0});
        Vector vector5 = new Vector(5, new double[]{3.0, 4.0});
        Vector vector6 = new Vector(vector5);
        Vector vector7 = new Vector(1);
//        Vector vector8 = new Vector(0);
//        Vector vector9 = new Vector(new double[]{});

        System.out.println(vector1);
        System.out.println(vector2);
        System.out.println(vector3);
        System.out.println(vector4);
        System.out.println(vector5);
        System.out.println(vector6);
        System.out.println(vector7);
        System.out.println();

        System.out.println("Длина вектора " + vector2 + " = " + vector2.getLength() + System.lineSeparator());

        System.out.println(vector1.equals(vector3)
                ? "Векторы " + vector1 + " и " + vector3 + " равны" + System.lineSeparator()
                : "Векторы " + vector1 + " и " + vector3 + " не равны" + System.lineSeparator());

        System.out.println("Изменим вторую координату вектора " + vector4);
        vector4.setCoordinate(1, 5);
        System.out.println("Результат " + vector4 + System.lineSeparator());

        System.out.println("Прибавим вектор " + vector2 + " к вектору " + vector1);
        vector1.add(vector2);
        System.out.println("Результат " + vector1 + System.lineSeparator());

        System.out.println("Вычтем вектор " + vector5 + " из вектора " + vector4);
        vector4.subtract(vector5);
        System.out.println("Результат " + vector4 + System.lineSeparator());

        System.out.println("Умножим вектор " + vector6 + " на 3 и развернем");
        vector6.multiplyByScalar(3);
        vector6.reverse();
        System.out.println("Результат " + vector6 + System.lineSeparator());

        System.out.println("Сумма векторов " + vector2 + " и " + vector3 + " = " + Vector.getSum(vector2, vector3));
        System.out.println("Разность векторов " + vector2 + " и " + vector3 + " = " + Vector.getDifference(vector2, vector3));
        System.out.println("Скалярное произведение векторов " + vector2 + " и " + vector3 + " = " + Vector.getScalarProduct(vector2, vector3));
    }
}