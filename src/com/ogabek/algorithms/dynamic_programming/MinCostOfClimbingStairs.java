package com.ogabek.algorithms.dynamic_programming;

public class MinCostOfClimbingStairs {
    Integer[] memo;

    /**
     * time: O(n)
     * space: O(n)
     */
    public int minCostClimbingStairsMemoization(int[] cost) {
        int n = cost.length;
        memo = new Integer[n];

        return Math.min(minCost(n - 1, cost), minCost(n - 2, cost));
    }

    public int minCost(int pos, int[] cost) {
        if(pos < 0) return 0;
        if(pos == 0 || pos == 1) return cost[pos];
        if(memo[pos] != null) return memo[pos];

        memo[pos] = cost[pos] + Math.min(minCost(pos - 1, cost), minCost(pos - 2, cost));
        return memo[pos];
    }


    /**
     * time: O(n)
     * space: O(1)
     */
    public int minCostClimbingStairsTabulation(int[] cost) {
        int n = cost.length;

        for(int i = 2; i < n; i++) {
            cost[i] = cost[i] + Math.min(cost[i-1], cost[i-2]);
        }

        return Math.min(cost[n-1], cost[n-2]);
    }
}
