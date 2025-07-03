package ru.academits.pronin.array_list_main;

import ru.academits.pronin.array_list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println(list);

        System.out.println(Arrays.toString(list.toArray(new Integer[10])));

        Object[] objArr = list.toArray();

        System.out.println(Arrays.toString(objArr));
    }
}
