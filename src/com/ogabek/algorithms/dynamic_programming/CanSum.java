package com.ogabek.algorithms.dynamic_programming;

public class CanSum {
    public static void main(String[] args) {
        System.out.println(canSumMemoized(7, new int[] {2,3})); // true
        System.out.println(canSumMemoized(7, new int[] {5,3,4,7})); // true
        System.out.println(canSumMemoized(7, new int[] {2,4})); // false
        System.out.println(canSumMemoized(8, new int[] {2,3,5})); // true

        long arrayMemoStartTime = System.nanoTime();
        System.out.println(canSumMemoized(300, new int[] {7,14})); // false
        long arrayMemoEndTime = System.nanoTime();
        System.out.println("Memoized Execution Time: " + (arrayMemoEndTime - arrayMemoStartTime));


        System.out.println(canSumTabulation(7, new int[] {2,3})); // true
        System.out.println(canSumTabulation(7, new int[] {5,3,4,7})); // true
        System.out.println(canSumTabulation(7, new int[] {2,4})); // false
        System.out.println(canSumTabulation(8, new int[] {2,3,5})); // true

        long tabulationStartTime = System.nanoTime();
        System.out.println(canSumTabulation(300, new int[] {7,14})); // false
        long tabulationEndTime = System.nanoTime();
        System.out.println("Tabulation Execution Time: " + (tabulationEndTime - tabulationStartTime));

    }

    /**
     * n = targetSum, m = numbers.length
     * time: O(n*m)
     * space: O(n)
     */
    public static boolean canSumMemoized(int targetSum, int[] numbers) {
        Boolean[] memo = new Boolean[targetSum + 1];
        return canSumMemoized(targetSum, numbers, memo);
    }

    private static boolean canSumMemoized(int targetSum, int[] numbers, Boolean[] memo) {
        if(targetSum == 0) return true;
        if(targetSum < 0) return false;
        if(memo[targetSum] != null) return memo[targetSum];

        for (int number : numbers) {
            int newTarget = targetSum - number;
            if (canSumMemoized(newTarget, numbers, memo)) {
                memo[targetSum] = true;
                return true;
            }
        }

        memo[targetSum] = false;
        return false;
    }

    /**
     * n = targetSum, m = numbers.length
     * time: O(n*m)
     * space: O(n)
     */
    public static boolean canSumTabulation(int targetSum, int[] numbers) {
        boolean[] table = new boolean[targetSum + 1];
        table[0] = true;

        for(int i = 0; i <= targetSum; ++i) {
            // skip the cell with false value, as it cannot be reached
            if(!table[i]) continue;

            for(int number : numbers) {
                int nextStep = i + number;
                // if we're not out of bounds, make the cell "number" steps
                // away from current "true" (=reachable)
                if(nextStep <= targetSum) {
                    table[nextStep] = true;
                }
                // early exit
                if(nextStep == targetSum) return true;
            }
        }

        return table[targetSum];
    }
}
