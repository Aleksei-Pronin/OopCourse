package ru.academits.pronin.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] buckets;
    private int size;
    private int modCount;

    public HashTable() {
        // noinspection unchecked
        buckets = (ArrayList<E>[]) new ArrayList[100];
    }

    public HashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер массива хэш-таблицы должен быть больше 0, size = " + size);
        }

        // noinspection unchecked
        buckets = (ArrayList<E>[]) new ArrayList[size];
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
        int index = getIndex(o);
        return buckets[index] != null && buckets[index].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] objectsArray = new Object[size];

        int i = 0;

        for (E element : this) {
            objectsArray[i] = element;
            i++;
        }

        return objectsArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Массив не должен быть null");
        }

        Object[] elements = toArray();

        if (a.length < size) {
            // noinspection unchecked
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        }

        // noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        int index = getIndex(e);

        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
        }

        buckets[index].add(e);
        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (buckets[index] == null) {
            return false;
        }

        boolean isRemoved = buckets[index].remove(o);

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

        if (isEmpty()) {
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

        for (ArrayList<E> bucket : buckets) {
            if (bucket != null && !bucket.isEmpty()) {
                int initialBucketSize = bucket.size();

                if (bucket.removeAll(c)) {
                    isModified = true;
                    size -= (initialBucketSize - bucket.size());
                }
            }
        }

        if (isModified) {
            modCount++;
        }

        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не должна быть null");
        }

        if (isEmpty()) {
            return false;
        }

        if (c.isEmpty()) {
            clear();
            return true;
        }

        boolean isModified = false;

        for (ArrayList<E> bucket : buckets) {
            if (bucket != null && !bucket.isEmpty()) {
                int initialBucketSize = bucket.size();

                if (bucket.retainAll(c)) {
                    isModified = true;
                    size -= (initialBucketSize - bucket.size());
                }
            }
        }

        if (isModified) {
            modCount++;
        }

        return isModified;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        for (ArrayList<E> bucket : buckets) {
            if (bucket != null) {
                bucket.clear();
            }
        }

        size = 0;
        modCount++;
    }

    private int getIndex(Object o) {
        return (o == null) ? 0 : Math.abs(o.hashCode() % buckets.length);
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (ArrayList<E> bucket : buckets) {
            if (bucket != null && !bucket.isEmpty()) {
                stringBuilder.append(bucket).append(", ");
            }
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private class HashTableIterator implements Iterator<E> {
        private int currentBucketIndex;
        private int currentElementIndexInBucket = -1;
        private int returnedElementsCount;
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

            while (currentBucketIndex < buckets.length) {
                if (buckets[currentBucketIndex] != null) {
                    currentElementIndexInBucket++;

                    if (currentElementIndexInBucket < buckets[currentBucketIndex].size()) {
                        returnedElementsCount++;
                        return buckets[currentBucketIndex].get(currentElementIndexInBucket);
                    }
                }

                currentBucketIndex++;
                currentElementIndexInBucket = -1;
            }

            throw new NoSuchElementException("Нет следующего элемента");
        }
    }
}