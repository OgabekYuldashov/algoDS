package com.ogabek.algorithms.trees;

public class CountCompleteTreeNodes {
    int maxHeight;
    public int countNodes(TreeNode root) {
        if(root == null) return 0;

        // 1. get the max height
        maxHeight = getMaxHeight(root); // time: O(h) OR O(log n);  space: O(1)
        if(maxHeight == 0) return 1;

        // 2. calculate total nodes except in the last level
        int totalUpperNodes = (int) Math.pow(2, maxHeight) - 1; // upper total nodes = 2^(h) - 1

        // 3. calculate the nodes in the last level
        // 3.1 get max index of the last level, 0 - based
        // nodes in the last level = 2^(h+1) / 2
        int maxIndex = (int) Math.pow(2, maxHeight + 1) / 2 - 1; // subtract 1 for zero based

        // 3.2 find the leftmost non-null value in the last level
        int leftmostNodeIndex = binarySearch(0, maxIndex, root);

        return totalUpperNodes + leftmostNodeIndex + 1;
    }

    /**
     * time: O(h) OR O(log n)
     * space: O(1)
     */
    private int getMaxHeight(TreeNode node) {
        int count = 0;
        while(node.left != null) {
            node = node.left;
            count++;
        }
        return count;
    }

    /**
     * time: O(h * h) OR O(log^2 n)
     * space: O(h^2)
     */
    // finds the index of leftmost node which is non-null
    private int binarySearch(int left, int right, TreeNode node) {
        if(left > right) return right;

        int midIndex = (int) Math.ceil((left + right)/2f);

        // traverse from root to the node at desired index
        TreeNode nodeAtIndex = traverseToIndex(node, midIndex, 0);
        if(nodeAtIndex == null) {
            return binarySearch(left, midIndex - 1, node);
        } else {
            return binarySearch(midIndex + 1, right, node);
        }
    }

    private TreeNode traverseToIndex(TreeNode node, int targetIndex, int currentHeight) {
        // we are at the last level
        if(currentHeight == maxHeight) return node;

        int leftSubtreeNodesAtLastLevel = (int) Math.pow(2, maxHeight - 1 - currentHeight);

        // target index is in the last level of left subtree
        if(targetIndex < leftSubtreeNodesAtLastLevel) { // the index is 0 based, no need to subtract 1
            return traverseToIndex(node.left, targetIndex, currentHeight + 1);
        } else {        // target index is in the last level of right subtree
            return traverseToIndex(node.right, targetIndex - leftSubtreeNodesAtLastLevel, currentHeight + 1);
        }
    }
    private boolean nodeExists(TreeNode node, int targetIndex) {
        int currHeight = 0, left = 0;
        int right = (int) Math.pow(2, maxHeight) - 1; // max index of the nodes in the last level

        while (currHeight < maxHeight) {
            int midIndex = (int) Math.ceil((left + right) / 2f);

            if(targetIndex < midIndex ) { // targetIndex lies on the left subtree
                node = node.left;
                right = midIndex - 1;
            } else { // targetIndex lies on the right subtree
                node = node.right;
                left = midIndex;
            }

            currHeight++;
        }

        return node != null;
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
