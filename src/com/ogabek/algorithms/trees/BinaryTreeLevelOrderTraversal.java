package com.ogabek.algorithms.trees;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {

    }

    /**
     * time: O(n)
     * space: O(n + n/2) -> O(n)
     */
    public static List<List<Integer>> levelOrderWithCounter(TreeNode root) {
        List<List<Integer>> output = new LinkedList<>();

        if(root == null) return output;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> levelElements = new LinkedList<>();
            int lvlSize = queue.size(); // current size of queue equals the total elements in the current level

            // process all the elements in the current level
            TreeNode node;
            while(lvlSize > 0) {
                node = queue.poll();
                levelElements.add(node.val);
                lvlSize--;

                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }

            output.add(levelElements);
        }

        return output;
    }

    /**
     * time: O(n)
     * space: O(n + n/2) -> O(n)
     */
    public static List<List<Integer>> levelOrderWithDeque(TreeNode root) {
        List<List<Integer>> output = new LinkedList<>();
        List<Integer> levelElements = new LinkedList<>();

        if(root == null) return output;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        TreeNode node;
        TreeNode rightmost = root;
        while(queue.size() > 0) {
            node = queue.poll();
            levelElements.add(node.val);

            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);

            // last element of the level
            if(node == rightmost) {
                // 1. add the elements from curr level to output
                output.add(new LinkedList<>(levelElements));
                levelElements = new LinkedList<>(); // reset the list

                // 2. decide the last element of the next level
                if(node.right != null) {
                    rightmost = node.right;
                } else if(node.left != null) {
                    rightmost = node.left;
                } else {
                    rightmost = queue.peekLast();
                }
            }
        }

        return output;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
