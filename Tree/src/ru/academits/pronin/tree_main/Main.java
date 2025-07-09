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

        System.out.println((tree.bfs()));

        System.out.println(tree);
        System.out.println(tree.find(14));

        System.out.println(tree.remove(14));
        System.out.println((tree.bfs()));
        System.out.println(tree.find(14));

        System.out.println(tree.find(20));
        tree.insert(14);
        System.out.println(tree.bfs());
        System.out.println(tree.dfsRecursive());
        System.out.println(tree.dfs());
    }
}
