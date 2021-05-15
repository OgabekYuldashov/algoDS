package com.ogabek.algorithms.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{1, 3, 7, 9, 2}, 11)));
    }

    public static int[] twoSum(int[] numbers, int target) {
        if(numbers == null) return null;
        Map<Integer, Integer> complementaries = new HashMap<>(); // 10; 8; 4; 2; 9;

        for(int i = 0; i < numbers.length; ++i) {
            int newTarget = target - numbers[i];
            // check if current number is a complement of any previously seen number
            if(complementaries.containsKey(numbers[i])) {
                return new int[] {complementaries.get(numbers[i]), i};
            } else {
                complementaries.put(newTarget, i);
            }
        }
        return null;
    }
}
