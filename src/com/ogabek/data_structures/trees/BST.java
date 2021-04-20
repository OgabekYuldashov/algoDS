package com.ogabek.data_structures.trees;

public class BST {
    public static void main(String[] args) {
        //     9
        //  4     20
        //1  6  15 170

        BST bst = new BST();
        bst.insert(9);
        bst.insert(4);
        bst.insert(6);
        bst.insert(20);
        bst.insert(170);
        bst.insert(15);
        bst.insert(1);

        System.out.println("Remove 9: " + bst.remove(9));
        System.out.println("Root: " + bst.getRoot());
        System.out.println("Remove 170: " + bst.remove(170));
        System.out.println("Remove 4: " + bst.remove(4));
        System.out.println("Remove 400: " + bst.remove(400));

        System.out.println("lookup 1: " + bst.lookup(1));
        System.out.println("lookup 10: " + bst.lookup(10));

        System.out.println("These numbers have been deleted, lookup should return null");
        System.out.println("lookup 9: " + bst.lookup(9));
        System.out.println("lookup 170: " + bst.lookup(170));
    }

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        boolean shouldContinue = true;
        while (shouldContinue) {
            if (value >= current.getValue()) {
                if (current.getRight() == null) {
                    current.setRight(newNode);
                    shouldContinue = false;
                } else {
                    current = current.getRight();
                }
            } else {
                if (current.getLeft() == null) {
                    current.setLeft(newNode);
                    shouldContinue = false;
                } else {
                    current = current.getLeft();
                }
            }
        }

    }

    public Node lookup(int value) {
        Node current = root;
        while (current != null) {
            if (value == current.getValue()) {
                return current;
            } else if (value > current.getValue()){
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }

        return null;
    }

    public Node remove(int value) {
        Node parent = null;
        Node toRemove = null;

        // locate the element to be removed with the parent
        Node current = root;
        while (current != null) {
            if (current.getValue() == value) {
                toRemove = current;
                break;
            } else {
                parent = current;
                if (value < current.getValue()) {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }
            }
        }

        if (toRemove != null) { // found the element to remove
            if (toRemove.getLeft() == null && toRemove.getRight() == null) { // CASE 1. toRemove is a leaf node
                // find it and set it to null
                linkParentWithGrandchild(parent, toRemove, null);
            } else if (toRemove.getLeft() == null || toRemove.getRight() == null){ // CASE 2. toRemove has 1 child
                // 1. find the child
                Node childDependent;
                if (toRemove.getLeft() != null){
                    childDependent = toRemove.getLeft();
                } else {
                    childDependent = toRemove.getRight();
                }
                // 2. link with the parent
                linkParentWithGrandchild(parent, toRemove, childDependent);
            } else { // CASE 3. toRemove has 2 children
                // 3.1. Right child doesn't have a left child
                if (toRemove.getRight().getLeft() == null) {
                    toRemove.getRight().setLeft(toRemove.getLeft());
                    linkParentWithGrandchild(parent, toRemove, toRemove.getRight());
                }
                // 3.2 Right child has a left child
                else {
                    // find the smallest node (successor) the in right subtree.
                    // this successor can only have one child at most
                    Node leftmostParent = toRemove;
                    Node leftmost = toRemove.getRight();
                    while (leftmost.getLeft() != null) {
                        leftmostParent = leftmost;
                        leftmost = leftmost.getLeft();
                    }

                    leftmostParent.setLeft(leftmost.getRight());
                    leftmost.setLeft(toRemove.getLeft());
                    leftmost.setRight(toRemove.getRight());

                    linkParentWithGrandchild(parent, toRemove, leftmost);
                }

            }
        }

        return toRemove;
    }

    private void linkParentWithGrandchild(Node parent, Node toBeRemoved, Node toBeReplacedWith) {
        if (parent == null) { // removing the root
            this.root = toBeReplacedWith;
        } else if (parent.getLeft() != null && parent.getLeft().getValue() == toBeRemoved.getValue()) {
            parent.setLeft(toBeReplacedWith);
        } else {
            parent.setRight(toBeReplacedWith);
        }
    }


    private class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}