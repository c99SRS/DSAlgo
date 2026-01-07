package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

public class MaxPathSumSolution {
    /*
    Maximum Sum Path in Binary Tree



0

Problem Statement:
Given a Binary Tree, determine the maximum sum achievable along any path within the tree. A path in a binary tree is defined as
a sequence of nodes where each pair of adjacent nodes is connected by an edge. Nodes can only appear once in the sequence,
and the path is not required to start from the root. Identify and compute the maximum sum possible along any path
within the given binary tree.
     */
    private int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) {
        // Creating test tree
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        MaxPathSumSolution sol = new MaxPathSumSolution();
        System.out.println("Maximum Path Sum: " + sol.maxPathSum(root)
        );
    }

    public int maxPathSum(TreeNode root) {
        calculateMaxGain(root);
        return maxSum;
    }

    private int calculateMaxGain(TreeNode node) {
        if (node == null) return 0;

        // 1. Recursively get max path sum from children
        // Use Math.max(..., 0) to ignore paths that would decrease our sum
        int leftGain = Math.max(calculateMaxGain(node.left), 0);
        int rightGain = Math.max(calculateMaxGain(node.right), 0);

        // 2. Local potential path: current node + left gain + right gain
        // This is a path where this node is the 'highest' point
        int currentPathSum = node.val + leftGain + rightGain;

        // 3. Update global maximum if this local path is better
        maxSum = Math.max(maxSum, currentPathSum);

        // 4. Return the maximum gain this node can contribute to its parent
        // (Must only pick ONE branch to maintain a valid path for the parent)
        return node.val + Math.max(leftGain, rightGain);
    }
}
