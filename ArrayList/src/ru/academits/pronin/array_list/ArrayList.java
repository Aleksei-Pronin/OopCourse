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
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость должна быть больше 0, capacity = " + capacity);
        }

        // noinspection unchecked
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
        if (o == null) {
            throw new NullPointerException("Объект не должен быть null");
        }

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

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        if (array == null) {
            throw new NullPointerException("Массив не должен быть null");
        }

        if (array.length < size) {
            array = (T[]) new Object[size];
        } else if (array.length > size) {
            array[size] = null;
        }

        for (int i = 0; i < size; i++) {
            array[i] = (T) items[i];
        }

        return array;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index);
        }

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        if (element == null) {
            throw new NullPointerException("Элемент не должен быть null");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index);
        }

        E replacedElement = items[index];
        items[index] = element;
        return replacedElement;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            throw new NullPointerException("Объект не должен быть null");
        }

        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            throw new NullPointerException("Объект не должен быть null");
        }

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
        if (element == null) {
            throw new NullPointerException("Элемент не должен быть null");
        }

        if (size >= items.length) {
            increaseCapacity();
        }

        items[size++] = element;
        modCount++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (element == null) {
            throw new NullPointerException("Элемент не должен быть null");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index);
        }

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
        if (o == null) {
            throw new NullPointerException("Объект не должен быть null");
        }

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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index);
        }

        E element = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[--size] = null;
        modCount++;
        return element;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не должна быть null");
        }

        if (collection.isEmpty()) {
            return true;
        }

        if (size < collection.size()) {
            return false;
        }

        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не должна быть null");
        }

        if (collection.isEmpty()) {
            return false;
        }

        int newCapacity = collection.size() + size;

        if (newCapacity > items.length) {
            items = Arrays.copyOf(items, newCapacity);
        }

        for (E element : collection) {
            items[size++] = element;
        }

        modCount++;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не должна быть null");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index);
        }

        if (collection.isEmpty()) {
            return false;
        }

        int newElementsCount = collection.size();
        int newCapacity = collection.size() + size;

        if (newCapacity > items.length) {
            items = Arrays.copyOf(items, newCapacity);
        }

        System.arraycopy(items, index, items, index + newElementsCount, size - index);

        int i = index;

        for (E element : collection) {
            items[i++] = element;
        }

        size += newElementsCount;
        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не должна быть null");
        }

        if (collection.isEmpty()) {
            return false;
        }

        boolean isModified = false;

        for (int i = size - 1; i >= 0; i--) {
            if (collection.contains(items[i])) {
                remove(i);
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не должна быть null");
        }

        if (collection.isEmpty()) {
            clear();
            return true;
        }

        boolean isModified = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!collection.contains(items[i])) {
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
        // noinspection DataFlowIssue
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        // noinspection DataFlowIssue
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        // noinspection DataFlowIssue
        return null;
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
        trimToSize();
        arrayList.trimToSize();
        return size == arrayList.size && Arrays.equals(items, arrayList.items);
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