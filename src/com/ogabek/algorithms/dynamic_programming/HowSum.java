package com.ogabek.algorithms.dynamic_programming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Write a function that takes in a targetSum and an array of numbers as arguments.
 *
 * The function should return a list containing any combination of elements that add up to exactly the targetSum.
 * If there is no combination that adds up to the targetSum, return null.
 *
 * If there are multiple combinations possible, return any single one.
 *
 * Input: 7, [5,3,4,7] => Output: [3,4]
 * Input: 8, [2,3,5] => Output: [2,2,2,2] or [3,5]
 */
public class HowSum {

    public static void main(String[] args) {
        System.out.println(howSumMemoized(7, new int[] {5,3,4,7})); // [4,3]
        System.out.println(howSumMemoized(8, new int[] {2,3,5})); // [2,2,2,2]
        long arrayMemoStartTime = System.nanoTime();
        System.out.println(howSumMemoized(300, new int[] {7,14})); // null
        long arrayMemoEndTime = System.nanoTime();
        System.out.println("Memoized Execution Time: " + (arrayMemoEndTime - arrayMemoStartTime));

        System.out.println(howSumTabulation(7, new int[] {5,3,4,7})); // [4,3]
        System.out.println(howSumTabulation(8, new int[] {2,3,5})); // [2,2,2,2]
        long tabulationStartTime = System.nanoTime();
        System.out.println(howSumTabulation(300, new int[] {7,14})); // null
        long tabulationEndTime = System.nanoTime();
        System.out.println("Tabulation Execution Time: " + (tabulationEndTime - tabulationStartTime));
    }

    public static List<Integer> howSumMemoized(int targetSum, int[] numbers) {
        return howSumMemoized(targetSum, numbers, new HashMap<>());
    }

    /**
     * n = targetSum, m = numbers.length
     * time: O(n*m)
     * space: O(n*m)
     */
    private static List<Integer> howSumMemoized(int targetSum, int[] numbers, HashMap<Integer, List<Integer>> memo) {
        if(targetSum < 0) return null;
        if(targetSum == 0) return new LinkedList<>();
        if(memo.containsKey(targetSum)) return memo.get(targetSum);

        List<Integer> result = null;
        for(int number : numbers) {
            int newTarget = targetSum - number;
            result = howSumMemoized(newTarget, numbers, memo);
            if(result != null) {
                result.add(number);
                break;
            }
        }

        memo.put(targetSum, result);
        return result;
    }

    /**
     * n = targetSum, m = number.length
     * time: O(n*m)
     * space: O(n*m)
     */
    public static List<Integer> howSumTabulation(int targetSum, int[] numbers) {
        List<Integer>[] table = new LinkedList[targetSum + 1];
        // seeding the initial value. We can still obtain the target of "0" by taking nothing from the numbers array
        table[0] = new LinkedList<Integer>();

        for(int i = 0; i < table.length; ++i) {
            // we skip null cells as they are not reachable
            if(table[i] == null) continue;

            for(int number : numbers) {
                int step = i + number;
                // check if we're within bounds
                if(step <= targetSum) {
                    List<Integer> newCombination = new LinkedList<>(table[i]);
                    newCombination.add(number);
                    table[step] = newCombination;
                }
                // found a combination for the targetSum, exit early
                if(step == targetSum) break;
            }
        }

        return table[targetSum];
    }
}
