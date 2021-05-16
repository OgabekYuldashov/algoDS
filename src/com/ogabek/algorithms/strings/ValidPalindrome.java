package com.ogabek.algorithms.strings;

public class ValidPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindromeBruteForce("A man, a plan, a canal: Panama"));
        System.out.println(isPalindromeOptimal("A man, a plan, a canal: Panama"));
    }

    /**
     * time: O(n)
     * space: O(1)
     */
    public static boolean isPalindromeOptimal(String s) {
        int left = 0, right = s.length() - 1;
        while(left < right) {
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while(left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    /**
     * time: O(n)
     * space: O(n)
     */
    public static boolean isPalindromeBruteForce(String s) {
        if(s.length() <= 1) return true;
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isLetterOrDigit(ch)) {
                builder.append(Character.toLowerCase(ch));
            }
        }

        String reversed = builder.toString();
        int left = 0, right = reversed.length() - 1;
        while (left <= right) {
            if(reversed.charAt(left++) != reversed.charAt(right--)) return false;
        }

        return true;
    }
}
