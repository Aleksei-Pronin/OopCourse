package ru.academits.pronin.list_main;

import ru.academits.pronin.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        list.addFirst(6);
        list.addFirst(7);

        System.out.println(list);

        System.out.println("Размер списка - " + list.getSize());
        System.out.println("Первый элемент - " + list.getFirst());
        System.out.println("Элемент с индексом 3 - " + list.get(3));

        System.out.println("Изменили элемент с индексом 2 на 7, старое значение - " + list.set(2, 7));
        list.add(4, 9);
        System.out.println("Вставили 9 по индексу 4, текущий список - " + list);

        System.out.println("Удалили элемент с индексом 6 (" + list.remove(6) + "), текущий список - " + list);
        System.out.println("Удаляем элемент со значением 6, результат - " + list.remove((Integer) 6) + ", текущий список - " + list);
        System.out.println("Удаляем элемент со значением 3, результат - " + list.remove((Integer) 11) + ", текущий список - " + list);
        System.out.println("Удалили первый элемент (" + list.removeFirst() + "), текущий список - " + list);

        System.out.println("Копия списка - " + list.copy());

        list.turn();
        System.out.println("Развернули список - " + list);
    }
}