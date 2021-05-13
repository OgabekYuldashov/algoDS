package com.ogabek.algorithms.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 39. Combination Sum
 *
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen
 * numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen
 * numbers is different.
 *
 * It is guaranteed that the number of unique combinations that sum up to
 * target is less than 150 combinations for the given input.
 *
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        List<List<Integer>> result = combinationSum(candidates, 7);
        System.out.println(result);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // sorting allows to perform the early breaking of the loop
        Arrays.sort(candidates);    // Time: O(nlogn)
        combinationSum(candidates, 0, new LinkedList<>(), target, result);
        return result;
    }

    private static void combinationSum(int[] candidates, int startIndex, LinkedList<Integer> combinations, int target, List<List<Integer>> result) {
        // we found a combination, add a deep copy of it to the result list
        if(target == 0) {
            result.add(new ArrayList<>(combinations));
            return;
        }

        // to avoid permutations, decrease candidates with each loop
        for(int i = startIndex; i < candidates.length; i++) {
            int newTarget = target - candidates[i];
            // if the target is less than 0, no need to check the combination as the next candidate is even bigger
            if(target < 0) break;

            // give the recursive function a combination with the current candidate in it
            combinations.addLast(candidates[i]);
            combinationSum(candidates, i, combinations, newTarget, result);

            // remove the last candidate before the next iteration
            combinations.removeLast();
        }
    }


}
