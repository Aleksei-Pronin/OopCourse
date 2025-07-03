package ru.academits.pronin.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        // noinspection unchecked
        items = (E[]) new Object[10];
    }

    public ArrayList(int capacity) {
        //noinspection unchecked
        items = (E[]) new Object[capacity];
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
        for (E item : items) {
            if (o.equals(item)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // noinspection JoinDeclarationAndAssignmentJava
            a = (T[]) new Object[size];
        } else if (a.length > size) {
            a[size] = null;
        }

        for (int i = 0; i < size; i++) {
            a[i] = (T) items[i];
        }

        return a;
    }


    @Override
    public E get(int index) {
        return items[index];
    }

    @Override
    public E set(int index, E element) {
        E replacedElement = items[index];
        items[index] = element;
        return replacedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                index = i;
            }
        }

        return index;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public boolean add(E element) {
        if (size >= items.length) {
            increaseCapacity();
        }

        items[size++] = element;
        modCount++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index == size) {
            add(element);
            return;
        }

        if (size >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = element;
        size++;
        modCount++;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                if (i < size - 1) {
                    System.arraycopy(items, i + 1, items, i, size - i - 1);
                }

                items[--size] = null;
                modCount++;
                return true;
            }
        }

        return false;
    }

    @Override
    public E remove(int index) {
        E element = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[--size] = null;
        modCount++;
        return element;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.isEmpty()) {
            return true;
        }

        if (size() < c.size()) {
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
        if (c.isEmpty()) {
            return false;
        }

        for (E element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (E element : c) {
            add(index++, element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        boolean isModified = false;

        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(items[i])) {
                remove(i);
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            clear();
            return true;
        }

        boolean isModified = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(items[i])) {
                remove(i);
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public void clear() {
        Arrays.fill(items, null);
        size = 0;
        modCount++;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArrayList<?> arrayList = (ArrayList<?>) o;
        return size == arrayList.size && Objects.deepEquals(items, arrayList.items);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + size;
        hash = prime * hash + Arrays.hashCode(items);
        return hash;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        int lastIndex = size - 1;

        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < lastIndex; i++) {
            stringBuilder.append(get(i)).append(", ");
        }

        stringBuilder.append(get(lastIndex)).append(']');
        return stringBuilder.toString();
    }

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 <= size;
        }

        @Override
        public E next() {
            if (currentIndex >= size) {
                throw new NoSuchElementException("Нет элемента с таким индексом.");
            }

            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен.");
            }

            currentIndex++;
            return items[currentIndex];
        }
    }
}