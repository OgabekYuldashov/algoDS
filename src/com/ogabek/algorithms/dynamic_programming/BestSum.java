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
        System.out.println(bestSumMemoized(7, new int[] {2,3,6,7})); // [7]
        System.out.println(bestSumMemoized(7, new int[] {7})); // [7]
        long arrayMemoStartTime = System.nanoTime();
        System.out.println(bestSumMemoized(300, new int[] {7,14, 14, 14, 14, 14})); // null
        long arrayMemoEndTime = System.nanoTime();
        System.out.println("Memoized Execution Time: " + (arrayMemoEndTime - arrayMemoStartTime));


        System.out.println(bestSumTabulation(7, new int[] {2,3,6,7})); // [7]
        System.out.println(bestSumTabulation(7, new int[] {7})); // [7]
        long tabulationStartTime = System.nanoTime();
        System.out.println(bestSumTabulation(300, new int[] {7,14, 14, 14, 14, 14})); // null
        long tabulationEndTime = System.nanoTime();
        System.out.println("Tabulation Execution Time: " + (tabulationEndTime - tabulationStartTime));
    }

    public static List<Integer> bestSumMemoized(int targetSum, int[] numbers) {
        return bestSumMemoized(targetSum, numbers, new HashMap<>());
    }

    private static List<Integer> bestSumMemoized(int targetSum, int[] numbers, Map<Integer, List<Integer>> memo) {
        if(targetSum < 0) return null;
        if(targetSum == 0) return new LinkedList<>();
        if(memo.containsKey(targetSum)) return memo.get(targetSum); // check if solution exists in the memo

        List<Integer> bestCombination = null;
        for(int number : numbers) {
            int newTarget = targetSum - number;
            List<Integer> currCombination = bestSumMemoized(newTarget, numbers, memo);
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


    public static List<Integer> bestSumTabulation(int targetSum, int[] numbers) {
        if(numbers == null) return null;

        LinkedList<Integer>[] table = new LinkedList[targetSum + 1];
        table[0] = new LinkedList<>();

        for(int i = 0; i <= targetSum; ++i) {
            // if the cell is null, it's not reachable. So no need to explore further.
            if(table[i] == null) continue;

            for(int number : numbers) {
                int stepsAhead = i + number;
                LinkedList<Integer> newCombination = new LinkedList<>(table[i]);
                newCombination.add(number);

                // skip the iteration if stepsAhead is out of bounds
                if(stepsAhead > targetSum) continue;

                // replace the combination only if the new combination is smaller or there was no combination before.
                if(table[stepsAhead] == null || newCombination.size() < table[stepsAhead].size()) {
                    table[stepsAhead] = newCombination;
                }
            }
        }

        return table[targetSum];
    }
}
