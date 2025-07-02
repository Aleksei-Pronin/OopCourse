package ru.academits.pronin.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return count;
    }

    public T getFirst() {
        return head.getData();
    }

    public T get(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index);
        }

        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item.getData();
    }

    public T set(int index, T data) {
        if (data == null) {
            throw new NullPointerException("Данные не могут быть null");
        }

        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index);
        }

        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        T oldData = item.getData();
        item.setData(data);
        return oldData;
    }

    public T removeFirst() {
        T removedData = head.getData();
        head = head.getNext();
        count--;
        return removedData;
    }

    public T remove(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index);
        }

        T removedData;

        if (index == 0) {
            removedData = head.getData();
            head = head.getNext();
        } else {
            ListItem<T> currentItem = head;
            ListItem<T> previousItem = null;

            for (int i = 0; i < index; i++) {
                previousItem = currentItem;
                currentItem = currentItem.getNext();
            }

            removedData = currentItem.getData();
            previousItem.setNext(currentItem.getNext());
        }

        count--;
        return removedData;
    }

    public boolean remove(T data) {
        if (data == null) {
            throw new NullPointerException("Данные не могут быть null");
        }

        if (head == null) {
            return false;
        }

        for (ListItem<T> currentItem = head, previousItem = null; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
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

    public void addFirst(T data) {
        if (data == null) {
            throw new NullPointerException("Данные не могут быть null");
        }

        if (head == null) {
            head = new ListItem<>(data);
        } else {
            head = new ListItem<>(data, head);
        }

        count++;
    }

    public void add(int index, T data) {
        if (data == null) {
            throw new NullPointerException("Данные не могут быть null");
        }

        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index);
        }

        if (index == 0) {
            addFirst(data);
        } else {
            ListItem<T> item = new ListItem<>(data);
            ListItem<T> currentItem = head;

            for (int i = 0; i < index - 1; i++) {
                currentItem = currentItem.getNext();
            }

            item.setNext(currentItem.getNext());
            currentItem.setNext(item);
            count++;
        }
    }

    public void turn() {
        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;
        ListItem<T> temp;

        while (currentItem != null) {
            temp = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = temp;
        }

        head = previousItem;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> copy = new SinglyLinkedList<>();

        if (head == null) {
            return copy;
        }

        copy.head = new ListItem<>(head.getData());
        ListItem<T> copyItem = copy.head;
        copy.count++;

        for (ListItem<T> item = head.getNext(); item != null; item = item.getNext()) {
            copyItem.setNext(new ListItem<>(item.getData()));
            copyItem = copyItem.getNext();
            copy.count++;
        }

        return copy;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[" + head);

        for (ListItem<T> currentItem = head.getNext(); currentItem != null; currentItem = currentItem.getNext()) {
            stringBuilder.append(", ").append(currentItem.getData());
        }

        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}