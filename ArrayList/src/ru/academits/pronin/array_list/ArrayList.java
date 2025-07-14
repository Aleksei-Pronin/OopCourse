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
            throw new IllegalArgumentException("Вместимость должна быть больше либо равна 0, capacity = " + capacity);
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
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @SuppressWarnings({"unchecked", "SuspiciousSystemArraycopy"})
    @Override
    public <T> T[] toArray(T[] array) {
        if (array == null) {
            throw new NullPointerException("Массив не должен быть null");
        }

        if (array.length < size) {
            return (T[]) Arrays.copyOf(items, size, array.getClass());
        }

        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public E get(int index) {
        checkIndex(index, size - 1);

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index, size - 1);

        E replacedElement = items[index];
        items[index] = element;
        return replacedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }

        return -1;
    }

    private void increaseCapacity() {
        int newCapacity = (items.length == 0) ? 1 : items.length * 2;
        items = Arrays.copyOf(items, newCapacity);
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            items = Arrays.copyOf(items, minCapacity);
        }
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

        items[size] = element;
        size++;
        modCount++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index, size);

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
    public E remove(int index) {
        checkIndex(index, size - 1);

        E element = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[size - 1] = null;
        size--;
        modCount++;
        return element;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не должна быть null");
        }

        if (collection.isEmpty()) {
            return true;
        }

        if (isEmpty()) {
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
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не должна быть null");
        }

        checkIndex(index, size);

        if (collection.isEmpty()) {
            return false;
        }

        int newElementsCount = collection.size();
        ensureCapacity(newElementsCount + size);
        System.arraycopy(items, index, items, index + newElementsCount, size - index);

        int i = index;

        for (E element : collection) {
            items[i] = element;
            i++;
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

        if (isEmpty()) {
            return false;
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
        if (isEmpty()) {
            return;
        }

        Arrays.fill(items, 0, size, null);
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

        if (size != arrayList.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(items[i], arrayList.items[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + size;

        for (int i = 0; i < size; i++) {
            hash = prime * hash + Objects.hashCode(items[i]);
        }

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
            stringBuilder.append(items[i]).append(", ");
        }

        stringBuilder.append(items[size - 1]).append(']');
        return stringBuilder.toString();
    }

    private class ArrayListIterator implements Iterator<E> {
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

            currentIndex++;
            return items[currentIndex];
        }
    }

    private void checkIndex(int index, int endIndex) {
        if (index < 0 || index > endIndex) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index + ". Допустимы границы от 0 до " + endIndex);
        }
    }
}