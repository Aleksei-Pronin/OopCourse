package ru.academits.pronin.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] table;
    private int size;
    int modCount;

    public HashTable() {
        // noinspection unchecked
        table = (ArrayList<E>[]) new ArrayList[100];
    }

    public HashTable(int size) {
        // noinspection unchecked
        table = (ArrayList<E>[]) new ArrayList[size];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException("Объект не должен быть null");
        }

        int index = Math.abs(o.hashCode() % table.length);
        return table[index] != null && table[index].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyHashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] objectsArray = new Object[size];

        int i = 0;

        for (E element : this) {
            objectsArray[i++] = element;
        }

        return objectsArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Массив не должен быть null");
        }

        if (a.length < size) {
            // noinspection unchecked
            a = (T[]) new Object[size];
        } else if (a.length > size) {
            a[size] = null;
        }

        int i = 0;

        for (E element : this) {
            // noinspection unchecked
            a[i++] = (T) element;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("Элемент не должен быть null");
        }

        int index = Math.abs(e.hashCode() % table.length);

        if (table[index] == null) {
            table[index] = new ArrayList<>();
        }

        table[index].add(e);
        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("Элемент не должен быть null");
        }

        int index = Math.abs(o.hashCode() % table.length);

        if (table[index] == null) {
            return false;
        }

        boolean isRemoved = table[index].remove(o);

        if (table[index].isEmpty()) {
            table[index] = null;
        }

        if (isRemoved) {
            size--;
            modCount++;
        }

        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не должна быть null");
        }

        if (c.isEmpty()) {
            return true;
        }

        if (size < c.size()) {
            return false;
        }

        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не должна быть null");
        }

        if (c.isEmpty()) {
            return false;
        }

        for (E element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не должна быть null");
        }

        if (c.isEmpty()) {
            return false;
        }

        boolean isModified = false;

        for (Object element : c) {
            if (contains(element)) {
                remove(element);
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не должна быть null");
        }

        if (c.isEmpty()) {
            clear();
            return true;
        }

        ArrayList<E> elementsNeedRemove = new ArrayList<>();

        for (E element : this) {
            if (!c.contains(element)) {
                elementsNeedRemove.add(element);
            }
        }

        boolean isModified = false;

        for (E element : elementsNeedRemove) {
            remove(element);
            isModified = true;
        }

        return isModified;
    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                table[i].clear();
                table[i] = null;
            }
        }

        size = 0;
        modCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HashTable<?> hashTable = (HashTable<?>) o;
        return size == hashTable.size && Arrays.equals(toArray(), hashTable.toArray());
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + size;
        hash = prime * hash + Arrays.hashCode(table);
        return hash;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (ArrayList<E> element : table) {
            if (element != null) {
                stringBuilder.append(element).append(", ");
            }
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private class MyHashTableIterator implements Iterator<E> {
        private int currentListIndex = 0;
        private int currentElementIndexInList = -1;
        private int returnedElementsCount = 0;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return returnedElementsCount < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Нет следующего элемента");
            }

            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }

            while (currentListIndex < table.length) {
                if (table[currentListIndex] != null) {
                    currentElementIndexInList++;

                    if (currentElementIndexInList < table[currentListIndex].size()) {
                        returnedElementsCount++;
                        return table[currentListIndex].get(currentElementIndexInList);
                    }
                }

                currentListIndex++;
                currentElementIndexInList = -1;
            }

            throw new NoSuchElementException("Нет следующего элемента");
        }
    }
}