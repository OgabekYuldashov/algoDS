package com.ogabek.algorithms.strings;

public class AlmostPalindrome {
    public static void main(String[] args) {
        System.out.println(validPalindrome("abca"));
        System.out.println(validPalindromeWithInputValidation("abca"));
    }

    /**
     * SOLUTION 1: DOES NOT CHECK THE VALIDITY OF THE INPUT STRING
     *
     * time: O(n)
     * space: O(1)
     */
    public static boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if(s.charAt(left) != s.charAt(right)) {
                return isValid(s, left + 1, right) || isValid(s, left, right - 1);
            }
            left++;
            right--;
        }

        return true;
    }

    private static boolean isValid(String s, int left, int right) {
        while (left < right) {
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }


    /**
     * SOLUTION 2: VALIDATES THE INPUT STRING BY STRIPPING UNNECESSARY CHARACTERS
     *
     * time: O(n)
     * space: O(1)
     */
    public static boolean validPalindromeWithInputValidation(String s) {
        if(s.length() <= 2) return true;
        int[] result = validate(s, 0, s.length() - 1);

        if(result[0] == 0) {
            int[] attempt = validate(s, result[1] + 1, result[2]);
            if(attempt[0] == 1) return true;

            attempt = validate(s, result[1], result[2] - 1);
            if(attempt[0] == 0) return false;
        }

        return true;
    }

    private static int[] validate(String s, int left, int right) {
        if(left > right) return new int[] {0, left, right};
        int[] result = new int[3];

        while(left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left)))
                left++;

            while (left < right && !Character.isLetterOrDigit(s.charAt(right)))
                right--;

            if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                result[1] = left;
                result[2] = right;
                return result; // result[0] -> is not valid
            }
            left++;
            right--;
        }

        result[0] = 1; // true => isValid
        result[1] = left;
        result[2] = right;
        return result;
    }
}
