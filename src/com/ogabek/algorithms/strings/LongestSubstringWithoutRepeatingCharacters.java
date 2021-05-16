package com.ogabek.algorithms.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {

        long arrayMemoStartTime = System.nanoTime();
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // "cbda" => 4
        long arrayMemoEndTime = System.nanoTime();
        System.out.println("Two-Pointer with a HashMap Solution Execution Time: " + (arrayMemoEndTime - arrayMemoStartTime));


        long tabulationStartTime = System.nanoTime();
        System.out.println(lengthOfLongestSubstringBruteForce("abcabcbb")); // "cbda" => 4
        long tabulationEndTime = System.nanoTime();
        System.out.println("Brute Force with a Set Solution Execution Time: " + (tabulationEndTime - tabulationStartTime));

        long optimalSolutionStartTime = System.nanoTime();
        System.out.println(lengthOfLongestSubstringOptimal("abcabcbb")); // "cbda" => 4
        long optimalSolutionEndTime = System.nanoTime();
        System.out.println("Optimal Sliding Window Solution Execution Time: " + (optimalSolutionEndTime - optimalSolutionStartTime));
    }

    /**
     * time: O(n)
     * space: O(n)
     */
    public static int lengthOfLongestSubstringOptimal(String s) {
        if(s.length() < 2) return s.length();
        int left = 0;
        int longest = 0;
        int currentLen = 0;
        Integer[] table = new Integer[128];

        for(int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if(table[c] == null || table[c] < left) {
                currentLen++;
            } else {
                left = table[c] + 1;
                currentLen = right - left + 1;
            }

            table[c] = right;

            if(currentLen > longest) longest = currentLen;
        }

        return longest;
    }

    public static int lengthOfLongestSubstring(String s) {
        if(s.length() < 2) return s.length();
        int left = 0, // 2
                right = 1,
                longest = 0; // 3
        Map<Character, Integer> map = new HashMap<>(); // {c,b,d, }
        map.put(s.charAt(0), 0);

        while (right <= s.length()-1) { // 5 <= 5
            char c = s.charAt(right); // d
            if(map.containsKey(c) || right == s.length()-1) { // found the right bound
                int len = right - left; // 5-2 = 3

                if(right != s.length()-1) { // if we have not reached the end of the string
                    left = map.get(c) + 1; // move left to the right of the first occurrence of this char
                    map.clear();
                    for(int i = left; i < right; i++) {
                        map.put(s.charAt(i), i);
                    }
                } else {
                    len++; // account for the 1 position off from the end
                }

                if(len > longest) longest = len;
            }
            map.put(c, right);
            right++;
        }
        return longest;
    }


    /**
     * time: O(n^2)
     * space: O(n)
     */
    public static int lengthOfLongestSubstringBruteForce(String s) {
        if(s.length() < 2) return s.length();
        Set<Character> set = new HashSet<>(); // {c, b, d,  }
        int longest = 0; // 3

        // calculate the longest starting at each character of the string
        for(int i = 0; i < s.length() - 1; i++) { // 2
            set.clear(); // clear set for each iteration
            set.add(s.charAt(i));

            // explore characters starting at the right of current character
            for(int j = i + 1; j <= s.length() - 1; j++) { // 5
                char c = s.charAt(j); // a

                if(set.contains(c)) {
                    int len = j - i;
                    if(len > longest) longest = len;
                    break;
                } else if(j == s.length() - 1) {
                    int len = s.length() - i;
                    if(len > longest) longest = len;
                    return longest;
                }

                set.add(c);
            }
        }

        return longest;
    }
}
