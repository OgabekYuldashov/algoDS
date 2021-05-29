package com.ogabek.algorithms.trees;

import javax.swing.tree.TreeNode;

/**
 * 104. Maximum Depth of Binary Tree
 * <p>
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 */
public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(17)));
        System.out.println(maxDepth(node)); // 3
    }

    /**
     * time: O(n)
     * space:
     *      Balanced Tree: O(log n)
     *      Heavily Unbalanced Tree: O(n)
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
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
