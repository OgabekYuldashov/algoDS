package com.ogabek.interview_questions.miscellanous;

/**
 * Reverse a given string
 * EX: "I am Groot" => "toorG ma I"
 */
public class ReverseString {

    public static void main(String[] args) {
        String result = reverseString("I am Groot");
        System.out.println(result);
    }

    /**
     * @param string the string to be reversed
     * @return reversed string
     *
     * RUNNING TIME: O(n)
     */
    public static String reverseString(String string) {

        char[] reversed = new char[string.length()];

        int i = string.length() - 1;
        for(char ch : string.toCharArray()) {
            reversed[i] = ch;
            i--;
        }

        return String.copyValueOf(reversed);
    }

}
