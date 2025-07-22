package ru.academits.pronin.graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntConsumer;

public class Graph {
    private final int[][] edges;

    public Graph(int[][] edges) {
        if (edges == null) {
            throw new NullPointerException("Матрица смежности не может быть null");
        }

        int size = edges.length;
        this.edges = new int[size][];

        for (int i = 0; i < size; i++) {
            if (edges[i] == null) {
                throw new NullPointerException("Матрица смежности не может содержать null строки");
            } else if (edges[i].length != size) {
                throw new IllegalArgumentException("Матрица смежности должна быть квадратной");
            }
            this.edges[i] = Arrays.copyOf(edges[i], edges[i].length);
        }
    }

    public void traverseGraphInBreadth(IntConsumer action) {
        int size = edges.length;

        if (size == 0) {
            return;
        }

        boolean[] visited = new boolean[size];
        Queue<Integer> queue = new LinkedList<>();

        for (int startVertex = 0; startVertex < size; startVertex++) {
            if (visited[startVertex]) {
                continue;
            }

            queue.add(startVertex);

            while (!queue.isEmpty()) {
                int currentVertex = queue.poll();

                if (visited[currentVertex]) {
                    continue;
                }

                action.accept(currentVertex);
                visited[currentVertex] = true;

                for (int adjacentVertex = 0; adjacentVertex < size; adjacentVertex++) {
                    if (edges[currentVertex][adjacentVertex] != 0 && !visited[adjacentVertex]) {
                        queue.add(adjacentVertex);
                    }
                }
            }
        }
    }

    public void traverseGraphInDeep(IntConsumer action) {
        int size = edges.length;
        if (size == 0) {
            return;
        }

        boolean[] visited = new boolean[size];
        Deque<Integer> stack = new LinkedList<>();

        for (int startVertex = 0; startVertex < size; startVertex++) {
            if (visited[startVertex]) {
                continue;
            }

            stack.push(startVertex);

            while (!stack.isEmpty()) {
                int currentVertex = stack.pop();

                if (visited[currentVertex]) {
                    continue;
                }

                action.accept(currentVertex);
                visited[currentVertex] = true;

                for (int adjacentVertex = size - 1; adjacentVertex >= 0; adjacentVertex--) {
                    if (edges[currentVertex][adjacentVertex] != 0 && !visited[adjacentVertex]) {
                        stack.push(adjacentVertex);
                    }
                }
            }
        }
    }

    public void traverseGraphInDeepRecursive(IntConsumer action) {
        int size = edges.length;
        if (size == 0) {
            return;
        }

        boolean[] visited = new boolean[size];

        for (int vertex = 0; vertex < size; vertex++) {
            if (!visited[vertex]) {
                traverseGraphInDeepRecursive(vertex, visited, action);
            }
        }
    }

    private void traverseGraphInDeepRecursive(int vertex, boolean[] visited, IntConsumer action) {
        action.accept(vertex);
        visited[vertex] = true;

        for (int adjacentVertex = 0; adjacentVertex < edges.length; adjacentVertex++) {
            if (edges[vertex][adjacentVertex] != 0 && !visited[adjacentVertex]) {
                traverseGraphInDeepRecursive(adjacentVertex, visited, action);
            }
        }
    }
}