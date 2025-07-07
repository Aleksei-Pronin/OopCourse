package ru.academits.pronin.array_list_main;

import ru.academits.pronin.array_list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>(5);

        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.add(5);
        list1.add(5);

        System.out.println("Размер списка 1 " + list1 + " - " + list1.size());
        System.out.println("Размер списка 2 " + list2 + " - " + list2.size());

        System.out.println("Список 1 содержит 5 - " + list1.contains(5));
        System.out.println("Список 2 содержит 5 - " + list2.contains(5));

        Object[] objectsArray = list1.toArray();
        System.out.println("Список 1 в массив объектов - " + Arrays.toString(objectsArray));

        System.out.println("Список 1 в массив new Integer[10] - " + Arrays.toString(list1.toArray(new Integer[10])));
        System.out.println("Список 1 в массив new Integer[2] - " + Arrays.toString(list1.toArray(new Integer[2])));
        System.out.println("Список 1 в массив new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9} - " + Arrays.toString(list1.toArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9})));

        System.out.println("Элемент с индексом 3 - " + list1.get(3));
        System.out.println("Изменили элемент с индексом 2 на 7, старое значение - " + list1.set(2, 7) + ", текущий список - " + list1);
        System.out.println("Первое вхождение элемента 4 имеет индекс - " + list1.indexOf(4));
        System.out.println("Первое вхождение элемента 9 имеет индекс - " + list1.indexOf(9));
        System.out.println("Последнее вхождение элемента 5 имеет индекс - " + list1.indexOf(5));

        list1.add(4, 9);
        System.out.println("Вставили 9 по индексу 4, текущий список - " + list1);

        System.out.println("Удаляем элемент с индексом 6 (" + list1.remove(6) + "), текущий список - " + list1);
        System.out.println("Удаляем элемент со значением 5, результат - " + list1.remove((Integer) 5) + ", текущий список - " + list1);
        System.out.println("Удаляем элемент со значением 11, результат - " + list1.remove((Integer) 11) + ", текущий список - " + list1);

        list2.add(11);
        list2.add(12);
        list2.add(13);
        list2.add(14);

        System.out.println("Список 1 - " + list1);
        System.out.println("Список 2 - " + list2);
        System.out.println("Список 1 содержит все элементы списка 2 - " + list1.containsAll(list2));

        System.out.println("Добавили список 2 в конец списка 1 - " + list1.addAll(list2) + " " + list1);
        System.out.println("Добавили список 2 в список 1 (индекс 5) - " + list1.addAll(5, list2) + " " + list1);
        System.out.println("Список 1 содержит все элементы списка 2 - " + list1.containsAll(list2));
        System.out.println("Удалили все элементы списка 2 из списка 1 - " + list1.removeAll(list2) + " " + list1);

        System.out.println("Добавили список 2 в список 1 (индекс 5) - " + list1.addAll(5, list2) + " " + list1);
        System.out.println("Оставили в списке 1 только элементы списка 2 - " + list1.retainAll(list2) + " " + list1);

        System.out.println("Список 1 равен списку 2 - " + list1.equals(list2));
    }
}