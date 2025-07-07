package ru.academits.pronin.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return count;
    }

    public E getFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        return head.getData();
    }

    public E get(int index) {
        checkIndex(index, count - 1);

        ListItem<E> item = getIndexItem(index);
        return item.getData();
    }

    public E set(int index, E data) {
        checkIndex(index, count - 1);

        ListItem<E> item = getIndexItem(index);
        E oldData = item.getData();
        item.setData(data);
        return oldData;
    }

    public E removeFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        E removedData = head.getData();
        head = head.getNext();
        count--;
        return removedData;
    }

    public E remove(int index) {
        checkIndex(index, count - 1);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<E> currentItem = head;
        ListItem<E> previousItem = null;

        for (int i = 0; i < index; i++) {
            previousItem = currentItem;
            currentItem = currentItem.getNext();
        }

        E removedData = currentItem.getData();
        // noinspection DataFlowIssue
        previousItem.setNext(currentItem.getNext());

        count--;
        return removedData;
    }

    public boolean remove(E data) {
        if (head == null) {
            return false;
        }

        for (ListItem<E> currentItem = head, previousItem = null; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (data.equals(currentItem.getData())) {
                if (previousItem == null) {
                    head = head.getNext();
                } else {
                    previousItem.setNext(currentItem.getNext());
                }

                count--;
                return true;
            }
        }

        return false;
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void add(int index, E data) {
        checkIndex(index, count);

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<E> item = new ListItem<>(data);
        ListItem<E> currentItem = getIndexItem(index);
        item.setNext(currentItem.getNext());
        currentItem.setNext(item);
        count++;
    }

    public void turn() {
        ListItem<E> currentItem = head;
        ListItem<E> previousItem = null;

        while (currentItem != null) {
            ListItem<E> newCurrentItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = newCurrentItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> copy = new SinglyLinkedList<>();

        if (head == null) {
            return copy;
        }

        copy.count = count;
        copy.head = new ListItem<>(head.getData());
        ListItem<E> copyItem = copy.head;

        for (ListItem<E> item = head.getNext(); item != null; item = item.getNext()) {
            copyItem.setNext(new ListItem<>(item.getData()));
            copyItem = copyItem.getNext();
        }

        return copy;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (ListItem<E> currentItem = head; currentItem != null; currentItem = currentItem.getNext()) {
            stringBuilder.append(currentItem.getData()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private void checkIndex(int index, int endIndex) {
        if (index < 0 || index > endIndex) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index + ". Допустимы границы от 0 до " + endIndex);
        }
    }

    private ListItem<E> getIndexItem(int index) {
        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }
}