package ru.academits.pronin.graph_main;

import ru.academits.pronin.graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(new int[][]{
                {0, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 1, 0}
        });

        System.out.println("Обход в ширину:");
        graph.traverseGraphInBreadth(data -> System.out.print(data + " "));
        System.out.println();

        System.out.println("Обход в глубину:");
        graph.traverseGraphInDeep(data -> System.out.print(data + " "));
        System.out.println();

        System.out.println("Обход в глубину с рекурсией:");
        graph.traverseGraphInDeepRecursive(data -> System.out.print(data + " "));
    }
}