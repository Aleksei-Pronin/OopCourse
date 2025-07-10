package ru.academits.pronin.tree;

import java.util.*;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public Tree() {
    }

    public TreeNode<T> find(T data) {
        if (root == null) {
            return null;
        }

        TreeNode<T> currentNode = root;

        while (!currentNode.getData().equals(data)) {
            if (data.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return null;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return null;
                }
            }
        }

        return currentNode;
    }

    public void insert(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            return;
        }

        TreeNode<T> currentNode = root;

        while (true) {
            int compareResult = data.compareTo(currentNode.getData());

            if (compareResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    return;
                }
            } else if (compareResult > 0) {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data));
                    return;
                }
            } else {
                return;
            }
        }
    }

    public boolean remove(T data) {
        if (root == null) {
            return false;
        }

        TreeNode<T> currentNode = root;
        TreeNode<T> parentNode = null;
        boolean isLeftChild = false;

        while (!currentNode.getData().equals(data)) {
            parentNode = currentNode;

            if (data.compareTo(currentNode.getData()) < 0) {
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
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            if (parentNode == null) {
                root = null;
            } else if (isLeftChild) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }
        } else if (currentNode.getLeft() == null) {
            if (parentNode == null) {
                root = currentNode.getRight();
            } else if (isLeftChild) {
                parentNode.setLeft(currentNode.getRight());
            } else {
                parentNode.setRight(currentNode.getRight());
            }
        } else if (currentNode.getRight() == null) {
            if (parentNode == null) {
                root = currentNode.getLeft();
            } else if (isLeftChild) {
                parentNode.setLeft(currentNode.getLeft());
            } else {
                parentNode.setRight(currentNode.getLeft());
            }
        } else {
            TreeNode<T> successorParentNode = currentNode;
            TreeNode<T> successorNode = currentNode.getRight();

            while (successorNode.getLeft() != null) {
                successorParentNode = successorNode;
                successorNode = successorNode.getLeft();
            }

            if (successorNode.getRight() != null) {
                successorParentNode.setLeft(successorNode.getRight());
            } else {
                successorParentNode.setLeft(null);
            }

            successorNode.setLeft(currentNode.getLeft());

            if (currentNode.getRight() != successorNode) {
                successorNode.setRight(currentNode.getRight());
            }

            if (parentNode == null) {
                root = successorNode;
            } else if (isLeftChild) {
                parentNode.setLeft(successorNode);
            } else {
                parentNode.setRight(successorNode);
            }
        }

        return true;
    }

    public List<T> traverseTreeInBreadth() {
        List<T> list = new ArrayList<>();

        if (root == null) {
            return list;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            list.add(node.getData());

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }

        return list;
    }

    public List<T> traverseTreeInDeepRecursive() {
        List<T> list = new ArrayList<>();

        if (root == null) {
            return list;
        }

        TreeNode<T> node = root;
        visit(node, list);
        return list;
    }

    private void visit(TreeNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }

        list.add(node.getData());

        visit(node.getLeft(), list);
        visit(node.getRight(), list);
    }

    public List<T> traverseTreeInDeep() {
        List<T> list = new ArrayList<>();

        if (root == null) {
            return list;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.removeLast();
            list.add(node.getData());

            if (node.getRight() != null) {
                stack.addLast(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.addLast(node.getLeft());
            }
        }

        return list;
    }
}