package com.ogabek.algorithms.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 40. Combination Sum II
 *
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output: [ [1,2,2], [5] ]
 */
public class CombinationSum2 {

    public static void main(String[] args) {
        int[] candidates = {2,5,2,1,2};
        List<List<Integer>> result = combinationSum2(candidates, 5);
        System.out.println(result);
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(candidates, 0, new LinkedList<>(), target, result);
        return result;
    }

    private static void combinationSum(int[] candidates, int startIndex, LinkedList<Integer> combination, int target, List<List<Integer>> result) {
        if(target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for(int i = startIndex; i < candidates.length; ++i) {
            // avoid duplicate work that will still give the same combination
            if(i == startIndex || candidates[i] != candidates[i-1]) {
                int newTarget = target - candidates[i];
                // early break the loop
                if(newTarget < 0) break;

                combination.addLast(candidates[i]);
                // decrease the available number candidates by 1 as, if the current candidate is part of a combination,
                // then there should be one less candidate available as a candidate can only be used once
                combinationSum(candidates, i + 1, combination, newTarget, result);
                combination.removeLast();
            }
        }
    }


}
