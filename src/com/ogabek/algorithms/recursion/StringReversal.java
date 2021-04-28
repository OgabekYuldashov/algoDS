package com.ogabek.algorithms.recursion;

public class StringReversal {
    public static void main(String[] args) {
        System.out.println("reverseStringRecursively: " + reverseStringRecursively("hello world"));
        System.out.println("reverseStringIteratively: " + reverseStringIteratively("hello world"));
    }

    public static String reverseStringRecursively(String string) {
        return recursiveStringReversalHelper(string, string.length()-1);
    }

    private static String recursiveStringReversalHelper(String str, int lastIndex) {
        if (lastIndex < 0) return "";
        return str.charAt(lastIndex) + recursiveStringReversalHelper(str, lastIndex - 1);
    }

    public static String reverseStringIteratively(String str) {
        if(str == null) throw new IllegalArgumentException("String should not be NULL");

        StringBuilder builder = new StringBuilder();
        for (int i = str.length()-1; i >= 0; i--) {
            builder.append(str.charAt(i));
        }

        return builder.toString();
    }
}
