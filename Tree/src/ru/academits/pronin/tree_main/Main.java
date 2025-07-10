package ru.academits.pronin.tree_main;

import ru.academits.pronin.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();

        tree.insert(8);
        tree.insert(3);
        tree.insert(1);
        tree.insert(6);
        tree.insert(4);
        tree.insert(7);
        tree.insert(10);
        tree.insert(14);
        tree.insert(13);
        tree.insert(20);
        tree.insert(17);
        tree.insert(25);
        tree.insert(18);

        System.out.println("Обход в ширину - " + tree.traverseTreeInBreadth());
        System.out.println("Обход в глубину - " + tree.traverseTreeInDeep());
        System.out.println("Обход в глубину с рекурсией - " + tree.traverseTreeInDeepRecursive());

        System.out.println("Ищем элемент 14 - " + tree.find(14));
        System.out.println("Удаляем элемент 14 - " + tree.remove(14));
        System.out.println("Ищем элемент 14 - " + tree.find(14));
        System.out.println("Удаляем элемент 14 - " + tree.remove(14));

        System.out.println("Обход в ширину - " + tree.traverseTreeInBreadth());
        System.out.println("Обход в глубину - " + tree.traverseTreeInDeep());

        tree.insert(14);
        System.out.println("Добавили элемент 14");

        System.out.println("Обход в ширину - " + tree.traverseTreeInBreadth());
        System.out.println("Обход в глубину - " + tree.traverseTreeInDeep());
    }
}