package ru.academits.pronin.shapes_main;

import ru.academits.pronin.shapes_comparators.ShapeAreaComparator;
import ru.academits.pronin.shapes_comparators.ShapePerimeterComparator;
import ru.academits.pronin.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(5),
                new Square(7),
                new Triangle(1, 2, 3, 2, 1, 5),
                new Triangle(0, 5, 0, 2, 3, 2),
                new Circle(4),
                new Circle(7),
                new Rectangle(4, 5),
                new Rectangle(7, 1)
        };

        System.out.println("Фигура с максимальной площадью - " + getMaxAreaShape(shapes));
        System.out.println("Фигура со вторым по величине периметром - " + getSecondPerimeterShape(shapes));
    }

    public static Shape getMaxAreaShape(Shape[] shapes) {
        Arrays.sort(shapes, new ShapeAreaComparator());
        return shapes[shapes.length - 1];
    }

    public static Shape getSecondPerimeterShape(Shape[] shapes) {
        Arrays.sort(shapes, new ShapePerimeterComparator());
        return shapes[shapes.length - 2];
    }
}