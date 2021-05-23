package com.ogabek.algorithms.stacks;

import java.util.Stack;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 *
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
 * so that the resulting parentheses string is valid and return any valid string.
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 */
public class MinimumRemoveToMakeValidParentheses {

    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValidWithoutStack("a)b(c)d")); // ab(c)d
        System.out.println(minRemoveToMakeValidWithoutStack("((())((")); // (())
        System.out.println(minRemoveToMakeValidWithoutStack("))((")); // ""

        System.out.println(minRemoveToMakeValidWithStack("a)b(c)d")); // ab(c)d
        System.out.println(minRemoveToMakeValidWithStack("((())((")); // (())
        System.out.println(minRemoveToMakeValidWithStack("))((")); // ""
    }

    /**
     * FASTEST SOLUTION
     *
     * time: O(3n) => O(n)
     * space: O(3n) => O(n)
     */
    public static String minRemoveToMakeValidWithoutStack(String s) {
        char[] arr = s.toCharArray(); // space: O(n)
        int open = 0;
        int removedClosingBrackets = 0;

        for(int i = 0; i < arr.length; i++) { // time: O(n)
            char c = arr[i];
            if(c == '(') { // CASE 1: c is an opening bracket
                open++;
            } else if(c == ')'){ // CASE 2: c is a closing bracket
                if(open > 0) { // we have a matching opening bracket
                    open--;
                } else { // no opening bracket to match, remove closing bracket
                    arr[i] = '0'; // replace extra closing bracket with '0'
                    removedClosingBrackets++;
                }
            }
        }

        // final output with reduced size
        char[] output = new char[arr.length - open - removedClosingBrackets]; // space: O(n)
        int writePointer = output.length - 1;
        for(int i = arr.length - 1; i >= 0; i--) { // time: O(n)
            char c = arr[i];

            if(c == '0') continue; // skip removed closing bracket

            if(open > 0 && c == '(') { // skip extra opening bracket
                open--;
                continue;
            }

            output[writePointer--] = c;
        }

        return new String(output); // space: O(n); time: O(n)
    }


    /**
     * time: O(5n) => O(n)
     * space: O(3n) => O(n)
     */
    public static String minRemoveToMakeValidWithStack(String s) {
        Stack<Integer> stack = new Stack<>(); // space: O(n)
        char[] arr = s.toCharArray(); // space: O(n)
        int removedClosingBrackets = 0;

        // STEP 1: Remove the extra CLOSING BRACKETS (by replacing with '0')
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == '(') { // CASE 1: c is an opening bracket
                stack.push(i);
            } else if(arr[i] == ')') { // CASE 2:  c is a closing bracket
                if(stack.isEmpty()) {
                    arr[i] = '0'; // replace with an arbitrary value to be skipped in the final string
                    removedClosingBrackets++;
                } else {
                    stack.pop();
                }
            }
        }

        char[] res = new char[arr.length - stack.size() - removedClosingBrackets]; // space: O(n)
        int writePointer = res.length - 1;
        int openingBracketIndex = -1;
        if(!stack.isEmpty()) openingBracketIndex = stack.pop();


        // STEP 2: Write the output by skipping the extra OPENING and CLOSING BRACKETS
        for(int i = arr.length - 1; i >= 0; i--) {
            if(arr[i] == '0') continue; // skip removed closing bracket

            if(i == openingBracketIndex) { // detected an opening bracket index to be removed
                openingBracketIndex = -1;
                if(!stack.isEmpty()) openingBracketIndex = stack.pop();
                continue;
            }

            res[writePointer--] = arr[i];
        }

        return new String(res);
    }

}
