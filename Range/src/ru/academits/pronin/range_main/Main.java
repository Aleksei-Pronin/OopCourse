package ru.academits.pronin.range_main;

import ru.academits.pronin.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начало интервала 1:");
        double from1 = scanner.nextDouble();

        System.out.println("Введите конец интервала 1:");
        double to1 = scanner.nextDouble();

        Range range1 = new Range(from1, to1);

        System.out.println("Длина интервала " + range1 + " - " + range1.getLength());

        System.out.println("Введите число:");
        double number = scanner.nextDouble();

        System.out.println(range1.isInside(number) ? "Число " + number + " принадлежит интервалу " + range1 : "Число " + number + " не принадлежит интервалу " + range1);

        System.out.println("Введите начало интервала 2:");
        double from2 = scanner.nextDouble();

        System.out.println("Введите конец интервала 2:");
        double to2 = scanner.nextDouble();

        Range range2 = new Range(from2, to2);

        System.out.println("Пересечение интервалов " + range1 + " и " + range2 + " - " + range1.getIntersection(range2));
        System.out.println("Объединение интервалов " + range1 + " и " + range2 + " - " + Arrays.toString(range1.getUnion(range2)));
        System.out.println("Разность интервалов " + range1 + " и " + range2 + " - " + Arrays.toString(range1.getDifference(range2)));
    }
}