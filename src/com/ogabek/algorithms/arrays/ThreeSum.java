package com.ogabek.algorithms.arrays;

import java.util.*;

public class ThreeSum {

    /**
     * time: O(nlogn + n^2) => O(n^2)
     * space: O(1) => output list is not considered
     */
    public List<List<Integer>> threeSumWithTwoPointers(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++) {
            int curr = nums[i];
            if(curr > 0) break; // if current is greater than 0, values on the right cannot add up to 0
            if(i > 0 && nums[i-1] == curr) continue; // skip duplicate combinations

            int left = i + 1, right = nums.length - 1;
            while(left < right) {
                int total = curr + nums[left] + nums[right];
                if(total < 0) {
                    left++;
                } else if(total > 0) {
                    right--;
                } else { // found a triplet
                    res.add(Arrays.asList(curr, nums[left], nums[right]));
                    int leftVal = nums[left];
                    while(left + 1 < nums.length && nums[left + 1] == leftVal) left++;

                    left++;
                    right--;
                }
            }
        }

        return res;
    }


    /**
     * time: O(nlogn + n^2 + n/3) => O(n^2)
     * space: O(n + n) => O(n)
     */
    public List<List<Integer>> threeSumWithSet(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++) {
            int curr = nums[i];
            int target = -curr;
            if(curr > 0) break; // if current is greater than 0, values on the right cannot add up to 0
            if(i > 0 && nums[i-1] == curr) continue; // skip duplicate combinations

            Map<Integer, Integer> comps = new HashMap<>();
            for(int j = i + 1; j < nums.length; j++) {
                int val = nums[j];
                Integer compCount = comps.get(val);

                if(compCount != null && compCount > 0) {
                    List<Integer> subset = Arrays.asList(curr, target - val, val);
                    set.add(subset);
                    comps.put(val, --compCount);
                } else {
                    if(compCount == null) comps.put(target - val, 1);
                    else comps.put(val, ++compCount);
                }
            }
        }

        return new ArrayList<>(set);
    }
}
