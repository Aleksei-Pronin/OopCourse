package ru.academits.pronin.hash_table_main;

import ru.academits.pronin.hash_table.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable1 = new HashTable<>();
        HashTable<Integer> hashTable2 = new HashTable<>(30);

        hashTable1.add(45);
        hashTable1.add(4);
        hashTable1.add(56);
        hashTable1.add(34);
        hashTable1.add(23);
        hashTable1.add(756);
        hashTable1.add(45);
        hashTable1.add(98);
        hashTable1.add(54);

        System.out.println("Размер таблицы 1 " + hashTable1 + " - " + hashTable1.size());
        System.out.println("Размер таблицы 2 " + hashTable2 + " - " + hashTable2.size());
        System.out.println("Таблица 1 пустая - " + hashTable1.isEmpty());
        System.out.println("Таблица 2 пустая - " + hashTable2.isEmpty());

        System.out.println("Таблица 1 содержит 4 - " + hashTable1.contains(4));
        System.out.println("Таблица 2 содержит 5 - " + hashTable2.contains(5));

        Object[] objectsArray = hashTable1.toArray();
        System.out.println("Таблица 1 в массив объектов - " + Arrays.toString(objectsArray));

        System.out.println("Таблица 1 в массив new Integer[10] - " + Arrays.toString(hashTable1.toArray(new Integer[10])));
        System.out.println("Таблица 1 в массив new Integer[2] - " + Arrays.toString(hashTable1.toArray(new Integer[2])));
        System.out.println("Таблица 1 в массив new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11} - " + Arrays.toString(hashTable1.toArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})));

        System.out.println("Удаляем элемент со значением 4, результат - " + hashTable1.remove(4) + ", текущая таблица - " + hashTable1);
        System.out.println("Удаляем элемент со значением 11, результат - " + hashTable1.remove(11) + ", текущая таблица - " + hashTable1);

        hashTable2.add(11);
        hashTable2.add(12);
        hashTable2.add(13);
        hashTable2.add(14);

        System.out.println("Таблица 1 - " + hashTable1);
        System.out.println("Таблица 2 - " + hashTable2);
        System.out.println("Таблица 1 содержит все элементы таблицы 2 - " + hashTable1.containsAll(hashTable2));

        System.out.println("Добавили элементы таблицы 2 к таблице 1 - " + hashTable1.addAll(hashTable2) + " " + hashTable1);
        System.out.println("Таблица 1 содержит все элементы таблицы 2 - " + hashTable1.containsAll(hashTable2));
        System.out.println("Удалили все элементы таблицы 2 из таблицы 1 - " + hashTable1.removeAll(hashTable2) + " " + hashTable1);

        System.out.println("Добавили элементы таблицы 2 к таблице 1 - " + hashTable1.addAll(hashTable2) + " " + hashTable1);
        System.out.println("Оставили в таблице 1 только элементы таблицы 2 - " + hashTable1.retainAll(hashTable2) + " " + hashTable1);

        System.out.println("Таблица 1 равна таблице 2 - " + hashTable1.equals(hashTable2));
    }
}