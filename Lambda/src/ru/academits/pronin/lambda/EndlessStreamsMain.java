package ru.academits.pronin.lambda;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

public class EndlessStreamsMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество элементов:");
        int squareRootsCount = scanner.nextInt();

        System.out.println("Квадратные корни чисел от 0 до " + (squareRootsCount - 1) + ":");
        DoubleStream.iterate(0, x -> x + 1)
                .map(Math::sqrt)
                .limit(squareRootsCount)
                .forEach(System.out::println);

        System.out.println("Введите количество чисел Фибоначчи:");
        int fibonacciNumbersCount = scanner.nextInt();

        System.out.println("Первые " + fibonacciNumbersCount + " чисел Фибоначчи:");
        LongStream.iterate(0, n -> n + 1)
                .map(n -> (long) ((Math.pow((1 + Math.sqrt(5)) / 2, n) - Math.pow((1 - Math.sqrt(5)) / 2, n)) / Math.sqrt(5)))
                .limit(fibonacciNumbersCount)
                .forEach(System.out::println);
    }
}