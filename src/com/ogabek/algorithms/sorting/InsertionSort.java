package com.ogabek.algorithms.sorting;

public class InsertionSort implements SortingAlgorithm {
    @Override
    public int[] sort(int[] input) {
        if(input == null || input.length < 2) return input;

        for(int i = 1; i < input.length; ++i) {
            int currElem = input[i];
            int j = i;

            // while currElem is less than previous element, move them to the right to make room for the "currElem"
            while (j > 0 && currElem < input[j-1]) {
                input[j] = input[j-1];
                j--;
            }
            // inset currElem at the correct position
            input[j] = currElem;
        }

        return input;
    }
}
