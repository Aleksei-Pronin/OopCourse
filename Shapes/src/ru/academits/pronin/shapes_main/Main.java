package ru.academits.pronin.shapes_main;

import ru.academits.pronin.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[8];

        shapes[0] = new Square(5);
        shapes[1] = new Square(7);
        shapes[2] = new Triangle(1, 2, 3, 2, 1, 5);
        shapes[3] = new Triangle(0, 5, 0, 2, 3, 2);
        shapes[4] = new Circle(4);
        shapes[5] = new Circle(7);
        shapes[6] = new Rectangle(4, 5);
        shapes[7] = new Rectangle(7, 1);

        System.out.println("Фигура с максимальной площадью - " + getMaxAreaShape(shapes));
        System.out.println("Фигура со вторым по величине периметром - " + getSecondPerimeterShape(shapes));
    }

    public static Shape getMaxAreaShape(Shape[] shapes) {
        Arrays.sort(shapes, new AreaComparator());
        return shapes[shapes.length - 1];
    }

    public static Shape getSecondPerimeterShape(Shape[] shapes) {
        Arrays.sort(shapes, new PerimeterComparator());
        return shapes[shapes.length - 2];
    }
}