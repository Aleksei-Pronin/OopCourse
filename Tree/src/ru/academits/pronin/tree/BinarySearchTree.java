package ru.academits.pronin.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private final Comparator<E> comparator;
    private TreeNode<E> root;
    private int size;

    public BinarySearchTree() {
        comparator = null;
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private int compare(E data1, E data2) {
        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null) {
            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        // noinspection unchecked
        Comparable<E> comparableData1 = (Comparable<E>) data1;
        return comparableData1.compareTo(data2);
    }

    public int getSize() {
        return size;
    }

    public boolean contains(E data) {
        if (root == null) {
            return false;
        }

        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            int compareResult = compare(data, currentNode.getData());

            if (compareResult == 0) {
                return true;
            }

            currentNode = (compareResult < 0) ? currentNode.getLeft() : currentNode.getRight();
        }

        return false;
    }

    public void insert(E data) {
        if (root == null) {
            root = new TreeNode<>(data);
            size++;
            return;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            if (compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    size++;
                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data));
                    size++;
                    return;
                }
            }
        }
    }

    public boolean remove(E data) {
        if (root == null) {
            return false;
        }

        TreeNode<E> currentNode = root;
        TreeNode<E> parentNode = null;
        boolean isLeftChild = false;

        int comparisonResult = compare(data, currentNode.getData());

        while (comparisonResult != 0) {
            parentNode = currentNode;

            if (comparisonResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                    isLeftChild = true;
                } else {
                    return false;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }

            comparisonResult = compare(data, currentNode.getData());
        }

        if (currentNode.getLeft() == null || currentNode.getRight() == null) {
            TreeNode<E> childNode = (currentNode.getLeft() == null) ? currentNode.getRight() : currentNode.getLeft();
            replaceNode(parentNode, childNode, isLeftChild);
        } else {
            TreeNode<E> successorParentNode = currentNode;
            TreeNode<E> successorNode = currentNode.getRight();

            while (successorNode.getLeft() != null) {
                successorParentNode = successorNode;
                successorNode = successorNode.getLeft();
            }

            if (successorParentNode == currentNode) {
                successorParentNode.setRight(successorParentNode.getRight());
            } else {
                successorParentNode.setLeft(successorNode.getRight());
            }

            successorNode.setLeft(currentNode.getLeft());

            if (currentNode.getRight() != successorNode) {
                successorNode.setRight(currentNode.getRight());
            }

            replaceNode(parentNode, successorNode, isLeftChild);
        }

        size--;
        return true;
    }

    public void traverseTreeInBreadth(Consumer<E> action) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> node = queue.poll();
            action.accept(node.getData());

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void traverseTreeInDeepRecursive(Consumer<E> action) {
        if (root == null) {
            return;
        }

        traverseTreeInDeepRecursive(root, action);
    }

    private void traverseTreeInDeepRecursive(TreeNode<E> node, Consumer<E> action) {
        if (node == null) {
            return;
        }

        action.accept(node.getData());

        traverseTreeInDeepRecursive(node.getLeft(), action);
        traverseTreeInDeepRecursive(node.getRight(), action);
    }

    public void traverseTreeInDeep(Consumer<E> action) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<E>> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<E> node = stack.pop();
            action.accept(node.getData());

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    private void replaceNode(TreeNode<E> parentNode, TreeNode<E> newNode, boolean isLeftChild) {
        if (parentNode == null) {
            root = newNode;
        } else if (isLeftChild) {
            parentNode.setLeft(newNode);
        } else {
            parentNode.setRight(newNode);
        }
    }

    @Override
    public String toString() {
        if (root == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        traverseTreeInBreadth(data -> stringBuilder.append(data).append(", "));
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}