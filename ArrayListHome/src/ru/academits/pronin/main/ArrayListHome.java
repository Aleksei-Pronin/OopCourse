package ru.academits.pronin.main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("text.txt"))) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла.");
        }

        System.out.println(list);

        ArrayList<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integerList.add(i);
            integerList.add(i);
        }

        System.out.println(integerList);

        for (int i = integerList.size() - 1; i >= 0; i--) {
            if (integerList.get(i) % 2 == 0) {
                integerList.remove(i);
            }
        }

        System.out.println(integerList);

        for (int i = 0; i < 10; i++) {
            integerList.add(i);
        }

        ArrayList<Integer> integerList2 = new ArrayList<>();

        for (Integer element : integerList) {
            if (!integerList2.contains(element)) {
                integerList2.add(element);
            }
        }

        System.out.println(integerList);
        System.out.println(integerList2);
    }
}