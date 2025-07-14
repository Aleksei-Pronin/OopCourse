package ru.academits.pronin.tree_main;

import ru.academits.pronin.tree.binarySearchTree;

public class Main {
    public static void main(String[] args) {
        binarySearchTree<Integer> tree = new binarySearchTree<>();

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
        tree.insert(null);
        tree.insert(8);

        System.out.println(tree);
        System.out.println("Количество элементов - " + tree.getSize());

        System.out.println("Обход в ширину:");
        tree.traverseTreeInBreadth(data -> System.out.print(data + " "));
        System.out.println();
        System.out.println("Обход в глубину:");
        tree.traverseTreeInDeep(data -> System.out.print(data + " "));
        System.out.println();
        System.out.println("Обход в глубину с рекурсией:");
        tree.traverseTreeInDeepRecursive(data -> System.out.print(data + " "));
        System.out.println();

        System.out.println("Ищем элемент 14 - " + tree.find(14));
        System.out.println("Удаляем элемент 14 - " + tree.remove(14));
        System.out.println("Количество элементов - " + tree.getSize());

        System.out.println("Ищем элемент 14 - " + tree.find(14));
        System.out.println("Удаляем элемент 14 - " + tree.remove(14));

        System.out.println("Обход в ширину:");
        tree.traverseTreeInBreadth(data -> System.out.print(data + " "));
        System.out.println();
        System.out.println("Обход в глубину:");
        tree.traverseTreeInDeep(data -> System.out.print(data + " "));
        System.out.println();

        tree.insert(14);
        System.out.println("Добавили элемент 14");
        System.out.println("Количество элементов - " + tree.getSize());

        System.out.println("Обход в ширину:");
        tree.traverseTreeInBreadth(data -> System.out.print(data + " "));
        System.out.println();
        System.out.println("Обход в глубину:");
        tree.traverseTreeInDeep(data -> System.out.print(data + " "));
        System.out.println();

        binarySearchTree<Integer> reverseTree = new binarySearchTree<>((n1, n2) -> n2.compareTo(n1));

        reverseTree.insert(8);
        reverseTree.insert(3);
        reverseTree.insert(1);
        reverseTree.insert(6);
        reverseTree.insert(4);
        reverseTree.insert(7);
        reverseTree.insert(10);
        reverseTree.insert(14);
        reverseTree.insert(13);
        reverseTree.insert(20);
        reverseTree.insert(17);
        reverseTree.insert(25);
        reverseTree.insert(18);
        reverseTree.insert(null);
        reverseTree.insert(8);

        System.out.println("Обход в ширину:");
        reverseTree.traverseTreeInBreadth(data -> System.out.print(data + " "));
        System.out.println();
        System.out.println("Обход в глубину:");
        reverseTree.traverseTreeInDeep(data -> System.out.print(data + " "));
    }
}