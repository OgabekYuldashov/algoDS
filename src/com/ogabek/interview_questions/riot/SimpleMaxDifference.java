package com.ogabek.interview_questions.riot;

public class SimpleMaxDifference {
    public static void main(String[] args) {
        System.out.println(simpleMaxDifference(new int[] {7,1,2,5}));
//        System.out.println(simpleMaxDifference(new int[] {2, 3, 10, 6, 4, 8, 1}));
//        System.out.println(simpleMaxDifference(new int[] {2, 3, 10, 6, 4, 8, 1}));
//        System.out.println(simpleMaxDifference(new int[] {1, 2, 90, 10, 110}));
//        System.out.println(simpleMaxDifference(new int[] {80, 2, 6, 3, 100}));
        System.out.println(simpleMaxDifference(new int[] {-3, -2}));
    }

    /**
     * time: O(n)
     * space: O(1)
     */
    public static int simpleMaxDifference(int[] input) { // [-3, -2]
        int res = -1;
        if(input == null || input.length <= 1) return res;

        int min = input[0];
        for (int i = 1; i < input.length; i++) {
            int current = input[i];
            min = Math.min(min, current);

            if (current > min) res = Math.max(res, current - min);
        }

        return res;
    }
}
