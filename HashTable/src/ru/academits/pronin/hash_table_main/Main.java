package ru.academits.pronin.hash_table_main;

import ru.academits.pronin.hash_table.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> table1 = new HashTable<>();
        HashTable<Integer> table2 = new HashTable<>(30);

        table1.add(45);
        table1.add(4);
        table1.add(56);
        table1.add(34);
        table1.add(23);
        table1.add(756);
        table1.add(45);
        table1.add(98);
        table1.add(54);

        System.out.println("Размер таблицы 1 " + table1 + " - " + table1.size());
        System.out.println("Размер таблицы 2 " + table2 + " - " + table2.size());
        System.out.println("Таблица 1 пустая - " + table1.isEmpty());
        System.out.println("Таблица 2 пустая - " + table2.isEmpty());


        System.out.println("Таблица 1 содержит 4 - " + table1.contains(4));
        System.out.println("Таблица 2 содержит 5 - " + table2.contains(5));

        Object[] objectsArray = table1.toArray();
        System.out.println("Таблица 1 в массив объектов - " + Arrays.toString(objectsArray));

        System.out.println("Таблица 1 в массив new Integer[10] - " + Arrays.toString(table1.toArray(new Integer[10])));
        System.out.println("Таблица 1 в массив new Integer[2] - " + Arrays.toString(table1.toArray(new Integer[2])));
        System.out.println("Таблица 1 в массив new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9} - " + Arrays.toString(table1.toArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})));

        System.out.println("Удаляем элемент со значением 4, результат - " + table1.remove(4) + ", текущая таблица - " + table1);
        System.out.println("Удаляем элемент со значением 11, результат - " + table1.remove(11) + ", текущая таблица - " + table1);

        table2.add(11);
        table2.add(12);
        table2.add(13);
        table2.add(14);

        System.out.println("Таблица 1 - " + table1);
        System.out.println("Таблица 2 - " + table2);
        System.out.println("Таблица 1 содержит все элементы таблицы 2 - " + table1.containsAll(table2));

        System.out.println("Добавили элементы таблицы 2 к таблице 1 - " + table1.addAll(table2) + " " + table1);
        System.out.println("Таблица 1 содержит все элементы таблицы 2 - " + table1.containsAll(table2));
        System.out.println("Удалили все элементы таблицы 2 из таблицы 1 - " + table1.removeAll(table2) + " " + table1);

        System.out.println("Добавили элементы таблицы 2 к таблице 1 - " + table1.addAll(table2) + " " + table1);
        System.out.println("Оставили в таблице 1 только элементы таблицы 2 - " + table1.retainAll(table2) + " " + table1);

        System.out.println("Таблица 1 равна таблице 2 - " + table1.equals(table2));
    }
}