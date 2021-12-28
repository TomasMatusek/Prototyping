package com.matusek.hackerrank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class P001_MinMax {
    public static void main(String[] args) {
        int[] number = {1,2,3,4};
        // List<Integer> values = Arrays.asList(number);
        List<Integer> values = Arrays.stream(number).boxed().collect(Collectors.toList());
        List<Integer> lows = values.stream().sorted(Comparator.naturalOrder()).limit(4).collect(Collectors.toList());
        List<Long> valuesLong = Arrays.stream(number).boxed().map(Long::valueOf).collect(Collectors.toList());
        long x = valuesLong.stream().collect(Collectors.summarizingLong(Long::longValue)).getSum();
    }
}
