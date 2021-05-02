package com.ogabek.algorithms.sorting;

public class QuickSort implements SortingAlgorithm {

    @Override
    public int[] sort(int[] input) {
        return inPlaceQuickSort(input, 0, input.length - 1);
    }

    private int[] inPlaceQuickSort(int[] input, int leftIndex, int rightIndex) {
        if(rightIndex < leftIndex) return input;

        int pivotIndex = pickPivot(input, leftIndex, rightIndex); // pick a pivot

        swap(input, pivotIndex, rightIndex); // move the pivot to the rightmost index

        int pivotValue = input[rightIndex]; // get the value of pivot which is now at the end

        int newPivotIndex = inPlacePartition(input, pivotValue, leftIndex, rightIndex);

        inPlaceQuickSort(input, leftIndex, newPivotIndex - 1);
        inPlaceQuickSort(input, newPivotIndex + 1, rightIndex);

        return input;
    }


    /**
     * partitions the input array into "LessThan", "EqualTo", and "GreaterThan"
     * sections in comparison to pivot value
     * @return the new index of the pivot after partitioning
     */
    private int inPlacePartition(int[] input, int pivotValue, int leftIndex, int rightIndex) {
        int writePointer = leftIndex;

        for(int i = leftIndex; i < rightIndex; i++) {
            if(input[i] < pivotValue) {
                swap(input, i, writePointer);
                writePointer++;
            }
        }
        swap(input, rightIndex, writePointer);
        return writePointer;
    }

    private void swap(int[] input, int firstIndex, int secondIndex) {
        int temp = input[firstIndex];
        input[firstIndex] = input[secondIndex];
        input[secondIndex] = temp;
    }

    private int pickPivot(int[] input, int leftIndex, int rightIndex) {
        int leftMost = input[leftIndex];
        int rightMost = input[rightIndex];
        int midIndex = (leftIndex + rightIndex) / 2;
        int middle = input[midIndex];

        int medianIndex;
        if (leftMost > rightMost && leftMost > middle) { // leftmost is the greatest
            if(rightMost > middle) { // second greatest should be the median
                medianIndex = rightIndex;
            } else {
                medianIndex = midIndex;
            }
        } else if (rightMost > middle && rightMost > leftMost){ // rightmost is the greatest
            if (leftMost > middle) { // second greatest should be the median
                medianIndex = leftIndex;
            } else {
                medianIndex = midIndex;
            }
        } else { // middle is the greatest
            if (leftMost > rightMost) {
                medianIndex = leftIndex;
            } else {
                medianIndex = rightIndex;
            }
        }
        return medianIndex;
    }
}
