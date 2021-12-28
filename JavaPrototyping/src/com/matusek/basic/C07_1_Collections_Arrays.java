package com.matusek.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class C07_1_Collections_Arrays {

    // Varargs
    public static void main(String... strings) {

        // Various init

        String[] animals = {"dog", "cat", "cow", "bird"};

        String cars[] = {"ford", "toyota", "mazda"};

        // Size of array up front specified

        Integer[] numbers = new Integer[10];

        int[][] dimensions = {{1,2},{3,4}};

        int[][] args = new int[4][];

        // Array operations

        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1,2,3,4,5));

        // Fixed size list - you can't remove or add operations
        List<Integer> fixedSizeList = Arrays.asList(1,2,3,4,5);

        list.forEach(System.out::print);
        list.removeIf(n -> (n > 3));
        list.spliterator().trySplit(); // What splitter do ????
        System.out.println(list);
    }
}
