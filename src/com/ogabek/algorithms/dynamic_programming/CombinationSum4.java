package com.ogabek.algorithms.dynamic_programming;

import java.util.Arrays;

public class CombinationSum4 {
    public static void main(String[] args) {
        System.out.println(combinationSum4(new int[] {1,2,3}, 4));
        System.out.println(combinationSum4Memoization(new int[] {1,2,3}, 4));
    }

    /**
     * time: O(nlogn + n * T) where "n" is nums.length and "T" is the target
     * space: O(T)
     */
    public static int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for(int currentSum = 1; currentSum <= target; currentSum++) {
            for(int num : nums) {
                int newTarget = currentSum - num;
                if(newTarget >= 0) {
                    dp[currentSum] += dp[newTarget];
                } else {
                    break;
                }
            }
        }

        return dp[target];
    }

    /**
     * time: O(nlogn + m * n) where "m" is nums.length and "n" is target
     * space: O(n)
     */
    public static int combinationSum4Memoization(int[] nums, int target) {
        Arrays.sort(nums);
        return cs(nums, target, new Integer[target + 1]);
    }

    private static int cs(int[] nums, int target, Integer[] memo) {
        if(target == 0) return 1;
        if(memo[target] != null) return memo[target];

        int count = 0;
        for(int num : nums) {
            if(target - num >= 0) count += cs(nums, target - num, memo);
            else break;
        }

        return memo[target] = count;
    }
}
