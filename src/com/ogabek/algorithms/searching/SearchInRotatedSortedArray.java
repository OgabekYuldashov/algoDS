package com.ogabek.algorithms.searching;

public class SearchInRotatedSortedArray {

    /**
     * time: O(logn)
     * space: O(1)
     */
    public int search1Pass(int[] nums, int target) {
        if(nums.length == 1) return nums[0] == target ? 0 : -1;

        int left = 0, right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            int val = nums[mid];

            if(val == target) return mid;

            if(nums[left] <= val) { // a. left subarray is not rotated
                if(nums[left] <= target && target < val) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // b. infliction point is on the left subarray, so right side is not rotated
                if(nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * time: O(2logn) => O(logn)
     * space: O(1)
     */
    public int search2Pass(int[] nums, int target) {
        if(nums.length == 1) return nums[0] == target ? 0 : -1;

        int left = 0, right = nums.length - 1;

        if(nums[left] < nums[right]) return binSearch(left, right, target, nums);

        int inflictionIndex = -1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            int val = nums[mid];

            if(val > nums[mid + 1]) {
                inflictionIndex = mid + 1;
                break;
            }

            if(val < nums[mid - 1]) {
                inflictionIndex = mid;
                break;
            }

            if(val > nums[left]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        int res = -1;
        if(inflictionIndex >= 0) {
            if(target >= nums[inflictionIndex] && target <= nums[nums.length - 1]) {
                res = binSearch(inflictionIndex, nums.length - 1, target, nums);
            } else {
                res = binSearch(0, inflictionIndex - 1, target, nums);
            }
        }

        return res;
    }

    private int binSearch(int left, int right, int target, int[] nums) {
        while(left <= right) {
            int mid = left + (right - left) / 2;
            int val = nums[mid];

            if(val == target) {
                return mid;
            } else if(val < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
