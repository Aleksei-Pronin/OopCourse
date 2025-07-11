package ru.academits.pronin.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayListHome {
    public static void main(String[] args) {
        ArrayList<String> linesList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("text.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                linesList.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден.");
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла.");
        }

        System.out.println(linesList);

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

        ArrayList<Integer> distinctIntegerList = new ArrayList<>(integerList.size());

        for (Integer element : integerList) {
            if (!distinctIntegerList.contains(element)) {
                distinctIntegerList.add(element);
            }
        }

        System.out.println(integerList);
        System.out.println(distinctIntegerList);
    }
}