package com.ogabek.algorithms.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private final Map<Character, Node> nodes;

    /** Initialize your data structure here. */
    public Trie() {
        this.nodes = new HashMap<>();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if(word == null || word.length() == 0) return;

        char c = word.charAt(0);
        Node currNode = nodes.get(c);
        if(currNode == null) {
            currNode = new Node(c);
            nodes.put(c, currNode);
        }

        for(int i = 1; i < word.length(); i++) {
            Node nextNode = currNode.getChild(word.charAt(i));
            if(nextNode == null) {
                nextNode = new Node(word.charAt(i));
                currNode.addChild(nextNode);
            }
            currNode = nextNode;
        }

        currNode.setIsEnd(true);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if(word == null || word.length() == 0) return true;

        Map<Character, Node> current = this.nodes;
        Node node = null;
        for(int i = 0; i < word.length(); i++) {
            node = current.get(word.charAt(i));
            if(node == null) return false;
            current = node.getNodes();
        }

        return node.isEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if(prefix == null || prefix.length() == 0) return true;

        Map<Character, Node> current = this.nodes;
        Node node;
        for(int i = 0; i < prefix.length(); i++) {
            node = current.get(prefix.charAt(i));
            if(node == null) return false;
            current = node.getNodes();
        }

        return true;
    }

    static class Node {
        private char val;
        private boolean isEnd;
        private final Map<Character, Node> nodes;

        Node(char val) {
            this.val = val;
            this.isEnd = false;
            this.nodes = new HashMap<>();
        }

        public char getVal() {
            return this.val;
        }

        public Map<Character, Node> getNodes() {
            return this.nodes;
        }

        public Node getChild(char c) {
            return nodes.get(c);
        }

        public void addChild(Node node) {
            this.nodes.put(node.getVal(), node);
        }

        public void setIsEnd(boolean isEnd) {
            this.isEnd = isEnd;
        }
        public boolean isEnd() {
            return this.isEnd;
        }
    }
}
