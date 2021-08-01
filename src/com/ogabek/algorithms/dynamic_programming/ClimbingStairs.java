package com.ogabek.algorithms.dynamic_programming;

public class ClimbingStairs {

    public int climbStairs(int n) {
        Integer[] memo = new Integer[n + 1];
        return climbStairsMemoized(n, memo);
    }

    /**
     * time: O(n)
     * space: O(n)
     */
    private int climbStairsMemoized(int n, Integer[] memo) {
        if(n == 0) return 1;
        if(n < 0) return 0;
        if(memo[n] != null) return memo[n];

        int steps = climbStairsMemoized(n - 1, memo) + climbStairsMemoized(n - 2, memo);
        memo[n] = steps;
        return steps;
    }


    /**
     * time: O(n)
     * space: O(1)
     */
    public int climbStairsTabulation(int n) {
        if(n == 1) return 1;

        int first = 1;
        int second = 2;

        for(int i = 3; i <= n; i++) {
            int temp = first + second;
            first = second;
            second = temp;
        }
        return second;
    }
}
