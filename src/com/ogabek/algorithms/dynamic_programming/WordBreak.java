package com.ogabek.algorithms.dynamic_programming;

import java.util.*;

public class WordBreak {
    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();

        System.out.println(wordBreak.wordBreakMemoization("leetcode", Arrays.asList("leet", "code")));
        System.out.println(wordBreak.wordBreakMemoization("catsandog", Arrays.asList("cats","dog","sand","and","cat")));
        System.out.println(wordBreak.wordBreakMemoization("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));


        System.out.println(wordBreak.wordBreakTabulation("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));

    }


    Set<String> dict;
    Boolean[] memo;

    /**
     * time: O(n^3) => two nested loops of "n" + substring on a string of length "n"
     * space: O(n) => dp array length is "n + 1";
     */
    public boolean wordBreakTabulation(String s, List<String> wordDict) {
        dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public boolean wordBreakMemoization(String s, List<String> wordDict) {
        dict = new HashSet<>(wordDict);
        memo = new Boolean[s.length()];

        return wordBreakable(s, 0);
    }

    private boolean wordBreakable(String s, int start) {
        if(start >= s.length()) return true;

        if(memo[start] != null) return memo[start];

        for(int end = start + 1; end <= s.length(); end++) {
            if(dict.contains(s.substring(start, end))
                    && wordBreakable(s, end)) {
                return memo[start] = true;
            }
        }

        return memo[start] = false;
    }

    /*private final Node root = new Node();

    public boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Boolean> map = new HashMap<>();
        for(String w : wordDict) {
            insert(w);
            map.put(w, false);
        }

        Node current = null;
        StringBuilder builder = new StringBuilder("");
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            current = root.getChildren().get(ch);

            if(current == null) return false;

            builder.append(ch);

            if(current.getIsWord()) {
                if(map.containsKey(builder.toString())) {
                    map.put(builder.toString(), true);
                    builder = new StringBuilder("");
                }
            }
        }
    }


    public void insert(String word) {
        Node current = this.root;

        for(char ch : word.toCharArray()) {
            current.getChildren().computeIfAbsent(ch, n -> new Node());
        }

        current.setIsWord(true);
    }

    static class Node {
        private final Map<Character, Node> children = new HashMap<>();
        private boolean isWord = false;

        public Map<Character, Node> getChildren() {
            return this.children;
        }

        public boolean getIsWord() {
            return this.isWord;
        }

        public void setIsWord(boolean isWord) {
            this.isWord = isWord;
        }
    }*/
}
