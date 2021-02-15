package com.ogabek.interview_questions.google;


import java.util.HashSet;

/**
 * Given an array of integers and a value "sum", return true/false to indicate
 * if the array contains 2 integers that add up to the given "sum" value
 *
 * EX: [1,2,3,9] sum=8 => False
 *     [1,2,4,4] sum=8 => True
 */
public class ArrayHasPairWithSum {

    public static void main(String[] args) {
        // SOLUTION 1. O(n^2) Do a nested loop and check if pairs add up to sum
        // SOLUTION 2. O(nlogn) For each item, do a binary search for its complementary number needed to add up to sum
        // SOLUTION 3. O(n) ASSUMING THE ARRAY IS SORTED: Add the leftmost (smallest) and rightmost (largest) numbers
        // If the result if greater than sum, move the right pointer, else, move the left pointer. Repeat while leftIndex < rightIndex
        // SOLUTION 4. O(n) ASSUMING THE ARRAY IS NOT SORTED: Use a set to store the complementary numbers for each of the numbers
        // encountered in the array. At each iteration, check if the set contains the current number. Return true if it contains, otherwise, false

        int[] data = {1, 2, 4, 4};
        boolean result = hasPairWithSum(data, 8);
        System.out.println(result);
    }

    /**
     * @param array of unsorted integers
     * @param sum the sum that any pair in the array is potentially equal to
     * @return "true" for when the array contains a pair of integers summing up to the sum input, "false" otherwise
     *
     * RUNNING TIME: O(n)
     */
    public static boolean hasPairWithSum(int[] array, int sum) {

        HashSet<Integer> complimentaries = new HashSet<>();

        for (int j : array) {
            if (complimentaries.contains(j)) {
                return true;
            } else {
                complimentaries.add(sum - j);
            }
        }

        return false;
    }



}
