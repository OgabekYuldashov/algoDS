package com.ogabek.algorithms.dynamic_programming;

public class HouseRobber {

    public static void main(String[] args) {

    }

    /**
     * time: O(n)
     * space: O(1)
     */
    public int robTabulationOptimalSpace(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];

        int oneStepAhead = nums[n-1];
        int twoStepsAhead = 0;
        int max = 0;

        for(int curr = n-2; curr >= 0; curr--) {
            max = Math.max(nums[curr] + twoStepsAhead, oneStepAhead);

            twoStepsAhead = oneStepAhead;
            oneStepAhead = max;
        }

        return max;
    }

    /**
     * time: O(n)
     * space: O(n)
     */
    public int robTabulationLessOptimalSpace(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[n-1] = nums[n-1];

        for(int curr = n-2; curr >= 0; curr--) {
            dp[curr] = Math.max(nums[curr] + dp[curr + 2], dp[curr + 1]);
        }

        return dp[0];
    }


    public static int rob(int[] nums) {
        return robMemoized(nums, 0, new Integer[nums.length]);
    }

    /**
     * time: O(n^2)
     * space: O(n)
     */
    private static int robMemoized(int[] nums, int start, Integer[] memo) {
        if (start >= nums.length) return 0;
        if(memo[start] != null) return memo[start];

        int max = Math.max(nums[start] + robMemoized(nums, start + 2, memo),
                robMemoized(nums, start + 1, memo));

        return memo[start] = max;
    }
}
