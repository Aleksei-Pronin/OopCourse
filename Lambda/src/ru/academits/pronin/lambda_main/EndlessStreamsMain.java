package ru.academits.pronin.lambda_main;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class EndlessStreamsMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество элементов:");
        int squareRootsLimit = scanner.nextInt();

        System.out.println("Квадратные корни чисел от 0 до " + (squareRootsLimit - 1) + ":");
        DoubleStream.iterate(0, x -> x + 1)
                .map(Math::sqrt)
                .limit(squareRootsLimit)
                .forEach(System.out::println);

        System.out.println("Введите количество чисел Фибоначчи:");
        int fibonacciNumbersLimit = scanner.nextInt();

        System.out.println("Первые " + fibonacciNumbersLimit + " чисел Фибоначчи:");
        Stream.iterate(new long[]{0, 1}, fibonacciNumber -> new long[]{fibonacciNumber[1], fibonacciNumber[0] + fibonacciNumber[1]})
                .mapToLong(fibonacciNumber -> fibonacciNumber[0])
                .limit(fibonacciNumbersLimit)
                .forEach(System.out::println);
    }
}