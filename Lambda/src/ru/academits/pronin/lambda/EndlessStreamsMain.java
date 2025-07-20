package ru.academits.pronin.lambda;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

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
        Stream.iterate(new long[]{0, 1}, fibonacciNumbers -> {
                    long temp = fibonacciNumbers[1];
                    fibonacciNumbers[1] += fibonacciNumbers[0];
                    fibonacciNumbers[0] = temp;
                    return fibonacciNumbers;
                })
                .mapToLong(fibonacciNumbers -> fibonacciNumbers[0])
                .limit(fibonacciNumbersCount)
                .forEach(System.out::println);
    }
}