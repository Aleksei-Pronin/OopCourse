package ru.academits.pronin.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E>  {
    private ArrayList<E>[] table;
    private int size;
    int modCount;

    public HashTable() {
        // noinspection unchecked
        table = (ArrayList<E>[]) new ArrayList[100];

        for (int i = 0; i < 100; i++) {
            table[i] = new ArrayList<>();
        }
    }

    public HashTable(int size) {
        // noinspection unchecked
        table = (ArrayList<E>[]) new ArrayList[size];

        for (int i = 0; i < 100; i++) {
            table[i] = new ArrayList<>();
        }
    }

    // int index = Math.abs(o.hashCode() % array.length);

    @Override

    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Нет следующего элемента");
            }

            return items[++currentIndex];
        }
    }
}