package com.ogabek.data_structures.arrays;

import java.util.Arrays;
import java.util.HashMap;

/**
 * LeetCode: 1. Two Sum
 *
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 */
public class TwoSum {
    public static void main(String[] args){
        int[] nums = new int[]{2,7,11,15,2};
        Arrays.stream(twoSum(nums, 4)).forEach(System.out::println);
    }

    /**
     * @param nums integer array with exactly one pair of integers summing up to the target
     * @param target target sum of two integers in the array
     * @return indices of two integers
     *
     * RUNNING TIME: O(n)
     */
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i = 0; i<nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            } else {
                map.put(target - nums[i], i);
            }
        }

        throw new IllegalArgumentException("No solution with given arguments");
    }
}
