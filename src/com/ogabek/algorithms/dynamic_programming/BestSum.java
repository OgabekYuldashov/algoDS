package com.ogabek.algorithms.dynamic_programming;

import java.util.*;

/**
 * Write a function 'bestSum(targetSum, numbers)' that takes in a
 * targetSum and an array of numbers as arguments.
 *
 * The function should return an array containing the shortest combination of numbers
 * that add up to exactly the targetSum.
 *
 * If there is a tie for the shortest combination, you may return any one of the shortest
 *
 * input: bestSum(7, [5,3,4,7]) => [3,4] VS [7] -> output: [7]
 *
 */
public class BestSum {

    public static void main(String[] args) {
        System.out.println(bestSum(7, new int[] {2,3,6,7})); // [7]
        System.out.println(bestSum(7, new int[] {7})); // [7]
        long arrayMemoStartTime = System.nanoTime();
        System.out.println(bestSum(300, new int[] {7,14})); // null
        long arrayMemoEndTime = System.nanoTime();
        System.out.println("Memoized Execution Time: " + (arrayMemoEndTime - arrayMemoStartTime));
    }

    public static List<Integer> bestSum(int targetSum, int[] numbers) {
        return bestSum(targetSum, numbers, new HashMap<>());
    }

    private static List<Integer> bestSum(int targetSum, int[] numbers, Map<Integer, List<Integer>> memo) {
        if(targetSum < 0) return null;
        if(targetSum == 0) return new LinkedList<>();
        if(memo.containsKey(targetSum)) return memo.get(targetSum); // check if solution exists in the memo

        List<Integer> bestCombination = null;
        for(int number : numbers) {
            int newTarget = targetSum - number;
            List<Integer> currCombination = bestSum(newTarget, numbers, memo);
            // check if this branch returned a combination
            if(currCombination != null) {
                currCombination.add(number); // add the current number to the list
                // if there are no previous combinations found or the new combination is smaller than the previous one
                if(bestCombination == null || currCombination.size() < bestCombination.size()) {
                    bestCombination = currCombination; // update the bestCombination found so far
                }
            }
        }

        memo.put(targetSum, bestCombination); // save the solution in the memo
        return bestCombination;
    }

}
