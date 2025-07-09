package ru.academits.pronin.tree;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public Tree() {
    }

    public TreeNode<T> find(T data) {
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
            if (currentNode == root) {
                root = null;
            } else if (isLeftChild) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }
        } else if (currentNode.getLeft() == null) {
            if (currentNode == root) {
                root = currentNode.getRight();
            } else if (isLeftChild) {
                parentNode.setLeft(currentNode.getRight());
            } else {
                parentNode.setRight(currentNode.getRight());
            }
        } else if (currentNode.getRight() == null) {
            if (currentNode == root) {
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

            if (currentNode == root) {
                root = successorNode;
            } else if (isLeftChild) {
                parentNode.setLeft(successorNode);
            } else {
                parentNode.setRight(successorNode);
            }
        }

        return true;
    }
}