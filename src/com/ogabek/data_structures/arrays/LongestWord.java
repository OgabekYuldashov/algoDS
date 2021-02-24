package com.ogabek.data_structures.arrays;

import java.util.Scanner;

/**
 * Have the function LongestWord(sen) take the sen parameter being passed
 * and return the largest word in the string. If there are two or more words
 * that are the same length, return the first word from the string with that length.
 * Ignore punctuation and assume sen will not be empty.
 *
 * Input: "fun&!! time"
 * Output: time
 */
public class LongestWord {

    public static void main (String[] args) {
        /*Scanner s = new Scanner(System.in);
        System.out.print(longestWord(s.nextLine()));*/

        System.out.println(longestWord(" Crocodiles are dangerous!!"));
    }

    /**
     * @param sen a non-empty sentence
     * @return longest word in the sentence
     *
     * TIME COMPLEXITY: O(n)
     * SPATE COMPLEXITY: O(1)
     */
    public static String longestWord(String sen) {
        int start = 0;
        int max = 0;
        int localSum = 0;

        for (int i = 0; i < sen.length(); i++) {
            if (isLetter(sen.charAt(i))) { // char is part of a word
                localSum++;

                if (i == sen.length()-1 && localSum > max) { // sentence ends with a word greater than max word
                    start = sen.length() - localSum;
                    max = localSum;
                }
            } else if (i != 0 && isLetter(sen.charAt(i-1))) { // word ended
                if (localSum > max) {
                    max = localSum;
                    start = i - localSum;
                }
                localSum = 0;
            }
        }

        return String.valueOf(sen.toCharArray(), start, max);
    }

    public static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

}
