package com.greatlearning.stack;

class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

public class BSTPairSumFinder {
    private static Node root;

    public static void main(String[] args) {
        // Create the BST as given in the example
        BSTPairSumFinder tree = new BSTPairSumFinder();
        tree.insert(40);
        tree.insert(20);
        tree.insert(60);
        tree.insert(10);
        tree.insert(30);
        tree.insert(50);
        tree.insert(70);

        int sum = 130;
        if (tree.isPairPresent(sum)) {
            System.out.println("Pair is (" + tree.firstNode.data + ", " + tree.secondNode.data + ")");
        } else {
            System.out.println("Nodes are not found.");
        }
    }

    // Helper function to insert a node into the BST
    private void insert(int data) {
        root = insertRecursive(root, data);
    }

    private Node insertRecursive(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        if (data < root.data) {
            root.left = insertRecursive(root.left, data);
        } else if (data > root.data) {
            root.right = insertRecursive(root.right, data);
        }

        return root;
    }

    // Helper function to check if a pair with given sum exists in the BST
    private Node firstNode, secondNode;

    private boolean isPairPresent(int targetSum) {
        firstNode = null;
        secondNode = null;

        // Initialize two pointers: one for the smallest value and another for the largest value
        Node left = root;
        Node right = root;
        // Initialize two stacks for in-order and reverse in-order traversals
        java.util.Stack<Node> leftStack = new java.util.Stack<>();
        java.util.Stack<Node> rightStack = new java.util.Stack<>();

        // The following loops perform in-order and reverse in-order traversals
        while (true) {
            // Find the next smallest node
            while (left != null) {
                leftStack.push(left);
                left = left.left;
            }

            // Find the next largest node
            while (right != null) {
                rightStack.push(right);
                right = right.right;
            }

            // If either traversal is complete, break the loop
            if (leftStack.isEmpty() || rightStack.isEmpty()) {
                break;
            }

            // Get the next nodes from each traversal
            Node leftNode = leftStack.peek();
            Node rightNode = rightStack.peek();

            // Check if we found the pair with the target sum
            if (leftNode.data + rightNode.data == targetSum) {
                firstNode = leftNode;
                secondNode = rightNode;
                return true;
            } else if (leftNode.data + rightNode.data < targetSum) {
                // If the sum is smaller, move the left pointer forward (in-order traversal)
                left = leftNode.right;
                leftStack.pop();
            } else {
                // If the sum is larger, move the right pointer backward (reverse in-order traversal)
                right = rightNode.left;
                rightStack.pop();
            }
        }

        // If no pair is found with the given sum
        return false;
    }
}
