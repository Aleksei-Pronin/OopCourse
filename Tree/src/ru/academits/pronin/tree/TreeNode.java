package ru.academits.pronin.tree;

class TreeNode<E> {
    private TreeNode<E> left;
    private TreeNode<E> right;
    private final E data;

    public TreeNode(E data) {
        this.data = data;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }

    public E getData() {
        return data;
    }
}