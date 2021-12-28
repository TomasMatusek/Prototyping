package com.matusek.hackerrank;

import java.util.Arrays;

public class P003_VeryBigSum {
    public static void main(String[] args) {
        long[] nums = { 1000000001, 1000000002, 1000000003, 1000000004, 1000000005 };
        long result = Arrays.stream(nums)
                .sum();
        System.out.println(result);
    }
}
