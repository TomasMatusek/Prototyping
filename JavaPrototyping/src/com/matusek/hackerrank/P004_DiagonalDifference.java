package com.matusek.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P004_DiagonalDifference {
    public static void main(String[] args) {
        List<List<Integer>> arr = Arrays.asList(
            Arrays.asList(1,2,3),
            Arrays.asList(4,5,6),
            Arrays.asList(7,8,9)
        );

        int diagonalASum = 0;
        int diagonalBSum = 0;
        for (int i = 0; i < arr.size(); i++) {
            diagonalASum += arr.get(i).get(i);
            diagonalBSum += arr.get(i).get((arr.size() - 1) - i);
        }

        Math.abs(diagonalASum - diagonalBSum);

    }
}