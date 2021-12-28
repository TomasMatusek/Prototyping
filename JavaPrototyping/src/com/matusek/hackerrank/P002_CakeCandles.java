package com.matusek.hackerrank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class P002_CakeCandles {
    public static void main(String[] args) {

        int[] arr = {3,2,1,3};
        List<Integer> numbers = Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        Integer max = numbers.get(0);

        Long result = numbers.stream()
                .filter(v -> v.equals(max))
                .count();

        System.out.println(result);
    }
}
