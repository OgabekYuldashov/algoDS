package com.ogabek.algorithms.sorting;

public class MergeSort implements SortingAlgorithm{
    private int[] inputArray;

    @Override
    public int[] sort(int[] input) {
        inputArray = input;
        int[] tempArray = new int[input.length];
        mergeSort(tempArray, 0, input.length - 1);
        return this.inputArray;
    }

    private void mergeSort(int[] tempArray, int lowerBound, int upperBound) {
        if (lowerBound != upperBound) {
            int mid = (upperBound + lowerBound) / 2;
            mergeSort(tempArray, lowerBound, mid);
            mergeSort(tempArray, mid + 1, upperBound);
            merge(tempArray, lowerBound, mid + 1, upperBound);
        }
    }

    private void merge(int[] tempArray, int lowerPointer, int upperPointer, int upperBound) {
        int j = 0;  // tempArray index
        int middleIndex = upperPointer-1;
        int lowerBound = lowerPointer;
        int totalElements = upperBound - lowerBound + 1;

        while (lowerPointer <= middleIndex && upperPointer <= upperBound) {
            if (inputArray[lowerPointer] <= inputArray[upperPointer]) {
                tempArray[j++] = inputArray[lowerPointer++];
            } else {
                tempArray[j++] = inputArray[upperPointer++];
            }
        }

        while (lowerPointer <= middleIndex) {
            tempArray[j++] =
                    inputArray[lowerPointer++];
        }

        while (upperPointer <= upperBound) {
            tempArray[j++] = inputArray[upperPointer++];
        }

        for(int i = 0; i < totalElements; ++i) {
            inputArray[lowerBound + i] = tempArray[i];
        }
    }


}
