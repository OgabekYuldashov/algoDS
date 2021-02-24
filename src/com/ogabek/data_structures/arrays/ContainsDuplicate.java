package com.ogabek.data_structures.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, find if the array contains any duplicates.
 * Your function should return true if any value appears at least twice in the array,
 * and it should return false if every element is distinct.
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[] {1,2,3,4,1}));

        System.out.println(containsDuplicateSortingSolution(new int[] {1,2,3,4,1}));
    }




    /**
     * @param nums integer array
     * @return "true" for contains, "false" for doesn't contain duplicates
     *
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(n)
     */
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for(int n: nums) {
            if (set.contains(n)) return true;
            set.add(n);
        }

        return false;
    }

    /**
     * @param nums integer array
     * @return "true" for contains, "false" for doesn't contain duplicates
     *
     * TIME COMPLEXITY: O(nlogn) => O(nlogn) (heap sort) + O(n) array sweeping
     * SPACE COMPLEXITY: O(1)
     */
    public static boolean containsDuplicateSortingSolution(int[] nums) {
        Arrays.sort(nums);

        for(int i = 0; i < nums.length-1; i++) {
            if (nums[i] == nums[i+1]) return true;
        }

        return false;
    }
}
