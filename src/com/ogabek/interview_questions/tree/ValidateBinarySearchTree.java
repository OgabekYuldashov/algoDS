package com.ogabek.interview_questions.tree;

public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        //[5,4,6,null,null,3,7]
        TreeNode leftNode = new TreeNode(4);
        TreeNode rightNode = new TreeNode(6, new TreeNode(3), new TreeNode(7));
        TreeNode root = new TreeNode(5, leftNode, rightNode);

        System.out.println("isValidBSTApproach1: " + isValidBSTApproach1(root));
        System.out.println("isValidBSTInOrderTraversal: " + isValidBSTInOrderTraversal(root));
    }

    /**
     * Approach 1: Solution using "min" and "max" range that each node should belong to
     * @return true if valid BST, false otherwise
     */
    public static boolean isValidBSTApproach1(TreeNode root) {
        return isValidBSTApproach1(root, null, null);
    }

    private static boolean isValidBSTApproach1(TreeNode node, Integer minValue, Integer maxValue) {
        if (node == null) return true;
        if((minValue != null && node.val <= minValue) || (maxValue != null && node.val >= maxValue)) return false;
        return isValidBSTApproach1(node.left, minValue, node.val) && isValidBSTApproach1(node.right, node.val, maxValue);
    }


    private static Integer prev;
    /**
     * Approach 2: Solution using "In-order traversal" of DFS. When BST nodes are traversed in order,
     * each node should be greater than the previous node.
     * @return true if valid BST, false otherwise
     */
    public static boolean isValidBSTInOrderTraversal(TreeNode root) {
        if(root == null) return true;
        if(!(isValidBSTInOrderTraversal(root.left))) return false; // if left subtree is not valid, no need to check the right subtree
        if(prev != null && root.val <= prev) return false; // if current node is less than prev node, it is invalid
        prev = root.val; // save the current value for comparison with the next node
        return isValidBSTInOrderTraversal(root.right); // return the result of the right subtree
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
