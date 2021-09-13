package com.ogabek.algorithms.dynamic_programming;

public class HouseRobber2 {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        else if (n == 2) return Math.max(nums[0], nums[1]);
        return Math.max(robDP(nums, 0, nums.length - 2), robDP(nums, 1, nums.length - 1));
    }

    // time: O(n)   space: O(1)
    // intuition: starting with the second house, at each cell the robber has either robs the current  + twoPrevious
    // OR just the previous house. The cell shows the current max robbed so far.
    private int robDP(int[] nums, int start, int end) {
        int twoPrevious = 0;
        int previous = nums[start];
        int max = 0;

        for(int i = start + 1; i <= end; i++) {
            max = Math.max(nums[i] + twoPrevious, previous);
            twoPrevious = previous;
            previous = max;
        }

        return max;
    }


     //time: O(n)   space: n^2
    public int robMemoization(int[] nums) {
        int n = nums.length;
        return robMemoized(nums, 0, n-1, new Integer[n][n]);
    }

    private int robMemoized(int[] nums, int start, int end, Integer[][] memo) {
        if(start > end) return 0;
        if(memo[start][end] != null) return memo[start][end];

        int case1;
        if(start == 0) case1 = nums[start] + robMemoized(nums, start + 2, end - 1, memo);
        else case1 = nums[start] + robMemoized(nums, start + 2, end, memo);

        int max = Math.max(case1, robMemoized(nums, start + 1, end, memo));

        return memo[start][end] = max;
    }
}
