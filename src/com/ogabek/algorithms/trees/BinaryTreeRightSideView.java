package com.ogabek.algorithms.trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
    public static void main(String[] args) {

    }

    static List<Integer> output;

    /**
     * time: O(n)
     * space:
     *      Worst Case: O(n)
     *      Best Case: O(log n)
     */
    public static List<Integer> rightSideViewWithDFS(TreeNode root) {
        output = new LinkedList<>();

        if(root != null) dfs(root, 1);

        return output;
    }

    private static void dfs(TreeNode node, int lvl) {
        if(lvl > output.size()) output.add(node.val);

        if(node.right != null) dfs(node.right, lvl + 1);
        if(node.left != null) dfs(node.left, lvl + 1);
    }


    /**
     * time: O(n)
     * space:
     *      Worst Case: O(n)
     *      Best Case: O(log n)
     */
    public static List<Integer> rightSideViewWithBFS(TreeNode root) {
        List<Integer> output = new LinkedList<>();

        if(root == null) return output;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int lvlSize = queue.size(); // total # of elements in the level

            TreeNode node;
            while(lvlSize > 0) {
                node = queue.poll();
                lvlSize--;

                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);

                if(lvlSize == 0) {
                    output.add(node.val);
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
